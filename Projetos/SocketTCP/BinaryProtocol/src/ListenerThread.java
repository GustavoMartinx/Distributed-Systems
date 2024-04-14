/**
 * ListenerThread: Thread responsável por receber e processar as requisições do
 * servidor.
 * 
 * Autores: Diogo Rodrigues dos Santos e Gustavo Zanzin Guerreiro Martins
 * 
 * Data de criação: 07/04/2024
 * 
 * Datas de atualização: 11/04, 13/04, 14/04
**/

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.logging.*;

public class ListenerThread extends Thread {

    DataInputStream input;
    DataOutputStream output;
    Socket clientSocket;

    String localPath = System.getProperty("user.dir");
    String serverPath = System.getProperty("user.dir") + "/Documents/";
    String downloadPath = System.getProperty("user.dir") + "/Downloads/";

    public ListenerThread(Socket clientSocket) {
        try {
            this.clientSocket = clientSocket;
            this.input = new DataInputStream(clientSocket.getInputStream());
            this.output = new DataOutputStream(clientSocket.getOutputStream());

            File directory = new File(serverPath);
            if (!directory.exists()) {
                directory.mkdir();
            }

        } catch (IOException ioe) {
            System.out.println("Connection:" + ioe.getMessage());
        }
    } // constructor

    /**
     * Método executado ao inicializar o ListenerThread. Este método realiza a leitura dos
     * cabeçalhos das requisições dos clientes e chama o método responsável por processá-las.
     * 
     */
    @Override
    public void run() {

        Logger logger = Logger.getLogger("server.log");  // Associa o arquivo de log ao objeto logger
        logger.setLevel(Level.FINE);

        try {
            while (true) {

                // Recebendo e processando a requisição
                byte[] request = new byte[258];
                this.input.read(request);
                processRequest(request, logger);

            } // while

        } catch (EOFException eofe) {
            System.out.println("EOF: " + eofe.getMessage());
        } catch (IOException ioe) {
            System.out.println("IOE: " + ioe.getMessage());
        } finally {
            try {
                this.input.close();
                this.output.close();
                this.clientSocket.close();
            } catch (IOException ioe) {
                System.err.println("IOE: " + ioe);
            }
        }
        logger.info("Client disconnected.\n");
    } // run

    /**
     * Recebe e processa os cabeçalhos das requisições dos clientes, chamando os métodos
     * respectivos a cada comando e registrando o necessário no log no servidor.
     * 
     * @param request - Cabeçalho da requisição.
     * @param logger - Objeto para registro no arquivo de log.
     * @throws IOException
     */
    private void processRequest(byte[] request, Logger logger) throws IOException {
        
        // Obtendo os dados do cabeçalho da requisição
        ByteBuffer header = ByteBuffer.wrap(request);       // Cria um ByteBuffer a partir do array de bytes recebido
        header.order(ByteOrder.BIG_ENDIAN);                 // Define a ordem dos bytes (BIG_ENDIAN)

        byte messageType = header.get();                    // Obtém o tipo da mensagem (1 byte)
        byte commandId = header.get();                      // Obtém o código do comando (1 byte)
        byte filenameSize = header.get();                   // Obtém o tamanho do nome do arquivo (1 byte)

        byte[] filenameBytes = new byte[filenameSize];      // Cria um array de bytes para o nome do arquivo
        header.get(filenameBytes);                          // Obtém o nome do arquivo em bytes (tamanho variável)
        String filename = new String(filenameBytes);        // Converte o nome do arquivo para String
        Integer sizeOfContentFile = header.getInt();        // Obtém o tamanho do conteúdo do arquivo
    
        // Verificando se o tipo da mensagem é uma requisição (0x01 == Request)
        if (messageType == 1) {
            logger.info("Message Type: "    + messageType +
                    " | Command ID: "       + commandId +
                    " | Size of FileName: " + filenameSize +
                    " | File Name: "        + filename + "\n");
    
            switch (commandId) {
                case 1:
                    handleAddFile(filename, sizeOfContentFile, commandId, logger);
                    break;
                case 2:
                    handleDeleteFile(filename, commandId, logger);
                    break;
                case 3:
                    // handleGetFilesList();
                    break;
                case 4:
                    // handleGetFile(filename);
                    break;
                default:
                    logger.warning("Unknown command ID: " + commandId + "\n");
                    break;
            }
        } else {
            logger.warning("Unknown message type: " + messageType + "\n");
        }
    } // processRequest
    
