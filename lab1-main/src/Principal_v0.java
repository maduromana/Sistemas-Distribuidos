/**
 * Lab0: Leitura de Base de Dados Não-Distribuida
 *
 * Autor: Lucio A. Rocha
 * Última atualização: 20/02/2023
 *
 * Referências:
 * https://docs.oracle.com/javase/tutorial/essential/io
 *
 * Modificações: 
 *  Métodos de leitura (read) e escrita (write) para o Laboratório 1 da disciplina de Sistemas Distribuídos.
 * - Maria Eduarda Soares e João Pedro Neigri 
 */


import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Principal_v0 {

    
    public final static Path path = Paths
            .get("src/fortune-br.txt");
    private int NUM_FORTUNES = 0;

    public class FileReader {

        public int countFortunes() throws FileNotFoundException {

            int lineCount = 0;

            InputStream is = new BufferedInputStream(new FileInputStream(
                    path.toString()));
            try (BufferedReader br = new BufferedReader(new InputStreamReader(
                    is))) {

                String line = "";
                while (!(line == null)) {

                    if (line.equals("%"))
                        lineCount++;

                    line = br.readLine();

                }// fim while

                //System.out.println(lineCount); 
            } catch (IOException e) {
                System.out.println("SHOW: Excecao na leitura do arquivo.");
            }
            return lineCount;
        }

        public void parser(HashMap<Integer, String> hm)
                throws FileNotFoundException {

            InputStream is = new BufferedInputStream(new FileInputStream(
                    path.toString()));
            try (BufferedReader br = new BufferedReader(new InputStreamReader(
                    is))) {

                int lineCount = 0;

                String line = "";
                while (!(line == null)) {

                    if (line.equals("%"))
                        lineCount++;

                    line = br.readLine();
                    StringBuffer fortune = new StringBuffer();
                    while (!(line == null) && !line.equals("%")) {
                        fortune.append(line + "\n");
                        line = br.readLine();
                        // System.out.print(lineCount + ".");
                    }

                    hm.put(lineCount, fortune.toString());
                    //System.out.println(fortune.toString()); 
                    //System.out.println(lineCount); 
                }// fim while

            } catch (IOException e) {
                System.out.println("SHOW: Excecao na leitura do arquivo.");
            }
        }

// ------------- codigo Maria Eduarda e João Neigri ----------------------------

        public void read(HashMap<Integer, String> hm)
                throws FileNotFoundException {

            
            System.out.println("\n--- Lendo uma fortuna aleatoria ---");

            if (hm == null || hm.isEmpty()) {
                System.out.println("Nenhuma fortuna para ler.");
                return;
            }
            
            ArrayList<String> fortunes = new ArrayList<>(hm.values()); // hashmap -> lista 

            SecureRandom random = new SecureRandom();
            int randomIndex = random.nextInt(fortunes.size()); // indice aleatorio 

            System.out.println(fortunes.get(randomIndex));  // fortuna sorteada 
            System.out.println("-----------------------------------\n");
        }

        public void write(HashMap<Integer, String> hm)
                throws FileNotFoundException {

            System.out.println("--- Adicionando uma nova fortuna ---");
            System.out.println("Por favor, digite a nova fortuna e pressione Enter:");
            
            Scanner scanner = new Scanner(System.in);
            String newFortune = scanner.nextLine();
            
            try (FileWriter fw = new FileWriter(path.toString(), true); // abrir arquivo no modo de ediçaõ 
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter out = new PrintWriter(bw)) {

                out.println("%"); // add separador 
                out.println(newFortune);
                System.out.println("Fortuna adicionada com sucesso!");

            } catch (IOException e) {
                System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
            }
        }
    }

    public void iniciar() {

        FileReader fr = new FileReader();
        try {
            NUM_FORTUNES = fr.countFortunes();
            HashMap hm = new HashMap<Integer, String>();
            fr.parser(hm);
            fr.read(hm);
            fr.write(hm);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new Principal_v0().iniciar();
    }

}

