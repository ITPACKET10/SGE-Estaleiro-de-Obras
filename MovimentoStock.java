public class MovimentoStock {
    private String data,material,tipo,motivo,responsavel; private int quantidade;
    public MovimentoStock(String data,String material,String tipo,int quantidade,String motivo,String responsavel){this.data=data;this.material=material;this.tipo=tipo;this.quantidade=quantidade;this.motivo=motivo;this.responsavel=responsavel;}
    public String serializar(){return data+";"+material+";"+tipo+";"+quantidade+";"+motivo.replace(";",",")+";"+responsavel;}
}
