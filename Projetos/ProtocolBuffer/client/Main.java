/*
 * # Autores: Christofer Daniel, Diogo Rodrigues e Gustavo Martins
 Data: 14/08/2024

Este código é a classe principal que implementa a interface CLI do sistema de consulta de filmes
* Esse código usa protobuffs para implementar a conversão de mensagens entre o client e o server
* 
*/


import java.util.Scanner;
import java.io.OutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.rmi.server.Operation;
import com.google.protobuf.InvalidProtocolBufferException;

public class Main {
    /**
    *Método usado para conver a opção escolhida pelo usuário de opção numerica para textual
    */
    public static String conversor(int op) {
        switch (op) {
            case 1:
                return Methods.create;
            case 2:
                return Methods.read;
            case 3:
                return Methods.delete;
            case 4:
                return Methods.update;
            case 5:
                return Methods.findByCast;
            case 6:
                return Methods.findByGenres;
            case 7:
                return Methods.exit;
        }
        return Methods.empty;
    }

    /**
     * Realiza o tratamento da escolha de opção do usuário, criando o objeto Movie
     * que será utilizado pela Requisição.
     * 
     * @param movieBuilder - Movies.Movie.Builder
     * @param movieFilterBuilder - Movies.MovieFilters.Builder
     * @param operation    - Opção escolhida pelo usuário
     * @param reader       - Objeto responsável pelo I/O
     */
    public static void handleOption(Movies.Movie.Builder movieBuilder, Movies.MovieFilters.Builder movieFilterBuilder, int operation, Scanner reader) {

        String movieID = "";
        String movieName = "";
        String directors = "";
        String plot = "";
        String cast = "";
        String genre = "";

        switch (operation) {
            case 1: // CREATE
                //Na opção 1 é criado um filme, cada informação do filme vai sendo solicitada e completado com obuilder
                // Solicita o nome do filme e define no movieBuilder
                System.out.println("Digite o nome do filme:");
                movieName = reader.nextLine();
                movieBuilder.setTitle(movieName);

                // Solicita os diretores do filme e adiciona no movieBuilder
                System.out.println("Digite os diretores do filme:");
                directors = reader.nextLine();
                movieBuilder.addDirectors(directors);

                // Solicita o gênero do filme e adiciona no movieBuilder
                System.out.println("Digite o gênero do filme:");
                genre = reader.nextLine();
                movieBuilder.addGenres(genre);

                // Solicita o nome de um membro do elenco e adiciona no movieBuilder
                System.out.println("Digite o nome de um membro do elenco:");
                cast = reader.nextLine();
                movieBuilder.addCast(cast);

                // Solicita a sinopse do filme e define no movieBuilder
                System.out.println("Digite a sinopse do filme:");
                plot = reader.nextLine();
                movieBuilder.setPlot(plot);

                break;

            case 2: // READ: Busca um filme na base de dados
                // Solicita o ID do filme e define no movieBuilder
                System.out.println("Digite o ID do filme:");
                movieID = reader.nextLine();
                movieBuilder.setId(movieID);
                break;

            case 3: // DELETE: Deleta um filme na base de dados
                // Solicita o ID do filme e define no movieBuilder
                System.out.println("Digite o ID do filme:");
                movieID = reader.nextLine();
                movieBuilder.setId(movieID);
                break;

            case 4: // UPDATE: Atualiza um filme na base de dados
                // Solicita o ID do filme e define no movieBuilder
                System.out.println("Digite o ID do filme:");
                movieID = reader.nextLine();
                movieBuilder.setId(movieID);

                // Solicita o nome do filme e define no movieBuilder
                System.out.println("Digite o nome do filme:");
                movieName = reader.nextLine();
                movieBuilder.setTitle(movieName);

                // Solicita os diretores do filme e adiciona no movieBuilder
                System.out.println("Digite os diretores do filme:");
                directors = reader.nextLine();
                movieBuilder.addDirectors(directors);

                // Solicita o gênero do filme e adiciona no movieBuilder
                System.out.println("Digite o gênero do filme:");
                genre = reader.nextLine();
                movieBuilder.addGenres(genre);

                // Solicita o nome de um membro do elenco e adiciona no movieBuilder
                System.out.println("Digite o nome de um membro do elenco:");
                cast = reader.nextLine();
                movieBuilder.addCast(cast);

                // Solicita a sinopse do filme e define no movieBuilder
                System.out.println("Digite a sinopse do filme:");
                plot = reader.nextLine();
                movieBuilder.setPlot(plot);
                break;

            case 5: // FIND BY CAST: Encontra um filme usando o elenco
                // Solicita os nomes dos membros do elenco e divide-os em um array    
                System.out.println("Digite os nomes dos membros do elenco (separados por vírgula):");
                String castInput = reader.nextLine();

                // Regex que lida com ", " ou ","
                String[] castArray = castInput.split(",\\s*");
                // Regex que lida com ", " ou ","
                
                // Iterando sobre cada substring e passando-a para o método addValues
                for (String castItem : castArray) {
                    movieFilterBuilder.addValues(castItem.trim());
                }
                break;

            case 6: // FIND BY GENRES: Encontra um filme usando genero
                // Solicita o gênero do filme e divide-os em um array    
                System.out.println("Digite o gênero do filme:");
                String genreInput = reader.nextLine();
                
                // Regex que lida com ", " ou ","
                String[] genreArray = genreInput.split(",\\s*");
                
                // Iterando sobre cada substring e passando-a para o método addValues
                for (String genreItem : genreArray) {
                    movieFilterBuilder.addValues(genreItem.trim());
                }
                break;
        }
    }

