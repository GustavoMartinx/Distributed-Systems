import grpc
import MoviesRPC_pb2_grpc
from concurrent import futures     
from MovieMethodsServicer import MovieMethodsServicer   

def serve():
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))
    MoviesRPC_pb2_grpc.add_MovieMethodsServicer_to_server(MovieMethodsServicer(), server)
    server.add_insecure_port('[::]:8080')
    server.start()
    print("Server started, listening on port 8080.")
    server.wait_for_termination()

if __name__ == '__main__':
    serve()
