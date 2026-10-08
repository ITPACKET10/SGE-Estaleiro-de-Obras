import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ContratoTrabalho {
    private String codigoTrabalhador;
    private String tipo;
    private String dataInicio;
    private String dataFim;

    public ContratoTrabalho(String codigoTrabalhador, String tipo, String dataInicio, String dataFim) {
        this.codigoTrabalhador = codigoTrabalhador;
        this.tipo = tipo;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public String getCodigoTrabalhador() { return codigoTrabalhador; }
    public String getTipo() { return tipo; }
    public String getDataInicio() { return dataInicio; }
    public String getDataFim() { return dataFim; }

    public boolean isIndeterminado() {
        return "Indeterminado".equalsIgnoreCase(tipo) || dataFim == null || dataFim.trim().isEmpty() || "-".equals(dataFim);
    }

    public long diasParaExpirar(LocalDate fim, LocalDate hoje) {
        return ChronoUnit.DAYS.between(hoje, fim);
    }
}
