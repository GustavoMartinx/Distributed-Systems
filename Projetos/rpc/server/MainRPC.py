# Autores: Christofer Daniel, Diogo Rodrigues e Gustavo Martins
# Data: 21/08/2024

# Este código é a classe principal que implementa o server do sistema de consulta de filmes
# Esse código usa grpc para implementar a comunicação entre o client e o server
 


import grpc
import MoviesRPC_pb2_grpc
from concurrent import futures     
from MovieMethodsServicer import MovieMethodsServicer   

def serve():
    # Solicita ao usuário que digite o IP de comunicação
    ip = input("Digite o IP de comunicação: ")
    # Solicita ao usuário que digite a porta de comunicação
    port = input("Digite a porta de comunicação: ")
     # Constrói o endereço do servidor usando o IP e a porta fornecidos
    server_address = f"{ip}:{port}"
    # Cria um servidor gRPC com um pool de threads de até 10 trabalhadores
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))
    # Adiciona o MovieMethodsServicer ao servidor gRPC
    MoviesRPC_pb2_grpc.add_MovieMethodsServicer_to_server(MovieMethodsServicer(), server)
    #server.add_insecure_port('[::]:8080')
    # Adiciona uma porta insegura ao servidor para escutar no endereço especificado
    server.add_insecure_port(server_address)
    # Inicia o servidor
    server.start()
    print(f"Server started, listening on {server_address}.")
     # Aguarda a terminação do servidor
    server.wait_for_termination()

if __name__ == '__main__':
    serve()
