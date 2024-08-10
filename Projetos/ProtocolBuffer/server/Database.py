from google.protobuf.json_format import MessageToDict
from pymongo.mongo_client import MongoClient
from pymongo.server_api import ServerApi
from pymongo.mongo_client import MongoClient
from bson.json_util import dumps
from bson.objectid import ObjectId

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

    def update(self, movie):
        print(f"[Database] Updating movie '{movie.title}'")
        print(movie)
        return self.collections.update_one({
            "_id":  ObjectId(movie.id),
        }, {"$set": {
            "title": movie.title,
            "directors": list(movie.directors),
            "genres": list(movie.genres),
            "cast": list(movie.cast),
            "plot": movie.plot,
        }})

    def findByGenres(self, values):
        print("[Database] Filtering categories with values: ", values)
        return self.collections.find({"genres": {"$in": list(values)}})

    def findByCast(self, values):
        print("[Database] Filtering autores with values: ", values)
        return self.collections.find({"cast": {"$in": list(values)}})

    def delete(self, movie): 
        print("[Database] Delete movie with id: ", movie.id)
        return self.collections.delete_one({"_id": ObjectId(movie.id)})
