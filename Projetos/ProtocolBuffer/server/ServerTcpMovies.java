/**
 * ServerTcpMovies: [TODO: fornecer descrição do arquivo, conforme descrito em
 * https://moodle.utfpr.edu.br/mod/page/view.php?id=1594607].
 * 
 * Autores: Christofer dos Santos, Diogo dos Santos e Gustavo Martins
 * 
 * Data de criação: 01/08/2024
 * 
 * Datas de atualização: 02/08
 * 
**/

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTcpMovies {

    public static void main(String args[]) {
        try {
            int serverPort = 7000;
            ServerSocket listenSocket = new ServerSocket(serverPort);
            while (true) {
                System.out.println("Server running...");
                Socket clientSocket = listenSocket.accept();

                DataInputStream inClient = 
                    new DataInputStream(clientSocket.getInputStream());
                
                String valueStr = inClient.readLine();
                
                int sizeBuffer = Integer.valueOf(valueStr);
                byte[] buffer = new byte[sizeBuffer];
                clientSocket.getInputStream().read(buffer);

                // Realiza o unmarshalling
                Movies.Movie m = Movies.Movie.parseFrom(buffer);
                System.out.println("==\n" + m + "==\n");
            }
        } catch (IOException e) {
            System.out.println("Listensocket: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
