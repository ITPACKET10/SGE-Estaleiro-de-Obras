import java.text.SimpleDateFormat;
import java.util.Date;

public class Auditoria {
    private Auditoria() {}

    public static void registar(String utilizador, String acao, String detalhe) {
        String dataHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
        Arquivo.salvar("auditoria.txt", dataHora + ";" + limpar(utilizador) + ";" + limpar(acao) + ";" + limpar(detalhe));
    }

    private static String limpar(String valor) {
        if (valor == null) return "-";
        return valor.replace(";", ",").replace("\n", " ").replace("\r", " ");
    }
}
