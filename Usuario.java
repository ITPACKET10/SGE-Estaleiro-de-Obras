public class Usuario implements Autenticavel {
    private String nome;
    private String apelido;
    private String bi;
    private String telefone;
    private String morada;
    private String sexo;
    private String email;
    private String senha;
    private String perfil;
    private String departamento = "-";
    private String cargo = "-";
    private double salarioBase = 0;

    public Usuario(String nome, String apelido, String bi, String telefone, String morada,
                   String sexo, String email, String senha, String perfil) {
        this.nome = nome;
        this.apelido = apelido;
        this.bi = bi;
        this.telefone = telefone;
        this.morada = morada;
        this.sexo = sexo;
        this.email = email;
        this.senha = senha;
        this.perfil = perfil;
    }

    @Override
    public boolean autenticar(String email, String senha) {
        return this.email.equalsIgnoreCase(email == null ? "" : email.trim())
                && this.senha.equals(senha);
    }

    public String[] getPermissoes() { return new String[]{"CONSULTAR"}; }
    public String[] getMenuOpcoes() { return new String[]{"Materiais"}; }

    public String getNome() { return nome; }
    public String getApelido() { return apelido; }
    public String getBi() { return bi; }
    public String getTelefone() { return telefone; }
    public String getMorada() { return morada; }
    public String getSexo() { return sexo; }
    public String getEmail() { return email; }
    public String getSenha() { return senha; }
    public String getPerfil() { return perfil; }
    public String getUsername() { return email; }
    public String getDepartamento() { return departamento; }
    public String getCargo() { return cargo; }
    public double getSalarioBase() { return salarioBase; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }
    public void setCargo(String cargo) { this.cargo = cargo; }
    public void setSalarioBase(double salarioBase) { this.salarioBase = salarioBase; }
    public void setPerfil(String perfil) { this.perfil = perfil; }
    public void setSenha(String senha) { this.senha = senha; }
}
