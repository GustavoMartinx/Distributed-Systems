import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class MovieClient {
    static MovieClient client;
    private final ManagedChannel channel;
    private static MovieMethodsGrpc.MovieMethodsBlockingStub blockingStub;

    // Constructor to set up the connection
    public MovieClient(String host, int port) {
        this.channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext() // Disable SSL for simplicity
                .build();
        this.blockingStub = MovieMethodsGrpc.newBlockingStub(channel);
    }

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

    public static void handleOption(int operation, Scanner reader, MoviesRPC.Movie.Builder movieBuilder)
            throws InterruptedException {

        String movieID = "";
        String movieName = "";
        String directors = "";
        String plot = "";
        String cast = "";
        String genre = "";

        switch (operation) {
            case 1: // CREATE
                movieBuilder = MoviesRPC.Movie.newBuilder();
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

                System.out.println("Digite a sinopse do filme:");
                plot = reader.nextLine();
                movieBuilder.setPlot(plot);

                createMovie(movieBuilder);
                break;
            case 2: // READ
                System.out.println("Digite o ID do filme:");
                movieName = reader.nextLine();

                client.getMovie(movieName);

                break;
            case 3: // DELETE
                System.out.println("Digite o nome do filme:");
                movieID = reader.nextLine();
                deleteMovie(movieID);
                break;

            case 4: // UPDATE
            movieBuilder = MoviesRPC.Movie.newBuilder();
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

                System.out.println("Digite a sinopse do filme:");
                plot = reader.nextLine();
                movieBuilder.setPlot(plot);
                updateMovie(movieID, movieBuilder); 
                break;
        }
    }

    // Shutdown the client
    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    // Example method to create a movie
    public static void createMovie(MoviesRPC.Movie.Builder movie) {
        /*
         * MoviesRPC.Movie request = MoviesRPC.Movie.newBuilder()
         * .setTitle(movie.getTitle())
         * .setYear(movie.getYear())
         * .addAllDirectors(movie.getDirectorsList())
         * .build();
         */

        MoviesRPC.Response response;
        try {
            response = blockingStub.createMovie(movie.build());
            if (response.getStatus() == 200) {
                System.out.println("Movie created successfully: " + movie.getTitle());
            } else {
                System.out.println("Failed to create movie.");
            }
        } catch (RuntimeException e) {
            System.out.println("RPC failed: " + e.getMessage());
        }
    }

    public static void deleteMovie(String movieName) {
        // Create a MovieName request to delete the movie
        MoviesRPC.MovieName request = MoviesRPC.MovieName.newBuilder().setNameMovie(movieName).build();
        MoviesRPC.Response response;

        try {
            // Call the deleteMovie RPC method
            response = blockingStub.deleteMovie(request);
            if (response.getStatus() == 200) {
                System.out.println("Movie deleted successfully: " + movieName);
            } else {
                System.out.println("Failed to delete movie.");
            }
        } catch (RuntimeException e) {
            System.out.println("RPC failed: " + e.getMessage());
        }
    }

    public static void updateMovie(String movieName, MoviesRPC.Movie.Builder updatedMovie) {
       /*  MoviesRPC.UpdateMovieRequest request = MoviesRPC.UpdateMovieRequest.newBuilder()
                .setMovieName(movieName)
                .setUpdatedMovie(updatedMovie)
                .build();*/

        MoviesRPC.Response response;

        try {
            // Call the updateMovie RPC method
            response = blockingStub.updateMovie(updatedMovie.build());
            if (response.getStatus() == 200) {
                System.out.println("Movie updated successfully: " + movieName);
            } else {
                System.out.println("Failed to update movie.");
            }
        } catch (RuntimeException e) {
            System.out.println("RPC failed: " + e.getMessage());
        }
    }

    // Example method to get a movie by name
    public void getMovie(String movieName) {
        MoviesRPC.MovieName request = MoviesRPC.MovieName.newBuilder().setNameMovie(movieName).build();
        MoviesRPC.Response response;
        try {
            response = blockingStub.getMovie(request);
            if (response.getStatus() == 200) {
                MoviesRPC.Movie movie = response.getMovie();
                System.out.println("Found movie: " + movie.getTitle() + " (" + movie.getYear() + ")");
            } else {
                System.out.println("Movie not found.");
            }
        } catch (StatusRuntimeException e) {
            System.err.println("RPC failed: " + e.getStatus());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        client = new MovieClient("localhost", 8080);

        int choise = -1;
        Scanner reader = new Scanner(System.in);
        String currentMethod = Methods.empty;
        System.out.println("\nBEM-VINDO AO PROTO FILMES!\n");
        MoviesRPC.Movie.Builder movieBuilder = null;

        while (true) {
            System.out.print(
                    "\n\n\nDigite o número da opção desejada:\n---------------------------------------\n1. Cadastrar um filme\n2. Consultar um filme\n3. Deletar um filme\n4. Atualizar as informações de um filme\n5. Listar os filmes de um ator ou atriz\n6. Listar os filmes de um gênero\n7. Sair\n---------------------------------------\n");

            choise = Integer.parseInt(reader.nextLine());
            currentMethod = conversor(choise);
            handleOption(choise, reader, movieBuilder);
        }
    }
}
