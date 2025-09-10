import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.SecureRandom;

public class Servidor {

    private static final int PORT = 12345;
    public static final Path path = Paths.get("src/fortune-br.txt");

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor de Fortunas iniciado na porta " + PORT);
            System.out.println("Aguardando conexões de clientes...");

            while (true) {
                // Aceita uma nova conexão de cliente
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress().getHostAddress());

                // Cria uma nova thread para lidar com o cliente, permitindo múltiplas conexões
                new ClientHandler(clientSocket).start();
            }
        } catch (IOException e) {
            System.err.println("Erro ao iniciar o servidor: " + e.getMessage());
        }
    }

    // Classe interna para lidar com cada cliente em uma thread separada
    private static class ClientHandler extends Thread {
        private final Socket clientSocket;
        private final FortuneFileHandler fileHandler;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
            this.fileHandler = new FortuneFileHandler();
        }

        @Override
        public void run() {
            try (
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
            ) {
                String request;
                // Lê as requisições do cliente
                while ((request = in.readLine()) != null) {
                    System.out.println("Recebido do cliente: " + request);
                    // Processa a requisição e obtém a resposta
                    String response = parser(request);
                    // Envia a resposta de volta ao cliente
                    out.println(response);
                    System.out.println("Enviado para o cliente: " + response);
                }
            } catch (IOException e) {
                System.err.println("Erro na comunicação com o cliente: " + e.getMessage());
            } finally {
                try {
                    clientSocket.close();
                    System.out.println("Conexão com o cliente fechada.");
                } catch (IOException e) {
                    System.err.println("Erro ao fechar o socket do cliente: " + e.getMessage());
                }
            }
        }

        private String parser(String request) {
            try {
                
                if (request.contains("\"method\": \"read\"")) {
                    String fortune = fileHandler.read();
                    
                    // Escapa as aspas na fortuna para não quebrar o JSON
                    fortune = fortune.replace("\"", "\\\"");
                    return String.format("{\"result\": \"%s\"}", fortune);
                } else if (request.contains("\"method\": \"write\"")) {
                    
                    // Extrai o argumento da mensagem
                    String arg = request.split("\"args\": \\[\"")[1].split("\"\\]")[0];
                    fileHandler.write(arg);
                    return String.format("{\"result\": \"%s\"}", arg);
                } else {
                     return "{\"result\": \"false\"}";
                }
            } catch (Exception e) {
                System.err.println("Erro ao processar a requisição: " + e.getMessage());
                return "{\"result\": \"false\"}";
            }
        }
    }

    // Classe para manipular o arquivo de fortunas
    private static class FortuneFileHandler {
        public String read() throws IOException {
            // Lê todas as fortunas do arquivo
            String content = new String(Files.readAllBytes(path));
            String[] fortunes = content.split("%");
            
            if (fortunes.length == 0) {
                return "Nenhuma fortuna encontrada.";
            }

            // Seleciona uma fortuna aleatória
            SecureRandom random = new SecureRandom();
            return fortunes[random.nextInt(fortunes.length)].trim();
        }

        public void write(String newFortune) throws IOException {
            
            // Formata a nova fortuna para adicionar ao arquivo
            String formattedFortune = "\n%\n" + newFortune;
            // Adiciona a nova fortuna ao final do arquivo
            Files.write(path, formattedFortune.getBytes(), StandardOpenOption.APPEND);
        }
    }
}
