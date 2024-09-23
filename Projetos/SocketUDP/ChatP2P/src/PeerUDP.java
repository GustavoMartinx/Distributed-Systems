package src;

/**
 * Cria conexão com um processo peer remoto através de um socket UDP.
 * Lê as mensagens do teclado, as serializa e envia para o peer remoto.
 * Instancia uma Thread para ouvir as mensagens recebidas do peer remoto.
*/

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;
import java.io.IOException;

public class PeerUDP {

    public static void main(String args[]) {
        DatagramSocket dgramSocket;
        Scanner reader = new Scanner(System.in);

        try {

            // Criando um socket UDP
            int thisPort = Integer.parseInt(args[0]);
            dgramSocket = new DatagramSocket(thisPort);
            
            // Porta do peer remoto
            int peerPort = Integer.parseInt(args[1]);

            // Apelido deste peer
            String thisNickname = args[2];

            // Endereço IP do peer remoto
            String peerIP = args[3];
            InetAddress peerAddress = InetAddress.getByName(peerIP);

            // Instanciando a thread de escuta
            Listener listener = new Listener(dgramSocket, peerPort);
            listener.start();

            while (true) {
                String msg = reader.nextLine();
                
                if(msg.equals("exit")) break;

                try {
                    byte[] payload = marshallMessage(msg, thisNickname);
                    DatagramPacket dgramPkt = new DatagramPacket(payload, payload.length, peerAddress, peerPort);
                    dgramSocket.send(dgramPkt);

                } catch (Exception e) {
                    System.out.println("\nFormato inválido. Por favor, insira o formato correto:\n<tipo> <mensagem>\n\nOs tipos de mensagem são:\n1 - Mensagem\n2 - Emoji\n3 - URL\n4 - Echo\n\n");
                }

            } // while

            dgramSocket.close();
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            reader.close();
        } // finally
    } // main
    

    protected static byte[] createPayload(byte[] nickname, byte[] message, int msgType) {
        int payloadLength = 1 + 1 + nickname.length + 1 + message.length;
        byte[] payload = new byte[payloadLength];

        int offset = 0;
        payload[offset++] = (byte) msgType;

        payload[offset++] = (byte) nickname.length;
        System.arraycopy(nickname, 0, payload, offset, nickname.length);
        offset += nickname.length;

        payload[offset++] = (byte) message.length;
        System.arraycopy(message, 0, payload, offset, message.length);
        return payload;
    }


    protected static byte[] marshallMessage(String msg, String nickname) {
        Integer msgType = Integer.parseInt(msg.substring(0, 1));
        msg = msg.substring(1);

        byte[] nicknameBytes = nickname.getBytes();
        byte[] msgBytes = msg.getBytes();

        byte[] payload = createPayload(nicknameBytes, msgBytes, msgType);
        return payload;
    }

}// class
