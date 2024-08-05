class MovieService:
    def __init__(self, database):
        self.database = database
    
    def create(self):
        print("This is a create movie service")

    def update(self):
        print("This is a update movie service")
        
    def findByCategories(self):
        print("This is a find by actor movie service")
        
    def findByAtor(self):
        print("This is a movie find by ator service")

    def delete(self):
        print("THis is a movie delete service")