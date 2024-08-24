import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;


import java.util.concurrent.TimeUnit;

public class MovieClient {
    private final ManagedChannel channel;
    private final MovieMethodsGrpc.MovieMethodsBlockingStub blockingStub;

    // Constructor to set up the connection
    public MovieClient(String host, int port) {
        this.channel = ManagedChannelBuilder.forAddress(host, port)
            .usePlaintext() // Disable SSL for simplicity
            .build();
        this.blockingStub = MovieMethodsGrpc.newBlockingStub(channel);
    }

    // Shutdown the client
    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    // Example method to get a movie by name
    public void getMovie(String movieName) {
        MoviesRPC.MovieName request = MoviesRPC.MovieName.newBuilder().setNameMovie(movieName).build();
        MoviesRPC.Response response;
        try {
            response = blockingStub.getMovie(request);
            if (response.getStatus() == 0) {
                System.out.println("Movie not found.");
            } else {
                MoviesRPC.Movie movie = response.getMovie();
                System.out.println("Found movie: " + movie.getTitle() + " (" + movie.getYear() + ")");
            }
        } catch (StatusRuntimeException e) {
            System.err.println("RPC failed: " + e.getStatus());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MovieClient client = new MovieClient("localhost", 8080);
        try {
            String movieName = "Inception";
            client.getMovie(movieName);
        } finally {
            client.shutdown();
        }
    }
}
