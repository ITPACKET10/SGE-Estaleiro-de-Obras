public class AdiantamentoSalario {
    private String emailFuncionario, periodo, dataPedido, motivo, estado, decididoPor, dataDecisao;
    private double valor;

    public AdiantamentoSalario(String emailFuncionario, String periodo, double valor, String dataPedido,
                               String motivo, String estado, String decididoPor, String dataDecisao) {
        this.emailFuncionario = emailFuncionario;
        this.periodo = periodo;
        this.valor = valor;
        this.dataPedido = dataPedido;
        this.motivo = motivo;
        this.estado = estado;
        this.decididoPor = decididoPor;
        this.dataDecisao = dataDecisao;
    }

    public String getEmailFuncionario(){ return emailFuncionario; }
    public String getPeriodo(){ return periodo; }
    public double getValor(){ return valor; }
    public String getDataPedido(){ return dataPedido; }
    public String getMotivo(){ return motivo; }
    public String getEstado(){ return estado; }
    public String getDecididoPor(){ return decididoPor; }
    public String getDataDecisao(){ return dataDecisao; }

    public String serializar(){
        return emailFuncionario+";"+periodo+";"+valor+";"+dataPedido+";"+
                motivo.replace(";", ",")+";"+estado+";"+decididoPor+";"+dataDecisao;
    }
}
