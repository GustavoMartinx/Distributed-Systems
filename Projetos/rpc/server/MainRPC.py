import grpc
import MoviesRPC_pb2_grpc
from concurrent import futures     
from MovieMethodsServicer import MovieMethodsServicer   

def serve():
    ip = input("Digite o IP de comunicação: ")
    port = input("Digite a porta de comunicação: ")
    server_address = f"{ip}:{port}"
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))
    MoviesRPC_pb2_grpc.add_MovieMethodsServicer_to_server(MovieMethodsServicer(), server)
    #server.add_insecure_port('[::]:8080')
    server.add_insecure_port(server_address)
    server.start()
    print(f"Server started, listening on {server_address}.")
    server.wait_for_termination()

if __name__ == '__main__':
    serve()
