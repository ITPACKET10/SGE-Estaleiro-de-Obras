public class Obra {
    private String codigo, nome, local, responsavel, dataInicio, dataFim, estado;
    public Obra(String codigo,String nome,String local,String responsavel,String dataInicio,String dataFim,String estado){this.codigo=codigo;this.nome=nome;this.local=local;this.responsavel=responsavel;this.dataInicio=dataInicio;this.dataFim=dataFim;this.estado=estado;}
    public String getCodigo(){return codigo;} public String getNome(){return nome;} public String getLocal(){return local;} public String getResponsavel(){return responsavel;} public String getDataInicio(){return dataInicio;} public String getDataFim(){return dataFim;} public String getEstado(){return estado;}
    public void setEstado(String estado){this.estado=estado;}
    public String serializar(){return codigo+";"+nome+";"+local+";"+responsavel+";"+dataInicio+";"+dataFim+";"+estado;}
}
