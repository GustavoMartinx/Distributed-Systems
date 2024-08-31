// TODO: Adicionar cabeçalho e comentários em português nos métodos com JavaDocs.

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class MovieClient {

    static MovieClient client;
    private final ManagedChannel channel;
    private static MovieMethodsGrpc.MovieMethodsBlockingStub blockingStub;
    private static final String menuMessage = "\n\n\nDigite o número da opção desejada:\n---------------------------------------\n1. Cadastrar um filme\n2. Consultar um filme\n3. Deletar um filme\n4. Atualizar as informações de um filme\n5. Listar os filmes de um ator ou atriz\n6. Listar os filmes de um gênero\n7. Sair\n---------------------------------------\n";

    // Constructor to set up the connection
    public MovieClient(String host, int port) {
        this.channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext() // Disable SSL for simplicity
                .build();
        this.blockingStub = MovieMethodsGrpc.newBlockingStub(channel);
    }

    public static boolean isConvertibleToInt(String input) {
        try {
            Integer.parseInt(input);
            return true; // Conversion succeeded
        } catch (NumberFormatException e) {
            return false; // Conversion failed
        }
    }

    /**
     * Realiza o tratamento da escolha de opção do usuário, disparando
     * a chamada de procedimento remoto respectiva à escolha.
     * 
     * @param operation - Opção escolhida pelo usuário
     * @param reader - Objeto responsável pelo I/O
     * @param movieBuilder - Movies.Movie.Builder
     * @param movieFilterBuilder - Movies.MovieFilters.Builder
     * @return void
     * @throws InterruptedException
     */
    public static void handleOption(int operation, Scanner reader, MoviesRPC.Movie.Builder movieBuilder, MoviesRPC.MovieFilters.Builder movieFilterBuilder)
            throws InterruptedException {

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
                System.out.println("Digite o nome do filme:");
                movieName = reader.nextLine();
                getMovie(movieName);
                break;

            case 3: // DELETE
                System.out.println("Digite o nome do filme:");
                movieName = reader.nextLine();
                deleteMovie(movieName);
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
                updateMovie(movieName, movieBuilder);
                break;

            case 5: // FIND BY CAST
                System.out.println("Digite os nomes dos membros do elenco (separados por vírgula):");
                String castInput = reader.nextLine();

                // Regex que lida com ", " ou ","
                String[] castArray = castInput.split(",\\s*");
                
                // Iterando sobre cada substring e passando-a para o método addValues
                for (String castItem : castArray) {
                    movieFilterBuilder.addValues(castItem.trim());
                }
                
                getMoviesByCast(movieFilterBuilder);
                break;
        }
    }

    // Shutdown the client
    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    /**
     * Método responsável pela chamada da RPC de criação de um
     * filme e obtenção da sua resposta.
     * 
     * @param movie - Objeto filme a ser criado (MoviesRPC.Movie.Builder)
     * @return void
     */
    public static void createMovie(MoviesRPC.Movie.Builder movie) {

        MoviesRPC.Response response;
        try {
            // Realizando a chamada de procedimento remoto propriamente dita
            response = blockingStub.createMovie(movie.build());
            if (response.getStatus() == 200) {
                System.out.println("\n" + response.getMessage());
                System.out.println("\n" + response.getMovie());
            } else {
                System.out.println("\n" + response.getMessage());
            }
        } catch (RuntimeException e) {
            System.out.println("RPC failed: " + e.getMessage());
        }
    }

    /**
     * Método responsável pela chamada da RPC que deleta um
     * filme através do título e obtenção da sua resposta.
     * 
     * @param movieName - String do título do filme a ser deletado.
     * @return void
     */
    public static void deleteMovie(String movieName) {
        // Criando uma requisição do tipo MovieName para deletar o filme
        MoviesRPC.MovieName request = MoviesRPC.MovieName.newBuilder().setNameMovie(movieName).build();
        MoviesRPC.Response response;

        try {
            // Realizando a chamada de procedimento remoto propriamente dita
            response = blockingStub.deleteMovie(request);
            if (response.getStatus() == 200) {
                System.out.println("\n" + response.getMessage() + movieName);
            } else {
                System.out.println("\n" + response.getMessage());
            }
        } catch (RuntimeException e) {
            System.out.println("RPC failed: " + e.getMessage());
        }
    }

    /**
     * Método responsável pela chamada da RPC que atualiza os
     * dados de um filme e obtenção da sua resposta.
     * 
     * @param movieName - String do título do filme a ser atualizado.
     * @param updatedMovie - Objeto filme com as novas informações.
     * @return void
     */
    public static void updateMovie(String movieName, MoviesRPC.Movie.Builder updatedMovie) {
        MoviesRPC.Response response;

        try {
            // Realizando a chamada de procedimento remoto propriamente dita
            response = blockingStub.updateMovie(updatedMovie.build());
            if (response.getStatus() == 200) {
                System.out.println(response.getMessage());
                System.out.println(movieName);
            } else {
                System.out.println(response.getMessage());
            }
        } catch (RuntimeException e) {
            System.out.println("RPC failed: " + e.getMessage());
        }
    }

    /**
     * Método responsável pela chamada da RPC de consulta de um
     * filme através do título e obtenção da sua resposta.
     * 
     * @param movieName - String do título do filme a ser consultado.
     * @return void
     */
    public static void getMovie(String movieName) {
        MoviesRPC.MovieName request = MoviesRPC.MovieName.newBuilder().setNameMovie(movieName).build();
        MoviesRPC.Response response;
        try {
            // Realizando a chamada de procedimento remoto propriamente dita
            response = blockingStub.getMovie(request);
            if (response.getStatus() == 200) {
                System.out.println("\n" + response.getMessage());
                System.out.println("\n" + response.getMovie());
            } else {
                System.out.println("\n" + response.getMessage());
            }
        } catch (StatusRuntimeException e) {
            System.err.println("RPC failed: " + e.getStatus());
        }
    }

    /**
     * Método responsável pela chamada da RPC que, dado uma lista de membros do
     * elenco, obtém todos filmes em que esses membros compunham o elenco.
     * Em seguida, o método trata a resposta dessa operação.
     * 
     * @param movieFilters Estrutura que contém o vetor dos atributos
     * da busca. Neste caso, o vetor deve conter os nomes dos membros do elenco.
     * @return void
     */
    public static void getMoviesByCast(MoviesRPC.MovieFilters.Builder movieFilters) {
        MoviesRPC.Response response;
        
        try {
            // Realizando a chamada de procedimento remoto propriamente dita
            response = blockingStub.getMoviesByActor(movieFilters.build());
            if (response.getStatus() == 200) {
                System.out.println("\n" + response.getMessage());
                System.out.println("\n" + response.getMoviesList());
            } else {
                System.out.println("\n" + response.getMessage());
            }
        } catch (StatusRuntimeException e) {
            System.err.println("RPC failed: " + e.getStatus());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        client = new MovieClient("localhost", 8080);

        int choise = -1;
        Scanner reader = new Scanner(System.in);
        String readed = "";
        MoviesRPC.Movie.Builder movieBuilder = null;
        MoviesRPC.MovieFilters.Builder movieFilterBuilder = MoviesRPC.MovieFilters.newBuilder();
        System.out.println("\nBEM-VINDO AO PROTO FILMES!\n");

        while (true) {
            System.out.print(menuMessage);
            readed = reader.nextLine();
            if (isConvertibleToInt(readed)) {
                choise = Integer.parseInt(readed);
                handleOption(choise, reader, movieBuilder, movieFilterBuilder);
            }else{
                continue;
            }
        }
    }
}
