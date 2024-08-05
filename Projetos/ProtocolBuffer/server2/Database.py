from pymongo.mongo_client import MongoClient
from pymongo.server_api import ServerApi
from pymongo.mongo_client import MongoClient
from pymongo.server_api import ServerApi
from bson.json_util import dumps

class Database:
    def __init__(self):
        uri = "mongodb+srv://diogorodriguees:diogorodrigueesacess@sd-external-data-repres.9oh60so.mongodb.net/?retryWrites=true&w=majority&appName=sd-external-data-representation"
        self.client = MongoClient(uri, server_api=ServerApi('1'))
        self.database = self.client.get_database("sample_mflix")
        self.collections = self.database.get_collection("movies")

    def findByGenres(self, categoriesList):
        categories = self.collections.find({"genres": {"$in": categoriesList}}, {"genres": 1})
        print(dumps(categories, indent=4))

    def findByCast(self, castList):
        cast = self.collections.find({"cast": {"$in": castList}}, {"cast": 1})
        print(dumps(cast, indent=4))
        return dumps(cast, indent=4)

    def findByTitle(self, title):
        movies = self.collections.find({"title": title})
        print(dumps(movies))
        return dumps(movies)

    def insert(self, data):
        self.collections.insert_one(data)
    
    def delete(self, id): 
        self.collections.delete_one({"_id": id})
