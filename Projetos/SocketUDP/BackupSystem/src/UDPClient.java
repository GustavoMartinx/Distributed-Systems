package src;

/**
 * UDPClient: Cliente UDP
 * Descrição: Envia um arquivo para um servidor através de um socket datagrama.
 * 
**/
import java.net.*;
import java.io.*;
import java.security.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Scanner;

public class UDPClient {

    public static void main(String args[]) {
        DatagramSocket dgramSocket;
        Scanner reader =  new Scanner(System.in);
        
        try {
            // Criando um socket datagrama
            dgramSocket = new DatagramSocket();
            
            String inputBuffer = "";
            System.out.println("IP Destination:");
            inputBuffer = reader.nextLine();
            // inputBuffer = "127.0.0.1";
            String dstIP = inputBuffer;
            
            System.out.println("Port Destination:");
            inputBuffer = reader.nextLine();
            // inputBuffer = "6666";
            int dstPort = Integer.parseInt(inputBuffer);
            
            // Armazenando o IP do destino
            InetAddress serverAddr = InetAddress.getByName(dstIP);
            int serverPort = dstPort;
            
            while(true) {
                System.out.println("Type a file to upload:");
                System.out.print("$ ");
                String command = reader.nextLine();
                
                // Trata a entrada do usuário
                int validCommand = handleCommand(command);
                
                // Verificando se o arquivo existe ou se digitou "EXIT"
                if(validCommand == 0) {
                    break;
                } else if (validCommand == 1) {
                    continue;
                }

                // Envia o arquivo para o servidor
                uploadFile(command, serverAddr, serverPort, dgramSocket);

                // Criando um buffer vazio para receber a resposta
                byte[] buffer = new byte[1024];
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);

                // Aguarda o datagrama de resposta
                dgramSocket.receive(reply);
                System.out.println("Response: " + new String(reply.getData(), 0, reply.getLength()));
            } // while

            System.out.println("Client closed.");

            // Liberando o socket
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
        
        // Inicializa o MessageDigest com o algoritmo SHA-1
        MessageDigest md = MessageDigest.getInstance("SHA-1");

        // Criando o primeiro pacote a ser enviado (FileName e FileSize):
        
        // File name: String (tamanho variável)
        String fileName = command;
        File file = new File(System.getProperty("user.dir") + "/" + fileName);
        
        // File size: Int (4 bytes)
        long fileSize = file.length();
        int fileSizeInt = (int) fileSize;
        
        // Inserindo tais dados no buffer
        int sizeOfFileName = fileName.length();                     // Obtendo o tamanho do fileName
        ByteBuffer buffer = ByteBuffer.allocate(sizeOfFileName + 4);// tamanho do fileName + 4 bytes
        buffer.order(ByteOrder.BIG_ENDIAN);

        byte[] fileNameBytes = fileName.getBytes();     // Transforma o nome do aquivo em bytes
        buffer.put(fileNameBytes);                      // Insere o nome do aquivo no buffer
        buffer.putInt(fileSizeInt);                     // Insere o tamanho do aquivo no buffer
        byte[] payload = buffer.array();                // Converte o buffer em um array de bytes

        // Atualiza o MessageDigest com os bytes lidos até o momento
        md.update(payload, 0, payload.length);

        // Cria um pacote datagrama com o buffer como payload
        DatagramPacket dgramPacket_NameAndSize = new DatagramPacket(
            payload,
            payload.length,
            serverAddr,
            serverPort
        );

        // Envia o pacote com FileName e FileSize
        dgramSocket.send(dgramPacket_NameAndSize);


        // Obtendo e enviando o conteúdo do arquivo
        try (FileInputStream fis = new FileInputStream(file)) {

            int byteReaded;                         // Byte lido do arquivo
            int pktSize = 0;                        // Contador do tamanho do pacote
            byte[] fileContent = new byte[1024];    // Array para armazenar o conteúdo do arquivo

            do {
                // Lendo bytes do arquivo e inserindo em fileContent
                while ((byteReaded = fis.read()) != -1 && pktSize < 1024) {
                    fileContent[pktSize] = (byte) byteReaded;
                    
                    // System.out.println("\n" + pktSize);

                    // System.out.println("fileContent: ");
                    // System.out.println(fileContent);
                    // System.out.println("byteReaded: ");
                    // System.out.println(byteReaded);

                    pktSize++;
                }

                // Atualiza o MessageDigest com os bytes lidos até o momento
                md.update(fileContent, 0, pktSize);
              
                // Enviar datagrama cujo payload é fileContent
                DatagramPacket dgramPacket_FileContent = new DatagramPacket(
                    fileContent,
                    pktSize,
                    serverAddr,
                    serverPort
                );
                dgramSocket.send(dgramPacket_FileContent);

                Arrays.fill(fileContent, (byte)0);  // Limpar fileContent
                pktSize = 0;                        // Reinicia o contador do tamanho do pacote
            
            } while(byteReaded != -1);
            
            // Calcula o checksum SHA-1 final
            byte[] checksumBytes = md.digest();

            // Converte o checksum para uma representação em hexadecimal # isso n ta meio torto?
            // StringBuilder checksumHex = new StringBuilder();
            // for (byte b : checksumBytes) {
            //     checksumHex.append(String.format("%02x", b));
            // }

            // Envia o checksum final como uma string hexadecimal
            // byte[] checksumPayload = checksumHex.toString().getBytes();
            DatagramPacket dgramPacket_Checksum = new DatagramPacket(
                checksumBytes,
                checksumBytes.length,
                serverAddr,
                serverPort
            );
            dgramSocket.send(dgramPacket_Checksum);

            fis.close();
        }
    }

} // class