    public static void main(String[] args) {

        int choise = -1;
        Scanner reader = new Scanner(System.in);
        String currentMethod = Methods.empty;
        Movies.Movie.Builder movieBuilder;
        Movies.MovieFilters.Builder movieFilterBuilder;

        Socket socket;
        OutputStream outputStream;
        DataOutputStream dataOutputStream;
        InputStream inputStream;
        DataInputStream dataInputStream;

        System.out.println("\nBEM-VINDO AO PROTO FILMES!\n");

        try {
            // Cria um socket para se conectar ao servidor na porta 8080 do localhost
            socket = new Socket("localhost", 8080);
             // Obtém o OutputStream do socket para enviar dados ao servidor
            outputStream = socket.getOutputStream();
            dataOutputStream = new DataOutputStream(outputStream);
            // Obtém o InputStream do socket para receber dados do servidor
            inputStream = socket.getInputStream();
            dataInputStream = new DataInputStream(inputStream);

            // Loop infinito para manter a interação com o usuário
            while (true) {
                // Exibe o menu de opções para o usuário
                System.out.print("""
                        Digite o número da opção desejada:
                        ---------------------------------------
                        1. Cadastrar um filme
                        2. Consultar um filme
                        3. Deletar um filme
                        4. Atualizar as informações de um filme
                        5. Listar os filmes de um ator ou atriz
                        6. Listar os filmes de um gênero
                        7. Sair
                        ---------------------------------------
                        """);

                // Lê a escolha do usuário e converte para um inteiro
                choise = Integer.parseInt(reader.nextLine());

                // Reinicializando o builder a cada iteração do laço
                movieBuilder = Movies.Movie.newBuilder();
                movieFilterBuilder = Movies.MovieFilters.newBuilder();

                //converte a escolha para o metodo
                currentMethod = conversor(choise);

                if (currentMethod == Methods.exit) {
                    dataInputStream.close();
                    dataOutputStream.close();
                    socket.close();
                    System.out.println("PROTO FILMES FINALIZADO.");
                    break;
                }
                
                // Chama o método handleOption para processar a escolha do usuário
                // Passa os builders de filme e filtro, a escolha do usuário e o leitor de entrada
                handleOption(movieBuilder, movieFilterBuilder, choise, reader);
                
                // Constrói o objeto de filtro de filmes a partir do builder
                Movies.MovieFilters filter = movieFilterBuilder.build();
                // Constrói o objeto de filme a partir do builder
                Movies.Movie movie = movieBuilder.build();
                
                // Criando uma nova instância de Requisição usando o Builder
                Movies.Request request = Movies.Request.newBuilder()
                        .setMethod(currentMethod)
                        .setMovie(movie)
                        .setFilters(filter)
                        .build();

                // Serializando a instância de Requisição para um byte array
                byte[] movieBytes = request.toByteArray();

                // Enviando requisição
                // Enviando o tamanho do byte array primeiro
                dataOutputStream.writeInt(movieBytes.length);

                // Enviando o byte array
                dataOutputStream.write(movieBytes);
                System.out.println("\nDados enviados ao servidor.");

                // Recebendo resposta
                System.out.println("Aguardando resposta do servidor.");

                // Lendo a quantidade de bytes da resposta
                String sizeString = dataInputStream.readLine();
                int responseSize = Integer.parseInt(sizeString);
                System.out.println("Tamanho da resposta em bytes: " + responseSize);

                // Lendo o conteúdo da resposta do socket
                byte[] buffer = new byte[responseSize];
                dataInputStream.read(buffer);

                // Desserialização da resposta através do método gerado pelo proto
                Movies.Response parsedResponse = Movies.Response.parseFrom(buffer);
                System.out.println("\nResposta: " + parsedResponse);
            }
        } catch (Exception e) {
            System.out.println("[Erro]: ");
            e.printStackTrace();
        }
    }

}
