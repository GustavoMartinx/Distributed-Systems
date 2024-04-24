package BackupSystem;

/**
 * UDPClient: Cliente UDP
 * Descrição: Envia uma mensagem em um datagrama e recebe a mesma mensagem do servidor
**/
import java.net.*;
import java.util.Scanner;
import java.io.*;

public class UDPClient {

    public static void main(String args[]) {
        DatagramSocket dgramSocket;
        Scanner reader =  new Scanner(System.in);
        
        try {
            String inputBuffer = "";
            dgramSocket = new DatagramSocket(); //cria um socket datagrama
            
            System.out.println("IP Destination:");
            inputBuffer = reader.nextLine();
            String dstIP = inputBuffer;
            
            System.out.println("Port Destination:");
            inputBuffer = reader.nextLine();
            int dstPort = Integer.parseInt(inputBuffer);
            
            /* armazena o IP do destino */
            InetAddress serverAddr = InetAddress.getByName(dstIP);
            int serverPort = dstPort; // porta do servidor
            
            while(true) {
                System.out.println("Type a message:");
                System.out.print("$ ");
                String command = reader.nextLine();
                
                int validCommand = handleCommand(command);
                
                if(validCommand == 0) {
                    break;
                } else if (validCommand == 1) {
                    continue;
                }

                byte[] m = command.getBytes(); // transforma a mensagem em bytes

                /* cria um pacote datagrama */
                DatagramPacket request
                        = new DatagramPacket(m, m.length, serverAddr, serverPort);

                /* envia o pacote */
                dgramSocket.send(request);

                /* cria um buffer vazio para receber datagramas */
                byte[] buffer = new byte[1000];
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);

                /* aguarda datagramas */
                dgramSocket.receive(reply);
                System.out.println("Response: " + new String(reply.getData(),0,reply.getLength()));
            } //while

            System.out.println("Client closed.");

            /* libera o socket */
            dgramSocket.close();

        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } // catch
    } // main

    static int handleCommand(String command) throws IOException {
        
        if (command.equals("EXIT")) {
            return 0;
        } else {
            String filename = command;
            File fileInCurrentDir = new File(System.getProperty("user.dir") + "/" + filename);
            if (!fileInCurrentDir.exists()) {
                System.out.println("Invalid command or file not found.");
                return 1;
            } else {
                return 2;
            }
        }
        
    } // handleCommand
} // class
