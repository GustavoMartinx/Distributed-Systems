import socket
import struct
import Movies_pb2  # Supondo que o arquivo Movies.proto foi compilado para Movies_pb2.py

def main():
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
            movie = Movies_pb2.Movie()
            movie.ParseFromString(buffer)
            
            print("==\n" + str(movie) + "==\n")
        
        except Exception as e:
            print("Error: ", e)
        
        finally:
            client_socket.close()

if __name__ == "__main__":
    main()
