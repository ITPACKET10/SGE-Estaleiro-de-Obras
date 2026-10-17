package sge.repository;

import sge.model.*;
import sge.controller.*;
import sge.view.*;
import sge.repository.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

/**
 * Disponibiliza operações de leitura e escrita em ficheiros para a persistência actual.
 *
 * <p>Projecto académico SGE: documentação das responsabilidades desta classe.</p>
 */
public class Arquivo {
    private static Path caminho(String nomeArquivo) {
        Path raiz = Paths.get(System.getProperty("sge.data.dir", "dados")).toAbsolutePath().normalize();
        Path caminho = raiz.resolve(nomeArquivo).normalize();
        if (!caminho.startsWith(raiz)) throw new IllegalArgumentException("Nome de ficheiro inválido");
        return caminho;
    }
    public static void salvar(String nomeArquivo, String texto) {
        try {
            Path p = caminho(nomeArquivo);
            Files.createDirectories(p.getParent());
            Files.write(p, Collections.singletonList(texto), StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) { throw new UncheckedIOException("Erro ao guardar " + nomeArquivo, e); }
    }
    public static void substituirTudo(String nomeArquivo, List<String> linhas) {
        try {
            Path p = caminho(nomeArquivo);
            Files.createDirectories(p.getParent());
            Path temp = Files.createTempFile(p.getParent(), "sge-", ".tmp");
            try {
                Files.write(temp, linhas, StandardCharsets.UTF_8);
                try { Files.move(temp, p, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE); }
                catch (AtomicMoveNotSupportedException ex) { Files.move(temp, p, StandardCopyOption.REPLACE_EXISTING); }
            } finally { Files.deleteIfExists(temp); }
        } catch (IOException e) { throw new UncheckedIOException("Erro ao actualizar " + nomeArquivo, e); }
    }
    public static List<String> lerLinhas(String nomeArquivo) {
        Path p = caminho(nomeArquivo);
        if (!Files.exists(p)) return new ArrayList<>();
        try {
            List<String> resultado = new ArrayList<>();
            for (String linha : Files.readAllLines(p, StandardCharsets.UTF_8))
                if (!linha.trim().isEmpty()) resultado.add(linha);
            return resultado;
        } catch (IOException e) { throw new UncheckedIOException("Erro ao ler " + nomeArquivo, e); }
    }
}
