# Autores: Christofer Daniel, Diogo Rodrigues e Gustavo Martins
# Data: 21/08/2024

# Este código implementa a camada de serviço para o sistema de consulta de filmes

from google.protobuf.json_format import ParseDict
from MoviesRPC_pb2 import Movie

class MovieService:
    # Inicializa o serviço de filmes com uma instância do banco de dados
    def __init__(self, database):
        self.database = database
    
    # Método para criar um novo filme
    def create(self, movie):
        print("[Movie Service] Executing method create()")
        new_movie = Movie()
        new_movie.title = movie.title
        new_movie.directors.extend(list(movie.directors))
        new_movie.genres.extend(list(movie.genres))
        new_movie.cast.extend(list(movie.cast))
        new_movie.plot = movie.plot

        # Insere o novo filme no banco de dados
        movie_created = self.database.insert(new_movie)

        movie_creation_response = Movie()
        movie_creation_response.id = str(movie_created.inserted_id)

        # Recupera o filme criado para retornar como resposta
        movie_creation_response = self.retrieveById(movie_creation_response.id)
        return movie_creation_response
    
    # Método para recuperar um filme
    def retrieve(self, movie):
        print("[Movie Service] Executing method retrieve()")
        # Busca o filme no banco de dados
        movie_retrieved = self.database.find(movie)
        
        # Se o filme não for encontrado, retorna None
        if movie_retrieved == None:
            return None

        # Realizando parsing do filme obtido da consulta ao banco para corresponder ao campo id da classe Movie
        movie_retrieved['id'] = str(movie_retrieved.pop('_id'))
        # Converte o dicionário do filme para uma instância de Movie
        movie_retrieved_response = ParseDict(movie_retrieved, Movie(), ignore_unknown_fields=True)

        return movie_retrieved_response
    
    # Método para recuperar um filme pelo ID
    def retrieveById(self, movie_id):
        print("[Movie Service] Executing method retrieveById()")
        movie_retrieved = self.database.findById(movie_id)
        
        # Se o filme não for encontrado, retorna None
        if movie_retrieved == None:
            return None

        # Realizando parsing do filme obtido da consulta ao banco para corresponder ao campo id da classe Movie
        movie_retrieved['id'] = str(movie_retrieved.pop('_id'))
        # Converte o dicionário do filme para uma instância de Movie
        movie_retrieved_response = ParseDict(movie_retrieved, Movie(), ignore_unknown_fields=True)

        return movie_retrieved_response

    # Método para atualizar um filme
    def update(self, movie):
        print("[Movie Service] Executing method update()")
        # Atualiza o filme no banco de dados
        movie_updated = self.database.update(movie)
        # Retorna a contagem de documentos modificados
        movie_update_response = movie_updated.modified_count
        return movie_update_response
        
    # Método para encontrar filmes por gêneros
    def findByGenres(self, values):
        print("[Movie Service] Executing method findByGenres()")
        # Busca filmes no banco de dados pelos gêneros fornecidos
        movies = self.database.findByGenres(values)

        movies_list = []
        for movie in movies:
             # Cria uma nova instância de Movie e define seus atributos
            new_movie = Movie()
            new_movie.id = str(movie.get("_id", "N/A"))
            new_movie.title = movie.get("title", "N/A")
            new_movie.genres.extend(list(movie.get("genres", [])))
            new_movie.cast.extend(list(movie.get("cast", [])))
            new_movie.directors.extend(list(movie.get("directors", [])))
            new_movie.plot = movie.get("plot", "N/A")
            # Adiciona o novo filme à lista de filmes
            movies_list.append(new_movie)

        return movies_list
        
    # Método para encontrar filmes por membros do elenco
    def findByCast(self, values):
        print("[Movie Service] Executing method findByCast()")
        # Busca filmes no banco de dados pelos membros do elenco fornecidos
        movies = self.database.findByCast(values)

        movies_list = []
        for movie in movies:
            # Cria uma nova instância de Movie e define seus atributos
            new_movie = Movie()
            new_movie.id = str(movie.get("_id", "N/A"))
            new_movie.title = movie.get("title", "N/A")
            new_movie.genres.extend(list(movie.get("genres", [])))
            new_movie.cast.extend(list(movie.get("cast", [])))
            new_movie.directors.extend(list(movie.get("directors", [])))
            new_movie.plot = movie.get("plot", "N/A")
            # Adiciona o novo filme à lista de filmes
            movies_list.append(new_movie)

        return movies_list
    
    # Método para deletar um filme
    def delete(self, movie):
        print("[Movie Service] Executing method delete()")
        # Deleta o filme no banco de dados pelo ID
        movie_deleted = self.database.delete(movie)
         # Retorna a contagem de documentos deletados
        movie_delete_response = movie_deleted.deleted_count
        return movie_delete_response
