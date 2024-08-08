import java.util.Scanner;
import java.io.OutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
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
     * @param movieBuilder - Movies.Movie.Builder
     * @param operation - Opção escolhida pelo usuário
     * @param reader - Objeto responsável pelo I/O
     * @return Movies.Movie
     */
    public static Movies.Movie handleOption(Movies.Movie.Builder movieBuilder, int operation, Scanner reader) {
        String movieID = "";
        String movieName = "";
        String directors = "";
        String plot = "";
        String cast = "";
        String genre = "";
        Movies.Movie movie;

        switch (operation) {
            case 1: // CREATE
                System.out.println("Digite o nome do filme:");
                movieName = reader.nextLine();
                movieBuilder.setTitle(movieName);

                System.out.println("Digite os diretores do filme:");
                directors = reader.nextLine();
                movieBuilder.addDirectors(directors);
                
                System.out.println("Digite o gênero do filme:");
                genre = reader.nextLine();
                movieBuilder.addGenres(genre);
                
                System.out.println("Digite o nome de um membro do elenco:");
                cast = reader.nextLine();
                movieBuilder.addCast(cast);
                break;

            case 2: // READ # TODO: passar nome ou ID?
                System.out.println("Digite o nome do filme:");
                movieName = reader.nextLine();
                movieBuilder.setTitle(movieName);

                // System.out.println("Digite o ID do filme:");
                // movieID = reader.nextLine();
                // movieBuilder.setId(movieID);
                break;

            case 3: // DELETE
                System.out.println("Digite o ID do filme:");
                movieID = reader.nextLine();
                movieBuilder.setId(movieID);
                break;
                
            case 4: // UPDATE
                System.out.println("Digite o ID do filme:");
                movieID = reader.nextLine();
                movieBuilder.setId(movieID);
                
                System.out.println("Digite o nome do filme:");
                movieName = reader.nextLine();
                movieBuilder.setTitle(movieName);

                System.out.println("Digite o nome de um membro do elenco:");
                cast = reader.nextLine();
                movieBuilder.addCast(cast);

                System.out.println("Digite a sinopse do filme:");
                plot = reader.nextLine();
                movieBuilder.setPlot(plot);

                System.out.println("Digite o gênero do filme:");
                genre = reader.nextLine();
                movieBuilder.addGenres(genre);
                break;

            case 5: // FIND BY CAST
                System.out.println("Digite o nome de um membro do elenco:");
                cast = reader.nextLine();
                movieBuilder.addCast(cast);
                break;

            case 6: // FIND BY GENRES
                System.out.println("Digite o gênero do filme:");
                genre = reader.nextLine();
                movieBuilder.addGenres(genre);
                break;
        }

        movie = movieBuilder.build();
        return movie;
    }

    public static void main(String[] args) {
        
        int choise = -1;
        Scanner reader = new Scanner(System.in);
        String currentMethod = Methods.empty;
        Movies.Movie.Builder movieBuilder;
        Movies.MovieFilters.Builder movieFilterBuilder;

        System.out.println("BEM-VINDO AO PROTO FILMES!");
        try{

        Socket socket = new Socket("localhost", 8080);
        OutputStream outputStream = socket.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                
        InputStream inputStream = socket.getInputStream();
        DataInputStream dataInputStream = new DataInputStream(inputStream);
                
        while (true) {
            System.out.print("""
                DIGITE O NÚMERO DA OPÇÃO DESEJADA:
                ---------------------------------------
                1. CADASTRAR UM FILME
                2. CONSULTAR UM FILME PELO TÍTULO
                3. DELETAR UM FILME PELO TÍTULO
                4. ATUALIZAR AS INFORMAÇÔES DE UM FILME
                5. LISTAR OS FILMES COM UM ATOR
                6. LISTAR OS FILMES DE UM GÊNERO
                ---------------------------------------
                """);

            choise = Integer.parseInt(reader.nextLine());

            // Reinicializando o builder a cada iteração do laço
            movieBuilder = Movies.Movie.newBuilder();
            movieFilterBuilder = Movies.MovieFilters.newBuilder();

            Movies.Movie movie = handleOption(movieBuilder, choise, reader);
            currentMethod = conversor(choise);

            if(currentMethod == Methods.findByCast) {
                movieFilterBuilder.addValues(Methods.findByCast);
            } else if(currentMethod == Methods.findByGenres) {
                movieFilterBuilder.addValues(Methods.findByGenres);
            }
            Movies.MovieFilters filter = movieFilterBuilder.build();

            // Criando uma nova instância de Requisição usando o Builder
            Movies.Request request = Movies.Request.newBuilder()
                    .setMethod(currentMethod)
                    .setMovie(movie)
                    .setFilters(filter)
                    .build();

            // Serializando a instância de Requisição para um byte array
            byte[] movieBytes = request.toByteArray();

            try {

                // Enviando o tamanho do byte array primeiro
                dataOutputStream.writeInt(movieBytes.length);

                // Enviando o byte array
                dataOutputStream.write(movieBytes);

                System.out.println("Dados enviados ao servidor.");
                
                
                // Recebendo resposta
                System.out.println("Aguardando resposta do servidor.");

                // 
                String sizeString = dataInputStream.readLine();
                System.out.println("Read line funcionando");
                int responseSize = Integer.parseInt(sizeString);
                System.out.println("Tamanho veio");
                // 
                byte[] buffer = new byte[responseSize];
                dataInputStream.read(buffer);
                System.out.println("Response tbm ta funfando");

                // Parsing the Movie instance from a byte array
                Movies.Response parsedMovie = Movies.Response.parseFrom(buffer);

              System.out.println(parsedMovie);
                //System.out.println("teste22");

            } catch (Exception e) {
                // e.printStackTrace();
                System.out.println("ALguma coisa n ta funfando");
            }
            // System.out.println("\n\n\n");
        }
    }
    catch(Exception e) {
        System.out.println("ALguma coisa n ta funfando");

    }
    }

}
