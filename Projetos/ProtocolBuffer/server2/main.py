from Database import Database
import json
class Main:
    def __init__(self):
        self.database = Database()
        self.castList = []
        self.genresList = []
        self.title = "title test"

    def testFindByCast(self):
        return self.database.findByCast(self.castList)

    def testFindByGenres(self):
        return self.database.findByGenres(self.genresList)

    def testFindByTitle(self):
        movies = self.database.findByTitle(self.title)
        return movies

    def insertTest(self):
        data = {"title": "title test", "genres": "Drama"}
        self.database.insert(data)

    def delete(self, movies):
        movie = json.loads(movies)
        self.database.delete(movie[0]["_id"]["$oid"])

main = Main()
main.testFindByTitle()
main.insertTest()
movies = main.testeList()
main.delete(movies)