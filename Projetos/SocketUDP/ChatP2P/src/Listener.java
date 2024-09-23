package src;

/*
 * Listener: Thread responsável por receber e processar as requisições do
 * processo peer remoto.
*/

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Listener extends Thread {

    DatagramSocket datagramSocket;
    int peerPort;
    String[] types = { "normal", "emoji", "url", "ECHO" };

    public Listener(DatagramSocket socket, int peerPort) {
        this.datagramSocket = socket;
        this.peerPort = peerPort;
    }

    @Override
    public void run() {

        try {
            while (true) {
                byte[] buffer = new byte[1000];
                DatagramPacket receivedDgramPkt = new DatagramPacket(buffer, buffer.length);

                this.datagramSocket.receive(receivedDgramPkt);

                String msg = new String(receivedDgramPkt.getData(), 0, receivedDgramPkt.getLength());
                if (msg.equals("exit")) break;

                Message message = unmarshallPacket(receivedDgramPkt);

                if (message.typeMessage == 4) {
                    executeEcho(this.datagramSocket, receivedDgramPkt, this.peerPort);
                    continue;
                }

                if (0 < message.typeMessage && message.typeMessage < 5) {
                    System.out.println(
                            message.nickname + "[" + this.types[message.typeMessage - 1] + "]: " + message.message);
                }
            }
        } catch (IOException ioe) {
            System.out.println("IO: " + ioe.getMessage());

        } catch (Exception e) {
            System.out.println("E: " + e.getMessage());
        }
    }

    protected static void executeEcho(DatagramSocket socket, DatagramPacket pkg, int peerPort)
            throws UnknownHostException, IOException {
        
        // Enviando como resposta echo o mesmo pacote recebido (por conveniência)
        // TODO: método para criar pacote de resposta echo
        byte[] echoPayload = pkg.getData();
        echoPayload[0] = 1;
        
        InetAddress peerAddr = pkg.getAddress();
        DatagramPacket echoPacket = new DatagramPacket(echoPayload, echoPayload.length, peerAddr, peerPort);

        socket.send(echoPacket);
    }

    protected static Message unmarshallPacket(DatagramPacket pkg) {
        byte[] data = pkg.getData();
        int offset = pkg.getOffset();

        // Obtendo os campos do pacote
        int messageType = data[offset++];
        byte nicknameLength = data[offset++];
        byte[] nicknameBytes = new byte[nicknameLength];
        System.arraycopy(data, offset, nicknameBytes, 0, nicknameLength);
        offset += nicknameLength;
        byte messageLength = data[offset++];
        byte[] messageBytes = new byte[messageLength];
        System.arraycopy(data, offset, messageBytes, 0, messageLength);

        String nickname = new String(nicknameBytes);
        String msg = new String(messageBytes);

        return new Message(messageType, nicknameLength, nickname, messageLength, msg);
    }
}
