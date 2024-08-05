import json
import socket

from Movies_pb2 import Movie, Request
from Database import Database
from MovieService import MovieService
from MovieController import MovieController

class Server:
    def __init__(self):
        self.database = Database()
        self.movieService = MovieService(self.database)
        self.movieController = MovieController(self.movieService)
    
    def run(self):
        server_port = 7000
        listen_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        listen_socket.bind(('', server_port))
        listen_socket.listen(1)
        
        print("Server running...")
        
        while True:
            client_socket, addr = listen_socket.accept()
            
            try:
                data_input = client_socket.makefile('rb')
                
                # Lendo o tamanho do byte array
                size_buffer = struct.unpack('!I', data_input.read(4))[0]
                buffer = data_input.read(size_buffer)
                
                # Realiza o unmarshalling
                request = Request()
                request.ParseFromString(buffer)
                
                # extrair metodo da request 
                method = request.method
                if method == 'create':
                    self.movieController.create(request)
                    
                print("==\n" + str(movie) + "==\n")
            
            except Exception as e:
                print("Error: ", e)
            
            finally:
                client_socket.close()

server = Server()
server.run()