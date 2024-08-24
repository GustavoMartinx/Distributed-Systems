import grpc
import pprint
from concurrent import futures
import MoviesRPC_pb2
import MoviesRPC_pb2_grpc

# In-memory database for demonstration purposes
movies_db = {1:{
            "id": "1",
            "plot": "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O.",
            "genres": ["Action", "Sci-Fi", "Thriller"],
            "runtime": 148,
            "cast": ["Leonardo DiCaprio", "Joseph Gordon-Levitt", "Elliot Page"],
            "num_mflix_comments": 1500,
            "title": "Inception",
            "fullplot": "Dom Cobb is a skilled thief, the absolute best in the dangerous art of extraction: stealing valuable secrets from deep within the subconscious during the dream state, when the mind is at its most vulnerable.",
            "languages": ["English", "Japanese", "French"],
            "directors": ["Christopher Nolan"],
            "rated": "PG-13",
            "lastupdated": "2024-08-23 00:00:00",
            "year": 2010,
            "countries": ["USA", "UK"],
            "type": "movie"
        }}

class MovieMethodsServicer(MoviesRPC_pb2_grpc.MovieMethodsServicer):
    # Create
    def CreateMovie(self, request, context):
        print(request)
        movie_id = request.title
        if movie_id in movies_db:
            return MoviesRPC_pb2.Response(status=404, message="Movie already exists.")
        
        movies_db[max(movies_db.keys()) + 1] = {
            'id': str(max(movies_db.keys()) + 1),
            'title': request.title if request.title else "Mock Title",
            'directors': request.directors if request.directors else ["Mock Director 1", "Mock Director 2"],
            'genres': request.genres if request.genres else ["Mock Genre 1", "Mock Genre 2"],
            'cast': request.cast if request.cast else ["Mock Actor 1", "Mock Actor 2"],
            'plot': request.plot if request.plot else "This is a mock plot for the movie.",
            'runtime': 120,  # Mock data
            'num_mflix_comments': 100,  # Mock data
            'fullplot': "This is a mock full plot for the movie.",  # Mock data
            'languages': ["English"],  # Mock data
            'rated': "PG-13",  # Mock data
            'lastupdated': "2024-08-23 00:00:00",  # Mock data
            'year': 2023,  # Mock data
            'countries': ["USA"],  # Mock data
            'type': "movie"  # Mock data
        }
        print(movie_id)
        pprint.pprint(movies_db)
        return MoviesRPC_pb2.Response(status=200, message="Movie created successfully.", movie=request)

    # Retrieve by ID or Name
    def GetMovie(self, request, context):

        print("teste")
        print(request)
        mock_movie = ""
        pprint.pprint(movies_db)
        for movie in movies_db.values():
            pprint.pprint(movie)
            if movie["id"] == request.nameMovie or movie["title"] == request.nameMovie:
                print("true")
                mock_movie = MoviesRPC_pb2.Movie(
                id=movie["id"],
                plot=movie["plot"],
                genres=movie["genres"],
                runtime=movie["runtime"],
                cast=movie["cast"],
                num_mflix_comments=movie["num_mflix_comments"],
                title=movie["title"],
                fullplot=movie["fullplot"],
                languages=movie["languages"],
                directors=movie["directors"],
                rated=movie["rated"],
                lastupdated=movie["lastupdated"],
                year=movie["year"],
                countries=movie["countries"],
                type=movie["type"]
        )

                # Create a response object
                response = MoviesRPC_pb2.Response(
                    status=200,
                    message="Movie found",
                    movie=mock_movie
                )

                return response
        return MoviesRPC_pb2.Response(status=404, message="Movie not found.")

    # Retrieve by Actor
    def GetMoviesByActor(self, request, context):
        filtered_movies = [movie for movie in movies_db.values() if any(actor in movie.cast for actor in request.values)]
        
        return MoviesRPC_pb2.Response(status=200, message=f"Found {len(filtered_movies)} movies.", movies=filtered_movies)

    # Retrieve by Category (Genre)
    def GetMoviesByCategory(self, request, context):
        filtered_movies = [movie for movie in movies_db.values() if any(genre in movie.genres for genre in request.values)]
        
        return MoviesRPC_pb2.Response(status=200, message=f"Found {len(filtered_movies)} movies.", movies=filtered_movies)

    # Update
    def UpdateMovie(self, request, context):
        movie_id = request.id
        if movie_id not in movies_db:
            return MoviesRPC_pb2.Response(status=404, message="Movie not found.")
        
        movies_db[movie_id] = request
        return MoviesRPC_pb2.Response(status=200, message="Movie updated successfully.", movie=request)

    # Delete
    def DeleteMovie(self, request, context):
        movie_id = request.title
        if movie_id not in movies_db:
            return MoviesRPC_pb2.Response(status=404, message="Movie not found.")
        
        del movies_db[movie_id]
        pprint.pprint(movies_db)
        return MoviesRPC_pb2.Response(status=200, message="Movie deleted successfully.")

def serve():
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))
    MoviesRPC_pb2_grpc.add_MovieMethodsServicer_to_server(MovieMethodsServicer(), server)
    server.add_insecure_port('[::]:8080')
    server.start()
    print("Server started, listening on port 8080.")
    server.wait_for_termination()

if __name__ == '__main__':
    serve()
