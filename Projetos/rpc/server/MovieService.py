# TODO: Adicionar cabeçalho.

from google.protobuf.json_format import ParseDict
from MoviesRPC_pb2 import Movie

class MovieService:
    def __init__(self, database):
        self.database = database
    
    def create(self, movie):
        print("[Movie Service] Executing method create()")
        new_movie = Movie()
        new_movie.title = movie.title
        new_movie.directors.extend(list(movie.directors))
        new_movie.genres.extend(list(movie.genres))
        new_movie.cast.extend(list(movie.cast))
        new_movie.plot = movie.plot

        movie_created = self.database.insert(new_movie)

        movie_creation_response = Movie()
        movie_creation_response.id = str(movie_created.inserted_id)

        movie_creation_response = self.retrieveById(movie_creation_response.id)
        return movie_creation_response
    
    def retrieve(self, movie):
        print("[Movie Service] Executing method retrieve()")
        movie_retrieved = self.database.find(movie)
        
        if movie_retrieved == None:
            return None

        # Realizando parsing do filme obtido da consulta ao banco para corresponder ao campo id da classe Movie
        movie_retrieved['id'] = str(movie_retrieved.pop('_id'))
        movie_retrieved_response = ParseDict(movie_retrieved, Movie(), ignore_unknown_fields=True)

        return movie_retrieved_response
    
    def retrieveById(self, movie_id):
        print("[Movie Service] Executing method retrieveById()")
        movie_retrieved = self.database.findById(movie_id)
        
        if movie_retrieved == None:
            return None

        # Realizando parsing do filme obtido da consulta ao banco para corresponder ao campo id da classe Movie
        movie_retrieved['id'] = str(movie_retrieved.pop('_id'))
        movie_retrieved_response = ParseDict(movie_retrieved, Movie(), ignore_unknown_fields=True)

        return movie_retrieved_response

    def update(self, movie):
        print("[Movie Service] Executing method update()")
        movie_updated = self.database.update(movie)
        movie_update_response = movie_updated.modified_count
        return movie_update_response
        
    def findByGenres(self, values):
        print("[Movie Service] Executing method findByGenres()")
        movies = self.database.findByGenres(values)

        movies_list = []
        for movie in movies:
            new_movie = Movie()
            new_movie.id = str(movie.get("_id", "N/A"))
            new_movie.title = movie.get("title", "N/A")
            new_movie.genres.extend(list(movie.get("genres", [])))
            new_movie.cast.extend(list(movie.get("cast", [])))
            new_movie.directors.extend(list(movie.get("directors", [])))
            new_movie.plot = movie.get("plot", "N/A")
            
            movies_list.append(new_movie)

        return movies_list
        
    def findByCast(self, values):
        print("[Movie Service] Executing method findByCast()")
        movies = self.database.findByCast(values)

        movies_list = []
        for movie in movies:
            new_movie = Movie()
            new_movie.id = str(movie.get("_id", "N/A"))
            new_movie.title = movie.get("title", "N/A")
            new_movie.genres.extend(list(movie.get("genres", [])))
            new_movie.cast.extend(list(movie.get("cast", [])))
            new_movie.directors.extend(list(movie.get("directors", [])))
            new_movie.plot = movie.get("plot", "N/A")

            movies_list.append(new_movie)

        return movies_list
    
    def delete(self, movie):
        print("[Movie Service] Executing method delete()")
        movie_deleted = self.database.delete(movie)
        movie_delete_response = movie_deleted.deleted_count
        return movie_delete_response
