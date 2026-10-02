public class Gestor extends Usuario {
    public Gestor(String nome, String apelido, String bi, String telefone, String morada,
                  String sexo, String email, String senha) {
        super(nome, apelido, bi, telefone, morada, sexo, email, senha, "GESTOR");
    }
    @Override public String[] getPermissoes() { return new String[]{"MATERIAIS", "STOCK", "TRABALHADORES", "CONSULTAR"}; }
    @Override public String[] getMenuOpcoes() { return new String[]{"Material", "Entrada de Material", "Trabalhadores"}; }
}
