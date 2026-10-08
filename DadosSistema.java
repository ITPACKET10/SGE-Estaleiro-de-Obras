import java.util.ArrayList;
import java.util.List;

public class DadosSistema {

    public static ArrayList<Usuario> usuarios = new ArrayList<>();
    public static ArrayList<Material> materiais = new ArrayList<>();
    public static ArrayList<Venda> vendas = new ArrayList<>();
    public static ArrayList<Cliente> clientes = new ArrayList<>();

    static {
        carregarPadroes();
    }

    private static void carregarPadroes() {
        usuarios.add(new Presidente(
                "Emilton", "Mabessa", "123456789012A", "846434483", "Maputo", "Masculino",
                "emiltonmabessa@sge.com", "123456@"
        ));

        usuarios.add(new Gestor(
                "Samuel", "Pelembe", "123456789013B", "843210987", "Mahotas", "Masculino",
                "samuelpelembe@sge.com", "1234567"
        ));

        usuarios.add(new Funcionario(
                "Joao", "Paulo", "123456789014C", "841111111", "Beira", "Masculino",
                "joaopaulo@sge.com", "1234567"
        ));

        Material m1 = new Material("Cimento 32.5N", 320, 420, "/imagens/cimento.png", "Ligantes", "Cimentos de Moçambique"); m1.setDisponivelCaixa(true); materiais.add(m1);
        Material m2 = new Material("Tijolo 15", 1800, 18, "/imagens/tijolo.png", "Alvenaria", "Cerâmica Matola"); m2.setDisponivelCaixa(true); materiais.add(m2);
        Material m3 = new Material("Areia lavada", 85, 950, "/imagens/areia.png", "Agregados", "Fornecedor Local"); m3.setDisponivelCaixa(true); materiais.add(m3);
        Material m4 = new Material("Varão de ferro 12mm", 140, 310, "/imagens/ferro.png", "Ferragens", "AçoSul"); m4.setDisponivelCaixa(true); materiais.add(m4);
        Material m5 = new Material("Tinta branca 20L", 45, 2850, "/imagens/tinta.png", "Acabamento", "Tintas Coral"); m5.setDisponivelCaixa(true); materiais.add(m5);
    }

    public static void carregarDados() {
        carregarUsuariosFicheiro();
        carregarMateriaisFicheiro();
        carregarVendasFicheiro();
        carregarClientesFicheiro();
    }

    private static void carregarUsuariosFicheiro() {
        List<String> linhas = Arquivo.lerLinhas("usuarios.txt");
        for (String linha : linhas) {
            String[] p = linha.split(";", -1);
            if (p.length >= 9 && procurarUsuarioPorEmail(p[6]) == null) {
                String perfil = p[8];
                Usuario carregado;
                if ("Presidente".equalsIgnoreCase(perfil)) {
                    carregado = new Presidente(p[0], p[1], p[2], p[3], p[4], p[5], p[6], p[7]);
                } else if ("GESTOR".equalsIgnoreCase(perfil)) {
                    carregado = new Gestor(p[0], p[1], p[2], p[3], p[4], p[5], p[6], p[7]);
                } else if ("FUNCIONARIO".equalsIgnoreCase(perfil)) {
                    carregado = new Funcionario(p[0], p[1], p[2], p[3], p[4], p[5], p[6], p[7]);
                } else {
                    carregado = new Usuario(p[0], p[1], p[2], p[3], p[4], p[5], p[6], p[7], perfil);
                }
                if (p.length >= 12) {
                    carregado.setDepartamento(p[9]);
                    carregado.setCargo(p[10]);
                    try { carregado.setSalarioBase(Double.parseDouble(p[11])); } catch (Exception ignored) {}
                }
                usuarios.add(carregado);
            }
        }
    }

