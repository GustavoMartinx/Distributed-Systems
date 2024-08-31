# TODO: Adicionar cabeçalho e comentários em português nos métodos.

import pprint
import MoviesRPC_pb2
import MoviesRPC_pb2_grpc
from Database import Database
from MovieService import MovieService
from MovieController import MovieController

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
    
    def __init__(self):
        self.database = Database()
        self.movieService = MovieService(self.database)
        self.movieController = MovieController(self.movieService)
        self.server_socket = None

    # Implementação da RPC de criação de um filme
    def CreateMovie(self, request, context):
        response = self.movieController.create(request)
        return response

    # Implementação da RPC que obtém um filme através do seu título
    def GetMovie(self, request, context):
        movie_retrieved = self.movieController.retrieve(request)
        pprint.pprint(movie_retrieved)
        return movie_retrieved
    
    # Implementação da RPC que obtém todos os filmes nos quais os membros do elenco fornecidos participam
    def GetMoviesByActor(self, request, context):
        retrieved_movies_list = self.movieController.findByCast(request)
        return retrieved_movies_list

    # Retrieve by Category (Genre)
    def GetMoviesByCategory(self, request, context):
        filtered_movies = [movie for movie in movies_db.values() if any(genre in movie.genres for genre in request.values)]
        
        return MoviesRPC_pb2.Response(status=200, message=f"Found {len(filtered_movies)} movies.", movies=filtered_movies)

    # Implementação da RPC que atualiza as informações de um filme através do seu título
    def UpdateMovie(self, request, context):
        response = self.movieController.update(request)
        return response

    # Implementação da RPC que deleta um filme através do seu título
    def DeleteMovie(self, request, context):
        movie = request.nameMovie
        response = self.movieController.delete(movie)
        return response
