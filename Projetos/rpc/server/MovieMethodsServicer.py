# TODO: Adicionar cabeçalho e comentários em português nos métodos.

import pprint
import MoviesRPC_pb2
import MoviesRPC_pb2_grpc
from Database import Database
from MovieService import MovieService
from MovieController import MovieController


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

    # Implementação da RPC que obtém todos os filmes dos gêneros especificados
    def GetMoviesByCategory(self, request, context):
        retrieved_movies_list = self.movieController.findByGenres(request)
        return retrieved_movies_list

    # Implementação da RPC que atualiza as informações de um filme através do seu título
    def UpdateMovie(self, request, context):
        response = self.movieController.update(request)
        return response

    # Implementação da RPC que deleta um filme através do seu título
    def DeleteMovie(self, request, context):
        movie = request.nameMovie
        response = self.movieController.delete(movie)
        return response
