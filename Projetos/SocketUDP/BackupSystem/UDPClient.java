package BackupSystem;

/**
 * UDPClient: Cliente UDP Descricao: Envia uma msg em um datagrama e recebe a
 * mesma msg do servidor
 */
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
            
            System.out.println("Type a message:");
            System.out.print("$ ");
            String msg = reader.nextLine();

            do {
                byte[] m = msg.getBytes(); // transforma a mensagem em bytes

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

                System.out.println("Type a message:");
                System.out.print("$ ");
                msg = reader.nextLine();
            
            } while (!msg.equals("EXIT"));

            System.out.println("Client closed.");

            /* libera o socket */
            dgramSocket.close();
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } //catch
    } //main		      	
} //class
