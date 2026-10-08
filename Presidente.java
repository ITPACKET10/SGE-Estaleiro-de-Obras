public class Presidente extends Usuario {
    public Presidente(String nome, String apelido, String bi, String telefone, String morada,
                      String sexo, String email, String senha) {
        super(nome, apelido, bi, telefone, morada, sexo, email, senha, "Presidente");
    }
    @Override public String[] getPermissoes() { return new String[]{"RELATORIOS", "UTILIZADORES", "GESTAO", "CONSULTAR"}; }
    @Override public String[] getMenuOpcoes() { return new String[]{"Painel de Controlo", "Relatório Completo", "Utilizadores"}; }
}
