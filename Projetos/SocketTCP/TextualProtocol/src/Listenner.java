package src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class Listenner extends Thread {

    Socket clientSocket;
    DataInputStream in;
    DataOutputStream out;

    User user;
    User[] users;

    Path home;
    Path userPath;

    Commands commands;
    Directory dirController;

    public Listenner(Socket clientSocket, User[] users) throws IOException {
        this.users = users;
        this.clientSocket = clientSocket;

        this.in = new DataInputStream(clientSocket.getInputStream());
        this.out = new DataOutputStream(clientSocket.getOutputStream());

        this.commands = new Commands();
        this.dirController = new Directory();
        this.home = FileSystems.getDefault().getPath("src/users", "");
    }

    @Override
    public void run() {

        try {
            System.out.println("[Thread] Fluxo de execução paralelo criado com sucesso!");
            this.awaitConnection();
            this.listenCommands();
        } catch (EOFException eofe) {
            System.out.println("[Thread] EOF: " + eofe.getMessage());
        } catch (IOException ioe) {
            System.out.println("[Thread] IOE: " + ioe.getMessage());
        } catch (UnsupportedOperationException uoe) {
            System.out.println("[Thread] UOE: " + uoe.getMessage());
        } catch (Exception e) {

            System.out.println("[Thread] DEFAULT: " + e.getMessage());
        } finally {
            try {
                in.close();
                out.close();
                clientSocket.close();
            } catch (IOException ioe) {
                System.err.println("[Thread] IOE: " + ioe);
            }
        }

        System.out.println("[Thread] Comunicação com o cliente finalizada.");
    }

    /**
     * Aguarda um loggin de um cliente e verifica se ele está autorizado.
     * A conexão é realizada através do comando "CONNECT" seguido do nome de
     * usuriario e senha.
     *
     * @throws IOException caso ocorra um erro de entrada ou saída
     */
    protected void awaitConnection() throws IOException {
        String buffer = "";
        boolean connected = false;

        while (!connected) {

            // Lendo o buffer de entrada
            buffer = in.readUTF();
            String[] cmdParams = buffer.split(" ");

            // Validando o comando e os parâmetros
            if (cmdParams.length == 3 && cmdParams[0].equals(this.commands.connect)) {
                
                // Extraindo os parâmetros
                String username = cmdParams[1];
                String password = cmdParams[2];

                // Tentando a conexão
                User user = this.getUserByName(username);
                boolean successOnConnect = this.connectUser(user, password);

                if (successOnConnect) {
                    connected = true;
                    continue;
                }

                // Caso não consiga conectar, envia mensagem de erro
                out.writeUTF("[Thread] Credenciais de usuário incorretas.");
                continue;
            }

            // Caso o comando não seja reconhecido, enviar mensagem de erro
            out.writeUTF(this.commands.error + " " + "Comando não encontrado\n");
        }
    }

    /**
     * Tenta conectar um usuário com base nas credenciais informadas.
     * Caso as credenciais sejam válidas, atualiza o caminho atual e o caminho
     * do usuário e retorna true. Caso contrário, retorna false.
     *
     * @param user Usuário a ser conectado
     * @param password Senha do usuário
     * @return true se a conexão for bem-sucedida, false caso contrário
     * @throws IOException caso ocorra um erro de entrada ou saída
     */
    protected boolean connectUser(User user, String password) throws IOException {
        if (user == null) {
            return false;
        }

        boolean passwordIsCorrect = this.checkUserPassword(password, user.password);

        if (!passwordIsCorrect) {
            return false;
        }

        // Atualizando o caminho atual e o caminho do usuário
        File file = this.dirController.mkdir(this.home.toString(), user.user);
        this.home = file.toPath();
        this.userPath = this.home;

        System.out.format("[Thread] Usuário %s conectado com sucesso!\n", user.user);
        out.writeUTF(this.commands.success + " " + this.home);
        return true;
    }

    /**
     * Aguarda os comandos do cliente e executa a ação correspondente.
     * O loop de execução é interrompido caso o cliente envie o comando
     * "EXIT" ou caso uma exceção seja lançada.
     * 
     * @throws IOException caso ocorra um erro de entrada ou saída
     */
    protected void listenCommands() throws IOException {
        boolean keepConnection = true;

        while (keepConnection) {
            String buffer = "";
            
            // Lendo o buffer de entrada
            buffer = in.readUTF();

            // Dividindo o buffer em tokens
            String[] cmdParams = buffer.split(" ");
            keepConnection = this.handleCommands(buffer, cmdParams);
        }
    }

    /**
     * Manipula os comandos recebidos do cliente e executa as ações
     * correspondentes. O loop de execução é interrompido caso o cliente
     * envie o comando "EXIT" ou caso uma exceção seja lançada.
     * 
     * @param buffer Buffer de entrada com o comando a ser executado
     * @param cmdParams Array com os parâmetros do comando
     * @return true se o comando foi executado com sucesso, false caso contrário.
     * @throws IOException caso ocorra um erro de entrada ou saída
     */
    protected boolean handleCommands(String buffer, String[] cmdParams) throws IOException {
        boolean keepConnection = true;
        String cmd = cmdParams[0];

        if (cmd.equals(this.commands.pwd)) {
            System.out.println("---> Executing PWD command ...");
            this.executePwd();
            System.out.println("<--- PWD executed ...");
            return keepConnection;
        }

        if (cmd.equals(this.commands.chdir)) {
            System.out.println("---> Executing CHDIR command ...");
            this.executedChdir(cmdParams[1]);
            System.out.println("<--- CHDIR executed ...");
            return keepConnection;
        }

        if (cmd.equals(this.commands.getFiles)) {
            System.out.println("---> Executing GETFILES command ...");
            this.executeGetFiles();
            System.out.println("<--- GETFILES executed ...");
            return keepConnection;
        }

        if (cmd.equals(this.commands.getDirs)) {
            System.out.println("---> Executing GETDIRS command ...");
            executeGetDirs();
            System.out.println("<--- GETDIRS executed ...");
            return keepConnection;
        }

        if (cmd.equals(this.commands.mkdir)) {
            System.out.println("---> Executing MKDIR command ...");
            this.executeMkdir(cmdParams[1]);
            System.out.println("<--- MKDIR executed ...");
            return keepConnection;
        }

        if (cmd.equals(this.commands.touch)) {
            System.out.println("---> Executing TOUCH command ...");
            this.executeTouch(cmdParams[1]);
            System.out.println("<--- TOUCH executed ...");
            return keepConnection;
        }

        if (buffer.equals(this.commands.exit)) {
            System.out.println("---> Executing EXIT command ...");
            out.writeUTF(this.commands.exit);
            System.out.println("<--- EXIT executed ...");
            return !keepConnection;
        }

        out.writeUTF("[Thread] Command not found.\n");
        return keepConnection;
    }

    protected boolean checkUserPassword(String password, String passoword2) {
        return password.equals(passoword2);
    }

    protected User getUserByName(String name) {
        User user = null;

        for (int i = 0; i < 2; i++)
            if (this.users[i].user.equals(name))
                user = this.users[i];

        return user;
    }

    protected void executePwd() throws IOException {
        out.writeUTF(this.home.toString() + " \n");
    }

    protected void executedChdir(String dirName) throws IOException {
        Path path = this.dirController.chdir(this.home, dirName, this.userPath);
        boolean pathIsValid = !(path == null);

        if (pathIsValid) {
            this.home = path;
            out.writeUTF(this.commands.chdir + " " + path);
        } else {
            out.writeUTF(this.commands.error + " \n");
        }
    }

    protected void executeTouch(String name) throws IOException {
        this.dirController.touch(this.home.toString(), name);
        out.writeUTF(this.commands.success);
    }

    protected void executeMkdir(String dirName) throws IOException {
        this.dirController.mkdir(home.toString(), dirName);
        out.writeUTF(this.commands.success);
    }

    protected void executeGetDirs() throws IOException, UnsupportedOperationException {
        File[] files = this.convertPathToFileList(this.home);
        String response = this.dirController.getDirs(files); // get only directories
        out.writeUTF(response);
    }

    protected void executeGetFiles() throws IOException {
        File[] files = this.convertPathToFileList(this.home);
        String response = this.dirController.getFiles(files); // get only files
        out.writeUTF(response);
    }

    protected File[] convertPathToFileList(Path path) {
        File file = path.toFile();
        return file.listFiles();
    }
} // class
