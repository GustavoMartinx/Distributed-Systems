from google.protobuf.json_format import ParseDict
from Movies_pb2 import Movie

class MovieService:
    # Inicializa o serviço de filmes com uma instância do banco de dados
    def __init__(self, database):
        self.database = database
    
    # Método para criar um novo filme
    def create(self, movie):
        print("[Movie Service] Executing method create()")
        # Cria uma nova instância de Movie e define seus atributos
        new_movie = Movie()
        new_movie.title = movie.title
        new_movie.directors.extend(list(movie.directors))
        new_movie.genres.extend(list(movie.genres))
        new_movie.cast.extend(list(movie.cast))
        new_movie.plot = movie.plot

        # Insere o novo filme no banco de dados
        movie_created = self.database.insert(new_movie)
        # Cria uma resposta com o ID do filme criado
        movie_creation_response = Movie()
        movie_creation_response.id = str(movie_created.inserted_id)

        # Recupera o filme criado para retornar como resposta
        movie_creation_response = self.retrieve(movie_creation_response)
        return movie_creation_response
    
    # Método para recuperar um filme pelo ID
    def retrieve(self, movie):
        print("[Movie Service] Executing method retrieve()")
        # Busca o filme no banco de dados
        movie_retrieved = self.database.find(movie)

        # Se o filme não for encontrado, retorna None
        if movie_retrieved == None:
            return None

        # Converte o ID do filme para string e remove o campo _id
        movie_retrieved['id'] = str(movie_retrieved.pop('_id'))
        # Converte o dicionário do filme para uma instância de Movie
        movie_retrieved_response = ParseDict(movie_retrieved, Movie())

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
    def findByCategories(self, values):
        print("[Movie Service] Executing method findByCategories()")
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
            # new_movie.runtime = movie["runtime"]
            # new_movie.num_mflix_comments = movie["num_mflix_comments"]
            # new_movie.fullplot = movie["fullplot"]
            # new_movie.languages.extend(list(movie["languages"]))
            # new_movie.rated = movie["rated"]
            # new_movie.lastupdated = movie["lastupdated"]
            # new_movie.year = movie["year"]
            # new_movie.countries.extend(list(movie["countries"]))
            # new_movie.type = movie["type"]
            # Adiciona o novo filme à lista de filmes
            movies_list.append(new_movie)

        return movies_list
        
    # Método para encontrar filmes por membros do elenco
    def findByAtor(self, values):
        print("[Movie Service] Executing method findByAtor()")
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
            # new_movie.runtime = movie.get("runtime")
            # new_movie.num_mflix_comments = movie.get("num_mflix_comments")
            # new_movie.fullplot = movie.get("fullplot")
            # new_movie.languages.extend(list(movie.get("languages", [])))
            # new_movie.rated = movie.get("rated")
            # new_movie.lastupdated = movie.get("lastupdated")
            # new_movie.year = movie.get("year")
            # new_movie.countries.extend(list(movie.get("countries", [])))
            # new_movie.type = movie.get("type")
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
