import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {

    private static final String SERVER_ADDRESS = "127.0.0.1"; // Endereço do servidor (localhost)
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                // Implementação do menu para o usuário
                System.out.println("\nEscolha uma opção:");
                System.out.println("1. Ler uma fortuna");
                System.out.println("2. Escrever uma nova fortuna");
                System.out.println("3. Sair");
                System.out.print("Opção: ");

                String choice = scanner.nextLine();

                switch (choice) {
                    case "1":
                        read();
                        break;
                    case "2":
                        write(scanner);
                        break;
                    case "3":
                        System.out.println("Saindo...");
                        return;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            }
        }
    }

    private static void read() {
        // Monta a requisição JSON para leitura
        String request = "{\"method\": \"read\", \"args\": [\"\"]}";
        String response = sendRequest(request);

        // Processa a resposta do servidor
        if (response != null && response.contains("\"result\":")) {
            String fortune = response.split("\"result\": \"")[1].split("\"}")[0];
            // Remove o escape de caracteres especiais
            fortune = fortune.replace("\\n", "\n").replace("\\\"", "\"");
            System.out.println("\n--- Sua Sorte de Hoje ---");
            System.out.println(fortune);
            System.out.println("--------------------------");
        } else {
            System.out.println("Não foi possível ler a fortuna do servidor.");
        }
    }

    private static void write(Scanner scanner) {
        System.out.print("Digite a nova fortuna: ");
        String newFortune = scanner.nextLine();

        // Escapa as aspas para não quebrar o formato JSON
        newFortune = newFortune.replace("\"", "\\\"");

        // Monta a requisição JSON para escrita
        String request = String.format("{\"method\": \"write\", \"args\": [\"%s\"]}", newFortune);
        String response = sendRequest(request);

        // Processa a resposta do servidor
        if (response != null && response.contains(newFortune)) {
            System.out.println("Fortuna enviada com sucesso!");
        } else {
            System.out.println("Falha ao enviar a fortuna.");
        }
    }

    private static String sendRequest(String request) {
        try (
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            // Envia a requisição para o servidor
            out.println(request);
            // Retorna a resposta do servidor
            return in.readLine();
        } catch (UnknownHostException e) {
            System.err.println("Servidor não encontrado: " + SERVER_ADDRESS);
        } catch (IOException e) {
            System.err.println("Erro de conexão com o servidor: " + e.getMessage() + ". O servidor está rodando?");
        }
        return null;
    }
}
