import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Arquivo {

    public static void salvar(String nomeArquivo, String texto) {
        try {
            FileWriter writer = new FileWriter(nomeArquivo, true);
            writer.write(texto + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Erro ao salvar ficheiro: " + e.getMessage());
        }
    }

    public static void substituirTudo(String nomeArquivo, List<String> linhas) {
        try {
            FileWriter writer = new FileWriter(nomeArquivo, false);
            for (String linha : linhas) {
                writer.write(linha + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Erro ao atualizar ficheiro: " + e.getMessage());
        }
    }

    public static List<String> lerLinhas(String nomeArquivo) {
        List<String> linhas = new ArrayList<>();
        File ficheiro = new File(nomeArquivo);
        if (!ficheiro.exists()) return linhas;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(ficheiro));
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (!linha.trim().isEmpty()) linhas.add(linha);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Erro ao ler ficheiro: " + e.getMessage());
        }
        return linhas;
    }
}
