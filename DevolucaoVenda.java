public class DevolucaoVenda {
    private String data;
    private String vendaId;
    private String cliente;
    private String material;
    private int quantidade;
    private double valor;
    private String motivo;
    private String responsavel;

    public DevolucaoVenda(String data, String vendaId, String cliente, String material, int quantidade, double valor, String motivo, String responsavel) {
        this.data = data;
        this.vendaId = vendaId;
        this.cliente = cliente;
        this.material = material;
        this.quantidade = quantidade;
        this.valor = valor;
        this.motivo = motivo;
        this.responsavel = responsavel;
    }

    public String serializar() {
        return limpar(data)+";"+limpar(vendaId)+";"+limpar(cliente)+";"+limpar(material)+";"+quantidade+";"+valor+";"+limpar(motivo)+";"+limpar(responsavel);
    }

    private String limpar(String s) { return s == null ? "" : s.replace(";", ",").replace("\n", " "); }
    public String getData(){ return data; }
    public String getVendaId(){ return vendaId; }
    public String getCliente(){ return cliente; }
    public String getMaterial(){ return material; }
    public int getQuantidade(){ return quantidade; }
    public double getValor(){ return valor; }
    public String getMotivo(){ return motivo; }
    public String getResponsavel(){ return responsavel; }
}