    /**
     * Este método trata a requisição ADDFILE no lado do servidor.
     * 
     * O arquivo enviado pelo cliente é recebido e salvo no diretório 'Documents' no servidor.
     * Em seguida, as devidas respostas são enviadas ao cliente, bem como registradas no log
     * do servidor.
     * 
     * @param filename - Nome do arquivo.
     * @param sizeOfContentFile - Tamanho do conteúdo do arquivo.
     * @param commandId - Identificador do comando (0x01 == ADDFILE).
     * @param logger - Objeto de escrita (server.log).
     * @throws IOException
     */
    public void handleAddFile(String filename, Integer sizeOfContentFile, byte commandId, Logger logger) throws IOException {

        byte SUCCESS = (byte) 1;
        byte ERROR = (byte) 2;
        
        if(sizeOfContentFile.intValue() > 0) {
            byte[] byteReaded = new byte[1];
            byte[] fileContentBytes = new byte[sizeOfContentFile];
            
            // Realiza a leitura dos bytes do arquivo recebido
            for (int i = 0; i < sizeOfContentFile; i++) {
                this.input.read(byteReaded);
                fileContentBytes[i] = byteReaded[0];
            }

            String fileContentString = new String(fileContentBytes);
            File newFile = new File(this.serverPath + filename);

            if (newFile.createNewFile()) {

                FileWriter writer = new FileWriter(newFile, true);
                BufferedWriter buf = new BufferedWriter(writer);
                buf.write(fileContentString);
                buf.flush();
                buf.close();

                logger.fine("Status code 1 - File '" + filename + "' uploaded successfully!\n");
                commonResponse(commandId, SUCCESS);

            } else {
                
                logger.warning("Status code 2 - Something went wrong when copying the file '" + filename + "'.\n");
                commonResponse(commandId, ERROR);
            }

        } else {

            logger.warning("Status code 2 - The file '" + filename + "' does not exist or has no content.\n");
            commonResponse(commandId, ERROR);
        }
    } // handleAddFile

    /** 
     * Este método trata a requisição DELETE no lado do servidor.
     * 
     * @param filename - Nome do arquivo a deletar.
     * @param commandId - Identificador do comando (0x02 == DELETE).
     * @param logger - Objeto de escrita (server.log).
     * @throws IOException
    */
    public void handleDeleteFile(String filename, byte commandId, Logger logger) throws IOException {

        byte SUCCESS = (byte) 1;
        byte ERROR = (byte) 2;

        File file = new File(this.serverPath + filename);

        if (file.delete()) {

            logger.fine("Status code 1 - File '" + filename + "' deleted successfully!\n");
            commonResponse(commandId, SUCCESS);
        
        } else {

            logger.warning("Status code 2 - Something went wrong when deleting the file '" + filename + "'.\n");
            commonResponse(commandId, ERROR);
        }
    } // handleDeleteFile


    /**
     * Este método envia um cabeçalho de resposta comum para os comandos que não exigem
     * um cabeçalho específico, de acordo com o protocolo estabelecido. Isto é, será 
     * utilizado como resposta aos comandos ADDFILE e DELETE.
     * 
     * @param commandId - Identificador do comando.
     * @param status - Status code (1 == SUCCESS || 2 == ERROR).
     * @throws IOException
     */
    private void commonResponse(byte commandId, byte status) throws IOException {
        ByteBuffer header = ByteBuffer.allocate(3); // Alocando 3 bytes para o cabeçalho
        header.order(ByteOrder.BIG_ENDIAN);         // Definindo a ordem dos bytes como big endian
        header.put((byte) 2);                       // Tipo da mensagem (2 == Resposta)
        header.put(commandId);                      // Identificador do comando
        header.put(status);                         // Status (1 == SUCCESS || 2 == ERROR)
        this.output.write(header.array());               // Convertendo o cabeçalho para um array de bytes
        this.output.flush();
    }
}
