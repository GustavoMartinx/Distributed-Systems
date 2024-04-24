package BackupSystem;

/**
 * UDPClient: Cliente UDP
 * Descrição: Envia uma mensagem em um datagrama e recebe a mesma mensagem do servidor
**/
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
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

                uploadFile(command, serverAddr, serverPort, dgramSocket);

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
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        } // catch
    } // main

    static int handleCommand(String command) throws IOException {
        
        if (command.equals("EXIT")) {
            return 0;       // 0: command == "EXIT"
        } else {
            String filename = command;
            File fileInCurrentDir = new File(System.getProperty("user.dir") + "/" + filename);
            if (!fileInCurrentDir.exists()) {
                System.out.println("Invalid command or file not found.");
                return 1;   // 1: command is invalid
            } else {
                return 2;   // 2: file exists
            }
        }
    } // handleCommand

    static void uploadFile(String command, InetAddress serverAddr, int serverPort, DatagramSocket dgramSocket) throws Exception {
        
        // Criando o primeiro pacote a ser enviado (FileName e FileSize):
        
        // File name: String (variável)
        String fileName = command;
        File file = new File(System.getProperty("user.dir") + "/" + fileName);
        
        // File size: Int (4 bytes)
        long fileSize = file.length();
        int fileSizeInt = (int) fileSize;
        
        // Inserindo esses dados no buffer
        int sizeOfFileName = fileName.length();

        ByteBuffer buffer = ByteBuffer.allocate(sizeOfFileName + 8); // tamanho do filename + 4 bytes
        buffer.order(ByteOrder.BIG_ENDIAN);

        byte[] fileNameBytes = fileName.getBytes();     // Transforma o nome do aquivo em bytes
        buffer.put(fileNameBytes);                      // Insere o nome do aquivo no buffer
        buffer.putInt(fileSizeInt);                        // Insere o tamanho do aquivo no buffer
        byte[] payload = buffer.array();                // Converte o buffer em um array de bytes

        // Cria um pacote datagrama com o buffer como payload
        DatagramPacket request = new DatagramPacket(
            payload,
            payload.length,
            serverAddr,
            serverPort
        );

        // Envia o pacote
        dgramSocket.send(request);
    }

} // class