    private static void carregarMateriaisFicheiro() {
        List<String> linhas = Arquivo.lerLinhas("materiais.txt");
        for (String linha : linhas) {
            try {
                String[] p = linha.split(";", -1);
                if (p.length >= 6 && p[0] != null && !p[0].trim().isEmpty()) {
                    Material existente = procurarMaterialPorNome(p[0]);
                    int qtd = Integer.parseInt(p[1]);
                    double preco = Double.parseDouble(p[2]);
                    boolean disponivel = p.length >= 7 ? Boolean.parseBoolean(p[6]) : qtd > 0;
                    String dataRegisto = p.length >= 8 ? p[7] : "-";
                    if (existente == null) {
                        Material novo = new Material(p[0], qtd, preco, p[5], p[3], p[4]);
                        novo.setDisponivelCaixa(disponivel);
                        novo.setDataRegisto(dataRegisto);
                        materiais.add(novo);
                    } else {
                        existente.setQuantidade(qtd);
                        existente.setPreco(preco);
                        existente.setCategoria(p[3]);
                        existente.setFornecedor(p[4]);
                        existente.setImagem(p[5]);
                        existente.setDisponivelCaixa(disponivel);
                        existente.setDataRegisto(dataRegisto);
                    }
                }
            } catch (Exception ignored) {}
        }
    }

    private static void carregarVendasFicheiro() {
        List<String> linhas = Arquivo.lerLinhas("vendas.txt");
        for (String linha : linhas) {
            try {
                String[] p = linha.split(";", -1);
                if (p.length >= 6) {
                    vendas.add(new Venda(p[1], p[2], Integer.parseInt(p[3]), Double.parseDouble(p[4]), p[5], p[0]));
                } else if (p.length >= 5) {
                    vendas.add(new Venda(p[0], p[1], Integer.parseInt(p[2]), Double.parseDouble(p[3]), p[4], "-"));
                }
            } catch (Exception ignored) {}
        }
    }

    private static void carregarClientesFicheiro() {
        clientes.clear();
        for (String linha : Arquivo.lerLinhas("clientes.txt")) {
            String[] p = linha.split(";", -1);
            if (p.length >= 2) clientes.add(new Cliente(p[0], p[1], p.length>2?p[2]:"", p.length>3?p[3]:"", p.length>4?p[4]:""));
        }
    }

    public static Cliente procurarClientePorContacto(String contacto) {
        if (contacto == null) return null;
        for (Cliente c : clientes) if (c.getContacto().equalsIgnoreCase(contacto.trim())) return c;
        return null;
    }

    public static void salvarClientes() {
        List<String> linhas = new ArrayList<>();
        for (Cliente c : clientes) linhas.add(c.getNome()+";"+c.getContacto()+";"+c.getNuit()+";"+c.getEmail()+";"+c.getEndereco());
        Arquivo.substituirTudo("clientes.txt", linhas);
    }

    public static Usuario procurarUsuarioPorEmail(String email) {
        for (Usuario u : usuarios) {
            if (u.getEmail().equalsIgnoreCase(email.trim())) return u;
        }
        return null;
    }

    public static Material procurarMaterialPorNome(String nome) {
        for (Material m : materiais) {
            if (m.getNome().equalsIgnoreCase(nome.trim())) return m;
        }
        return null;
    }

    public static void salvarUsuarios() {
        List<String> linhas = new ArrayList<>();
        for (Usuario u : usuarios) {
            linhas.add(u.getNome() + ";" + u.getApelido() + ";" + u.getBi() + ";" + u.getTelefone() + ";" +
                    u.getMorada() + ";" + u.getSexo() + ";" + u.getEmail() + ";" + u.getSenha() + ";" + u.getPerfil() + ";" +
                    u.getDepartamento() + ";" + u.getCargo() + ";" + u.getSalarioBase());
        }
        Arquivo.substituirTudo("usuarios.txt", linhas);
    }

    public static void salvarMateriais() {
        List<String> linhas = new ArrayList<>();
        for (Material m : materiais) {
            if (m.getNome() == null || m.getNome().trim().isEmpty()) continue;
            linhas.add(m.getNome() + ";" + m.getQuantidade() + ";" + m.getPreco() + ";" +
                    m.getCategoria() + ";" + m.getFornecedor() + ";" + m.getImagem() + ";" +
                    m.isDisponivelCaixa() + ";" + m.getDataRegisto());
        }
        Arquivo.substituirTudo("materiais.txt", linhas);
    }
}
