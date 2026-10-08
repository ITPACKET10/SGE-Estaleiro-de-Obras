public class Material {
    private String nome;
    private int quantidade;
    private double preco;
    private int totalVendido;
    private String imagem;
    private String categoria;
    private String fornecedor;
    private boolean disponivelCaixa;
    private String dataRegisto;

    public Material(String nome, int quantidade, double preco) {
        this(nome, quantidade, preco, "", "Geral", "");
    }

    public Material(String nome, int quantidade, double preco, String imagem, String categoria, String fornecedor) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
        this.totalVendido = 0;
        this.imagem = imagem;
        this.categoria = categoria;
        this.fornecedor = fornecedor;
        this.disponivelCaixa = quantidade > 0 && preco > 0;
        this.dataRegisto = "-";
    }

    public String getNome() { return nome; }
    public int getQuantidade() { return quantidade; }
    public double getPreco() { return preco; }
    public int getTotalVendido() { return totalVendido; }
    public String getImagem() { return imagem; }
    public String getCategoria() { return categoria; }
    public String getFornecedor() { return fornecedor; }
    public boolean isDisponivelCaixa() { return disponivelCaixa; }
    public String getDataRegisto() { return dataRegisto; }

    public void setImagem(String imagem) { this.imagem = imagem; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public void setFornecedor(String fornecedor) { this.fornecedor = fornecedor; }
    public void setDisponivelCaixa(boolean disponivelCaixa) { this.disponivelCaixa = disponivelCaixa; }
    public void setDataRegisto(String dataRegisto) { this.dataRegisto = dataRegisto; }

    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
    public void setPreco(double preco) { this.preco = preco; }

    public void adicionarQuantidade(int qtd) {
        quantidade += qtd;
    }

    public boolean vender(int qtd) {
        if (qtd > 0 && qtd <= quantidade) {
            quantidade -= qtd;
            totalVendido += qtd;
            return true;
        }
        return false;
    }
}
