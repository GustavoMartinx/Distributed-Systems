# Autores: Christofer Daniel, Diogo Rodrigues e Gustavo Martins
# Data: 21/08/2024

# Este código é a classe principal que implementa o server do sistema de consulta de filmes
# Esse código usa protobuffs para implementar a conversão de mensagens entre o client e o server
 



import socket
import struct
import sys

from Movies_pb2 import Request
from Database import Database
from MovieService import MovieService
from MovieController import MovieController

class Server:
    def __init__(self):
        # Inicializa o banco de dados, serviço de filmes e controlador de filmes
        self.database = Database()
        self.movieService = MovieService(self.database)
        self.movieController = MovieController(self.movieService)
        self.server_socket = None

    # Middleware para processar a requisição com base no método solicitado
    def middleware(self, request):
        print("Using middleware, method: ", request.method)
        if request.method == "CREATE":
            return self.movieController.create(request)
        if request.method == "READ":
            return self.movieController.retrieve(request)
        if request.method == "FIND_BY_ATOR":
            return self.movieController.findByAtor(request)
        if request.method == "FIND_BY_CATEGORIA":
            return self.movieController.findByCategories(request)
        if request.method == "DELETE":
            return self.movieController.delete(request)
        if request.method == "UPDATE":
            return self.movieController.update(request)
        if request.method == "EMPTY":
            return self.movieController.invalidMethod()
        
    def handle_client(self, client_socket):
        try:
            # Read data size
            size_data = client_socket.recv(4)
            size = struct.unpack('!I', size_data)[0]

            # Read payload
            data = client_socket.recv(size)
            
            # Unmarshalling Request
            request = Request()
            request.ParseFromString(data)

            # Process request
            response = self.middleware(request)
            print("Response: ", response)

            # Marshalling response
            response_data = response.SerializeToString()

            # Send response size followd by response
            client_socket.sendall(f"{len(response_data)}\n".encode('utf-8'))
            client_socket.sendall(response_data)
        except Exception as e:
            print(f"Erro ao processar requisição: {e}")
            raise e

    def run(self):
        # Cria um socket de servidor
        server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        server.bind(('localhost', 8080))  # Substitua 'localhost' pelo endereço IP desejado
        server.listen(1)
        self.server_socket = server

        print("Server online.")
        
        try:
            # Aceita a conexão do cliente
            client_socket, addr = server.accept()
            print(f"Conexão estabelecida com {addr}")
            while True:
                try:
                    # Processa as requisições do cliente
                    self.handle_client(client_socket)
                except Exception as e:
                    raise e
            
        except Exception as e:
            print("Error: ", e)
            
        finally:
            # Fecha o socket do cliente e do servidor
            client_socket.close()
            if self.server_socket:
                self.server_socket.close()

server = Server()
server.run()