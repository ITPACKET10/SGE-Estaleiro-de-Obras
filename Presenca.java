public class Presenca {
    private String emailFuncionario;
    private String data;
    private String horaEntrada;
    private String horaSaida;
    private String estado;
    private String justificacao;

    public Presenca(String emailFuncionario, String data, String horaEntrada, String horaSaida, String estado, String justificacao) {
        this.emailFuncionario=emailFuncionario; this.data=data; this.horaEntrada=horaEntrada; this.horaSaida=horaSaida;
        this.estado=estado; this.justificacao=justificacao;
    }
    public String getEmailFuncionario(){return emailFuncionario;} public String getData(){return data;}
    public String getHoraEntrada(){return horaEntrada;} public String getHoraSaida(){return horaSaida;}
    public String getEstado(){return estado;} public String getJustificacao(){return justificacao;}
    public void setHoraSaida(String v){horaSaida=v;} public void setEstado(String v){estado=v;} public void setJustificacao(String v){justificacao=v;}
    public String serializar(){return emailFuncionario+";"+data+";"+horaEntrada+";"+horaSaida+";"+estado+";"+justificacao.replace(";",",");}
}
