from google.protobuf.json_format import MessageToDict
from pymongo.mongo_client import MongoClient
from pymongo.server_api import ServerApi
from bson.objectid import ObjectId
from bson.json_util import dumps

class Database:
    def __init__(self):
        # URI de conexão com o MongoDB Atlas
        uri = "mongodb+srv://gustavomartins:qx6dTXRB8ArWSrz2@cluster0.60zsz.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0"
         # Cria um cliente MongoDB com a URI fornecida e a versão da API do servidor
        self.client = MongoClient(uri, server_api=ServerApi('1'))
         # Obtém o banco de dados "sample_mflix"
        self.database = self.client.get_database("sample_mflix")
         # Obtém a coleção "movies" do banco de dados
        self.collections = self.database.get_collection("movies")
    
    # Formata os dados BSON em uma string JSON com indentação de 4 espaços
    def format(self, data):
        return dumps(data, indent=4)

    # Insere um novo filme na coleção    
    def insert(self, movie):
        print(f"[Database] Inserting movie '{movie.title}'")
        # Converte o objeto de filme para um dicionário
        movie_dict = MessageToDict(movie)
        # Insere o dicionário na coleção e retorna o resultado da inserção
        return self.collections.insert_one(movie_dict)
    
    # Encontra um filme na coleção pelo ID
    def find(self, movie):
        print(f"[Database] Retrieving movie '{movie.id}'")
        # Busca um documento na coleção cujo _id corresponde ao ID do filme
        retrieved_movie = self.collections.find_one({"_id": ObjectId(movie.id)})
        return retrieved_movie

    # Atualiza um filme na coleção
    def update(self, movie):
        print(f"[Database] Updating movie '{movie.title}'")
        print(movie)
        # Atualiza o documento na coleção cujo _id corresponde ao ID do filme
        return self.collections.update_one({
            "_id":  ObjectId(movie.id),
        }, {"$set": {
            "title": movie.title,
            "directors": list(movie.directors),
            "genres": list(movie.genres),
            "cast": list(movie.cast),
            "plot": movie.plot,
        }})
    
    # Encontra filmes na coleção que pertencem a qualquer um dos gêneros fornecidos
    def findByGenres(self, genres_list):
        print("[Database] Filtering categories with genres list: ", genres_list)
        # Busca documentos na coleção cujo campo "genres" contém qualquer um dos gêneros na lista
        return self.collections.find({"genres": {"$in": list(genres_list)}})

    # Encontra filmes na coleção que possuem qualquer um dos membros do elenco fornecidos
    def findByCast(self, cast_list):
        print("[Database] Filtering autores with cast list: ", cast_list)
        # Busca documentos na coleção cujo campo "cast" contém qualquer um dos membros do elenco na lista
        return self.collections.find({"cast": {"$in": list(cast_list)}})
        
    # Deleta um filme da coleção pelo ID
    def delete(self, movie): 
        print("[Database] Delete movie with id: ", movie.id)
        # Remove o documento na coleção cujo _id corresponde ao ID do filme
        return self.collections.delete_one({"_id": ObjectId(movie.id)})
