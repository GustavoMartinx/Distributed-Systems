# TODO: Adicionar cabeçalho.

from google.protobuf.json_format import MessageToDict
from pymongo.mongo_client import MongoClient
from pymongo.server_api import ServerApi
from bson.objectid import ObjectId
from bson.json_util import dumps

class Database:
    def __init__(self):
        uri = "mongodb+srv://gustavomartins:qx6dTXRB8ArWSrz2@cluster0.60zsz.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0"
        self.client = MongoClient(uri, server_api=ServerApi('1'))
        self.database = self.client.get_database("sample_mflix")
        self.collections = self.database.get_collection("movies")
    
    def format(self, data):
        return dumps(data, indent=4)
        
    def insert(self, movie):
        print(f"[Database] Inserting movie '{movie.title}'")
        movie_dict = MessageToDict(movie)
        return self.collections.insert_one(movie_dict)
    
    def find(self, movie_title):
        print(f"[Database] Retrieving movie '{movie_title}'")
        retrieved_movie = self.collections.find_one({"title": movie_title})
        return retrieved_movie

    def findById(self, movie_id):
        print(f"[Database] Retrieving movie by ID '{movie_id}'")
        retrieved_movie = self.collections.find_one({"_id": ObjectId(movie_id)})
        return retrieved_movie

    def update(self, movie):
        print(f"[Database] Updating movie '{movie.title}'")
        print(movie)
        return self.collections.update_one({
            "title":  movie.title,
        }, {
            "$set": {
                "title": movie.title,
                "directors": list(movie.directors),
                "genres": list(movie.genres),
                "cast": list(movie.cast),
                "plot": movie.plot,
            }
        })

    def findByGenres(self, genres_list):
        print("[Database] Filtering categories with genres list: ", genres_list)
        return self.collections.find({"genres": {"$in": list(genres_list)}})

    def findByCast(self, cast_list):
        print("[Database] Filtering movies with cast list: ", cast_list)
        return self.collections.find({"cast": {"$in": list(cast_list)}})

    def delete(self, movie):
        print("[Database] Delete movie with name: ", movie)
        return self.collections.delete_one({"title": movie})
