import java.util.Scanner;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.rmi.server.Operation;
import com.google.protobuf.InvalidProtocolBufferException;

public class Main {

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
        }
        return Methods.empty;
    }

    /**
     * Realiza o tratamento da escolha de opção do usuário, criando o objeto Movie que será utilizado pela Requisição.
     * 
     * @param builder - Movies.Movie.Builder
     * @param operation - Opção escolhida pelo usuário
     * @param reader - Objeto responsável pelo I/O
     * @return Movies.Movie
     */
    public static Movies.Movie handleOption(Movies.Movie.Builder builder, int operation, Scanner reader) {
        String movieName = "";
        String cast = "";
        String genre = "";
        Movies.Movie movie;

        switch (operation) {
            case 1: // CREATE
            case 4: // UPDATE
                System.out.println("Digite o nome do filme:");
                movieName = reader.nextLine();
                builder.setTitle(movieName);

                System.out.println("Digite o gênero do filme:");
                genre = reader.nextLine();
                builder.addGenres(genre);
                break;

            case 2: // READ
            case 3: // DELETE
                System.out.println("Digite o nome do filme:");
                movieName = reader.nextLine();
                builder.setTitle(movieName);
                break;

            case 5: // TODO: FIND BY CAST (adicionar na estrutura protobuf)
                System.out.println("Oops! Opção indisponível no momento.");
                // System.out.println("Digite o nome de um membro do elenco:");
                // cast = reader.nextLine();
                // builder.addCast(cast);
                break;

            case 6: // FIND BY GENRES
                System.out.println("Digite o gênero do filme:");
                genre = reader.nextLine();
                builder.addGenres(genre);
                break;
        }

        movie = builder.build();
        return movie;
    }

    public static void main(String[] args) {
        
        int choise = -1;
        Scanner reader = new Scanner(System.in);
        String currentOperation = Methods.empty;
        Movies.Movie.Builder builder;

        System.out.println("BEM-VINDO AO PROTO FILMES!");

        while (true) {
            System.out.print("""
                DIGITE O NÚMERO DA OPÇÃO DESEJADA:
                1. CADASTRAR UM FILME
                2. CONSULTAR UM FILME PELO TÍTULO
                3. DELETAR UM FILME PELO TÍTULO
                4. ATUALIZAR AS INFORMAÇÔES DE UM FILME
                5. LISTAR OS FILMES COM UM ATOR
                6. LISTAR OS FILMES DE UM GÊNERO
                """);

            choise = Integer.parseInt(reader.nextLine());

            // Reinicializando o builder a cada iteração do laço
            builder = Movies.Movie.newBuilder();

            Movies.Movie movie = handleOption(builder, choise, reader);
            currentOperation = conversor(choise);

            // Criando uma nova instância de Requisição usando o Builder
            Movies.Request request = Movies.Request.newBuilder()
                    .setMethod(currentOperation)
                    .setMovie(movie)
                    .build();

            // Serializando a instância de Requisição para um byte array
            byte[] movieBytes = request.toByteArray();

            try (Socket socket = new Socket("localhost", 8080);
                    OutputStream outputStream = socket.getOutputStream();
                    DataOutputStream dataOutputStream = new DataOutputStream(outputStream)) {

                // Enviando o tamanho do byte array primeiro
                dataOutputStream.writeInt(movieBytes.length);

                // Enviando o byte array
                dataOutputStream.write(movieBytes);

                System.out.println("Movie bytes sent to server.");

            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                // Parsing the Movie instance from a byte array
                Movies.Request parsedMovie = Movies.Request.parseFrom(movieBytes);

                // Accessing fields from the parsed Movie instance
                // System.out.println("Parsed Movie Title: " + parsedMovie.getId());
                // System.out.println("Parsed Movie ID: " + parsedMovie.getValue());
            } catch (InvalidProtocolBufferException e) {
                e.printStackTrace();
            }
            System.out.println("\n\n\n");
        }
    }

}
