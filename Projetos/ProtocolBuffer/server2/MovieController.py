class MovieController:
    def __init__(self, movieService):
        self.movieService = movieService

    def create(self, request):
        movie = request.movie
        print("This is a create movie controller")

    def update(self, request):
        movie = request.movie
        print("This is a update movie controller")
        
    def findByCategories(self, request):
        movie = request.movie
        print("This is a find by actor movie controller")
        
    def findByAtor(self, request):
        movie = request.movie
        print("This is a movie find by ator controller")

    def delete(self, request):
        movie = request.movie
        print("THis is a movie delete controller")