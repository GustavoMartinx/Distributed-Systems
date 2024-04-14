/**
 * TCPServer: Servidor para conexões com clientes através de sockets TCP e Threads
 * Descricao: Ao receber uma conexão, mapeia uma thread para atendê-la de forma a
 * entrar em um estado não-bloqueante para atender às possíveis novas conexões.
 * 
 * Autores: Diogo Rodrigues dos Santos e Gustavo Zanzin Guerreiro Martins
 * 
 * Data de criação: 07/04/2024
 * 
**/

import java.io.*;
import java.net.*;
import java.util.logging.*;


public class TCPServer {

        public static void main(String args[]) {
    
            try {
                int serverPort = 6666;
    
                // Criando um socket e mapeando a porta para aguardar conexão
                ServerSocket listenSocket = new ServerSocket(serverPort);
    
                // Configurando o arquivo de log do servidor
                FileHandler fileHandler = new FileHandler("server.log");  // Cria um arquivo de log
                Logger logger = Logger.getLogger("server.log");              // Associa o arquivo de log ao objeto logger
                logger.setLevel(Level.FINE);                                      // Define a classe de mensagens que serão escritas no log
                logger.addHandler(fileHandler);                                   
                SimpleFormatter formatter = new SimpleFormatter();                // Cria um formatador para o arquivo de log
                fileHandler.setFormatter(formatter);                              // Adiciona o formatador ao arquivo de log
    
                while (true) {
                    // Aguardando conexões
                    System.out.println("Waiting connections...\n");
                    
                    Socket clientSocket = listenSocket.accept();
                    logger.info("Client conected!\n");
                    System.out.println("Client conected!\n");
                    
                    // Criando um thread para atender a conexão
                    ListenerThread listenerThread = new ListenerThread(clientSocket);
    
                    // inicializa a thread
                    listenerThread.start();
                } // while
    
            } catch (IOException e) {
                System.out.println("Listen socket:" + e.getMessage());
            } // catch
        } // main
    } // class