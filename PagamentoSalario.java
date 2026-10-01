public class PagamentoSalario {
    private String emailFuncionario, periodo, dataPagamento, estado;
    private double salarioBase, descontos, bonus, liquido;
    public PagamentoSalario(String emailFuncionario,String periodo,double salarioBase,double descontos,double bonus,double liquido,String dataPagamento,String estado){
        this.emailFuncionario=emailFuncionario;this.periodo=periodo;this.salarioBase=salarioBase;this.descontos=descontos;this.bonus=bonus;this.liquido=liquido;this.dataPagamento=dataPagamento;this.estado=estado;
    }
    public String getEmailFuncionario(){return emailFuncionario;} public String getPeriodo(){return periodo;} public double getSalarioBase(){return salarioBase;}
    public double getDescontos(){return descontos;} public double getBonus(){return bonus;} public double getLiquido(){return liquido;} public String getDataPagamento(){return dataPagamento;} public String getEstado(){return estado;}
    public String serializar(){return emailFuncionario+";"+periodo+";"+salarioBase+";"+descontos+";"+bonus+";"+liquido+";"+dataPagamento+";"+estado;}
}
