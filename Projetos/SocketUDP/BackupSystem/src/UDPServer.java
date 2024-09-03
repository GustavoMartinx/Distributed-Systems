package src;

/**
 * UDPServer: Servidor UDP
 * Descrição: Recebe um arquivo através de um socket datagrama
 * e salva-o no diretório "Documents".
 * 
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

            int entrou = 0;
            
            System.out.println("Server online!");
            while(true) {
                
                // Criando um buffer para receber o primeiro pacote
                byte[] buffer = new byte[1024];
                
                // Criando um pacote vazio
                DatagramPacket dgramPacket_NameAndSize = new DatagramPacket(buffer, buffer.length);
                
                System.out.println("Waiting for datagrams...");

                // Aguardando a chegada do primeiro datagrama (FileName e FileSize)
                dgramSocket.receive(dgramPacket_NameAndSize);
                int packetsReceived = 1;

                // Atualizando o MessageDigest com os dados recebidos no pacote
                md.update(dgramPacket_NameAndSize.getData(), 0, dgramPacket_NameAndSize.getLength());

                // Obtendo os dados do primeiro pacote (FileName e FileSize)
                byte[] datagramReceived = dgramPacket_NameAndSize.getData(); // usar bytebuffer aqui?
                int dgramPktLength = dgramPacket_NameAndSize.getLength();

                // Realizando conversões de tipos
                String filename = new String(datagramReceived, 0, dgramPktLength - 4);
                byte[] intBytes = Arrays.copyOfRange(datagramReceived, dgramPktLength - 4, dgramPktLength);
                int fileSize = ByteBuffer.wrap(intBytes).getInt();

                System.out.println("===\nCliente:" + "\nNome do arquivo: " + filename + "\nTamanho do arquivo: " + fileSize + "\n===");

                
                // RECEBENDO TODOS OS DATAGRAMAS REFERENTES AO CONTEÚDO DO ARQUIVO
                
                // Usando o tamanho do arquivo para calcular quantos pacotes serão recebidos
                int totalDatagramsPackets = (int) Math.ceil(fileSize / 1024);
                int checksumPacketIndex = totalDatagramsPackets + 3;

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
                    packetsReceived += 1;
                    System.out.println(packetsReceived);
                    
                    // Verificando se é o último pacote (checksum)
                    if (packetsReceived == checksumPacketIndex) {
                        DatagramPacket dgramPacket_Checksum = dgramPacket_FileContent;
                        
                        // Debuggin TODO: excluir
                        entrou += 1;
                        System.err.println("entrou: " + entrou);

                        // Verificando o checksum
                        byte[] receivedChecksum = Arrays.copyOf(dgramPacket_Checksum.getData(), dgramPacket_Checksum.getLength());
                        byte[] calculatedChecksum = md.digest();
                        
                        // TODO: Existe um caractere sendo pulado (ou no envio ou no recebimento/escrita) do arquivo que pode ser o que está gerando a incosistência nos checksums
                        // Debuggin TODO: excluir
                        System.out.println("receivedChecksum: " + receivedChecksum);
                        System.out.println("calculatedChecksum: " + calculatedChecksum);
                        
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

                    // Enviar resposta para o cliente que ocorreu um erro
                    String response = "Something went wrong when uploading file '" + filename + "'.";
                    System.out.println(response);

                    // Enviando resposta para o cliente
                    DatagramPacket reply = new DatagramPacket(response.getBytes(), response.length(), dgramPacket_NameAndSize.getSocketAddress());
                    dgramSocket.send(reply);
                } else {
                    bos.close();
                    String response = "File '" + filename + "' uploaded successfully!";
                    System.out.println(response);

                    // Enviando resposta para o cliente
                    DatagramPacket reply = new DatagramPacket(response.getBytes(), response.length(), dgramPacket_NameAndSize.getSocketAddress());
                    dgramSocket.send(reply);
                }
                // Debuggin TODO: excluir
                System.out.println("entrou final");
                System.out.println(entrou);
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
