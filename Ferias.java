public class Ferias {
    private String emailFuncionario, dataInicio, dataFim, motivo, estado, decididoPor, dataDecisao;
    public Ferias(String emailFuncionario,String dataInicio,String dataFim,String motivo,String estado,String decididoPor,String dataDecisao){
        this.emailFuncionario=emailFuncionario;this.dataInicio=dataInicio;this.dataFim=dataFim;this.motivo=motivo;this.estado=estado;this.decididoPor=decididoPor;this.dataDecisao=dataDecisao;
    }
    public String serializar(){return emailFuncionario+";"+dataInicio+";"+dataFim+";"+motivo.replace(";",",")+";"+estado+";"+decididoPor+";"+dataDecisao;}
}
