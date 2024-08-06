class MovieController:
    def __init__(self, movieService):
        self.movieService = movieService

    def create(self, request):
        print("[Movie Controller] Executing method create()")
        self.movieService.create(request.movie)

    def update(self, request):
        print("[Movie Controller] Executing method update()")
        movie = request.movie
        
    def findByCategories(self, request):
        print("[Movie Controller] Executing method findByCategories()")
        self.movieService.findByCategories(request.filters.values)
        
    def findByAtor(self, request):
        print("[Movie Controller] Executing method findByAtor()")
        self.movieService.findByAtor(request.filters.values)

    def delete(self, request):
        print("[Movie Controller] Executing method delete()")
        self.movieService.delete(request.movie.id)
