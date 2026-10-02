public class Venda {
    private String cliente;
    private String material;
    private int quantidade;
    private double total;
    private String vendedor;
    private String data;

    public Venda(String cliente, String material, int quantidade, double total, String vendedor) {
        this(cliente, material, quantidade, total, vendedor, "-");
    }

    public Venda(String cliente, String material, int quantidade, double total, String vendedor, String data) {
        this.cliente = cliente;
        this.material = material;
        this.quantidade = quantidade;
        this.total = total;
        this.vendedor = vendedor;
        this.data = data;
    }

    public String getCliente() { return cliente; }
    public String getMaterial() { return material; }
    public int getQuantidade() { return quantidade; }
    public double getTotal() { return total; }
    public String getVendedor() { return vendedor; }
    public String getData() { return data; }
}
