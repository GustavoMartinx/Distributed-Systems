import grpc
from concurrent import futures
import MoviesRPC_pb2
import MoviesRPC_pb2_grpc

# In-memory database for demonstration purposes
movies_db = {
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
        }

class MovieMethodsServicer(MoviesRPC_pb2_grpc.MovieMethodsServicer):
    # Create
    def CreateMovie(self, request, context):
        movie_id = request.id
        if movie_id in movies_db:
            return MoviesRPC_pb2.Response(status=409, message="Movie already exists.")
        
        movies_db[movie_id] = request
        print(movie_id)
        return MoviesRPC_pb2.Response(status=201, message="Movie created successfully.", movie=request)

    # Retrieve by ID or Name

    def GetMovie(self, request, context):
        # Mock movie data in a dictionary
        mock_movie_dict = {
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
        }

        # Convert the dictionary to a Movie object
        mock_movie = MoviesRPC_pb2.Movie(
            id=mock_movie_dict["id"],
            plot=mock_movie_dict["plot"],
            genres=mock_movie_dict["genres"],
            runtime=mock_movie_dict["runtime"],
            cast=mock_movie_dict["cast"],
            num_mflix_comments=mock_movie_dict["num_mflix_comments"],
            title=mock_movie_dict["title"],
            fullplot=mock_movie_dict["fullplot"],
            languages=mock_movie_dict["languages"],
            directors=mock_movie_dict["directors"],
            rated=mock_movie_dict["rated"],
            lastupdated=mock_movie_dict["lastupdated"],
            year=mock_movie_dict["year"],
            countries=mock_movie_dict["countries"],
            type=mock_movie_dict["type"]
        )

        # Create a response object
        response = MoviesRPC_pb2.Response(
            status=1,
            message="Movie found",
            movie=mock_movie
        )

        return response

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
        movie_id = request.name
        if movie_id not in movies_db:
            return MoviesRPC_pb2.Response(status=404, message="Movie not found.")
        
        del movies_db[movie_id]
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
