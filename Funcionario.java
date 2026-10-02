public class Funcionario extends Usuario {
    public Funcionario(String nome, String apelido, String bi, String telefone, String morada,
                       String sexo, String email, String senha) {
        super(nome, apelido, bi, telefone, morada, sexo, email, senha, "FUNCIONARIO");
    }
    @Override public String[] getPermissoes() { return new String[]{"VENDAS", "CONSULTAR_MATERIAIS"}; }
    @Override public String[] getMenuOpcoes() { return new String[]{"Materiais", "Caixa / Nova Venda", "Alertas Recebidos"}; }
}
