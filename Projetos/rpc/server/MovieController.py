from MoviesRPC_pb2 import Response
from MoviesRPC_pb2 import Movie

class MovieController:
    def __init__(self, movieService):
        self.movieService = movieService

    def create(self, request):
        try:
            print("[Movie Controller] Executing method create()")
            new_movie = self.movieService.create(request.movie)
            return Response(status=200, message="Movie created successfully!", movie=new_movie)
        except Exception as e:
            print(f"[Error] Failed to create movie: {e}")
            return Response(status=400, message="Failed on movie creation: " + str(e))
    
    def retrieve(self, request):
        try:
            print("[Movie Controller] Executing method retrieve()")
            movie_retrieved = self.movieService.retrieve(request.nameMovie)
            if movie_retrieved == None:
                return Response(status= 201, message="There are no movies with this ID.")
            return Response(status=200, message="Movie retrieved successfully!", movie=movie_retrieved)
        except Exception as e:
            print(f"[Error] Failed on retrieve movie: {e}")
            return Response(status=400, message="Failed on retrieve movie: " + str(e))

    def update(self, request):
        try:
            print("[Movie Controller] Executing method update()")
            movie_updated = self.movieService.update(request.movie)
            if movie_updated > 0:
                return Response(status=200, message="Movie updated successfully!")
            else:
                raise Exception("Something went wrong while updating movie.")
        except Exception as e:
            print(f"[Error] Failed on update movie: {e}")
            return Response(status=400, message="Failed on update movie: " + str(e))
        
        
    def findByCategories(self, request):
        try:
            print("[Movie Controller] Executing method findByCategories()")
            movies_array = self.movieService.findByCategories(request.filters.values)
            # Verificando se a lista filtrada é vazia
            if not movies_array:
                return Response(status=200, message="Successfully on find movie by genres! But there are no movies with this gender.")
            return Response(status=200, message="Successfully on find movie by genres!", movies=movies_array)
        except Exception as e:
            print(f"[Error] Failed on find movie by categories {e}")
            return Response(status=400, message="Failed on find movie by categories: " + str(e))
        
    def findByAtor(self, request):
        try:
            print("[Movie Controller] Executing method findByAtor()")
            movies_array = self.movieService.findByAtor(request.filters.values)
            # Verificando se a lista filtrada é vazia
            if not movies_array:
                return Response(status=200, message="Successfully on find movie by cast! But there are no movies with this cast.")
            return Response(status=200, message="Successfully on find movie by cast!", movies=movies_array)
        except Exception as e:
            print(f"[Error] Failed on find movie by actors {e}")
            return Response(status=400, message="Failed on find movie by actor: " + str(e))
    
    def delete(self, request):
        try:
            print("[Movie Controller] Executing method delete()")
            movie_deleted = self.movieService.delete(request.movie)
            if movie_deleted > 0:
                return Response(status=200, message="Movie deleted successfully!")
            else:
                raise Exception("Something went wrong while deleting movie.")
        except Exception as e:
            print(f"[Error] Failed on delete movie: {e}")
            return Response(status=400, message="Failed on delete movie" + str(e))
        
    def invalidMethod(self):
        return Response(status=400, message="Invalid operation. Please, insert a valid operation.")
