import json
import socket
import struct

from Movies_pb2 import Movie, Request, Response
from Database import Database
from MovieService import MovieService
from MovieController import MovieController

class Server:
    def __init__(self):
        self.database = Database()
        self.movieService = MovieService(self.database)
        self.movieController = MovieController(self.movieService)
        
    def handle_client(self, client_socket):
        try:
            # Lê o tamanho do payload
            size_data = client_socket.recv(4)
            size = struct.unpack('!I', size_data)[0]
            print("Size and size data ok")
            # Lê o payload
            data = client_socket.recv(size)
            
            # Deserializa a mensagem Request
            request = Request()
            print("Builder request ok")
            request.ParseFromString(data)
            print("Parse data to request ok")
            
            print(f"Método recebido: {request.method}")
            print(f"Movie: {request.movie}")
            
            # Prepara a resposta
            response = Response(status=200, message="Sucesso!")
            
            print("Response:", response)
            # Serializa a resposta
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

        print("Server running...")
        
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