# Autores: Christofer Daniel, Diogo Rodrigues e Gustavo Martins
# Data: 21/08/2024

# Este código implementa a camada de controller para o sistema de consulta de filmes

from MoviesRPC_pb2 import Response
from MoviesRPC_pb2 import Movie

class MovieController:
    #Inicializa o controlador com uma instância do serviço de filmes
    def __init__(self, movieService):
        self.movieService = movieService

    # Chama o método create do serviço de filmes para criar um novo filme
    def create(self, request):
        try:
            print("[Movie Controller] Executing method create()")
            new_movie = self.movieService.create(request)
            # Retorna uma resposta de sucesso com o novo filme criado
            return Response(status=200, message="Movie created successfully!", movie=new_movie)
        except Exception as e:
            print(f"[Error] Failed to create movie: {e}")
            # Retorna uma resposta de erro em caso de exceção
            return Response(status=400, message="Failed on movie creation: " + str(e))
    
    # Chama o método retrieve do serviço de filmes para recuperar um filme pelo ID
    def retrieve(self, request):
        try:
            print("[Movie Controller] Executing method retrieve()")
            movie_retrieved = self.movieService.retrieve(request.nameMovie)
            if movie_retrieved == None:
                # Retorna uma resposta indicando que não há filmes com o ID fornecido
                return Response(status= 201, message="There are no movies with this title.")
             # Retorna uma resposta de sucesso com o filme recuperado
            return Response(status=200, message="Movie retrieved successfully!", movie=movie_retrieved)
        except Exception as e:
            print(f"[Error] Failed on retrieve movie: {e}")
            # Retorna uma resposta de erro em caso de exceção
            return Response(status=400, message="Failed on retrieve movie: " + str(e))

    # Chama o método update do serviço de filmes para atualizar um filme
    def update(self, request):
        try:
            print("[Movie Controller] Executing method update()")
            movie_updated = self.movieService.update(request)
            # Verificando a quantidade de filme atualizados
            if movie_updated > 0:
                # Retorna uma resposta de sucesso se o filme foi atualizado
                return Response(status=200, message="Movie updated successfully!")
            else:
                # Lança uma exceção se algo deu errado durante a atualização
                raise Exception("Something went wrong while updating movie.")
        except Exception as e:
            print(f"[Error] Failed on update movie: {e}")
            # Retorna uma resposta de erro em caso de exceção
            return Response(status=400, message="Failed on update movie: " + str(e))
        
    # Chama o método findByCategories do serviço de filmes para encontrar filmes por gêneros
    def findByGenres(self, request):
        try:
            print("[Movie Controller] Executing method findByGenres()")
            movies_array = self.movieService.findByGenres(request.values)
            # Verificando se a lista filtrada é vazia
            if not movies_array:
                return Response(status=200, message="Successfully on find movie by genres! But there are no movies with this gender.")
            # Retorna uma resposta de sucesso com a lista de filmes encontrados
            return Response(status=200, message="Successfully on find movie by genres!", movies=movies_array)
        except Exception as e:
            print(f"[Error] Failed on find movie by genres {e}")
            # Retorna uma resposta de sucesso com a lista de filmes encontrados
            return Response(status=400, message="Failed on find movie by genres: " + str(e))
        
    # Chama o método findByAtor do serviço de filmes para encontrar filmes por membros do elenco
    def findByCast(self, request):
        try:
            print("[Movie Controller] Executing method findByCast()")
            movies_array = self.movieService.findByCast(request.values)
            # Verificando se a lista filtrada é vazia
            if not movies_array:
                return Response(status=200, message="Successfully on find movie by cast! But there are no movies with this cast.")
            return Response(status=200, message="Successfully on find movie by cast!", movies=movies_array)
        except Exception as e:
            print(f"[Error] Failed on find movie by actors {e}")
            return Response(status=400, message="Failed on find movie by actor: " + str(e))
    
    # Chama o método delete do serviço de filmes para deletar um filme pelo ID
    def delete(self, request):
        try:
            print("[Movie Controller] Executing method delete()")
            movie_deleted = self.movieService.delete(request)
            # Verificando a quantidade de filmes excluídos
            if movie_deleted > 0:
                # Retorna uma resposta de sucesso se o filme foi deletado
                return Response(status=200, message="Movie deleted successfully: ")
            else:
                raise Exception("Something went wrong while deleting movie.")
        except Exception as e:
            print(f"[Error] Failed on delete movie: {e}")
            # Retorna uma resposta de erro em caso de exceção
            return Response(status=400, message="Failed on delete movie" + str(e))
