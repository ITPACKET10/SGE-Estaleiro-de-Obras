public class RequisicaoMaterial {
    private String codigo, obra, material, solicitante, data, estado, observacao, decisor;
    private int quantidade;
    public RequisicaoMaterial(String codigo,String obra,String material,int quantidade,String solicitante,String data,String estado,String observacao,String decisor){this.codigo=codigo;this.obra=obra;this.material=material;this.quantidade=quantidade;this.solicitante=solicitante;this.data=data;this.estado=estado;this.observacao=observacao;this.decisor=decisor;}
    public String getCodigo(){return codigo;} public String getObra(){return obra;} public String getMaterial(){return material;} public int getQuantidade(){return quantidade;} public String getSolicitante(){return solicitante;} public String getData(){return data;} public String getEstado(){return estado;} public String getObservacao(){return observacao;} public String getDecisor(){return decisor;}
    public void setEstado(String estado){this.estado=estado;} public void setDecisor(String decisor){this.decisor=decisor;}
    public String serializar(){return codigo+";"+obra+";"+material+";"+quantidade+";"+solicitante+";"+data+";"+estado+";"+observacao+";"+decisor;}
}
