from google.protobuf.json_format import ParseDict
from Movies_pb2 import Movie

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

        movie_creation_response = self.retrieve(movie_creation_response)
        return movie_creation_response
    
    def retrieve(self, movie):
        print("[Movie Service] Executing method retrieve()")
        movie_retrieved = self.database.find(movie)

        if movie_retrieved == None:
            return None

        movie_retrieved['id'] = str(movie_retrieved.pop('_id'))
        movie_retrieved_response = ParseDict(movie_retrieved, Movie())

        return movie_retrieved_response

    def update(self, movie):
        print("[Movie Service] Executing method update()")
        movie_updated = self.database.update(movie)
        movie_update_response = movie_updated.modified_count
        return movie_update_response
        
    def findByCategories(self, values):
        print("[Movie Service] Executing method findByCategories()")
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
            # new_movie.runtime = movie["runtime"]
            # new_movie.num_mflix_comments = movie["num_mflix_comments"]
            # new_movie.fullplot = movie["fullplot"]
            # new_movie.languages.extend(list(movie["languages"]))
            # new_movie.rated = movie["rated"]
            # new_movie.lastupdated = movie["lastupdated"]
            # new_movie.year = movie["year"]
            # new_movie.countries.extend(list(movie["countries"]))
            # new_movie.type = movie["type"]
            movies_list.append(new_movie)

        return movies_list
        
    def findByAtor(self, values):
        print("[Movie Service] Executing method findByAtor()")
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
            # new_movie.runtime = movie.get("runtime")
            # new_movie.num_mflix_comments = movie.get("num_mflix_comments")
            # new_movie.fullplot = movie.get("fullplot")
            # new_movie.languages.extend(list(movie.get("languages", [])))
            # new_movie.rated = movie.get("rated")
            # new_movie.lastupdated = movie.get("lastupdated")
            # new_movie.year = movie.get("year")
            # new_movie.countries.extend(list(movie.get("countries", [])))
            # new_movie.type = movie.get("type")

            movies_list.append(new_movie)

        return movies_list
    
    def delete(self, movie):
        print("[Movie Service] Executing method delete()")
        movie_deleted = self.database.delete(movie)
        movie_delete_response = movie_deleted.deleted_count
        return movie_delete_response
