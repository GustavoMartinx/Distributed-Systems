class MovieService:
    def __init__(self, database):
        self.database = database
    
    def create(self, movie):
        print("[Movie Service] Executing method create()")
        data = {"title": movie.title}
        self.database.crete(data)

    def update(self):
        print("[Movie Service] Executing method update()")
        
    def findByCategories(self, values):
        print("[Movie Service] Executing method findByCategories()")
        self.database.findByGenres(values)
        
    def findByAtor(self, values):
        print("[Movie Service] Executing method findByAtor()")
        self.database.findByCast(values)

    def delete(self, movieId):
        print("[Movie Service] Executing method delete()")
        self.database.delete(movieId)