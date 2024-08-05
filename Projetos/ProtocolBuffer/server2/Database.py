from pymongo.mongo_client import MongoClient
from pymongo.server_api import ServerApi
from pymongo.mongo_client import MongoClient
from pymongo.server_api import ServerApi
from bson.json_util import dumps

class Database:
    def __init__(self):
        uri = ""
        self.client = MongoClient(uri, server_api=ServerApi('1'))
        self.database = self.client.get_database("sample_mflix")
        self.collections = self.database.get_collection("movies")
    
    def __format(data):
        return dumps(data, indent=4)

    def findByGenres(self, categoriesList):
        categories = self.collections.find({"genres": {"$in": categoriesList}}, {"genres": 1})
        return format(categories, indent=4)

    def findByCast(self, castList):
        cast = self.collections.find({"cast": {"$in": castList}}, {"cast": 1})
        return format(cast, indent=4)

    def findByTitle(self, title):
        movies = self.collections.find({"title": title})
        return format(movies)
        
    def insert(self, movie):
        self.collections.insert_one({"title": movie.title})
    
    def delete(self, id): 
        self.collections.delete_one({"_id": id})
