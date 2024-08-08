import socket
import struct

from Movies_pb2 import Movie, Request, Response
from Database import Database
from MovieService import MovieService
from MovieController import MovieController
from bson.json_util import dumps

class Server:
    def __init__(self):
        self.database = Database()
        self.movieService = MovieService(self.database)
        self.movieController = MovieController(self.movieService)
        
    def middleware(self, request):
        print("Using middleware, method: ", request.method)
        if request.method == "CREATE":
            return self.movieController.create(request)
        if request.method == "FIND_BY_ATOR":
            return self.movieController.findByAtor(request)
        if request.method == "FIND_BY_CATEGORIA":
            return self.movieController.findByCategories(request)
        if request.method == "DELETE":
            return self.movieController.delete(request)
        
    def handle_client(self, client_socket):
        try:
            # Lê o tamanho do payload
            size_data = client_socket.recv(4)
            size = struct.unpack('!I', size_data)[0]
            # Lê o payload
            data = client_socket.recv(size)
            
            # Deserializa a mensagem Request
            request = Request()
            request.ParseFromString(data)

            # Prepara a resposta
            response = self.middleware(request)
            response_data = response.SerializeToString()
            
            # Envia o tamanho da resposta seguido pela própria resposta
            client_socket.sendall(struct.pack('!I', len(response_data)))
            client_socket.sendall(response_data)
        except Exception as e:
            print(f"Erro ao processar requisição: {e}")

    def run(self):
        server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        server.bind(('localhost', 8080))  # Substitua 'localhost' pelo endereço IP desejado
        server.listen(1)

        print("Server online.")


        while True:
            try:
                client_socket, addr = server.accept()
                print(f"Conexão estabelecida com {addr}")
                self.handle_client(client_socket)

            except Exception as e:
                print("Error: ", e)
            
            finally:
                client_socket.close()

server = Server()
server.run()