"""
 * Client: [TODO: fornecer descrição do arquivo, conforme descrito em
 * https://moodle.utfpr.edu.br/mod/page/view.php?id=1594607].
 * 
 * Autores: Christofer dos Santos, Diogo dos Santos e Gustavo Martins
 * 
 * Data de criação: 01/08/2024
 * 
 * Datas de atualização: 02/08
 * 
"""

import socket
import Movies_pb2

def main():
    # Conecta-se ao servidor
    client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    client_socket.connect(("localhost", 7000))

    # Instancia e preenche a estrutura
    movie = Movies_pb2.Movie()
    movie.title = "EXT2: O Filme"
    movie.id = 2
    movie.directors = "Rodrigo Campiolo"

    # Serializa a mensagem para bytes (marshalling)
    message = movie.SerializeToString()
    size_message = f"{len(message)}\n".encode('utf-8')

    # Envia o tamanho da mensagem como string e a mensagem serializada
    client_socket.send(size_message)
    client_socket.send(message)

    # Fecha a conexão
    client_socket.close()

if __name__ == "__main__":
    main()
