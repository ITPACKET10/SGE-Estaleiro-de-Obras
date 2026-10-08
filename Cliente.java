public class Cliente {
    private String nome;
    private String contacto;
    private String nuit;
    private String email;
    private String endereco;

    public Cliente(String nome, String contacto) { this(nome, contacto, "", "", ""); }
    public Cliente(String nome, String contacto, String nuit, String email, String endereco) {
        this.nome = nome; this.contacto = contacto; this.nuit = nuit; this.email = email; this.endereco = endereco;
    }
    public String getNome(){return nome;} public void setNome(String v){nome=v;}
    public String getContacto(){return contacto;} public void setContacto(String v){contacto=v;}
    public String getNuit(){return nuit;} public void setNuit(String v){nuit=v;}
    public String getEmail(){return email;} public void setEmail(String v){email=v;}
    public String getEndereco(){return endereco;} public void setEndereco(String v){endereco=v;}
}
