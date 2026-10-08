public class AlocacaoObra {
    private String codigoObra, trabalhadorEmail, trabalhadorNome, departamento, cargo, dataAlocacao, estado;
    public AlocacaoObra(String codigoObra, String trabalhadorEmail, String trabalhadorNome, String departamento, String cargo, String dataAlocacao, String estado) {
        this.codigoObra=codigoObra; this.trabalhadorEmail=trabalhadorEmail; this.trabalhadorNome=trabalhadorNome; this.departamento=departamento; this.cargo=cargo; this.dataAlocacao=dataAlocacao; this.estado=estado;
    }
    public String getCodigoObra(){return codigoObra;} public String getTrabalhadorEmail(){return trabalhadorEmail;} public String getTrabalhadorNome(){return trabalhadorNome;} public String getDepartamento(){return departamento;} public String getCargo(){return cargo;} public String getDataAlocacao(){return dataAlocacao;} public String getEstado(){return estado;}
    public void setEstado(String estado){this.estado=estado;}
    public String serializar(){return codigoObra+";"+trabalhadorEmail+";"+trabalhadorNome+";"+departamento+";"+cargo+";"+dataAlocacao+";"+estado;}
}
