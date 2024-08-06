from Database import Database
from Movies_pb2 import Response, Movie
from bson.json_util import dumps
import logging

class MovieService:
    def __init__(self, database):
        self.database = database
    
    def create(self, movie):
        print("[Movie Service] Executing method create()")
        self.database.insert(movie)
        return Response(status=200, message="Successfuly on create movie")

    # def update(self):
    #     print("[Movie Service] Executing method update()")
        
    def findByCategories(self, values):
        print("[Movie Service] Executing method findByCategories()")
        self.database.findByGenres(values)
        return Response(status=200, message="Successfuly on find movie by categories")
        
    def findByAtor(self, values):
        print("[Movie Service] Executing method findByAtor()")
        self.database.findByCast(values)
        return Response(status=200, message="Successfuly on find movie by ator")

    # def delete(self, movieId):
    #     print("[Movie Service] Executing method delete()")
    #     return self.database.delete(movieId)