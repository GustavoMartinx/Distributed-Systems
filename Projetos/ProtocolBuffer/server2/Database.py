from pymongo.mongo_client import MongoClient
from pymongo.server_api import ServerApi
from pymongo.mongo_client import MongoClient
from pymongo.server_api import ServerApi
from bson.json_util import dumps
from Movies_pb2 import Movie
import pymongo

class Database:
    def __init__(self):
        uri = ""
        self.client = MongoClient(uri, connectTimeoutMS=60000, socketTimeoutMS=60000, server_api=ServerApi('1'))
        self.database = self.client.get_database("sample_mflix")
        self.collections = self.database.get_collection("movies")
    
    def format(self, data):
        return dumps(data, indent=4)

    def findByGenres(self, categoriesList):
        print("[Database] Filtering categories with values: ", categoriesList)
        return self.collections.find({"genres": {"$in": categoriesList}}, {"genres": 1})

    def findByCast(self, values):
        print("[Database] Filtering autores with values: ", values)
        return self.collections.find({"cast": {"$in": values}}, {"cast": 1})
        
    def insert(self, movie):
        print("[Database] Inserting movie: \n", dumps(movie, indent=4))
        return self.collections.insert_one({"title": movie.title})
    
    # def delete(self, movieId): 
        # self.collections.delete_one({"_id":movieId})
