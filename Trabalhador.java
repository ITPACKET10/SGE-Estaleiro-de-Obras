public class Trabalhador {
    private String codigo, nome, apelido, bi, nuit, contacto, contactoEmergencia, departamento, cargo;
    private String dataEntrada, tipoContrato, fimContrato, metodoPagamento, numeroPagamento, titularPagamento;
    private double salarioBase;
    private boolean acessoSistema;

    public Trabalhador(String codigo, String nome, String apelido, String bi, String nuit, String contacto,
                       String contactoEmergencia, String departamento, String cargo, double salarioBase,
                       String dataEntrada, String tipoContrato, String fimContrato, boolean acessoSistema,
                       String metodoPagamento, String numeroPagamento, String titularPagamento) {
        this.codigo=codigo; this.nome=nome; this.apelido=apelido; this.bi=bi; this.nuit=nuit; this.contacto=contacto;
        this.contactoEmergencia=contactoEmergencia; this.departamento=departamento; this.cargo=cargo; this.salarioBase=salarioBase;
        this.dataEntrada=dataEntrada; this.tipoContrato=tipoContrato; this.fimContrato=fimContrato; this.acessoSistema=acessoSistema;
        this.metodoPagamento=metodoPagamento; this.numeroPagamento=numeroPagamento; this.titularPagamento=titularPagamento;
    }
    public String getCodigo(){return codigo;} public String getNome(){return nome;} public String getApelido(){return apelido;}
    public String getBi(){return bi;} public String getNuit(){return nuit;} public String getContacto(){return contacto;}
    public String getContactoEmergencia(){return contactoEmergencia;} public String getDepartamento(){return departamento;}
    public String getCargo(){return cargo;} public double getSalarioBase(){return salarioBase;} public String getDataEntrada(){return dataEntrada;}
    public String getTipoContrato(){return tipoContrato;} public String getFimContrato(){return fimContrato;} public boolean isAcessoSistema(){return acessoSistema;}
    public String getMetodoPagamento(){return metodoPagamento;} public String getNumeroPagamento(){return numeroPagamento;} public String getTitularPagamento(){return titularPagamento;}
}
