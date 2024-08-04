import java.io.OutputStream;
import java.net.Socket;
import com.google.protobuf.InvalidProtocolBufferException;
import java.io.DataOutputStream;


public class Main {
    public static void main(String[] args) {
        // Criando uma nova instância de Movie usando o Builder
        Movies.Movie movie = Movies.Movie.newBuilder()
                .setTitle("Inception")
                .setId(1)
                .setDirectors("Christopher Nolan")
                .build();

        // Serializando a instância de Movie para um byte array
        byte[] movieBytes = movie.toByteArray();

        try (Socket socket = new Socket("localhost", 7000);
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
            Movies.Movie parsedMovie = Movies.Movie.parseFrom(movieBytes);

            // Accessing fields from the parsed Movie instance
            System.out.println("Parsed Movie Title: " + parsedMovie.getTitle());
            System.out.println("Parsed Movie ID: " + parsedMovie.getId());
            System.out.println("Parsed Movie Directors: " + parsedMovie.getDirectors());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }
    
}
