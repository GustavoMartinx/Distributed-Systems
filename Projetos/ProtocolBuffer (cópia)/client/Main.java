import java.io.OutputStream;
import java.net.Socket;
import java.rmi.server.Operation;

import com.google.protobuf.InvalidProtocolBufferException;
import java.util.Scanner;

import java.io.DataOutputStream;

public class Main {
    public static Operation conversor(int op) {
        switch (op) {
            case 1:
                return Operation.CREATE;
            case 2:
                return Operation.READ;
            case 3:
                return Operation.DELETE;
            case 4:
                return Operation.UPDATE;
            case 5:
                return Operation.LIST_BY_ACTOR;
            case 6:
                return Operation.LIST_BY_CATEGORY;
        }
        return Operation.EMPTY;
    }

    public enum Operation {
        EMPTY,
        CREATE,
        READ,
        UPDATE,
        DELETE,
        LIST_BY_ACTOR,
        LIST_BY_CATEGORY;
    }

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        Operation operacaoAtual = Operation.EMPTY;

        int escolha = -1;
        String nomeFilme = "";
        String nomeAtor = "";
        String nomeCategoria = "";

        System.out.println("BEM VINDO AO PROTO FILMES!");

        while (true) {
            System.out.println("ESCOLHA O QUE VOCÊ QUER FAZER");
            System.out.println("1. CADASTRAR UM FILME");
            System.out.println("2. CONSULTAR UM FILME PELO TITULO");
            System.out.println("3. DELETAR UM FILME PELO TITULO");
            System.out.println("4. ATUALIZAR AS INFORMAÇÔES DE UM FILME");
            System.out.println("5. LISTAR OS FILMES COM UM ATOR");
            System.out.println("6. LISTAR OS FILMES DE UMA CATEGORIA");
            System.out.println("DIGITE O NÚMERO DA OPÇÂO");

            escolha = Integer.parseInt(reader.nextLine());
            operacaoAtual = conversor(escolha);

            if (operacaoAtual != Operation.LIST_BY_ACTOR && operacaoAtual != Operation.LIST_BY_CATEGORY) {
                System.out.println("Digite o nome do filme");
                nomeFilme = reader.nextLine();
            }

            if (operacaoAtual == Operation.LIST_BY_ACTOR) {
                System.out.println("Digite o nome do ator");
                nomeAtor = reader.nextLine();
            }

            if (operacaoAtual == Operation.LIST_BY_CATEGORY) {
                System.out.println("Digite a categoria");
                nomeCategoria = reader.nextLine();
            }

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
        System.out.println("\n\n\n");
    }

}
