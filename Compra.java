public class Compra {
    private String id, data, fornecedor, material, responsavel;
    private int quantidade;
    private double precoUnitario, total;

    public Compra(String data,String fornecedor,String material,int quantidade,double precoUnitario,String responsavel){
        this("CP-"+System.currentTimeMillis(),data,fornecedor,material,quantidade,precoUnitario,responsavel);
    }

    public Compra(String id,String data,String fornecedor,String material,int quantidade,double precoUnitario,String responsavel){
        this.id=id; this.data=data;this.fornecedor=fornecedor;this.material=material;this.quantidade=quantidade;
        this.precoUnitario=precoUnitario;this.total=quantidade*precoUnitario;this.responsavel=responsavel;
    }
    public String getId(){return id;}
    public String serializar(){return data+";"+fornecedor+";"+material+";"+quantidade+";"+precoUnitario+";"+total+";"+responsavel+";"+id;}
}
