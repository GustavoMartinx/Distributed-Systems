from Movies_pb2 import Response

class MovieController:
    def __init__(self, movieService):
        self.movieService = movieService

    def create(self, request):
        try:
            print("[Movie Controller] Executing method create()")
            return self.movieService.create(request.movie)
        except:
            print("[Error] Failed on create movie")
            return Response(status=400, message="Failed on create movie")
        
    def findByCategories(self, request):
        try:
            print("[Movie Controller] Executing method findByCategories()")
            return self.movieService.findByCategories(request.filters.values)
        except:
            print("[Error] Failed on find movie by categories")
            return Response(status=400, message="Failed on find movie by categories")
        
    def findByAtor(self, request):
        try:
            print("[Movie Controller] Executing method findByAtor()")
            return self.movieService.findByAtor(request.filters.values)
        except:
            print("[Error] Failed on find movie by actors")
            return Response(status=400, message="Failed on find movie by actor")

    # def delete(self, request):
        # print("[Movie Controller] Executing method delete()")
        # self.movieService.delete(request.movie.id)
