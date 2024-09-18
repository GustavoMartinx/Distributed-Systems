package src;

/*
 * TCPClient: Cliente Java para conexão TCP.
 * Envia informações ao servidor e recebe as respostas através de um
 * socket TCP.
*/

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;

public class TCPClient {

    public static void main(String args[]) {

        Scanner reader = new Scanner(System.in);
        Socket clientSocket = null;
        Commands commands = new Commands();
        String localpath = "$";

        try {
            // Endereço e porta do servidor
            int serverPort = 6666;
            InetAddress serverAddr = InetAddress.getByName("127.0.0.1");

            // Inicializando o socket TCP
            clientSocket = new Socket(serverAddr, serverPort);

            // Criando os canais de entrada e saida
            DataInputStream in = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());

            boolean userNotConnected = true;
            boolean userConnected = true;

            while (userNotConnected) {
                String buffer = "";
                System.out.print(localpath + " ");
                buffer = reader.nextLine();

                String[] cmdParams = buffer.split(" ");
                if (checkAmountParams(cmdParams)) {
                    System.out.println("[Cliente] Parâmetros inválidos.");
                    continue;
                }

                String user = cmdParams[1];
                String password = cmdParams[2];

                // Codificando a senha usando SHA-512
                MessageDigest md = MessageDigest.getInstance("SHA-512");
                md.update(password.getBytes());
                byte[] bytes = md.digest();
                String hashedPassword = Base64.getEncoder().encodeToString(bytes);

                // Construindo e enviando o comando para o servidor
                String cmd = cmdParams[0] + " " + user + " " + hashedPassword;
                out.writeUTF(cmd);

                // Recebendo resposta
                buffer = in.readUTF();
                if (buffer.contains(commands.success)) {
                    String[] response = buffer.split(" ");
                    localpath = response[1];
                    userNotConnected = false;
                    break;
                }
                System.out.println(buffer);
            }

            // Enquanto não receber o comando exit
            while (userConnected) {
                
                // Lê a entrada do teclado
                String buffer = "";
                System.out.print(localpath + " $ ");
                buffer = reader.nextLine();
                String storageBuffer = buffer;

                // Saída do laço caso o comando for exit
                if (buffer.equals(commands.exit)) break;

                // Enviando o comando para o servidor
                out.writeUTF(buffer);

                // Recebendo resposta
                buffer = in.readUTF();

                // Atualiza o localpath caso o comando chdir retorne sucesso
                if (buffer.contains(commands.chdir)) {
                    String[] newPath = buffer.split(" ");
                    localpath = newPath[1];
                    continue;
                }

                // Verificando a resposta
                if (!buffer.contains(commands.success)) {
                    System.out.print(buffer);
                } else if (buffer.contains(commands.error)) {
                    System.out.println("Command error: " + storageBuffer);
                }
            }
        } catch (UnknownHostException ue) {
            System.out.println("Socket:" + ue.getMessage());
        } catch (EOFException eofe) {
            System.out.println("EOF:" + eofe.getMessage());
        } catch (IOException ioe) {
            System.out.println("IO:" + ioe.getMessage());
        } catch (NoSuchAlgorithmException e) {
            System.out.println("HASH" + e.getMessage());
        } finally {
            try {
                clientSocket.close();
                reader.close();
            } catch (IOException ioe) {
                System.out.println("IO: " + ioe);
            }
        }
    }

    /**
     * Verifica se a quantidade de parâmetros é 3.
     * @param cmdParams array de parâmetros passados pela linha de comando
     * @return true se a quantidade de parâmetros for 3, false caso contrário
     */
    static boolean checkAmountParams(String[] cmdParams) {
        return cmdParams.length != 3;
    }
}