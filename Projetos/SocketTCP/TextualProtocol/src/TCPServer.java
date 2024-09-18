package src;

/**
 * TCPServer: Servidor para conexão TCP com Threads. 
 * Aguarda uma conexão, ao receber uma solicitação de conexão, 
 * cria uma thread que fica responsável por gerenciá-la.
*/
 
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

    public static void main(String args[]) {
        ServerSocket listenSocket;

        try {
            int serverPort = 6666;
            listenSocket = new ServerSocket(serverPort);

            String user1 = "diogo";
            String user2 = "gustavo";
            String senha1 = "TpOUtNKHa4dBsQovtGWJtg8aHBIem8TCgProWvdbda6GCdSfDkIV87aCMG3H8mKxcf/BgfiG92TWOCENb/e6KA==";
            String senha2 = "TpOUtNKHa4dBsQovtGWJtg8aHBIem8TCgProWvdbda6GCdSfDkIV87aCMG3H8mKxcf/BgfiG92TWOCENb/e6KA==";

            User[] users = new User[] { new User(user1, senha1), new User(user2, senha2) };

            while (true) {
                System.out.println("[Servidor TCP] Aguardando solicitações de conexão.");
                
                // Espera uma solicitação de conexão
                Socket clientSocket = listenSocket.accept();
                System.out.println("[Servidor TCP] Novo cliente conectado! Criando thread...");

                // Cria uma thread para lidar com o cliente
                Listenner thread = new Listenner(clientSocket, users);
                thread.start();
            }

        } catch (EOFException eof) {
            System.out.println("[Servidor TCP] [Listen socket] " + eof.getMessage());
        } catch (IOException e) {
            System.out.println("[Servidor TCP] [Listen socket] " + e.getMessage());
        } catch (UnsupportedOperationException unoe) {
            System.out.println("[Servidor TCP] [Listen socket] " + unoe.getMessage());
        } catch (Exception ex) {
            System.out.println("[Servidor TCP] [Listen socket] " + ex.getMessage());
        }
    }
}
