public class RececaoMaterial {
    private String compraId, data, material, fornecedor, responsavel;
    private int quantidade;

    public RececaoMaterial(String compraId, String data, String material, int quantidade, String fornecedor, String responsavel) {
        this.compraId=compraId; this.data=data; this.material=material; this.quantidade=quantidade;
        this.fornecedor=fornecedor; this.responsavel=responsavel;
    }
    public String serializar(){
        return compraId+";"+data+";"+material+";"+quantidade+";"+fornecedor+";"+responsavel;
    }
}
