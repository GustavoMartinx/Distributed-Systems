package BackupSystem;

/**
 * UDPServer: Servidor UDP
 * Descricao: Recebe um datagrama de um cliente, imprime o conteúdo e retorna o mesmo
 * datagrama ao cliente
**/

import java.net.*;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.io.*;

public class UDPServer{
    public static void main(String args[]){ 
    	DatagramSocket dgramSocket = null;
        try{
            dgramSocket = new DatagramSocket(6666); // cria um socket datagrama em uma porta especifica
            
            while(true){
                System.out.println("Server online!");
                byte[] buffer = new byte[1000]; // cria um buffer para receber requisições

                /* cria um pacote vazio */
                DatagramPacket dgramPacket = new DatagramPacket(buffer, buffer.length);
                dgramSocket.receive(dgramPacket);  // aguarda a chegada de datagramas

                // obtendo os dados do primeiro pacote (filename e fileSize)
                byte[] datagramReceived = dgramPacket.getData();
                int dgramPktLength = dgramPacket.getLength();

                // converter o nome para string
                String filename = new String(datagramReceived, 0, dgramPktLength - 4);
                // converter o tamanho para int
                byte[] intBytes = Arrays.copyOfRange(datagramReceived, dgramPktLength - 4, dgramPktLength);
                int fileSize = ByteBuffer.wrap(intBytes).getInt();

                System.out.println("Cliente: " + filename + fileSize);

                /* imprime e envia o datagrama de volta ao cliente */ 
                // System.out.println("Cliente: " + datagramReceived);
                // System.out.println("Cliente: " + new String(dgramPacket.getData(), 0, dgramPacket.getLength()));
                DatagramPacket reply = new DatagramPacket(dgramPacket.getData(),
                        dgramPacket.getLength(), dgramPacket.getAddress(), dgramPacket.getPort()); // cria um pacote com os dados

                dgramSocket.send(reply); // envia o pacote
            } //while
        }catch (SocketException e){
            System.out.println("Socket: " + e.getMessage());
        }catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            dgramSocket.close();
        } //finally
    } //main
}//class
