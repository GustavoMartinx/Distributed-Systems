package BackupSystem;

/**
 * UDPServer: Servidor UDP
 * Descricao: Recebe um datagrama de um cliente, imprime o conteúdo e retorna o mesmo
 * datagrama ao cliente
**/

import java.net.*;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.Arrays;
import java.io.*;

public class UDPServer {

    public static String serverPath = System.getProperty("user.dir") + "/Documents/";

    public static void main(String args[]) { 
    	DatagramSocket dgramSocket = null;
        try {
            // Cria um socket datagrama em uma porta específica
            dgramSocket = new DatagramSocket(6666);

            // Inicializa o MessageDigest com o algoritmo SHA-1
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            
            // Verificando se a pasta "Documents" existe
            File directory = new File(serverPath);
            if (!directory.exists()) {
                directory.mkdir();
            }
            
            System.out.println("Server online!");
            while(true) {
                
                // Criando um buffer para receber o primeiro pacote
                byte[] buffer = new byte[1024];
                
                // Criando um pacote vazio
                DatagramPacket dgramPacket_NameAndSize = new DatagramPacket(buffer, buffer.length);
                
                System.out.println("Waiting for datagrams...");

                // Aguardando a chegada do primeiro datagrama (FileName e FileSize)
                dgramSocket.receive(dgramPacket_NameAndSize);

                // Atualizando o MessageDigest com os dados recebidos no pacote
                md.update(dgramPacket_NameAndSize.getData(), 0, dgramPacket_NameAndSize.getLength());

                // Obtendo os dados do primeiro pacote (FileName e FileSize)
                byte[] datagramReceived = dgramPacket_NameAndSize.getData();
                int dgramPktLength = dgramPacket_NameAndSize.getLength();

                // Realizando conversões de tipos
                String filename = new String(datagramReceived, 0, dgramPktLength - 4);
                byte[] intBytes = Arrays.copyOfRange(datagramReceived, dgramPktLength - 4, dgramPktLength);
                int fileSize = ByteBuffer.wrap(intBytes).getInt();

                System.out.println("Cliente: " + filename + fileSize);

                
                // Recebendo todos os datagramas referentes ao conteúdo do arquivo

                // Para criar o arquivo no servidor
                FileOutputStream fos = new FileOutputStream(serverPath + filename);
                BufferedOutputStream bos = new BufferedOutputStream(fos);

                // Variável para verificar se o checksum está correto
                boolean checksumCorrect = true;
                
                // Cria um buffer para receber o conteúdo do arquivo
                byte[] fileContent = new byte[1024];
                
                while (true) {
                    // Criando um pacote vazio
                    DatagramPacket dgramPacket_FileContent = 
                    new DatagramPacket(fileContent, fileContent.length);
                    
                    // Aguardando a chegada de datagramas com o conteúdo do arquivo
                    dgramSocket.receive(dgramPacket_FileContent);
                    
                    // Debuggin TODO: excluir
                    // System.out.println(dgramPacket_FileContent.getLength());
                    // byte[] fileContentBytes = dgramPacket_FileContent.getData();
                    // String fileContentString = new String(fileContentBytes);
                    // System.out.println(fileContentString + "\n");
                    
                    // Verificando se é o último pacote (checksum)
                    if (dgramPacket_FileContent.getLength() == 20) { // OBS.: isso aq ta com cara que vai explodir. ps.: eh, explodiu mesmo
                        DatagramPacket dgramPacket_Checksum = dgramPacket_FileContent;

                        // Verificando o checksum
                        byte[] receivedChecksum = Arrays.copyOf(dgramPacket_Checksum.getData(), dgramPacket_Checksum.getLength());
                        byte[] calculatedChecksum = md.digest();
                        
                        // Debuggin TODO: excluir
                        // System.out.println(receivedChecksum);
                        // System.out.println(calculatedChecksum);
                        
                        checksumCorrect = Arrays.equals(receivedChecksum, calculatedChecksum);
                        break; // No recebimento do datagrama checksum, o loop é finalizado
                    }

                    // Atualizando o MessageDigest com os dados recebidos no pacote
                    md.update(dgramPacket_FileContent.getData(), 0, dgramPacket_FileContent.getLength());
                    
                    // Escrevendo os dados recebidos no arquivo
                    bos.write(dgramPacket_FileContent.getData(), 0, dgramPacket_FileContent.getLength());
                    bos.flush();

                    // Limpando fileContent
                    Arrays.fill(fileContent, (byte)0);
                }

                // Se o checksum estiver incorreto, a operação é interrompida: o arquivo é excluído
                if (!checksumCorrect) {
                    bos.close();
                    File newFile = new File(serverPath + filename);
                    newFile.delete();
                    System.out.println("Something went wrong when uploading the file '" + filename + "'.");
                    // TODO: enviar resposta pro client que deu ruim
                } else {
                    bos.close();
                    System.out.println("File '" + filename + "' uploaded successfully!");
                }
                


                /* imprime e envia o datagrama de volta ao cliente */ 
                // System.out.println("Cliente: " + datagramReceived);
                // System.out.println("Cliente: " + new String(dgramPacket.getData(), 0, dgramPacket.getLength()));
                // DatagramPacket reply = new DatagramPacket(dgramPacket.getData(),
                //         dgramPacket.getLength(), dgramPacket.getAddress(), dgramPacket.getPort()); // cria um pacote com os dados

                // dgramSocket.send(reply); // envia o pacote
            } // while
        } catch (SocketException e){
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception:" + e.getMessage());
        } finally {
            dgramSocket.close();
        } //finally
    } //main
}//class
