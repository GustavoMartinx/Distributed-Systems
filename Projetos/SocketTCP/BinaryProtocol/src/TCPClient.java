/**
 * TCPClient: Cliente que conecta-se ao servidor através de um socket
 * para manipular arquivos no servidor com operações de leitura e escrita.
 * Descricao: A comunicação entre as entidades ocorre por meio de um
 * protocolo. O protocolo define as requisições do cliente ao servidor
 * como tipo de mensagem, identificador do comando, tamanho do nome do
 * arquivo e o nome do arquivo. As operações de leitura e escrita são
 * ADDFILE, DELETE, GETFILESLIST e GETFILE.
 * 
 * Autores: Diogo Rodrigues dos Santos e Gustavo Zanzin Guerreiro Martins
 * 
 * Data de criação: 07/04/2024
 * 
 * Datas de atualização: 11/04, 12/04, 14/04, 18/04
**/

import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TCPClient {
    public static void main(String args[]) {
        Socket clientSocket = null;               // Socket do cliente
        Scanner reader = new Scanner(System.in);  // Objeto para ler mensagens via teclado

        try {
            // Obtendo o endereço IP e a porta do servidor
            int serverPort = 6666;
            InetAddress serverAddr = InetAddress.getByName("127.0.0.1");

            // Conectando-se com o servidor
            clientSocket = new Socket(serverAddr, serverPort);

            // Criando objetos de leitura e escrita
            DataInputStream in = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());

            // Implementando o protocolo de comunicação
            String buffer = "";
            while (true) {

                System.out.print("$ ");
                buffer = reader.nextLine(); // lê mensagem via teclado

                boolean validCommand = handleCommand(buffer, output);
                if (!validCommand) {
                    continue;
                }

                // Response handler (TODO: modularizar)
                byte[] headerBytes = new byte[258];
                in.read(headerBytes);
                ByteBuffer headerBuffer = ByteBuffer.wrap(headerBytes);
                headerBuffer.order(ByteOrder.BIG_ENDIAN);
                byte messageType = headerBuffer.get();
                byte commandId = headerBuffer.get();

                // Verificando se o tipo da mensagem é uma resposta
                if(messageType == 0x02) {
                    byte statusCode = headerBuffer.get();
                    
                    // Verificando a qual comando o servidor está respondendo
                    switch(commandId){
                        case 0x01:
                            handleAddFileResponse(statusCode);
                            break;
                        case 0x02:
                            handleDeleteFileResponse(statusCode);
                            break;
                        case 0x03:
                            handleGetFilesListResponse(headerBuffer, statusCode);
                            break;
                        case 0x04:
                            handleGetFileResponse(headerBuffer, statusCode);
                            break;
                    }
                }
            } // while

        } catch (UnknownHostException ue) {
            System.out.println("Socket:" + ue.getMessage());
        } catch (EOFException eofe) {
            System.out.println("EOF:" + eofe.getMessage());
        } catch (IOException ioe) {
            System.out.println("IO:" + ioe.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException ioe) {
                System.out.println("IO: " + ioe);
            } // catch
        } // finally
    } // main


    /**
     * Método para tratar os comandos de ADDFILE, DELETE, GETFILESLIST e GETFILE.
     * 
     * @param command - Comando a ser tratado.
     * @param output - Stream de saída.
     * @throws IOException
     */
    static boolean handleCommand(String command, DataOutputStream output) throws IOException {
        String[] splitedCommand = command.split(" ");
        
        String filename = "";
        byte commandIdentifier;
        
        boolean validCommand = true;
        boolean invalidCommand = false;

        if (splitedCommand[0].equals("ADDFILE") && splitedCommand.length == 2) {
            filename = splitedCommand[1];
            commandIdentifier = (byte) 1;
            executeAddFile(commandIdentifier, output, filename);
            return validCommand;

        } else if (splitedCommand[0].equals("DELETE") && splitedCommand.length == 2) {
            filename = splitedCommand[1];
            commandIdentifier = (byte) 2;
            executeDeleteFile(commandIdentifier, output, filename);
            return validCommand;

        } else if (splitedCommand[0].equals("GETFILESLIST") && splitedCommand.length == 1) {
            commandIdentifier = (byte) 3;
            executeGetFilesList(commandIdentifier, output);
            return validCommand;

        } else if (splitedCommand[0].equals("GETFILE") && splitedCommand.length == 2) {
            filename = splitedCommand[1];
            // TODO: criar verificação de arquivo a ser baixado já existente ou não
            commandIdentifier = (byte) 4;            
            executeGetFile(commandIdentifier, output, filename);
            return validCommand;

        } else {
            System.out.println("Command not found.");
            return invalidCommand;
        }
    } // handleCommand


    /**
     * Método para executar o comando ADDFILE.
     * 
     * O comando ADDFILE transmitirá um arquivo para o servidor. Caso o arquivo já exista localmente, o cliente enviará
     * não só o cabeçalho da requisição, mas também o conteúdo do arquivo. Caso o arquivo não exista, o cliente enviará
     * apenas o cabeçalho da requisição.
     * 
     * 
     * @param commandIdentifier - Identificador do comando ADDFILE (0x01).
     * @param output - Objeto de escrita.
     * @param filename - Nome do arquivo a ser transmitido.
     * @throws IOException
     */
    private static void executeAddFile(byte commandIdentifier, DataOutputStream output, String filename) throws IOException {
        
        byte sizeOfFilename = (byte) filename.length();  // Tamanho do nome do arquivo em bytes
        byte[] filenameBytes = filename.getBytes();      // Nome em si do arquivo convertido para bytes
        
        // Verificando se o arquivo existe para obter seu tamanho
        File fileInCurrentDir = new File(System.getProperty("user.dir") + "/" + filename);
        long fileSize = fileInCurrentDir.exists() ? fileInCurrentDir.length() : 0;
        int fileSizeInt = (int) fileSize;

        // Criação do cabeçalho
        ByteBuffer header = createAddFileHeader(commandIdentifier, sizeOfFilename, filenameBytes, fileSizeInt);
        byte[] headerBytes = getHeaderBytes(header);

        // Envio do cabeçalho e, se o arquivo existir, envio do conteúdo do arquivo
        if (fileInCurrentDir.exists()) {
            try (FileInputStream fis = new FileInputStream(fileInCurrentDir)) {
                writeHeaderAndFileContent(output, headerBytes, fis);
            }
        } else {
            // Se o arquivo não existir, apenas o cabeçalho é enviado
            System.out.println("File not found: " + fileInCurrentDir);
            output.write(headerBytes);
        }

        output.flush();
    } // executeAddFile


    /**
     * Método para executar o comando DELETE. O comando DELETE deletará um arquivo do servidor.
     * 
     * @param commandIdentifier - Identificador do comando DELETE (0x02).
     * @param output - Objeto de escrita.
     * @param filename - Nome do arquivo a ser deletado.
     * @throws IOException
     * 
    **/
    private static void executeDeleteFile(byte commandIdentifier, DataOutputStream output, String filename) throws IOException {

        byte sizeOfFilename = (byte) filename.length();  // Tamanho do nome do arquivo em bytes
        byte[] filenameBytes = filename.getBytes();      // Nome em si do arquivo convertido para bytes
        
        // Criação do cabeçalho
        ByteBuffer header = createHeader(commandIdentifier, sizeOfFilename, filenameBytes);
        byte[] headerBytes = getHeaderBytes(header);      // Converte o cabeçalho para bytes
        
        // Envio para o servidor do cabeçalho de requisição para deletar um arquivo
        output.write(headerBytes);
        output.flush();
    } // executeDeleteFile

    /**
     * Método para executar o comando GETFILESLIST. O comando GETFILESLIST envia uma requisição para o servidor
     * para obter a lista de arquivos do diretório 'Documents'.
     * 
     * @param commandIdentifier - Identificador do comando GETFILESLIST (0x03).
     * @param output - Objeto de escrita.
     * @throws IOException
    */
    private static void executeGetFilesList(byte commandIdentifier, DataOutputStream output) throws IOException {

        // Criação do cabeçalho
        ByteBuffer header = createHeader(commandIdentifier, (byte) 0, new byte[0]);
        byte[] headerBytes = getHeaderBytes(header);      // Converte o cabeçalho para bytes
        
        // Envio para o servidor do cabeçalho de requisição para obter a lista de arquivos do diretório 'Documents'
        output.write(headerBytes);
        output.flush();
    }

    /**
     * Método para executar o comando GETFILE. O comando GETFILE envia uma requisição para o servidor
     * para fazer o download de um arquivo.
     * 
     * @param commandIdentifier - Identificador do comando GETFILE (0x04).
     * @param output - Objeto de escrita.
     * @param filename - Nome do arquivo a ser transmitido.
     * @throws IOException
    */
    private static void executeGetFile(byte commandIdentifier, DataOutputStream output, String filename) throws IOException {

        byte sizeOfFilename = (byte) filename.length();  // Tamanho do nome do arquivo em bytes
        byte[] filenameBytes = filename.getBytes();      // Nome em si do arquivo convertido para bytes
        
        // Criação do cabeçalho
        ByteBuffer header = createHeader(commandIdentifier, sizeOfFilename, filenameBytes);
        byte[] headerBytes = getHeaderBytes(header);      // Converte o cabeçalho para bytes
        
        // Envio para o servidor do cabeçalho de requisição para fazer o download de um arquivo
        output.write(headerBytes);
        output.flush();
    } // executeGetFile


    /**
     * Método para criar o cabeçalho de uma requisição comum.
     * 
     * @param commandIdentifier - Identificador do comando.
     * @param sizeOfFilename - Tamanho do nome do arquivo.
     * @param filenameBytes - Nome do arquivo em bytes.
     * @return - Cabeçalho (ByteBuffer).
    **/
    private static ByteBuffer createHeader(byte commandIdentifier, byte sizeOfFilename, byte[] filenameBytes) {
        ByteBuffer header = ByteBuffer.allocate(258);
        header.order(ByteOrder.BIG_ENDIAN);
        header.put((byte) 1);               // Message Type (1 == Request)
        header.put(commandIdentifier);
        header.put(sizeOfFilename);
        header.put(filenameBytes);
        return header;
    }

    /**
     * Método para criar o cabeçalho de uma requisição ADDFILE.
     * 
     * @param commandIdentifier - Identificador do comando.
     * @param sizeOfFilename - Tamanho do nome do arquivo.
     * @param filenameBytes - Nome do arquivo em bytes.
     * @param fileSizeInt - Tamanho do arquivo em bytes.
     * @return - Cabeçalho (ByteBuffer).
     */
    private static ByteBuffer createAddFileHeader(byte commandIdentifier, byte sizeOfFilename, byte[] filenameBytes, int fileSizeInt) {
        ByteBuffer header = ByteBuffer.allocate(262);
        header.order(ByteOrder.BIG_ENDIAN);
        header.put((byte) 1);               // Message Type (1 == Request)
        header.put(commandIdentifier);
        header.put(sizeOfFilename);
        header.put(filenameBytes);
        header.putInt(fileSizeInt);
        return header;
    } // createAddFileHeader
    
    /**
     * Método para obter o array de bytes do cabeçalho. Note que o array de bytes retornado tem o tamanho
     * exato do cabeçalho. Portanto, não é necessário especificar um comprimento ao escrever o cabeçalho
     * em output, pois o array headerBytes já contém apenas os bytes do cabeçalho, sem bytes extras.
     * 
     * @param header - Cabeçalho.
     * @return - Array de bytes do cabeçalho.
    */
    private static byte[] getHeaderBytes(ByteBuffer header) {
        int headerSize = header.position();
        return Arrays.copyOf(header.array(), headerSize);
    }

    /**
     * Método para enviar, byte a byte, o cabeçalho e o conteúdo de um arquivo ao servidor.
     * 
     * @param output - Stream de saída.
     * @param headerBytes - Cabeçalho em bytes.
     * @param fis - Stream de entrada que fará a leitura do conteúdo do arquivo a ser enviado.
     * @throws IOException
     */
    private static void writeHeaderAndFileContent(DataOutputStream output, byte[] headerBytes, FileInputStream fis) throws IOException {
        output.write(headerBytes);
        int byteReaded;
        while ((byteReaded = fis.read()) != -1) {
            output.write(byteReaded);
        }
    }

    
    /**
     * Método para tratar a resposta do servidor ao upload de um arquivo (comando ADDFILE).
     *
     * @param  statusCode - Resultado da operação de upload do arquivo.
     * @throws IOException
     */
    private static void handleAddFileResponse(byte statusCode) throws IOException{
        
        if(statusCode == 0x01) {
            System.out.println("Status: " + statusCode + " - File uploded successfully!");
        } else {
            System.out.println("Status: " + statusCode + " - Something went wrong when uploading the file.");
        }
    } // handleAddFileResponse

    /**
     * Método para tratar a resposta do servidor ao deletar um arquivo (comando DELETE).
     * 
     * @param statusCode - Resultado da operação de deletar um arquivo.
     * @throws IOException
     */
    private static void handleDeleteFileResponse(byte statusCode) throws IOException {
        
        if (statusCode == 0x01) {
            System.out.println("Status: " + statusCode + " - File deleted successfully!");
        } else {
            System.out.println("Status: " + statusCode + " - Something went wrong when deleting the file.");
        }
    } // handleDeleteFileResponse

    private static void handleGetFilesListResponse(ByteBuffer header, byte statusCode) throws IOException {

        if (statusCode == 0x01) {

            // Lendo o número total de arquivos
            short totalFiles = header.getShort();

            // Lendo os nomes dos arquivos
            List<String> fileNames = new ArrayList<>();
            for (int i = 0; i < totalFiles; i++) {

                byte nameLength = header.get();
                byte[] nameBytes = new byte[nameLength];
                header.get(nameBytes);
                String fileName = new String(nameBytes);
                fileNames.add(fileName);
            }

            // Exibindo os nomes dos arquivos
            for (String fileName : fileNames) {
                System.out.println(fileName);
            }

        } else {
            System.out.println("Status: " + statusCode + " - There are no files in the 'Documents' directory.");
        }    
    } // handleGetFilesListResponse

    private static void handleGetFileResponse(ByteBuffer header, byte statusCode) throws IOException {

        if (statusCode == 0x01) {

            // BUG: verificar por que o cabeçalho não chega aqui com o conteúdo completo
            
            // String headerString = new String(header.array(), StandardCharsets.UTF_8);
            // System.out.println("Conteúdo do header: " + headerString);

            // header.position() é 3, pois os 3 primeiros bytes são lidos antes da chamada deste método
            System.out.println("\nInicio\nheader.position()");
            System.out.println(header.position() + "\n");

            // Para acessar posições específicas do ByteBuffer header
            int offset = 0;
            int length = 0;

            // Obtendo do cabeçalho o tamanho do nome do arquivo
            byte nameLength = 0;
            nameLength = header.get();

            System.out.println("Tamanho do File Name (bytes):");
            System.out.println(nameLength + "\n"); // 14 é o certo

            System.out.println("header.position()");
            System.out.println(header.position() + "\n"); // 4 é o certo
            
            
            // Obtendo do cabeçalho o nome em si do arquivo
            byte[] nameBytes = new byte[nameLength];
            // offset = 0;
            length = (int) nameLength;

            header.get(nameBytes, offset, length);
            String fileName = new String(nameBytes);
            System.out.println("File name:");
            System.out.println(fileName + "\n");

            // Obtendo do cabeçalho o tamanho do conteúdo do arquivo
            // byte[] fileSizeBytes = new byte[4];
            // header.position(3); // Acessando a posição dos 4 bytes que representam o tamanho do arquivo no cabeçalho
            // header.get(fileSizeBytes);
            System.out.println("header.position()");
            System.out.println(header.position() + "\n"); // 18

            int fileSize = header.getInt();

            System.out.println("-> File Size:");
            System.out.println(fileSize + "\n");

            System.out.println("header.position() depois getInt file content size:");
            System.out.println(header.position() + "\n"); // tem q ser 22

            // Obtendo do cabeçalho o conteúdo do arquivo
            byte[] fileContentBytes = new byte[fileSize];
            length = fileSize;
            header.get(fileContentBytes, offset, length);
            String fileContentString = new String(fileContentBytes);
            
            // Cria o diretório "Downloads" se não existir
            String downloadPath = System.getProperty("user.dir") + "/Downloads/";
            File dir = new File(downloadPath);
            if (!dir.exists()) {
                dir.mkdir();
            }
            
            // Cria o arquivo com o conteúdo lido na pasta "Downloads"
            File downloadedFile = new File(downloadPath + fileName);
            downloadedFile.createNewFile();

            // Escrevendo o conteúdo do arquivo
            FileWriter writer = new FileWriter(downloadedFile, true);
            BufferedWriter buf = new BufferedWriter(writer);
            buf.write(fileContentString);
            buf.flush();
            buf.close();

            System.out.println("Status code: " + statusCode + " - File '" + fileName + "' downloaded successfully!");
            
        } else {
            System.out.println("Status code: " + statusCode + " - Something went wrong when getting the file.");
        }
    }

} // class
