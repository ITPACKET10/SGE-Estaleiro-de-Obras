public class Fornecedor {
    private String nome,nuit,contacto,endereco;
    public Fornecedor(String nome,String nuit,String contacto,String endereco){this.nome=nome;this.nuit=nuit;this.contacto=contacto;this.endereco=endereco;}
    public String getNome(){return nome;} public String getNuit(){return nuit;} public String getContacto(){return contacto;} public String getEndereco(){return endereco;}
    public String serializar(){return nome.replace(";",",")+";"+nuit+";"+contacto+";"+endereco.replace(";",",");}
}
