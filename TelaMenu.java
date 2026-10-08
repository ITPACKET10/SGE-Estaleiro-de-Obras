import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class TelaMenu extends JFrame {

    private Usuario usuarioLogado;
    private JPanel conteudo;

    private final Color azulEscuro = new Color(15, 31, 49);
    private final Color azulBotao = new Color(24, 45, 68);
    private final Color fundo = new Color(242, 245, 249);
    private final Color laranja = new Color(245, 166, 35);
    private final Color verde = new Color(0, 146, 93);
    private final Color vermelho = new Color(213, 65, 65);
    private final Color texto = new Color(35, 45, 60);
    private final Color cinzaSuave = new Color(226, 232, 240);

    public TelaMenu(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
        setTitle("SGE - Sistema de Gestão de Estaleiro de Obras");
        setSize(1280, 760);
        setMinimumSize(new Dimension(1120, 680));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(menuLateral(), BorderLayout.WEST);
        conteudo = new JPanel(new BorderLayout());
        conteudo.setBackground(fundo);
        add(conteudo, BorderLayout.CENTER);
        abrirTelaInicial();
        setVisible(true);
    }

    private JScrollPane menuLateral() {
        JPanel menu = new JPanel();
        menu.setBackground(azulEscuro);
        // A altura do menu e calculada pelo conteudo para que o JScrollPane
        // consiga rolar ate as ultimas opcoes (incluindo Sair).
        menu.setPreferredSize(null);
        menu.setMinimumSize(new Dimension(270, 0));
        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
        menu.setBorder(BorderFactory.createEmptyBorder(24, 18, 18, 18));

        JLabel logo = new JLabel("SGE OBRAS");
        logo.setForeground(Color.WHITE);
        logo.setFont(new Font("Arial", Font.BOLD, 24));
        logo.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel user = new JLabel("  " + usuarioLogado.getNome() + "  " + usuarioLogado.getPerfil());
        user.setForeground(new Color(190, 203, 216));
        user.setFont(new Font("Arial", Font.PLAIN, 13));
        user.setAlignmentX(Component.LEFT_ALIGNMENT);
        menu.add(logo);
        menu.add(Box.createVerticalStrut(6));
        menu.add(user);
        menu.add(Box.createVerticalStrut(28));

        String perfil = usuarioLogado.getPerfil();
        if (perfil.equals("Presidente")) {
            addMenuButton(menu, "Painel de Controlo", e -> mostrarPainelControlo());
            addMenuButton(menu, "Relatório Completo", e -> mostrarRelatorios());
            addMenuButton(menu, "Mensagem para Gestor", e -> mostrarMensagemPresidenteGestor());
            addMenuButton(menu, "Registar Gestor/Funcionário", e -> mostrarCadastroTrabalhadores());
            addMenuButton(menu, "Remover Gestor/Funcionário", e -> mostrarTelaRemoverFuncionario());
            addMenuButton(menu, "Departamentos", e -> mostrarDepartamentos());
            addMenuButton(menu, "Presenças e Justificações", e -> mostrarGestaoPresencas());
            addMenuButton(menu, "Salários", e -> mostrarSalarios());
            addMenuButton(menu, "Adiantamentos Salariais", e -> mostrarAdiantamentos());
            addMenuButton(menu, "Férias e Licenças", e -> mostrarGestaoFerias());
            addMenuButton(menu, "Contratos", e -> mostrarContratos());
            addMenuButton(menu, "Fornecedores", e -> mostrarFornecedores());
            addMenuButton(menu, "Compras / Aprovisionamento", e -> mostrarCompras());
            addMenuButton(menu, "Movimentos de Stock", e -> mostrarMovimentosStock());
            addMenuButton(menu, "Inventário Físico", e -> mostrarInventarioFisico());
            addMenuButton(menu, "Obras", e -> mostrarObras());
            addMenuButton(menu, "Equipa das Obras", e -> mostrarEquipaObras());
            addMenuButton(menu, "Custos das Obras", e -> mostrarCustosObras());
            addMenuButton(menu, "Requisições de Material", e -> mostrarRequisicoesMaterial());
            addMenuButton(menu, "Auditoria", e -> mostrarAuditoria());
            addMenuButton(menu, "Clientes", e -> mostrarClientes());
            addMenuButton(menu, "Devoluções de Vendas", e -> mostrarDevolucoes());
            addMenuButton(menu, "Notificações", e -> mostrarNotificacoes());
        }
        if (perfil.equals("GESTOR")) {
            addMenuButton(menu, "Material", e -> mostrarMateriais());
            addMenuButton(menu, "Registrar Material", e -> mostrarTelaRegistrarMaterial());
            addMenuButton(menu, "Entrada de Material", e -> mostrarTelaEntradaMaterial());
            addMenuButton(menu, "Atualizar Preços", e -> mostrarAtualizarPrecos());
            addMenuButton(menu, "Mensagens do Presidente", e -> mostrarMensagensRecebidas());
            addMenuButton(menu, "Alertas para Trabalhadores", e -> mostrarAlertasTrabalhadores());
            addMenuButton(menu, "Remover Material", e -> mostrarTelaRemoverMaterial());
            addMenuButton(menu, "Registar Trabalhador", e -> mostrarCadastroTrabalhadores());
            addMenuButton(menu, "Trabalhadores", e -> mostrarListaTrabalhadores());
            addMenuButton(menu, "Compras / Aprovisionamento", e -> mostrarCompras());
            addMenuButton(menu, "Inventário Físico", e -> mostrarInventarioFisico());
            addMenuButton(menu, "Obras", e -> mostrarObras());
            addMenuButton(menu, "Equipa das Obras", e -> mostrarEquipaObras());
            addMenuButton(menu, "Custos das Obras", e -> mostrarCustosObras());
            addMenuButton(menu, "Requisições de Material", e -> mostrarRequisicoesMaterial());
            addMenuButton(menu, "Clientes", e -> mostrarClientes());
            addMenuButton(menu, "Devoluções de Vendas", e -> mostrarDevolucoes());
            addMenuButton(menu, "Notificações", e -> mostrarNotificacoes());
        }
        if (perfil.equals("FUNCIONARIO")) {
            addMenuButton(menu, "Meu Painel", e -> mostrarDashboardFuncionario());
            addMenuButton(menu, "Minha Presença", e -> mostrarMinhaPresenca());
            addMenuButton(menu, "Minhas Férias", e -> mostrarMinhasFerias());
            addMenuButton(menu, "Meu Salário", e -> mostrarMeuSalario());
            addMenuButton(menu, "Meu Adiantamento", e -> mostrarMeuAdiantamento());
            addMenuButton(menu, "Minhas Tarefas", e -> mostrarMinhasTarefas());
            if (usuarioLogado.getDepartamento().equalsIgnoreCase("Vendas e Caixa") || usuarioLogado.getCargo().equalsIgnoreCase("Caixa") || usuarioLogado.getCargo().equalsIgnoreCase("Vendedor")) {
                addMenuButton(menu, "Materiais", e -> mostrarMateriais());
                addMenuButton(menu, "Caixa / Nova Venda", e -> mostrarVendas());
                addMenuButton(menu, "Devoluções", e -> mostrarDevolucoes());
                addMenuButton(menu, "Clientes", e -> mostrarClientes());
            } else if (usuarioLogado.getDepartamento().equalsIgnoreCase("Armazém e Stock")) {
                addMenuButton(menu, "Materiais / Stock", e -> mostrarMateriais());
                addMenuButton(menu, "Confirmar Entrada de Material", e -> mostrarTelaEntradaMaterial());
                addMenuButton(menu, "Movimentos de Stock", e -> mostrarMovimentosStock());
                addMenuButton(menu, "Inventário Físico", e -> mostrarInventarioFisico());
                addMenuButton(menu, "Requisições de Material", e -> mostrarRequisicoesMaterial());
            } else if (usuarioLogado.getDepartamento().equalsIgnoreCase("Operações / Obras")) {
                addMenuButton(menu, "Obras", e -> mostrarObras());
                addMenuButton(menu, "Equipa da Obra", e -> mostrarEquipaObras());
                addMenuButton(menu, "Resumo das Obras", e -> mostrarCustosObras());
                addMenuButton(menu, "Requisitar Material", e -> mostrarRequisicoesMaterial());
            } else if (usuarioLogado.getDepartamento().equalsIgnoreCase("Recursos Humanos")) {
                addMenuButton(menu, "Registar Trabalhador", e -> mostrarCadastroTrabalhadores());
                addMenuButton(menu, "Trabalhadores", e -> mostrarListaTrabalhadores());
                addMenuButton(menu, "Presenças e Justificações", e -> mostrarGestaoPresencas());
                addMenuButton(menu, "Férias e Licenças", e -> mostrarGestaoFerias());
                addMenuButton(menu, "Contratos", e -> mostrarContratos());
                addMenuButton(menu, "Adiantamentos Salariais", e -> mostrarAdiantamentos());
            } else if (usuarioLogado.getDepartamento().equalsIgnoreCase("Compras e Aprovisionamento")) {
                addMenuButton(menu, "Fornecedores", e -> mostrarFornecedores());
                addMenuButton(menu, "Compras / Aprovisionamento", e -> mostrarCompras());
            }
            addMenuButton(menu, "Alertas Recebidos", e -> mostrarMensagensRecebidas());
            addMenuButton(menu, "Notificações", e -> mostrarNotificacoes());
        }
        menu.add(Box.createVerticalStrut(12));
        addMenuButton(menu, "Sair", e -> {
            int opcao = JOptionPane.showConfirmDialog(this,
                    "Deseja sair da sua conta?", "Terminar sessão",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (opcao == JOptionPane.YES_OPTION) {
                dispose();
                new TelaLogin();
            }
        });

        // O menu pode ter muitas opções. A barra de rolagem garante que
        // todas ficam acessíveis, incluindo o botão Sair.
        JScrollPane scrollMenu = new JScrollPane(menu);
        scrollMenu.setPreferredSize(new Dimension(270, 720));
        scrollMenu.setMinimumSize(new Dimension(270, 0));
        scrollMenu.setBorder(null);
        scrollMenu.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollMenu.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollMenu.getVerticalScrollBar().setUnitIncrement(18);
        scrollMenu.getViewport().setBackground(azulEscuro);
        return scrollMenu;
    }


    private void addMenuButton(JPanel menu, String texto, java.awt.event.ActionListener acao) {
        JButton b = botaoMenu(texto); b.addActionListener(acao); menu.add(b); menu.add(Box.createVerticalStrut(10));
    }

    private JButton botaoMenu(String texto) {
        JButton botao = new JButton(texto);
        botao.setMaximumSize(new Dimension(235, 46));
        botao.setPreferredSize(new Dimension(235, 46));
        botao.setFocusPainted(false);
        botao.setForeground(Color.WHITE);
        botao.setBackground(azulBotao);
        botao.setFont(new Font("Arial", Font.BOLD, 14));
        botao.setBorder(BorderFactory.createEmptyBorder(8, 14, 8, 14));
        botao.setOpaque(true);
        botao.setContentAreaFilled(true);
        botao.setHorizontalAlignment(SwingConstants.LEFT);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) { botao.setBackground(new Color(36, 64, 91)); }
            public void mouseExited(java.awt.event.MouseEvent e) { botao.setBackground(azulBotao); }
        });
        return botao;
    }

    private void estilizarBotao(JButton botao, Color cor) {
        botao.setBackground(cor); botao.setForeground(Color.WHITE); botao.setFocusPainted(false);
        botao.setBorderPainted(false); botao.setOpaque(true); botao.setContentAreaFilled(true);
        botao.setFont(new Font("Arial", Font.BOLD, 14)); botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void abrirTelaInicial() {
        if (usuarioLogado.getPerfil().equals("Presidente")) mostrarPainelControlo();
        else if (usuarioLogado.getPerfil().equals("FUNCIONARIO")) mostrarDashboardFuncionario();
        else mostrarMateriais();
    }

    private void limparTela(String titulo) {
        conteudo.removeAll();
        JPanel topo = new JPanel(new BorderLayout(20, 0)); topo.setBackground(Color.WHITE); topo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, cinzaSuave),
                BorderFactory.createEmptyBorder(18, 28, 18, 28)));
        JPanel titulos = new JPanel(); titulos.setOpaque(false); titulos.setLayout(new BoxLayout(titulos, BoxLayout.Y_AXIS));
        JLabel label = new JLabel(titulo); label.setFont(new Font("Arial", Font.BOLD, 27)); label.setForeground(texto);
        JLabel subtitulo = new JLabel("Sistema de Gestão de Estaleiro de Obras"); subtitulo.setFont(new Font("Arial", Font.PLAIN, 12)); subtitulo.setForeground(new Color(112, 123, 136));
        titulos.add(label); titulos.add(Box.createVerticalStrut(3)); titulos.add(subtitulo);
        JPanel perfil = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0)); perfil.setOpaque(false);
        JLabel badge = new JLabel(usuarioLogado.getPerfil()); badge.setOpaque(true); badge.setBackground(new Color(255, 244, 220)); badge.setForeground(new Color(164, 101, 0)); badge.setFont(new Font("Arial", Font.BOLD, 12)); badge.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        JLabel nome = new JLabel(usuarioLogado.getNome()); nome.setFont(new Font("Arial", Font.BOLD, 13)); nome.setForeground(texto);
        perfil.add(nome); perfil.add(badge);
        topo.add(titulos, BorderLayout.WEST); topo.add(perfil, BorderLayout.EAST);
        conteudo.add(topo, BorderLayout.NORTH);
    }

    private JPanel card(String titulo, String valor, Color cor) {
        RoundedPanel card = new RoundedPanel(24, Color.WHITE);
        card.setLayout(new BorderLayout(8, 8));
        card.setBorder(BorderFactory.createEmptyBorder(18, 20, 18, 20));

        JPanel faixa = new JPanel();
        faixa.setBackground(cor);
        faixa.setPreferredSize(new Dimension(5, 1));

        JLabel t = new JLabel(titulo);
        t.setFont(new Font("Arial", Font.BOLD, 13));
        t.setForeground(new Color(100, 110, 120));

        JLabel v = new JLabel(valor);
        v.setFont(new Font("Arial", Font.BOLD, 27));
        v.setForeground(texto);

        JPanel dados = new JPanel();
        dados.setOpaque(false);
        dados.setLayout(new BoxLayout(dados, BoxLayout.Y_AXIS));
        dados.add(t);
        dados.add(Box.createVerticalStrut(9));
        dados.add(v);

        card.add(faixa, BorderLayout.WEST);
        card.add(dados, BorderLayout.CENTER);
        return card;
    }

    private JPanel tituloSecao(String titulo, String descricao) {
        JPanel p = new JPanel(new BorderLayout());
        p.setOpaque(false);
        JLabel t = new JLabel(titulo);
        t.setFont(new Font("Arial", Font.BOLD, 18));
        t.setForeground(texto);
        JLabel d = new JLabel(descricao);
        d.setFont(new Font("Arial", Font.PLAIN, 12));
        d.setForeground(new Color(112, 123, 136));
        p.add(t, BorderLayout.NORTH);
        p.add(d, BorderLayout.SOUTH);
        return p;
    }

    private JPanel painelResumoStock() {
        RoundedPanel painel = new RoundedPanel(24, Color.WHITE);
        painel.setLayout(new BorderLayout(12, 12));
        painel.setBorder(BorderFactory.createEmptyBorder(18, 20, 18, 20));
        painel.add(tituloSecao("Resumo do Stock", "Situação atual dos materiais registados"), BorderLayout.NORTH);

        int totalUnidades = 0;
        int disponiveis = 0;
        for (Material m : DadosSistema.materiais) {
            totalUnidades += Math.max(0, m.getQuantidade());
            if (m.getQuantidade() > 100) disponiveis++;
        }

        JPanel info = new JPanel(new GridLayout(3, 2, 8, 10));
        info.setOpaque(false);
        info.add(new JLabel("Materiais registados"));
        info.add(valorResumo(String.valueOf(DadosSistema.materiais.size())));
        info.add(new JLabel("Unidades em stock"));
        info.add(valorResumo(String.valueOf(totalUnidades)));
        info.add(new JLabel("Stock saudável"));
        info.add(valorResumo(String.valueOf(disponiveis)));
        painel.add(info, BorderLayout.CENTER);
        return painel;
    }

    private JLabel valorResumo(String valor) {
        JLabel l = new JLabel(valor, SwingConstants.RIGHT);
        l.setFont(new Font("Arial", Font.BOLD, 15));
        l.setForeground(texto);
        return l;
    }

    private void mostrarPainelControlo() {
        limparTela("Painel de Controlo");

        JPanel pagina = new JPanel();
        pagina.setBackground(fundo);
        pagina.setLayout(new BoxLayout(pagina, BoxLayout.Y_AXIS));
        pagina.setBorder(BorderFactory.createEmptyBorder(18, 28, 28, 28));

        JPanel boasVindas = new JPanel(new BorderLayout());
        boasVindas.setOpaque(false);
        JLabel titulo = new JLabel("Visão geral do estaleiro");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setForeground(texto);
        JLabel data = new JLabel(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        data.setFont(new Font("Arial", Font.BOLD, 12));
        data.setForeground(new Color(112, 123, 136));
        boasVindas.add(titulo, BorderLayout.WEST);
        boasVindas.add(data, BorderLayout.EAST);
        boasVindas.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
        pagina.add(boasVindas);
        pagina.add(Box.createVerticalStrut(14));

        JPanel indicadores = new JPanel(new GridLayout(2, 3, 14, 14));
        indicadores.setOpaque(false);
        indicadores.add(card("Vendas de Hoje", money(totalVendasHoje()), verde));
        indicadores.add(card("Vendas da Semana", money(totalVendasSemana()), azulBotao));
        indicadores.add(card("Vendas do Mês", money(totalVendasMes()), laranja));
        indicadores.add(card("Total Geral", money(totalVendas()), verde));
        indicadores.add(card("Número de Vendas", String.valueOf(DadosSistema.vendas.size()), azulBotao));
        indicadores.add(card("Stock Baixo", String.valueOf(contarStockBaixo()), vermelho));
        indicadores.setMaximumSize(new Dimension(Integer.MAX_VALUE, 230));
        pagina.add(indicadores);
        pagina.add(Box.createVerticalStrut(18));

        JPanel detalhes = new JPanel(new GridLayout(1, 3, 14, 14));
        detalhes.setOpaque(false);
        detalhes.add(painelAlertas());
        detalhes.add(painelMaisVendido());
        detalhes.add(painelResumoStock());
        detalhes.setMaximumSize(new Dimension(Integer.MAX_VALUE, 230));
        pagina.add(detalhes);

        JScrollPane scroll = new JScrollPane(pagina);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(fundo);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        conteudo.add(scroll, BorderLayout.CENTER);
        atualizar();
    }

    private double totalVendas() { double total = 0; for (Venda v : DadosSistema.vendas) total += v.getTotal(); return total; }
    private double totalVendasHoje() { return totalVendasPorPeriodo("DIA"); }
    private double totalVendasSemana() { return totalVendasPorPeriodo("SEMANA"); }
    private double totalVendasMes() { return totalVendasPorPeriodo("MES"); }

    private double totalVendasPorPeriodo(String periodo) {
        double total = 0;
        LocalDate hoje = LocalDate.now();
        for (Venda v : DadosSistema.vendas) {
            LocalDate dataVenda = extrairDataVenda(v.getData());
            if (dataVenda == null) continue;
            boolean contar = false;
            if (periodo.equals("DIA")) contar = dataVenda.equals(hoje);
            else if (periodo.equals("SEMANA")) contar = ChronoUnit.DAYS.between(dataVenda, hoje) >= 0 && ChronoUnit.DAYS.between(dataVenda, hoje) <= 6;
            else if (periodo.equals("MES")) contar = dataVenda.getMonthValue() == hoje.getMonthValue() && dataVenda.getYear() == hoje.getYear();
            if (contar) total += v.getTotal();
        }
        return total;
    }

    private LocalDate extrairDataVenda(String data) {
        try {
            if (data == null || data.trim().isEmpty() || data.equals("-")) return null;
            String apenasData = data.trim().split(" ")[0];
            return LocalDate.parse(apenasData, DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT));
        } catch (Exception e) { return null; }
    }

    private int contarStockBaixo() { int total = 0; for (Material m : DadosSistema.materiais) if (m.getQuantidade() <= 100) total++; return total; }

    private JPanel painelAlertas() {
        RoundedPanel painel = new RoundedPanel(24, Color.WHITE); painel.setLayout(new BorderLayout()); painel.setBorder(BorderFactory.createEmptyBorder(18,18,18,18));
        JTextArea area = new JTextArea(); area.setEditable(false); area.setOpaque(false); area.setFont(new Font("Arial", Font.PLAIN, 15)); area.setForeground(texto);
        StringBuilder sb = new StringBuilder("Alertas de Stock Baixo\n\n"); boolean encontrou = false;
        for (Material m : DadosSistema.materiais) if (m.getQuantidade() <= 100) { sb.append(" ").append(m.getNome()).append(" - ").append(m.getQuantidade()).append(" unidades\n"); encontrou = true; }
        if (!encontrou) sb.append("Todos os materiais têm stock suficiente."); area.setText(sb.toString()); painel.add(area); return painel;
    }

    private JPanel painelMaisVendido() {
        RoundedPanel painel = new RoundedPanel(24, Color.WHITE); painel.setLayout(new BorderLayout()); painel.setBorder(BorderFactory.createEmptyBorder(18,18,18,18));
        Material mais = null; for (Material m : DadosSistema.materiais) if (mais == null || m.getTotalVendido() > mais.getTotalVendido()) mais = m;
        JTextArea area = new JTextArea(); area.setEditable(false); area.setOpaque(false); area.setFont(new Font("Arial", Font.BOLD, 16)); area.setForeground(texto);
        if (mais == null || mais.getTotalVendido() == 0) area.setText("Material Mais Vendido\n\nAinda não existem vendas.");
        else area.setText("Material Mais Vendido\n\n" + mais.getNome() + "\nQuantidade vendida: " + mais.getTotalVendido());
        painel.add(area); return painel;
    }

    private void mostrarMateriais() {
        limparTela("Materiais & Stock");
        JPanel painel = corpo();

        JPanel resumo = new JPanel(new GridLayout(1, 4, 14, 14));
        resumo.setOpaque(false);
        int totalUnidades = 0, stockBaixo = 0, disponiveis = 0;
        double valorStock = 0;
        for (Material m : DadosSistema.materiais) {
            if (!validarTextoObrigatorio(m.getNome())) continue;
            totalUnidades += m.getQuantidade();
            valorStock += m.getQuantidade() * m.getPreco();
            if (m.getQuantidade() <= 100) stockBaixo++;
            if (m.getQuantidade() > 0 && m.isDisponivelCaixa()) disponiveis++;
        }
        resumo.add(cardResumoMaterial("TIPOS DE MATERIAIS", String.valueOf(DadosSistema.materiais.size()), "Cadastrados no sistema", azulEscuro));
        resumo.add(cardResumoMaterial("UNIDADES EM STOCK", String.valueOf(totalUnidades), "Quantidade total disponível", verde));
        resumo.add(cardResumoMaterial("STOCK BAIXO", String.valueOf(stockBaixo), "Materiais que precisam atenção", vermelho));
        resumo.add(cardResumoMaterial("VALOR DO STOCK", money(valorStock), disponiveis + " disponíveis no caixa", laranja));

        RoundedPanel barra = new RoundedPanel(22, Color.WHITE);
        barra.setLayout(new BorderLayout(12, 0));
        barra.setBorder(BorderFactory.createEmptyBorder(12, 16, 12, 16));
        JLabel info = new JLabel("Visão geral dos materiais e níveis de stock");
        info.setFont(new Font("Arial", Font.BOLD, 15)); info.setForeground(texto);
        JTextField pesquisa = new JTextField();
        pesquisa.setPreferredSize(new Dimension(300, 38));
        estilizarCampoFormulario(pesquisa);
        pesquisa.setToolTipText("Pesquisar material ou categoria");
        barra.add(info, BorderLayout.WEST); barra.add(pesquisa, BorderLayout.EAST);

        JPanel grid = new JPanel(new GridLayout(0, 3, 16, 16)); grid.setBackground(fundo);
        Runnable carregarCards = () -> {
            grid.removeAll();
            String termo = pesquisa.getText().trim().toLowerCase();
            for (Material m : DadosSistema.materiais) {
                if (!validarTextoObrigatorio(m.getNome())) continue;
                String alvo = (m.getNome()+" "+nvl(m.getCategoria())).toLowerCase();
                if (termo.isEmpty() || alvo.contains(termo)) grid.add(materialCard(m));
            }
            grid.revalidate(); grid.repaint();
        };
        carregarCards.run();
        pesquisa.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { carregarCards.run(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { carregarCards.run(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { carregarCards.run(); }
        });

        JScrollPane scrollCards = new JScrollPane(grid); scrollCards.setBorder(null); scrollCards.getViewport().setBackground(fundo);
        JScrollPane scrollTabela = new JScrollPane(tabelaMateriais()); scrollTabela.setBorder(BorderFactory.createLineBorder(new Color(225,230,237)));
        JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scrollCards, scrollTabela); split.setResizeWeight(.58); split.setBorder(null); split.setDividerSize(8);

        JPanel centro = new JPanel(new BorderLayout(0, 14)); centro.setOpaque(false); centro.add(barra, BorderLayout.NORTH); centro.add(split, BorderLayout.CENTER);
        painel.add(resumo, BorderLayout.NORTH); painel.add(centro, BorderLayout.CENTER);
        conteudo.add(painel, BorderLayout.CENTER); atualizar();
    }

    private JPanel cardResumoMaterial(String titulo, String valor, String detalhe, Color destaque) {
        RoundedPanel card = new RoundedPanel(22, Color.WHITE);
        card.setLayout(new BorderLayout(0, 6)); card.setBorder(BorderFactory.createEmptyBorder(15,17,15,17));
        JLabel t = new JLabel(titulo); t.setFont(new Font("Arial", Font.BOLD, 11)); t.setForeground(new Color(110,120,132));
        JLabel v = new JLabel(valor); v.setFont(new Font("Arial", Font.BOLD, 23)); v.setForeground(destaque);
        JLabel d = new JLabel(detalhe); d.setFont(new Font("Arial", Font.PLAIN, 11)); d.setForeground(new Color(125,132,142));
        card.add(t, BorderLayout.NORTH); card.add(v, BorderLayout.CENTER); card.add(d, BorderLayout.SOUTH); return card;
    }

    private JPanel materialCard(Material m) {
        RoundedPanel card = new RoundedPanel(24, Color.WHITE); card.setLayout(new BorderLayout(12,10)); card.setBorder(BorderFactory.createEmptyBorder(14,14,14,14));
        JLabel img = new JLabel(criarIconeMaterial(m, 120, 82)); img.setHorizontalAlignment(JLabel.CENTER);
        JLabel nome = new JLabel(m.getNome()); nome.setFont(new Font("Arial", Font.BOLD, 16)); nome.setForeground(texto);
        JLabel detalhes = new JLabel("<html>Categoria: " + nvl(m.getCategoria()) + "<br>Stock: <b>" + m.getQuantidade() + "</b> unidades<br>Preço: <b>" + money(m.getPreco()) + "</b></html>");
        detalhes.setFont(new Font("Arial", Font.PLAIN, 13)); detalhes.setForeground(new Color(86,96,110));
        JLabel estado = new JLabel(m.getQuantidade() <= 100 ? "  STOCK BAIXO  " : "  DISPONÍVEL  "); estado.setOpaque(true); estado.setForeground(Color.WHITE); estado.setBackground(m.getQuantidade() <= 100 ? vermelho : verde); estado.setFont(new Font("Arial", Font.BOLD, 11));
        JPanel sul = new JPanel(new BorderLayout()); sul.setOpaque(false); sul.add(detalhes, BorderLayout.CENTER); sul.add(estado, BorderLayout.SOUTH);
        card.add(img, BorderLayout.NORTH); card.add(nome, BorderLayout.CENTER); card.add(sul, BorderLayout.SOUTH); return card;
    }

    private ImageIcon criarIconeMaterial(Material m, int w, int h) {
        try {
            String path = m.getImagem(); ImageIcon ic = null;
            if (path != null && !path.trim().isEmpty()) {
                java.net.URL url = getClass().getResource(path); if (url != null) ic = new ImageIcon(url); else if (new File(path).exists()) ic = new ImageIcon(path);
            }
            if (ic != null && ic.getIconWidth() > 0) return new ImageIcon(ic.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH));
        } catch (Exception ignored) {}
        return desenharIconeMaterial(m.getNome(), w, h);
    }

    private ImageIcon desenharIconeMaterial(String nome, int w, int h) {
        java.awt.image.BufferedImage bi = new java.awt.image.BufferedImage(w, h, java.awt.image.BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bi.createGraphics(); g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(new Color(230,235,240)); g.fillRoundRect(0,0,w,h,22,22); g.setColor(laranja); g.fillRoundRect(12,18,w-24,h-36,14,14);
        g.setColor(azulEscuro); g.setFont(new Font("Arial", Font.BOLD, 13)); String s = nome.length()>14?nome.substring(0,14):nome; g.drawString(s, 16, h/2+5); g.dispose(); return new ImageIcon(bi);
    }

    private void mostrarTelaRegistrarMaterial() {
        limparTela("Registo de Material");

        JPanel principal = corpo();
        principal.setLayout(new BorderLayout(18,18));

        RoundedPanel cabecalho = new RoundedPanel(24, Color.WHITE);
        cabecalho.setLayout(new BorderLayout());
        cabecalho.setBorder(BorderFactory.createEmptyBorder(18,22,18,22));
        JLabel titulo = new JLabel("Registar dados do material que entrou na empresa");
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setForeground(azulEscuro);
        JLabel subtitulo = new JLabel("Primeiro faça Entrada de Material. Depois informe aqui preço e categoria para ficar disponível no caixa.");
        subtitulo.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitulo.setForeground(new Color(95,105,120));
        cabecalho.add(titulo, BorderLayout.NORTH);
        cabecalho.add(subtitulo, BorderLayout.SOUTH);

        JPanel formulario = formulario(4, 2);
        JComboBox<String> nome = comboMateriaisComEntrada();
        JTextField preco = new JTextField();
        JComboBox<String> categoria = new JComboBox<>(new String[]{"Ligantes", "Alvenaria", "Agregados", "Ferragens", "Acabamento", "Ferramentas", "Canalização", "Eletricidade", "Geral"});

        addCampoCombo(formulario, "Material que entrou *", nome);
        addCampo(formulario, "Preço unitário *", preco);
        addCampoCombo(formulario, "Categoria", categoria);

        JButton guardar = new JButton("Guardar Dados do Material");
        estilizarBotao(guardar, verde);
        JButton limpar = new JButton("Limpar Campos");
        estilizarBotao(limpar, vermelho);
        formulario.add(guardar);
        formulario.add(limpar);

        guardar.addActionListener(e -> {
            try {
                if (nome.getSelectedItem() == null) { msg("Faça primeiro a entrada do material."); return; }
                if (!validarValorPositivoMaiorQueZero(preco.getText())) { msg("Preço inválido."); return; }

                Material material = procurarMaterial(nome.getSelectedItem().toString());
                if (material == null) { msg("Material não encontrado."); return; }

                double valor = Double.parseDouble(preco.getText().trim());
                String cat = categoria.getSelectedItem().toString();
                material.setPreco(valor);
                material.setCategoria(cat);
                material.setDisponivelCaixa(material.getQuantidade() > 0 && valor > 0);
                if (material.getDataRegisto() == null || material.getDataRegisto().equals("-")) material.setDataRegisto(dataAtual());
                DadosSistema.salvarMateriais();
                Arquivo.salvar("materiais_registados.txt", dataAtual()+";"+material.getNome()+";"+cat+";"+material.getFornecedor()+";"+valor+";"+usuarioLogado.getNome()+" "+usuarioLogado.getApelido());
                Arquivo.salvar("logs.txt", dataAtual() + ";Dados do material registados;" + material.getNome() + ";" + usuarioLogado.getNome() + " " + usuarioLogado.getApelido());
                msg("Material registado com sucesso. Já pode aparecer no caixa se tiver stock.");
                mostrarTelaRegistrarMaterial();
            } catch (Exception ex) {
                Arquivo.salvar("erros.txt", "Erro ao registar material: " + ex.getMessage());
                msg("Erro: verifique os dados do material.");
            }
        });

        limpar.addActionListener(e -> { preco.setText(""); categoria.setSelectedIndex(0); });

        JPanel topo = new JPanel(new BorderLayout(0,14));
        topo.setOpaque(false);
        topo.add(cabecalho, BorderLayout.NORTH);
        topo.add(formulario, BorderLayout.CENTER);
        principal.add(topo, BorderLayout.NORTH);
        principal.add(new JScrollPane(tabelaMateriais()), BorderLayout.CENTER);
        conteudo.add(principal, BorderLayout.CENTER);
        atualizar();
    }


    private JTable tabelaMateriais() {
        String[] colunas = {"Data Registo", "Material", "Categoria", "Quantidade", "Preço", "Fornecedor", "Caixa", "Estado"};
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0) { public boolean isCellEditable(int r, int c) { return false; } };
        for (Material m : DadosSistema.materiais) if (validarTextoObrigatorio(m.getNome())) modelo.addRow(new Object[]{nvl(m.getDataRegisto()), m.getNome(), nvl(m.getCategoria()), m.getQuantidade(), money(m.getPreco()), nvl(m.getFornecedor()), m.isDisponivelCaixa() ? "Sim" : "Não", m.getQuantidade() <= 100 ? "Stock Baixo" : "Disponível"});
        return estilizarTabela(new JTable(modelo));
    }

    private void mostrarTelaEntradaMaterial() {
        limparTela("Receção de Material no Armazém");
        JPanel principal = corpo();
        principal.setLayout(new BorderLayout(18,18));

        RoundedPanel formulario = new RoundedPanel(24, Color.WHITE);
        formulario.setLayout(new GridLayout(5,2,12,12));
        formulario.setBorder(BorderFactory.createEmptyBorder(22,22,22,22));

        JComboBox<String> compraPendente = new JComboBox<>();
        compraPendente.addItem("-- Selecionar compra pendente --");
        java.util.Set<String> recebidas = new java.util.HashSet<>();
        for(String r:Arquivo.lerLinhas("rececoes_compras.txt")){String[]x=r.split(";",-1);if(x.length>0)recebidas.add(x[0]);}
        for(String l:Arquivo.lerLinhas("compras.txt")){
            String[]x=l.split(";",-1);
            if(x.length>=7){String id=x.length>=8?x[7]:"LEG-"+Math.abs(l.hashCode()); if(!recebidas.contains(id)) compraPendente.addItem(id+" | "+x[2]+" | "+x[3]+" un. | "+x[1]);}
        }
        JTextField nomeMaterial = new JTextField();
        JTextField quantidadeEntrada = new JTextField();
        JTextField fornecedor = new JTextField();
        nomeMaterial.setEditable(false); quantidadeEntrada.setEditable(false); fornecedor.setEditable(false);

        formulario.add(labelFormulario("Compra pendente *")); formulario.add(compraPendente);
        addCampo(formulario,"Material",nomeMaterial);
        addCampo(formulario,"Quantidade",quantidadeEntrada);
        addCampo(formulario,"Fornecedor",fornecedor);

        JButton guardar = new JButton("Confirmar Receção Física");
        estilizarBotao(guardar, verde);
        JButton limpar = new JButton("Limpar");
        estilizarBotao(limpar, vermelho);
        formulario.add(guardar); formulario.add(limpar);

        compraPendente.addActionListener(e->{
            if(compraPendente.getSelectedIndex()<=0){nomeMaterial.setText("");quantidadeEntrada.setText("");fornecedor.setText("");return;}
            String id=compraPendente.getSelectedItem().toString().split(" \\| ")[0];
            for(String l:Arquivo.lerLinhas("compras.txt")){String[]x=l.split(";",-1);if(x.length>=7){String cid=x.length>=8?x[7]:"LEG-"+Math.abs(l.hashCode());if(cid.equals(id)){nomeMaterial.setText(x[2]);quantidadeEntrada.setText(x[3]);fornecedor.setText(x[1]);break;}}}
        });

        guardar.addActionListener(e -> {
            if(compraPendente.getSelectedIndex()<=0){msg("Selecione uma compra pendente de receção.");return;}
            String id=compraPendente.getSelectedItem().toString().split(" \\| ")[0];
            for(String r:Arquivo.lerLinhas("rececoes_compras.txt")){String[]x=r.split(";",-1);if(x.length>0&&x[0].equals(id)){msg("Esta compra já foi recebida no armazém.");return;}}
            String nome=nomeMaterial.getText().trim(), forn=fornecedor.getText().trim(); int qtd=Integer.parseInt(quantidadeEntrada.getText().trim());
            Material material=procurarMaterial(nome);
            if(material==null){material=new Material(nome,0,0,"","Pendente",forn);material.setDisponivelCaixa(false);material.setDataRegisto(dataAtual());DadosSistema.materiais.add(material);}
            material.adicionarQuantidade(qtd); material.setFornecedor(forn); if(material.getPreco()>0)material.setDisponivelCaixa(true);
            DadosSistema.salvarMateriais();
            Arquivo.salvar("rececoes_compras.txt",new RececaoMaterial(id,dataAtual(),nome,qtd,forn,usuarioLogado.getEmail()).serializar());
            Arquivo.salvar("entradas.txt",dataAtual()+";"+nome+";"+qtd+";"+forn+";"+usuarioLogado.getEmail()+";Receção de Compra "+id);
            Arquivo.salvar("movimentos_stock.txt",new MovimentoStock(dataAtual(),nome,"ENTRADA - Receção de Compra",qtd,"Compra "+id+" | "+forn,usuarioLogado.getEmail()).serializar());
            Auditoria.registar(usuarioLogado.getEmail(),"CONFIRMOU RECEÇÃO DE MATERIAL",id+" | "+nome+" | qtd "+qtd);
            msg("Receção confirmada.\nO material entrou fisicamente no stock.\nStock atual: "+material.getQuantidade());
            mostrarTelaEntradaMaterial();
        });
        limpar.addActionListener(e->{compraPendente.setSelectedIndex(0);});
        principal.add(formulario,BorderLayout.NORTH);
        principal.add(new JScrollPane(tabelaEntradasRelatorio()),BorderLayout.CENTER);
        conteudo.add(principal,BorderLayout.CENTER); atualizar();
    }

    private Material procurarMaterial(String nome) { for (Material m : DadosSistema.materiais) if (m.getNome().equalsIgnoreCase(nome.trim())) return m; return null; }

    private void mostrarVendas() {
        limparTela("Caixa do Vendedor");
        JPanel principal = corpo();
        principal.setLayout(new BorderLayout(14,14));

        double totalHoje = 0;
        int qtdVendas = DadosSistema.vendas.size();
        int unidadesVendidas = 0;
        for (Venda v : DadosSistema.vendas) {
            totalHoje += v.getTotal();
            unidadesVendidas += v.getQuantidade();
        }
        JPanel resumo = new JPanel(new GridLayout(1,3,12,0));
        resumo.setOpaque(false);
        resumo.add(cardResumoMaterial("Total vendido", money(totalHoje), "Valor acumulado das vendas", verde));
        resumo.add(cardResumoMaterial("Vendas realizadas", String.valueOf(qtdVendas), "Operações registadas", azulBotao));
        resumo.add(cardResumoMaterial("Unidades vendidas", String.valueOf(unidadesVendidas), "Materiais movimentados", laranja));
        principal.add(resumo, BorderLayout.NORTH);

        JPanel caixa = new JPanel(new BorderLayout(14,14));
        caixa.setOpaque(false);
        caixa.add(criarPainelProdutosCaixa(), BorderLayout.CENTER);
        caixa.add(criarPainelCaixaReal(), BorderLayout.EAST);

        JScrollPane areaCaixa = new JScrollPane(caixa);
        areaCaixa.setBorder(null);
        areaCaixa.getViewport().setBackground(fundo);
        areaCaixa.getHorizontalScrollBar().setUnitIncrement(18);
        areaCaixa.getVerticalScrollBar().setUnitIncrement(18);

        JPanel historico = new JPanel(new BorderLayout(8,8));
        historico.setOpaque(false);
        JLabel histTitulo = new JLabel("Últimas vendas registadas");
        histTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        histTitulo.setForeground(texto);
        historico.add(histTitulo, BorderLayout.NORTH);
        JScrollPane vendasScroll = new JScrollPane(tabelaVendas());
        vendasScroll.setPreferredSize(new Dimension(500, 150));
        vendasScroll.setBorder(BorderFactory.createLineBorder(new Color(222,227,232)));
        historico.add(vendasScroll, BorderLayout.CENTER);
        principal.add(areaCaixa, BorderLayout.CENTER);
        principal.add(historico, BorderLayout.SOUTH);
        conteudo.add(principal, BorderLayout.CENTER);
        atualizar();
    }


    private JPanel criarPainelProdutosCaixa() {
        RoundedPanel p = new RoundedPanel(24, Color.WHITE);
        p.setLayout(new BorderLayout(12,12));
        p.setBorder(BorderFactory.createEmptyBorder(18,18,18,18));
        JPanel cab = new JPanel(new GridLayout(0,1,2,2));
        cab.setOpaque(false);
        JLabel title = new JLabel("Materiais disponíveis para venda");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(texto);
        JLabel sub = new JLabel("Selecione um material e adicione-o ao carrinho do cliente.");
        sub.setFont(new Font("Arial", Font.PLAIN, 13));
        sub.setForeground(new Color(105,115,125));
        cab.add(title); cab.add(sub);
        p.add(cab, BorderLayout.NORTH);
        JPanel grid = new JPanel(new GridLayout(0,2,12,12));
        grid.setOpaque(false);
        boolean temDisponivel = false;
        for (Material m : DadosSistema.materiais) {
            if (validarTextoObrigatorio(m.getNome()) && m.getQuantidade() > 0 && m.getPreco() > 0 && m.isDisponivelCaixa()) {
                grid.add(materialCard(m));
                temDisponivel = true;
            }
        }
        if (!temDisponivel) {
            JLabel vazio = new JLabel("Nenhum material disponível no caixa. O material precisa ter entrada e preço definido pelo gestor.", JLabel.CENTER);
            vazio.setFont(new Font("Arial", Font.BOLD, 15));
            vazio.setForeground(new Color(95,105,120));
            grid.add(vazio);
        }
        JScrollPane sp = new JScrollPane(grid);
        sp.setBorder(null);
        sp.getViewport().setBackground(Color.WHITE);
        p.add(sp, BorderLayout.CENTER);
        return p;
    }


    private JPanel criarPainelCaixaReal() {
        RoundedPanel caixa = new RoundedPanel(28, new Color(28, 42, 58));
        caixa.setMinimumSize(new Dimension(430, 560));
        caixa.setPreferredSize(new Dimension(450, 590));
        caixa.setLayout(new BorderLayout(10,10));
        caixa.setBorder(BorderFactory.createEmptyBorder(16,18,16,18));

        JPanel topoCaixa = new JPanel(new GridLayout(0,1,2,2));
        topoCaixa.setOpaque(false);
        JLabel titulo = new JLabel("CAIXA DE VENDA", JLabel.CENTER);
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 21));
        JLabel subtitulo = new JLabel("Nova operação", JLabel.CENTER);
        subtitulo.setForeground(new Color(185,195,205));
        subtitulo.setFont(new Font("Arial", Font.PLAIN, 12));
        topoCaixa.add(titulo); topoCaixa.add(subtitulo);
        caixa.add(topoCaixa, BorderLayout.NORTH);

        JPanel formulario = new JPanel(new GridLayout(0,1,8,8));
        formulario.setOpaque(false);

        JTextArea visor = new JTextArea("SGE ESTALEIRO\nAdicione vários materiais antes de finalizar.");
        visor.setEditable(false);
        visor.setBackground(new Color(190, 229, 190));
        visor.setForeground(new Color(20,50,25));
        visor.setFont(new Font("Consolas", Font.BOLD, 13));
        visor.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        formulario.add(visor);

        JComboBox<String> cliente = new JComboBox<>();
        cliente.addItem("SEM REGISTO");
        for (Cliente c : DadosSistema.clientes) cliente.addItem(c.getNome() + " | " + c.getContacto());
        cliente.setEditable(true);
        JComboBox<String> material = comboMateriaisDisponiveis();
        JTextField quantidade = new JTextField();
        JTextField precoUnitario = new JTextField();
        precoUnitario.setEditable(false);
        JTextField vendedor = new JTextField(usuarioLogado.getNome()+" "+usuarioLogado.getApelido());
        vendedor.setEditable(false);

        formulario.add(comboComLabel("Cliente registado / venda avulsa", cliente));
        formulario.add(comboComLabel("Material em depósito", material));
        formulario.add(campoComLabel("Quantidade", quantidade));
        formulario.add(campoComLabel("Preço unitário", precoUnitario));
        formulario.add(campoComLabel("Vendedor", vendedor));

        String[] colunas = {"Material", "Qtd", "Preço", "Total"};
        DefaultTableModel modeloCarrinho = new DefaultTableModel(colunas, 0) { public boolean isCellEditable(int r, int c) { return false; } };
        JTable tabelaCarrinho = estilizarTabela(new JTable(modeloCarrinho));
        JScrollPane scrollCarrinho = new JScrollPane(tabelaCarrinho);
        scrollCarrinho.setPreferredSize(new Dimension(400, 130));

        JLabel totalCarrinho = new JLabel("Total: " + money(0), JLabel.RIGHT);
        totalCarrinho.setForeground(Color.WHITE);
        totalCarrinho.setFont(new Font("Arial", Font.BOLD, 18));

        JPanel botoes = new JPanel(new GridLayout(2,2,10,8));
        botoes.setOpaque(false);
        JButton buscar = new JButton("BUSCAR");
        estilizarBotao(buscar, laranja);
        JButton adicionar = new JButton("ADICIONAR");
        estilizarBotao(adicionar, azulBotao);
        JButton remover = new JButton("REMOVER");
        estilizarBotao(remover, vermelho);
        JButton finalizar = new JButton("FINALIZAR");
        estilizarBotao(finalizar, verde);
        botoes.add(buscar); botoes.add(adicionar); botoes.add(remover); botoes.add(finalizar);

        JPanel centro = new JPanel(new BorderLayout(8,8));
        centro.setOpaque(false);
        centro.add(formulario, BorderLayout.NORTH);
        centro.add(scrollCarrinho, BorderLayout.CENTER);
        centro.add(totalCarrinho, BorderLayout.SOUTH);
        caixa.add(centro, BorderLayout.CENTER);
        caixa.add(botoes, BorderLayout.SOUTH);

        Runnable atualizarTotalCarrinho = () -> {
            double soma = 0;
            for (int i = 0; i < modeloCarrinho.getRowCount(); i++) soma += Double.parseDouble(modeloCarrinho.getValueAt(i, 3).toString());
            totalCarrinho.setText("Total: " + money(soma));
        };

        buscar.addActionListener(e -> {
            if (material.getSelectedItem() == null) { msg("Não há material disponível em depósito para vender."); return; }
            Material m = procurarMaterial(material.getSelectedItem().toString());
            if (m == null || m.getQuantidade() <= 0) { visor.setText("MATERIAL SEM STOCK"); msg("Material sem stock no depósito."); }
            else { precoUnitario.setText(String.valueOf(m.getPreco())); visor.setText("ITEM: "+m.getNome()+"\nSTOCK: "+m.getQuantidade()+"\nPREÇO: "+money(m.getPreco())); }
        });

        adicionar.addActionListener(e -> {
            if (material.getSelectedItem() == null) { msg("Selecione o material."); return; }
            if (!validarInteiroPositivoMaiorQueZero(quantidade.getText())) { msg("Quantidade inválida."); return; }
            Material m = procurarMaterial(material.getSelectedItem().toString());
            if (m == null) { msg("Material não encontrado."); return; }
            int qtd = Integer.parseInt(quantidade.getText().trim());
            int qtdJaNoCarrinho = quantidadeNoCarrinho(modeloCarrinho, m.getNome());
            if (qtd + qtdJaNoCarrinho > m.getQuantidade()) { msg("Stock insuficiente. Disponível: " + m.getQuantidade() + ". Já no carrinho: " + qtdJaNoCarrinho); return; }
            double preco = m.getPreco();
            if (preco <= 0) { msg("Este material ainda não tem preço registado."); return; }
            double total = qtd * preco;
            modeloCarrinho.addRow(new Object[]{m.getNome(), qtd, String.valueOf(preco), String.valueOf(total)});
            quantidade.setText("");
            precoUnitario.setText(String.valueOf(preco));
            visor.setText("ADICIONADO:\n" + m.getNome() + "\nQtd: " + qtd + "\nTotal: " + money(total));
            atualizarTotalCarrinho.run();
        });

        remover.addActionListener(e -> {
            int linha = tabelaCarrinho.getSelectedRow();
            if (linha < 0) { msg("Selecione um item do carrinho para remover."); return; }
            modeloCarrinho.removeRow(linha);
            atualizarTotalCarrinho.run();
        });

        finalizar.addActionListener(e -> {
            Object escolhido = cliente.getEditor().getItem();
            String nomeCliente = escolhido == null ? "SEM REGISTO" : escolhido.toString().trim();
            if (nomeCliente.contains(" | ")) nomeCliente = nomeCliente.substring(0, nomeCliente.indexOf(" | ")).trim();
            realizarVendaCarrinho(nomeCliente, modeloCarrinho, vendedor.getText());
        });
        return caixa;
    }

    private int quantidadeNoCarrinho(DefaultTableModel modelo, String material) {
        int total = 0;
        for (int i = 0; i < modelo.getRowCount(); i++) {
            if (modelo.getValueAt(i, 0).toString().equalsIgnoreCase(material)) total += Integer.parseInt(modelo.getValueAt(i, 1).toString());
        }
        return total;
    }

    private void addLabel(JPanel p, String t, int x, int y) { JLabel l = new JLabel(t); l.setForeground(new Color(220,225,230)); l.setFont(new Font("Arial", Font.BOLD, 12)); l.setBounds(x,y,120,22); p.add(l); }
    private JTextField field(String text, int x, int y, int w) { JTextField f = new JTextField(text); f.setBounds(x,y,w,38); f.setFont(new Font("Arial", Font.PLAIN, 14)); f.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(60,80,100)), BorderFactory.createEmptyBorder(6,10,6,10))); return f; }

    private JTable tabelaVendas() {
        String[] colunas = {"Data", "Cliente", "Material", "Quantidade", "Total", "Vendedor"};
        DefaultTableModel modelo = new DefaultTableModel(colunas,0) { public boolean isCellEditable(int r,int c){return false;} };
        for (Venda v : DadosSistema.vendas) modelo.addRow(new Object[]{v.getData(), v.getCliente(), v.getMaterial(), v.getQuantidade(), money(v.getTotal()), v.getVendedor()});
        return estilizarTabela(new JTable(modelo));
    }


    private void realizarVendaFormulario(String cliente, String nomeMaterial, String qtdTexto, String precoTexto, String vendedor) {
        try {
            if (!validarTextoObrigatorio(nomeMaterial)) { msg("Informe o material."); return; }
            if (!validarInteiroPositivoMaiorQueZero(qtdTexto)) { msg("Quantidade inválida."); return; }
            Material material = procurarMaterial(nomeMaterial); if (material == null) { msg("Material não encontrado!"); return; }
            if (material.getPreco() <= 0) { msg("Este material ainda não tem preço registado."); return; }
            int qtd = Integer.parseInt(qtdTexto); if (qtd > material.getQuantidade()) { msg("Stock insuficiente."); return; }
            double preco = material.getPreco();
            double total = qtd * preco;
            if (material.vender(qtd)) {
                material.setDisponivelCaixa(material.getQuantidade() > 0 && material.getPreco() > 0);
                DadosSistema.vendas.add(new Venda(cliente, material.getNome(), qtd, total, vendedor, dataAtual()));
                Arquivo.salvar("vendas.txt", dataAtual()+";"+cliente+";"+material.getNome()+";"+qtd+";"+total+";"+vendedor);
                Arquivo.salvar("movimentos_stock.txt", new MovimentoStock(dataAtual(), material.getNome(), "SAÍDA - Venda", qtd, "Venda a " + cliente, usuarioLogado.getEmail()).serializar());
                Auditoria.registar(usuarioLogado.getEmail(), "VENDA", material.getNome()+" | Qtd: "+qtd+" | Cliente: "+cliente+" | Total: "+money(total));
                DadosSistema.salvarMateriais();
                gerarFaturaPDF(cliente, material.getNome(), qtd, preco, total, vendedor);
                msg("Venda realizada com sucesso!\nPreço aplicado: " + money(preco) + "\nTotal: " + money(total));
                mostrarVendas();
            }
        } catch (Exception e) { Arquivo.salvar("erros.txt", "Erro ao realizar venda: " + e.getMessage()); msg("Erro ao realizar venda."); }
    }

    private void realizarVendaCarrinho(String cliente, DefaultTableModel modeloCarrinho, String vendedor) {
        try {
            if (modeloCarrinho.getRowCount() == 0) { msg("Adicione pelo menos um material ao carrinho."); return; }
            if (!validarTextoObrigatorio(cliente)) cliente = "SEM REGISTO";

            for (int i = 0; i < modeloCarrinho.getRowCount(); i++) {
                String nomeMaterial = modeloCarrinho.getValueAt(i, 0).toString();
                int qtd = Integer.parseInt(modeloCarrinho.getValueAt(i, 1).toString());
                Material m = procurarMaterial(nomeMaterial);
                if (m == null) { msg("Material não encontrado: " + nomeMaterial); return; }
                if (qtd > m.getQuantidade()) { msg("Stock insuficiente para " + nomeMaterial + ". Disponível: " + m.getQuantidade()); return; }
            }

            String dataVenda = dataAtual();
            double totalGeral = 0;
            StringBuilder itensFatura = new StringBuilder();

            for (int i = 0; i < modeloCarrinho.getRowCount(); i++) {
                String nomeMaterial = modeloCarrinho.getValueAt(i, 0).toString();
                int qtd = Integer.parseInt(modeloCarrinho.getValueAt(i, 1).toString());
                Material m = procurarMaterial(nomeMaterial);
                double preco = m.getPreco();
                double total = qtd * preco;
                if (m.vender(qtd)) {
                    m.setDisponivelCaixa(m.getQuantidade() > 0 && m.getPreco() > 0);
                    DadosSistema.vendas.add(new Venda(cliente, m.getNome(), qtd, total, vendedor, dataVenda));
                    Arquivo.salvar("vendas.txt", dataVenda+";"+cliente+";"+m.getNome()+";"+qtd+";"+total+";"+vendedor);
                    Arquivo.salvar("movimentos_stock.txt", new MovimentoStock(dataVenda, m.getNome(), "SAÍDA - Venda", qtd, "Venda a " + cliente, usuarioLogado.getEmail()).serializar());
                    itensFatura.append(m.getNome()).append(" | Qtd: ").append(qtd).append(" | Preço: ").append(money(preco)).append(" | Total: ").append(money(total)).append("\n");
                    totalGeral += total;
                }
            }

            DadosSistema.salvarMateriais();
            Auditoria.registar(usuarioLogado.getEmail(), "VENDA", "Cliente: "+cliente+" | Itens: "+modeloCarrinho.getRowCount()+" | Total: "+money(totalGeral));
            gerarFaturaCarrinhoPDF(cliente, itensFatura.toString(), totalGeral, vendedor, dataVenda);
            msg("Venda finalizada com sucesso!\nTotal geral: " + money(totalGeral));
            mostrarVendas();
        } catch (Exception e) {
            Arquivo.salvar("erros.txt", "Erro ao finalizar venda com vários materiais: " + e.getMessage());
            msg("Erro ao finalizar venda.");
        }
    }

    private void gerarFaturaPDF(String cliente, String material, int quantidade, double preco, double total, String vendedor) {
        try { String nomeFicheiro = "fatura_" + System.currentTimeMillis() + ".txt"; java.io.PrintWriter writer = new java.io.PrintWriter(nomeFicheiro);
            writer.println("SGE - FATURA DE VENDA"); writer.println("--------------------------------"); writer.println("Vendedor: " + vendedor); writer.println("Cliente: " + cliente); writer.println("Material: " + material); writer.println("Quantidade: " + quantidade); writer.println("Preço unitário: " + money(preco)); writer.println("TOTAL A PAGAR: " + money(total)); writer.close(); msg("Fatura criada: " + nomeFicheiro);
        } catch (Exception e) { msg("Erro ao criar fatura."); }
    }

    private void gerarFaturaCarrinhoPDF(String cliente, String itens, double totalGeral, String vendedor, String dataVenda) {
        try {
            String nomeFicheiro = "fatura_" + System.currentTimeMillis() + ".txt";
            java.io.PrintWriter writer = new java.io.PrintWriter(nomeFicheiro);
            writer.println("SGE - FATURA DE VENDA");
            writer.println("--------------------------------");
            writer.println("Data: " + dataVenda);
            writer.println("Vendedor: " + vendedor);
            writer.println("Cliente: " + cliente);
            writer.println("--------------------------------");
            writer.println("ITENS COMPRADOS:");
            writer.print(itens);
            writer.println("--------------------------------");
            writer.println("TOTAL A PAGAR: " + money(totalGeral));
            writer.close();
            msg("Fatura criada: " + nomeFicheiro);
        } catch (Exception e) { msg("Erro ao criar fatura."); }
    }

    private void mostrarClientes() {
        limparTela("Gestão de Clientes");
        JPanel principal = corpo(); principal.setLayout(new BorderLayout(16,16));
        JPanel form = formulario(3,4);
        JTextField nome=new JTextField(), contacto=new JTextField(), nuit=new JTextField(), email=new JTextField(), endereco=new JTextField();
        addCampo(form,"Nome completo",nome); addCampo(form,"Contacto",contacto); addCampo(form,"NUIT",nuit); addCampo(form,"Email",email); addCampo(form,"Endereço",endereco);
        JButton novo=new JButton("Registar cliente"), atualizarB=new JButton("Atualizar selecionado"), remover=new JButton("Remover selecionado");
        estilizarBotao(novo,verde); estilizarBotao(atualizarB,laranja); estilizarBotao(remover,vermelho); form.add(novo); form.add(atualizarB); form.add(remover);
        JTable tabela=tabelaClientes(); JScrollPane sp=new JScrollPane(tabela);
        tabela.getSelectionModel().addListSelectionListener(e->{ if(!e.getValueIsAdjusting()&&tabela.getSelectedRow()>=0){int r=tabela.getSelectedRow();nome.setText(String.valueOf(tabela.getValueAt(r,0)));contacto.setText(String.valueOf(tabela.getValueAt(r,1)));nuit.setText(String.valueOf(tabela.getValueAt(r,2)));email.setText(String.valueOf(tabela.getValueAt(r,3)));endereco.setText(String.valueOf(tabela.getValueAt(r,4)));}});
        novo.addActionListener(e->{
            if(!validarTextoObrigatorio(nome.getText())||!validarContacto(contacto.getText())){msg("Informe o nome e um contacto válido (82/83/84/85/86/87 + 7 dígitos).");return;}
            if(DadosSistema.procurarClientePorContacto(contacto.getText())!=null){msg("Já existe um cliente com este contacto.");return;}
            DadosSistema.clientes.add(new Cliente(nome.getText().trim(),contacto.getText().trim(),nuit.getText().trim(),email.getText().trim(),endereco.getText().trim())); DadosSistema.salvarClientes();
            Auditoria.registar(usuarioLogado.getEmail(),"CLIENTE","Registou cliente "+nome.getText().trim()); msg("Cliente registado com sucesso."); mostrarClientes();
        });
        atualizarB.addActionListener(e->{int r=tabela.getSelectedRow();if(r<0){msg("Selecione um cliente na tabela.");return;}String contactoAntigo=String.valueOf(tabela.getValueAt(r,1));Cliente c=DadosSistema.procurarClientePorContacto(contactoAntigo);if(c==null)return;if(!validarTextoObrigatorio(nome.getText())||!validarContacto(contacto.getText())){msg("Dados inválidos.");return;}Cliente outro=DadosSistema.procurarClientePorContacto(contacto.getText());if(outro!=null&&outro!=c){msg("Este contacto já pertence a outro cliente.");return;}c.setNome(nome.getText().trim());c.setContacto(contacto.getText().trim());c.setNuit(nuit.getText().trim());c.setEmail(email.getText().trim());c.setEndereco(endereco.getText().trim());DadosSistema.salvarClientes();Auditoria.registar(usuarioLogado.getEmail(),"CLIENTE","Atualizou cliente "+c.getNome());msg("Cliente atualizado.");mostrarClientes();});
        remover.addActionListener(e->{int r=tabela.getSelectedRow();if(r<0){msg("Selecione um cliente na tabela.");return;}Cliente c=DadosSistema.procurarClientePorContacto(String.valueOf(tabela.getValueAt(r,1)));if(c!=null&&JOptionPane.showConfirmDialog(this,"Remover o cliente "+c.getNome()+"?","Confirmar",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){DadosSistema.clientes.remove(c);DadosSistema.salvarClientes();Auditoria.registar(usuarioLogado.getEmail(),"CLIENTE","Removeu cliente "+c.getNome());mostrarClientes();}});
        principal.add(form,BorderLayout.NORTH); principal.add(sp,BorderLayout.CENTER); conteudo.add(principal,BorderLayout.CENTER); atualizar();
    }

    private JTable tabelaClientes(){
        String[] c={"Nome","Contacto","NUIT","Email","Endereço","Compras","Total gasto"}; DefaultTableModel m=new DefaultTableModel(c,0){public boolean isCellEditable(int r,int c){return false;}};
        for(Cliente cl:DadosSistema.clientes){int compras=0;double total=0;for(Venda v:DadosSistema.vendas)if(v.getCliente()!=null&&v.getCliente().equalsIgnoreCase(cl.getNome())){compras++;total+=v.getTotal();}m.addRow(new Object[]{cl.getNome(),cl.getContacto(),nvl(cl.getNuit()),nvl(cl.getEmail()),nvl(cl.getEndereco()),compras,money(total)});}return estilizarTabela(new JTable(m));
    }

    private void mostrarRelatorios() {
        limparTela("Relatório Completo da Empresa");
        JPanel principal = corpo();
        principal.setLayout(new BorderLayout(18,18));

        JPanel cards = new JPanel(new GridLayout(1,4,14,14));
        cards.setOpaque(false);
        cards.add(card("Total vendido", money(totalVendas()), verde));
        cards.add(card("Número de vendas", String.valueOf(DadosSistema.vendas.size()), azulBotao));
        cards.add(card("Entradas de material", String.valueOf(Arquivo.lerLinhas("entradas.txt").size()), laranja));
        cards.add(card("Stock baixo", String.valueOf(contarStockBaixo()), vermelho));

        JTabbedPane abas = new JTabbedPane();
        abas.addTab("Vendas", new JScrollPane(tabelaVendasRelatorio()));
        abas.addTab("Entradas / Compras", new JScrollPane(tabelaEntradasRelatorio()));
        abas.addTab("Materiais", new JScrollPane(tabelaMateriais()));
        abas.addTab("Trabalhadores", new JScrollPane(tabelaFuncionarios()));
        abas.addTab("Clientes", new JScrollPane(tabelaClientes()));
        abas.addTab("Salários", new JScrollPane(tabelaRelatorioSalarios()));
        abas.addTab("Presenças", new JScrollPane(tabelaRelatorioPresencas()));
        abas.addTab("Departamentos", new JScrollPane(tabelaRelatorioDepartamentos()));

        principal.add(cards, BorderLayout.NORTH);
        principal.add(abas, BorderLayout.CENTER);
        conteudo.add(principal, BorderLayout.CENTER);
        atualizar();
    }


    private void mostrarCadastroTrabalhadores() {
        limparTela("Registo de Trabalhador");
        JPanel principal = corpo();
        principal.setLayout(new BorderLayout(18,18));

        RoundedPanel info = new RoundedPanel(24, Color.WHITE);
        info.setLayout(new BorderLayout());
        info.setBorder(BorderFactory.createEmptyBorder(18,22,18,22));
        JLabel titulo = new JLabel("Registo completo de trabalhador / gestor");
        titulo.setFont(new Font("Arial", Font.BOLD, 21));
        titulo.setForeground(azulEscuro);
        JLabel sub = new JLabel("O Presidente pode registar Gestor ou Funcionário; o Gestor regista apenas Funcionários.");
        sub.setFont(new Font("Arial", Font.PLAIN, 14));
        sub.setForeground(new Color(95,105,120));
        info.add(titulo, BorderLayout.NORTH);
        info.add(sub, BorderLayout.SOUTH);

        JPanel formulario = formulario(21,2);
        JTextField nome = campoTextoNormal(), bi = campoTextoNormal(), nascimento = campoTextoNormal(), contacto = campoTextoNormal(), salario = campoTextoNormal(), numeroPagamento = campoTextoNormal(), titularPagamento = campoTextoNormal();
        JTextField nuit = campoTextoNormal(), contactoEmergencia = campoTextoNormal(), fimContrato = campoTextoNormal();
        JComboBox<String> cargo = new JComboBox<>();
        JComboBox<String> morada = comboMoradasMaputoMatola();
        JComboBox<String> departamento = new JComboBox<>(Departamento.nomes());
        for(String c: Departamento.cargosDe(departamento.getSelectedItem().toString())) cargo.addItem(c);
        departamento.addActionListener(ev -> { cargo.removeAllItems(); for(String c: Departamento.cargosDe(departamento.getSelectedItem().toString())) cargo.addItem(c); });
        JComboBox<String> perfilAcesso = usuarioLogado.getPerfil().equals("Presidente") ? new JComboBox<>(new String[]{"SEM_ACESSO", "FUNCIONARIO", "GESTOR"}) : new JComboBox<>(new String[]{"SEM_ACESSO", "FUNCIONARIO"});
        JComboBox<String> sexo = new JComboBox<>(new String[]{"Masculino","Feminino"});
        JComboBox<String> metodoPagamento = new JComboBox<>(new String[]{"M-Pesa","e-Mola","Transferência Bancária","Dinheiro"});
        JComboBox<String> tipoContrato = new JComboBox<>(new String[]{"Indeterminado","Prazo certo","Prazo incerto","Estágio"});
        JSpinner entrada = new JSpinner(new SpinnerDateModel());
        entrada.setEditor(new JSpinner.DateEditor(entrada, "dd/MM/yyyy"));

        addCampo(formulario,"Nome completo",nome);
        addCampo(formulario,"Número de BI",bi);
        addCampo(formulario,"NUIT",nuit);
        addCampo(formulario,"Data nascimento dd/mm/aaaa",nascimento);
        addCampoCombo(formulario,"Sexo",sexo);
        addCampo(formulario,"Contacto +258",contacto);
        addCampo(formulario,"Contacto de emergência",contactoEmergencia);
        addCampoCombo(formulario,"Morada",morada);
        addCampoCombo(formulario,"Cargo / Função",cargo);
        addCampoCombo(formulario,"Acesso ao sistema",perfilAcesso);
        addCampoCombo(formulario,"Departamento",departamento);
        addCampo(formulario,"Salário",salario);
        addCampoCombo(formulario,"Tipo de contrato",tipoContrato);
        addCampo(formulario,"Fim do contrato dd/mm/aaaa",fimContrato);
        addCampoCombo(formulario,"Método de pagamento",metodoPagamento);
        addCampo(formulario,"Número/Conta de pagamento",numeroPagamento);
        addCampo(formulario,"Titular da conta/carteira",titularPagamento);
        addCampoSpinner(formulario,"Data de entrada",entrada);

        JButton guardar = new JButton("Guardar Funcionário");
        estilizarBotao(guardar, verde);
        JButton limpar = new JButton("Limpar Campos");
        estilizarBotao(limpar, vermelho);
        formulario.add(guardar);
        formulario.add(limpar);

        guardar.addActionListener(e -> {
            String nomeCompleto = nome.getText().trim(), biTexto = bi.getText().trim(), dataNascimento = nascimento.getText().trim(), dataEntrada = formatarDataSpinner(entrada);
            String dep = departamento.getSelectedItem().toString();
            String perfilEscolhido = perfilAcesso.getSelectedItem().toString();
            String moradaEscolhida = morada.getSelectedItem().toString();
            if (!validarNome(nomeCompleto) || nomeCompleto.trim().split(" ").length < 2) { msg("Informe nome e apelido. Use apenas letras."); return; }
            if (!validarBI(biTexto)) { msg("BI inválido. Ex: 123456789012A"); return; }
            if (!nuit.getText().trim().matches("\\d{9}")) { msg("NUIT inválido. Informe 9 dígitos."); return; }
            if (!validarData(dataNascimento) || !validarDataNaoFutura(dataNascimento) || !validarMaiorDe18(dataNascimento)) { msg("Data de nascimento inválida ou trabalhador menor de 18 anos."); return; }
            if (!validarContacto(contacto.getText())) { msg("Contacto inválido. Use: +258 84 227 4246"); return; }
            if (!validarContacto(contactoEmergencia.getText())) { msg("Contacto de emergência inválido."); return; }
            String cargoEscolhido = cargo.getSelectedItem().toString();
            if (!validarValorMinimoSalario(salario.getText())) { msg("Salário inválido. O salário mínimo da empresa é 8000 MT."); return; }
            String metodo = metodoPagamento.getSelectedItem().toString();
            String numeroPag = numeroPagamento.getText().trim();
            String titularPag = titularPagamento.getText().trim();
            if ((metodo.equals("M-Pesa") || metodo.equals("e-Mola")) && !numeroPag.replaceAll("\\D", "").matches("(?:258)?8[2-7]\\d{7}")) { msg("Informe um número móvel válido para " + metodo + "."); return; }
            if (titularPag.isEmpty()) { msg("Informe o nome do titular do método de pagamento."); return; }
            String tipoContratoEscolhido = tipoContrato.getSelectedItem().toString();
            String fimContratoTexto = fimContrato.getText().trim();
            if (!tipoContratoEscolhido.equals("Indeterminado") && (!validarData(fimContratoTexto))) { msg("Informe uma data válida para o fim do contrato."); return; }
            if (tipoContratoEscolhido.equals("Indeterminado")) fimContratoTexto = "-";
            if (!validarData(dataEntrada) || !validarDataNaoFutura(dataEntrada)) { msg("Data de entrada inválida."); return; }

            String[] partes = nomeCompleto.split(" ");
            String primeiroNome = partes[0];
            String apelido = partes[partes.length-1];
            boolean temAcessoSistema = perfilEscolhido.equals("GESTOR") || perfilEscolhido.equals("FUNCIONARIO");
            String emailGerado = temAcessoSistema ? gerarEmailFuncionario(primeiroNome, apelido) : "-";
            String senhaGerada = temAcessoSistema ? gerarSenhaAutomatica() : "-";
            String perfilFinal = perfilEscolhido.equals("GESTOR") ? "GESTOR" : (temAcessoSistema ? "FUNCIONARIO" : "SEM_ACESSO");

            if (temAcessoSistema) {
                Usuario novoUsuario = perfilFinal.equals("GESTOR")
                        ? new Gestor(primeiroNome, apelido, biTexto, contacto.getText(), moradaEscolhida, sexo.getSelectedItem().toString(), emailGerado, senhaGerada)
                        : new Funcionario(primeiroNome, apelido, biTexto, contacto.getText(), moradaEscolhida, sexo.getSelectedItem().toString(), emailGerado, senhaGerada);
                novoUsuario.setDepartamento(dep);
                novoUsuario.setCargo(cargoEscolhido);
                try { novoUsuario.setSalarioBase(Double.parseDouble(salario.getText())); } catch(Exception ignored) {}
                DadosSistema.usuarios.add(novoUsuario);
                DadosSistema.salvarUsuarios();
            }
            String codigoTrabalhador = "TRB-" + String.format("%04d", Arquivo.lerLinhas("trabalhadores.txt").size() + 1);
            Arquivo.salvar("trabalhadores.txt", primeiroNome+";"+apelido+";"+biTexto+";"+contacto.getText()+";"+moradaEscolhida+";"+sexo.getSelectedItem()+";"+cargoEscolhido+";"+dep+";"+salario.getText()+";"+dataEntrada+";"+emailGerado+";"+perfilFinal+";"+metodo+";"+numeroPag+";"+titularPag+";"+nuit.getText().trim()+";"+contactoEmergencia.getText().trim()+";"+tipoContratoEscolhido+";"+fimContratoTexto+";"+codigoTrabalhador);
            if (temAcessoSistema) gerarDocumentoDadosLogin(primeiroNome, apelido, cargoEscolhido, emailGerado, senhaGerada, perfilFinal);
            String mensagem = "Registo feito com sucesso!\nCódigo: " + codigoTrabalhador + "\nNome: " + primeiroNome + " " + apelido + "\nCargo: " + cargoEscolhido + "\nDepartamento: " + dep + "\nEntrada: " + dataEntrada;
            mensagem += "\nPagamento: " + metodo + " - " + numeroPag;
            if (temAcessoSistema) mensagem += "\nEmail: " + emailGerado + "\nSenha: " + senhaGerada + "\nDados de login gerados.";
            else mensagem += "\nEste trabalhador não tem acesso ao sistema e será gerido pelo RH.";
            msg(mensagem);
            mostrarCadastroTrabalhadores();
        });

        limpar.addActionListener(e -> { nome.setText(""); bi.setText(""); nascimento.setText(""); contacto.setText(""); salario.setText(""); cargo.setSelectedIndex(0); morada.setSelectedIndex(0); departamento.setSelectedIndex(0); sexo.setSelectedIndex(0); perfilAcesso.setSelectedIndex(0); entrada.setValue(new Date()); });

        JPanel centro = new JPanel(new BorderLayout(0,14));
        centro.setOpaque(false);
        centro.add(info, BorderLayout.NORTH);

        // O formulário é comprido. Com rolagem vertical os campos e botões
        // continuam visíveis mesmo em ecrãs menores ou com escala do Windows.
        JScrollPane scrollFormulario = new JScrollPane(formulario);
        scrollFormulario.setBorder(null);
        scrollFormulario.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollFormulario.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollFormulario.getVerticalScrollBar().setUnitIncrement(18);
        scrollFormulario.getViewport().setBackground(fundo);
        centro.add(scrollFormulario, BorderLayout.CENTER);
        principal.add(centro, BorderLayout.CENTER);
        conteudo.add(principal, BorderLayout.CENTER);
        atualizar();
    }


    private String gerarEmailFuncionario(String nome, String apelido) {
        String base = (removerAcentos(nome) + removerAcentos(apelido)).toLowerCase().replaceAll("[^a-z]", "") + "@sge.com";
        String email = base;
        int contador = 1;
        while (DadosSistema.procurarUsuarioPorEmail(email) != null) {
            email = base.replace("@sge.com", contador + "@sge.com");
            contador++;
        }
        return email;
    }

    private String removerAcentos(String texto) {
        return java.text.Normalizer.normalize(texto, java.text.Normalizer.Form.NFD).replaceAll("\\p{M}", "");
    }

    private String gerarSenhaAutomatica() { String letras="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz", numeros="0123456789", simbolos="@#$%&*", todos=letras+numeros+simbolos; StringBuilder senha=new StringBuilder(); senha.append(letras.charAt((int)(Math.random()*letras.length()))); senha.append(numeros.charAt((int)(Math.random()*numeros.length()))); senha.append(simbolos.charAt((int)(Math.random()*simbolos.length()))); for(int i=0;i<5;i++) senha.append(todos.charAt((int)(Math.random()*todos.length()))); return senha.toString(); }

    private void gerarDocumentoDadosLogin(String nome, String apelido, String cargo, String email, String senha, String perfil) {
        try {
            String ficheiro = "login_" + removerAcentos(nome + apelido).toLowerCase().replaceAll("[^a-z]", "") + "_" + System.currentTimeMillis() + ".txt";
            java.io.PrintWriter writer = new java.io.PrintWriter(ficheiro);
            writer.println("SGE - DADOS DE ACESSO");
            writer.println("--------------------------------");
            writer.println("Nome: " + nome + " " + apelido);
            writer.println("Cargo: " + cargo);
            writer.println("Perfil: " + perfil);
            writer.println("Email: " + email);
            writer.println("Senha: " + senha);
            writer.println("--------------------------------");
            writer.println("Guarde este documento com segurança.");
            writer.close();
        } catch (Exception e) {
            Arquivo.salvar("erros.txt", "Erro ao gerar documento de login: " + e.getMessage());
        }
    }

    private JTable tabelaFuncionarios() {
        String[] colunas={"Código","Nome","BI","NUIT","Contacto","Cargo","Departamento","Contrato","Pagamento","Email","Acesso"};
        DefaultTableModel modelo=new DefaultTableModel(colunas,0){ public boolean isCellEditable(int r,int c){return false;} };
        for (String linha : Arquivo.lerLinhas("trabalhadores.txt")) {
            String[] p = linha.split(";", -1);
            if (p.length >= 11) {
                String perfil = "SEM_ACESSO";
                if (p.length >= 12) perfil = p[11];
                else if (!p[10].equals("-")) perfil = "FUNCIONARIO";
                String codigo = p.length >= 20 ? p[19] : "-";
                String nuitTabela = p.length >= 16 ? p[15] : "-";
                String contratoTabela = p.length >= 18 ? p[17] : "-";
                String pagamentoTabela = p.length >= 13 ? p[12] : "-";
                modelo.addRow(new Object[]{codigo, p[0]+" "+p[1], p[2], nuitTabela, p[3], p[6], p[7], contratoTabela, pagamentoTabela, p[10], perfil});
            }
        }
        if (modelo.getRowCount() == 0) {
            for(Usuario u:DadosSistema.usuarios) if(!u.getPerfil().equals("Presidente")) modelo.addRow(new Object[]{"-",u.getNome()+" "+u.getApelido(),u.getBi(),"-",u.getTelefone(),u.getCargo(),u.getDepartamento(),"-","-",u.getUsername(),u.getPerfil()});
        }
        return estilizarTabela(new JTable(modelo));
    }

    private void mostrarListaTrabalhadores() {
        limparTela("Trabalhadores e Utilizadores");
        JPanel principal = corpo();
        principal.setLayout(new BorderLayout(18,18));

        JPanel topo = new JPanel(new BorderLayout(0,14));
        topo.setOpaque(false);

        JPanel cards = new JPanel(new GridLayout(1,4,14,14));
        cards.setOpaque(false);
        int total = Arquivo.lerLinhas("trabalhadores.txt").size();
        if (total == 0) total = Math.max(0, DadosSistema.usuarios.size()-1);
        int gestores = 0, funcionarios = 0, semAcesso = 0;
        for (Usuario u : DadosSistema.usuarios) {
            if ("GESTOR".equalsIgnoreCase(u.getPerfil())) gestores++;
            else if ("FUNCIONARIO".equalsIgnoreCase(u.getPerfil())) funcionarios++;
            else if ("SEM_ACESSO".equalsIgnoreCase(u.getPerfil())) semAcesso++;
        }
        cards.add(cardResumoMaterial("Trabalhadores", String.valueOf(total), "Total registado", azulBotao));
        cards.add(cardResumoMaterial("Gestores", String.valueOf(gestores), "Acesso de gestão", laranja));
        cards.add(cardResumoMaterial("Funcionários", String.valueOf(funcionarios), "Acesso operacional", verde));
        cards.add(cardResumoMaterial("Sem acesso", String.valueOf(semAcesso), "Registo sem login", vermelho));

        RoundedPanel barra = new RoundedPanel(22, Color.WHITE);
        barra.setLayout(new BorderLayout(12,0));
        barra.setBorder(BorderFactory.createEmptyBorder(14,18,14,18));
        JLabel lbl = new JLabel("Pesquisar trabalhador:");
        lbl.setFont(new Font("Arial", Font.BOLD, 13));
        JTextField pesquisa = new JTextField();
        pesquisa.setToolTipText("Pesquise por nome, BI, contacto, cargo, departamento, email ou perfil");
        barra.add(lbl, BorderLayout.WEST);
        barra.add(pesquisa, BorderLayout.CENTER);

        topo.add(cards, BorderLayout.NORTH);
        topo.add(barra, BorderLayout.SOUTH);

        JTable tabela = tabelaFuncionarios();
        javax.swing.table.TableRowSorter<javax.swing.table.TableModel> sorter = new javax.swing.table.TableRowSorter<>(tabela.getModel());
        tabela.setRowSorter(sorter);
        pesquisa.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            private void filtrar() {
                String textoPesquisa = pesquisa.getText().trim();
                if (textoPesquisa.isEmpty()) sorter.setRowFilter(null);
                else sorter.setRowFilter(javax.swing.RowFilter.regexFilter("(?i)" + java.util.regex.Pattern.quote(textoPesquisa)));
            }
            public void insertUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
        });

        RoundedPanel tabelaPainel = new RoundedPanel(24, Color.WHITE);
        tabelaPainel.setLayout(new BorderLayout(0,10));
        tabelaPainel.setBorder(BorderFactory.createEmptyBorder(18,18,18,18));
        JLabel titulo = new JLabel("Lista de trabalhadores cadastrados");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setForeground(azulEscuro);
        tabelaPainel.add(titulo, BorderLayout.NORTH);
        tabelaPainel.add(new JScrollPane(tabela), BorderLayout.CENTER);

        principal.add(topo, BorderLayout.NORTH);
        principal.add(tabelaPainel, BorderLayout.CENTER);
        conteudo.add(principal, BorderLayout.CENTER);
        atualizar();
    }

    private void mostrarTelaRemoverMaterial() {
        limparTela("Remover Material");
        JPanel principal = corpo();
        principal.setLayout(new BorderLayout(18,18));

        RoundedPanel pesquisa = new RoundedPanel(24, Color.WHITE);
        pesquisa.setLayout(new GridLayout(2,2,12,12));
        pesquisa.setBorder(BorderFactory.createEmptyBorder(22,22,22,22));
        JComboBox<String> combo = comboMateriais();
        JButton remover = new JButton("Remover Material");
        estilizarBotao(remover, vermelho);
        pesquisa.add(labelFormulario("Material:"));
        pesquisa.add(combo);
        pesquisa.add(new JLabel(""));
        pesquisa.add(remover);

        remover.addActionListener(e -> {
            if (combo.getSelectedItem() == null) { msg("Não existem materiais para remover."); return; }
            String nome = combo.getSelectedItem().toString();
            Material encontrado = procurarMaterial(nome);
            if (encontrado == null) { msg("Material não encontrado."); return; }
            int opcao = JOptionPane.showConfirmDialog(this, "Deseja remover o material: " + nome + "?", "Confirmar remoção", JOptionPane.YES_NO_OPTION);
            if (opcao != JOptionPane.YES_OPTION) return;
            DadosSistema.materiais.remove(encontrado);
            DadosSistema.salvarMateriais();
            Arquivo.salvar("logs.txt", dataAtual() + ";Material removido;" + nome + ";Gestor;" + usuarioLogado.getNome() + " " + usuarioLogado.getApelido());
            msg("Material removido com sucesso.");
            mostrarTelaRemoverMaterial();
        });

        principal.add(pesquisa, BorderLayout.NORTH);
        principal.add(new JScrollPane(tabelaMateriais()), BorderLayout.CENTER);
        conteudo.add(principal, BorderLayout.CENTER);
        atualizar();
    }

    private JTable tabelaVendasRelatorio() {
        String[] colunas = {"Data", "Vendedor", "Cliente", "Material", "Quantidade", "Total"};
        DefaultTableModel modelo = new DefaultTableModel(colunas,0){ public boolean isCellEditable(int r,int c){return false;} };
        for (Venda v : DadosSistema.vendas) modelo.addRow(new Object[]{v.getData(), v.getVendedor(), v.getCliente(), v.getMaterial(), v.getQuantidade(), money(v.getTotal())});
        return estilizarTabela(new JTable(modelo));
    }

    private JTable tabelaEntradasRelatorio() {
        String[] colunas = {"Data", "Material", "Quantidade", "Fornecedor/Empresa", "Responsável", "Tipo"};
        DefaultTableModel modelo = new DefaultTableModel(colunas,0){ public boolean isCellEditable(int r,int c){return false;} };
        for (String linha : Arquivo.lerLinhas("entradas.txt")) {
            String[] p = linha.split(";", -1);
            if (p.length >= 6) modelo.addRow(new Object[]{p[0], p[1], p[2], p[3], p[4], p[5]});
            else if (p.length >= 4) modelo.addRow(new Object[]{"-", p[0], p[1], p[2], "-", p[3]});
        }
        return estilizarTabela(new JTable(modelo));
    }

    private JPanel campoComLabel(String label, JTextField campo) {
        JPanel p = new JPanel(new BorderLayout(0,4));
        p.setOpaque(false);
        JLabel l = new JLabel(label);
        l.setForeground(new Color(220,225,230));
        l.setFont(new Font("Arial", Font.BOLD, 12));
        campo.setFont(new Font("Arial", Font.PLAIN, 14));
        campo.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(60,80,100)), BorderFactory.createEmptyBorder(7,10,7,10)));
        p.add(l, BorderLayout.NORTH);
        p.add(campo, BorderLayout.CENTER);
        return p;
    }

    private JPanel comboComLabel(String label, JComboBox<String> combo) {
        JPanel p = new JPanel(new BorderLayout(0,4));
        p.setOpaque(false);
        JLabel l = new JLabel(label);
        l.setForeground(new Color(220,225,230));
        l.setFont(new Font("Arial", Font.BOLD, 12));
        combo.setFont(new Font("Arial", Font.PLAIN, 14));
        combo.setBackground(Color.WHITE);
        p.add(l, BorderLayout.NORTH);
        p.add(combo, BorderLayout.CENTER);
        return p;
    }

    private JComboBox<String> comboMoradasMaputoMatola() {
        return new JComboBox<>(new String[]{
                "Maputo - Alto Maé", "Maputo - Baixa", "Maputo - Central", "Maputo - Polana", "Maputo - Sommerschield",
                "Maputo - Malhangalene", "Maputo - Maxaquene", "Maputo - Mafalala", "Maputo - Chamanculo", "Maputo - Xipamanine",
                "Maputo - Aeroporto", "Maputo - Hulene", "Maputo - Mavalane", "Maputo - Laulane", "Maputo - Costa do Sol",
                "Maputo - Zimpeto", "Maputo - Jardim", "Maputo - Bagamoyo", "Maputo - Ferroviário", "Maputo - Inhagoia",
                "Matola - Cidade da Matola", "Matola - Matola A", "Matola - Matola B", "Matola - Matola C", "Matola - Fomento",
                "Matola - Liberdade", "Matola - Machava", "Matola - T3", "Matola - Tsalala", "Matola - Ndlavela",
                "Matola - Khongolote", "Matola - Intaka", "Matola - Boquisso", "Matola - Malhampsene", "Matola - Beluluane"
        });
    }

    private String dataAtual() {
        return new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date());
    }



    private void mostrarAtualizarPrecos() {
        limparTela("Atualizar Preços dos Materiais");
        JPanel principal = corpo();
        principal.setLayout(new BorderLayout(18,18));

        RoundedPanel formulario = new RoundedPanel(24, Color.WHITE);
        formulario.setLayout(new GridLayout(3,2,12,12));
        formulario.setBorder(BorderFactory.createEmptyBorder(22,22,22,22));
        JComboBox<String> materialCombo = comboMateriais();
        JTextField novoPreco = new JTextField();
        JButton atualizar = new JButton("Atualizar Preço");
        estilizarBotao(atualizar, verde);
        JButton limpar = new JButton("Limpar");
        estilizarBotao(limpar, vermelho);

        formulario.add(labelFormulario("Material")); formulario.add(materialCombo);
        addCampo(formulario, "Novo preço", novoPreco);
        formulario.add(atualizar); formulario.add(limpar);

        atualizar.addActionListener(e -> {
            if (materialCombo.getSelectedItem() == null) { msg("Não existem materiais cadastrados."); return; }
            if (!validarValorPositivoMaiorQueZero(novoPreco.getText())) { msg("Preço inválido."); return; }
            Material m = procurarMaterial(materialCombo.getSelectedItem().toString());
            if (m == null) { msg("Material não encontrado."); return; }
            double antigo = m.getPreco();
            double novo = Double.parseDouble(novoPreco.getText().trim());
            m.setPreco(novo);
            m.setDisponivelCaixa(m.getQuantidade() > 0 && novo > 0);
            DadosSistema.salvarMateriais();
            Arquivo.salvar("logs.txt", dataAtual()+";Preço atualizado;"+m.getNome()+";Antigo:"+antigo+";Novo:"+novo+";"+usuarioLogado.getNome()+" "+usuarioLogado.getApelido());
            msg("Preço atualizado com sucesso.\nMaterial: " + m.getNome() + "\nNovo preço: " + money(novo));
            mostrarAtualizarPrecos();
        });
        limpar.addActionListener(e -> novoPreco.setText(""));

        principal.add(formulario, BorderLayout.NORTH);
        principal.add(new JScrollPane(tabelaMateriais()), BorderLayout.CENTER);
        conteudo.add(principal, BorderLayout.CENTER);
        atualizar();
    }

    private void mostrarMensagemPresidenteGestor() {
        limparTela("Mensagem para Gestor");
        JPanel principal = corpo();
        principal.setLayout(new BorderLayout(18,18));
        RoundedPanel formulario = new RoundedPanel(24, Color.WHITE);
        formulario.setLayout(new BorderLayout(12,12));
        formulario.setBorder(BorderFactory.createEmptyBorder(22,22,22,22));

        JComboBox<String> gestores = new JComboBox<>();
        for (Usuario u : DadosSistema.usuarios) if (u.getPerfil().equals("GESTOR")) gestores.addItem(u.getEmail());
        JTextArea mensagem = new JTextArea(6, 40);
        mensagem.setLineWrap(true); mensagem.setWrapStyleWord(true);
        JButton enviar = new JButton("Enviar Mensagem");
        estilizarBotao(enviar, verde);

        JPanel topo = new JPanel(new BorderLayout(8,8)); topo.setOpaque(false);
        topo.add(labelFormulario("Gestor"), BorderLayout.NORTH); topo.add(gestores, BorderLayout.CENTER);
        formulario.add(topo, BorderLayout.NORTH);
        formulario.add(new JScrollPane(mensagem), BorderLayout.CENTER);
        formulario.add(enviar, BorderLayout.SOUTH);

        enviar.addActionListener(e -> {
            if (gestores.getSelectedItem() == null) { msg("Não existe gestor cadastrado."); return; }
            if (!validarTextoObrigatorio(mensagem.getText())) { msg("Escreva a mensagem."); return; }
            Arquivo.salvar("mensagens.txt", dataAtual()+";"+usuarioLogado.getEmail()+";"+gestores.getSelectedItem().toString()+";"+mensagem.getText().replace(";", ","));
            msg("Mensagem enviada ao gestor.");
            mostrarMensagemPresidenteGestor();
        });

        principal.add(formulario, BorderLayout.NORTH);
        principal.add(new JScrollPane(tabelaMensagens()), BorderLayout.CENTER);
        conteudo.add(principal, BorderLayout.CENTER);
        atualizar();
    }

    private void mostrarAlertasTrabalhadores() {
        limparTela("Alertas para Todos os Trabalhadores");
        JPanel principal = corpo();
        principal.setLayout(new BorderLayout(18,18));
        RoundedPanel formulario = new RoundedPanel(24, Color.WHITE);
        formulario.setLayout(new BorderLayout(12,12));
        formulario.setBorder(BorderFactory.createEmptyBorder(22,22,22,22));

        JLabel destino = new JLabel("Destino: todos os trabalhadores cadastrados");
        destino.setFont(new Font("Arial", Font.BOLD, 14));
        destino.setForeground(texto);
        JTextArea alerta = new JTextArea(6, 40);
        alerta.setLineWrap(true); alerta.setWrapStyleWord(true);
        JButton enviar = new JButton("Enviar Alerta para Todos");
        estilizarBotao(enviar, laranja);

        formulario.add(destino, BorderLayout.NORTH);
        formulario.add(new JScrollPane(alerta), BorderLayout.CENTER);
        formulario.add(enviar, BorderLayout.SOUTH);

        enviar.addActionListener(e -> {
            if (!validarTextoObrigatorio(alerta.getText())) { msg("Escreva o alerta."); return; }
            int total = 0;
            for (String linha : Arquivo.lerLinhas("trabalhadores.txt")) {
                String[] p = linha.split(";", -1);
                if (p.length >= 11) {
                    String destinoAlerta = p[10].trim().isEmpty() || p[10].equals("-") ? p[0] + " " + p[1] : p[10];
                    Arquivo.salvar("mensagens.txt", dataAtual()+";"+usuarioLogado.getEmail()+";"+destinoAlerta+";"+alerta.getText().replace(";", ","));
                    total++;
                }
            }
            if (total == 0) { msg("Não existe trabalhador cadastrado."); return; }
            msg("Alerta enviado para " + total + " trabalhador(es).");
            mostrarAlertasTrabalhadores();
        });

        principal.add(formulario, BorderLayout.NORTH);
        principal.add(new JScrollPane(tabelaMensagens()), BorderLayout.CENTER);
        conteudo.add(principal, BorderLayout.CENTER);
        atualizar();
    }

    private JTable tabelaMensagens() {
        String[] colunas = {"Data", "De", "Para", "Mensagem"};
        DefaultTableModel modelo = new DefaultTableModel(colunas,0){ public boolean isCellEditable(int r,int c){return false;} };
        for (String linha : Arquivo.lerLinhas("mensagens.txt")) {
            String[] p = linha.split(";", -1);
            if (p.length >= 4) modelo.addRow(new Object[]{p[0], p[1], p[2], p[3]});
        }
        return estilizarTabela(new JTable(modelo));
    }

    private void mostrarMensagensRecebidas() {
        limparTela("Mensagens Recebidas");
        JPanel principal = corpo();
        principal.setLayout(new BorderLayout(18,18));
        principal.add(new JScrollPane(tabelaMensagensDoUsuario()), BorderLayout.CENTER);
        conteudo.add(principal, BorderLayout.CENTER);
        atualizar();
    }

    private JTable tabelaMensagensDoUsuario() {
        String[] colunas = {"Data", "De", "Mensagem"};
        DefaultTableModel modelo = new DefaultTableModel(colunas,0){ public boolean isCellEditable(int r,int c){return false;} };
        for (String linha : Arquivo.lerLinhas("mensagens.txt")) {
            String[] p = linha.split(";", -1);
            if (p.length >= 4 && p[2].equalsIgnoreCase(usuarioLogado.getEmail())) modelo.addRow(new Object[]{p[0], p[1], p[3]});
        }
        return estilizarTabela(new JTable(modelo));
    }

    private void mostrarTelaRemoverFuncionario() {
        limparTela("Remover Gestor / Funcionário");
        JPanel principal = corpo();
        principal.setLayout(new BorderLayout(18,18));

        RoundedPanel pesquisa = new RoundedPanel(24, Color.WHITE);
        pesquisa.setLayout(new GridLayout(2,2,12,12));
        pesquisa.setBorder(BorderFactory.createEmptyBorder(22,22,22,22));
        JTextField campoUsername = new JTextField();
        JButton remover = new JButton("Remover pelo Email");
        estilizarBotao(remover, vermelho);
        pesquisa.add(labelFormulario("Email do gestor/funcionário:"));
        pesquisa.add(campoUsername);
        pesquisa.add(new JLabel(""));
        pesquisa.add(remover);

        remover.addActionListener(e -> {
            String username = campoUsername.getText().trim();
            if (!validarTextoObrigatorio(username)) { msg("Digite o email do gestor ou funcionário."); return; }
            Usuario encontrado = null;
            for (Usuario u : DadosSistema.usuarios) {
                if (u.getUsername().equalsIgnoreCase(username) && !u.getPerfil().equals("Presidente")) { encontrado = u; break; }
            }
            if (encontrado == null) { msg("Gestor/Funcionário não encontrado ou não pode remover Presidente."); return; }
            if (usuarioLogado.getPerfil().equals("GESTOR") && encontrado.getPerfil().equals("GESTOR")) { msg("Gestor não pode remover outro gestor."); return; }
            int opcao = JOptionPane.showConfirmDialog(this, "Deseja remover: " + encontrado.getNome() + " " + encontrado.getApelido() + "?", "Confirmar remoção", JOptionPane.YES_NO_OPTION);
            if (opcao != JOptionPane.YES_OPTION) return;
            encontrado.setPerfil("REMOVIDO");
            encontrado.setSenha("SEM_ACESSO");
            DadosSistema.salvarUsuarios();
            marcarTrabalhadorSemAcesso(username);
            Arquivo.salvar("logs.txt", dataAtual()+";Acesso removido;"+username+";"+usuarioLogado.getNome()+" "+usuarioLogado.getApelido());
            msg("Acesso removido com sucesso. O trabalhador continua no histórico, mas já não entra no sistema.");
            mostrarTelaRemoverFuncionario();
        });
        principal.add(pesquisa, BorderLayout.NORTH);
        principal.add(new JScrollPane(tabelaFuncionarios()), BorderLayout.CENTER);
        conteudo.add(principal, BorderLayout.CENTER);
        atualizar();
    }

    private void marcarTrabalhadorSemAcesso(String email) {
        java.util.List<String> novasLinhas = new java.util.ArrayList<>();
        for (String linha : Arquivo.lerLinhas("trabalhadores.txt")) {
            String[] p = linha.split(";", -1);
            if (p.length >= 11 && p[10].equalsIgnoreCase(email)) {
                String base = p[0]+";"+p[1]+";"+p[2]+";"+p[3]+";"+p[4]+";"+p[5]+";"+p[6]+";"+p[7]+";"+p[8]+";"+p[9]+";"+p[10]+";REMOVIDO";
                novasLinhas.add(base);
            } else {
                novasLinhas.add(linha);
            }
        }
        Arquivo.substituirTudo("trabalhadores.txt", novasLinhas);
    }

    private void mostrarRecuperarSenhaFuncionario() {
        limparTela("Recuperar Senha de Funcionário");
        JPanel principal = corpo();
        principal.setLayout(new BorderLayout(18,18));

        RoundedPanel formulario = new RoundedPanel(24, Color.WHITE);
        formulario.setLayout(new GridLayout(3,2,12,12));
        formulario.setBorder(BorderFactory.createEmptyBorder(22,22,22,22));
        JTextField email = new JTextField();
        JTextField novaSenha = new JTextField();
        JButton atualizarSenha = new JButton("Atualizar Senha");
        estilizarBotao(atualizarSenha, verde);
        formulario.add(labelFormulario("Email do funcionário/vendedor:"));
        formulario.add(email);
        formulario.add(labelFormulario("Nova senha:"));
        formulario.add(novaSenha);
        formulario.add(new JLabel(""));
        formulario.add(atualizarSenha);

        atualizarSenha.addActionListener(e -> {
            String mail = email.getText().trim();
            String senhaNova = novaSenha.getText().trim();
            if (!validarTextoObrigatorio(mail) || !validarTextoObrigatorio(senhaNova)) { msg("Informe email e nova senha."); return; }
            Usuario u = DadosSistema.procurarUsuarioPorEmail(mail);
            if (u == null || u.getPerfil().equals("REMOVIDO")) { msg("Utilizador não encontrado ou sem acesso ao sistema."); return; }
            if (!usuarioLogado.getPerfil().equals("Presidente")
                    && !usuarioLogado.getPerfil().equals("PRESIDENTE")) {
                msg("Apenas o Presidente pode recuperar senhas.");
                return;
            }

            if (u.getPerfil().equals("Presidente")
                    || u.getPerfil().equals("PRESIDENTE")) {
                msg("A senha do Presidente não pode ser alterada por esta tela.");
                return;
            }
            u.setSenha(senhaNova);
            DadosSistema.salvarUsuarios();
            gerarDocumentoDadosLogin(u.getNome(), u.getApelido(), "Funcionário/Vendedor", u.getEmail(), senhaNova, u.getPerfil());
            Arquivo.salvar("logs.txt", dataAtual()+";Senha recuperada;"+mail+";"+usuarioLogado.getNome()+" "+usuarioLogado.getApelido());
            msg("Senha atualizada com sucesso. Foi gerado um PDF com o email e a nova senha.");
            mostrarRecuperarSenhaFuncionario();
        });

        principal.add(formulario, BorderLayout.NORTH);
        principal.add(new JScrollPane(tabelaPedidosRecuperacao()), BorderLayout.CENTER);
        conteudo.add(principal, BorderLayout.CENTER);
        atualizar();
    }

    private JTable tabelaPedidosRecuperacao() {
        String[] colunas = {"Data", "Email", "Nome"};
        DefaultTableModel modelo = new DefaultTableModel(colunas,0){ public boolean isCellEditable(int r,int c){return false;} };
        for (String linha : Arquivo.lerLinhas("pedidos_recuperacao.txt")) {
            String[] p = linha.split(";", -1);
            if (p.length >= 3) modelo.addRow(new Object[]{p[0], p[1], p[2]});
        }
        return estilizarTabela(new JTable(modelo));
    }


    private void mostrarDepartamentos() {
        limparTela("Departamentos da Empresa");
        JPanel p=corpo(); String[] cols={"Departamento","Cargos definidos","Trabalhadores","Folha salarial estimada"};
        DefaultTableModel m=new DefaultTableModel(cols,0){public boolean isCellEditable(int r,int c){return false;}};
        for(Departamento d:Departamento.padroes()) { int n=0; double total=0; for(String l:Arquivo.lerLinhas("trabalhadores.txt")){String[] x=l.split(";",-1); if(x.length>=9 && x[7].equalsIgnoreCase(d.getNome())){n++;try{total+=Double.parseDouble(x[8]);}catch(Exception ignored){}}} m.addRow(new Object[]{d.getNome(),String.join(", ",d.getCargos()),n,money(total)}); }
        p.add(new JScrollPane(estilizarTabela(new JTable(m))),BorderLayout.CENTER); conteudo.add(p,BorderLayout.CENTER); atualizar();
    }

    private void mostrarMinhaPresenca() {
        limparTela("Minha Presença"); JPanel p=corpo(); JPanel botoes=new JPanel(new FlowLayout(FlowLayout.LEFT,12,0)); botoes.setOpaque(false);
        JButton entrada=new JButton("Marcar Entrada"); estilizarBotao(entrada,verde); JButton saida=new JButton("Marcar Saída"); estilizarBotao(saida,azulBotao); JButton justificar=new JButton("Justificar Falta"); estilizarBotao(justificar,laranja);
        entrada.addActionListener(e->{String hoje=dataAtual(); for(String l:Arquivo.lerLinhas("presencas.txt")){String[]x=l.split(";",-1);if(x.length>=2&&x[0].equalsIgnoreCase(usuarioLogado.getEmail())&&x[1].equals(hoje)){msg("A presença de hoje já foi marcada.");return;}} String hora=new SimpleDateFormat("HH:mm").format(new Date()); Arquivo.salvar("presencas.txt",usuarioLogado.getEmail()+";"+hoje+";"+hora+";-;PRESENTE;-"); Auditoria.registar(usuarioLogado.getEmail(),"MARCOU ENTRADA",hoje+" às "+hora); msg("Entrada marcada às "+hora); mostrarMinhaPresenca();});
        saida.addActionListener(e->{java.util.List<String> linhas=Arquivo.lerLinhas("presencas.txt");String hoje=dataAtual(),hora=new SimpleDateFormat("HH:mm").format(new Date());boolean ok=false;for(int i=0;i<linhas.size();i++){String[]x=linhas.get(i).split(";",-1);if(x.length>=6&&x[0].equalsIgnoreCase(usuarioLogado.getEmail())&&x[1].equals(hoje)){x[3]=hora;linhas.set(i,String.join(";",x));ok=true;}}if(ok){Arquivo.substituirTudo("presencas.txt",linhas);Auditoria.registar(usuarioLogado.getEmail(),"MARCOU SAÍDA",hoje+" às "+hora);msg("Saída marcada às "+hora);}else msg("Marque primeiro a entrada de hoje.");mostrarMinhaPresenca();});
        justificar.addActionListener(e->{String data=JOptionPane.showInputDialog(this,"Data da falta (dd/MM/yyyy):");if(data==null)return;String motivo=JOptionPane.showInputDialog(this,"Motivo da justificação:");if(motivo==null||motivo.trim().isEmpty())return;Arquivo.salvar("justificacoes.txt",usuarioLogado.getEmail()+";"+data+";"+motivo.replace(";",",")+";PENDENTE;-;-" );msg("Justificação enviada para análise.");});
        botoes.add(entrada);botoes.add(saida);botoes.add(justificar); p.add(botoes,BorderLayout.NORTH);
        String[] c={"Data","Entrada","Saída","Estado"};DefaultTableModel m=new DefaultTableModel(c,0);for(String l:Arquivo.lerLinhas("presencas.txt")){String[]x=l.split(";",-1);if(x.length>=5&&x[0].equalsIgnoreCase(usuarioLogado.getEmail()))m.addRow(new Object[]{x[1],x[2],x[3],x[4]});}p.add(new JScrollPane(estilizarTabela(new JTable(m))),BorderLayout.CENTER);conteudo.add(p,BorderLayout.CENTER);atualizar();
    }

    private void mostrarMinhasTarefas(){
        limparTela("Minhas Tarefas"); JPanel p=corpo(); JTextArea a=new JTextArea();a.setEditable(false);a.setFont(new Font("Arial",Font.PLAIN,16));a.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        String dep=usuarioLogado.getDepartamento(),cargo=usuarioLogado.getCargo(); String tarefas;
        if(dep.equalsIgnoreCase("Vendas e Caixa")) tarefas="• Atender clientes\n• Registar vendas autorizadas\n• Consultar materiais disponíveis\n• Conferir o caixa";
        else if(dep.equalsIgnoreCase("Armazém e Stock")) tarefas="• Consultar stock\n• Conferir materiais\n• Comunicar stock baixo e perdas";
        else if(dep.equalsIgnoreCase("Recursos Humanos")) tarefas="• Apoiar gestão de trabalhadores\n• Acompanhar presenças e justificações conforme autorização";
        else if(dep.equalsIgnoreCase("Logística e Transporte")) tarefas="• Apoiar entregas e movimentação de materiais\n• Cumprir rotas/tarefas atribuídas";
        else tarefas="• Executar as atividades atribuídas ao cargo\n• Marcar presença diariamente\n• Consultar avisos da empresa";
        a.setText("Departamento: "+dep+"\nCargo: "+cargo+"\n\nTAREFAS\n\n"+tarefas+"\n\nNota: funções de pagamento salarial não estão disponíveis para funcionários sem autorização.");p.add(a,BorderLayout.CENTER);conteudo.add(p,BorderLayout.CENTER);atualizar();
    }

    private void mostrarGestaoPresencas(){
        limparTela("Mapa de Presenças e Justificações");
        JPanel p=corpo();

        JPanel topoPresenca=new JPanel(new FlowLayout(FlowLayout.LEFT,10,0));
        topoPresenca.setOpaque(false);
        JButton marcarManual=new JButton("Marcar Presença - Sem Acesso"); estilizarBotao(marcarManual,verde);
        JButton gerarFaltas=new JButton("Gerar Faltas Automáticas"); estilizarBotao(gerarFaltas,laranja);
        JButton atualizarMapa=new JButton("Atualizar Mapa"); estilizarBotao(atualizarMapa,azulBotao);

        marcarManual.addActionListener(e -> {
            java.util.List<String> trabalhadores=Arquivo.lerLinhas("trabalhadores.txt");
            java.util.List<String> opcoes=new java.util.ArrayList<>();
            java.util.Map<String,String> chavePorOpcao=new java.util.LinkedHashMap<>();
            for(String l:trabalhadores){
                String[] x=l.split(";",-1);
                if(x.length>=12 && x[11].equalsIgnoreCase("SEM_ACESSO")){
                    String chave=chaveTrabalhador(x);
                    String label=x[0]+" "+x[1]+" | "+x[7]+" | "+x[6]+" | BI: "+x[2];
                    opcoes.add(label); chavePorOpcao.put(label,chave);
                }
            }
            if(opcoes.isEmpty()){msg("Não existem trabalhadores sem acesso ao sistema registados.");return;}
            JComboBox<String> cb=new JComboBox<>(opcoes.toArray(new String[0]));
            JTextField data=new JTextField(dataAtual());
            JTextField entrada=new JTextField(new SimpleDateFormat("HH:mm").format(new Date()));
            JComboBox<String> estado=new JComboBox<>(new String[]{"PRESENTE","FALTA"});
            JPanel f=formulario(4,2); addCampoCombo(f,"Trabalhador",cb); addCampo(f,"Data (dd/MM/yyyy)",data); addCampo(f,"Hora de entrada",entrada); addCampoCombo(f,"Estado",estado);
            int r=JOptionPane.showConfirmDialog(this,f,"Marcação manual pelo RH",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
            if(r!=JOptionPane.OK_OPTION)return;
            if(!validarData(data.getText().trim())){msg("Data inválida.");return;}
            String chave=chavePorOpcao.get(cb.getSelectedItem().toString());
            if(existePresenca(chave,data.getText().trim())){msg("Já existe um registo para este trabalhador nesta data.");return;}
            String est=estado.getSelectedItem().toString();
            String hora=est.equals("PRESENTE")?entrada.getText().trim():"-";
            Arquivo.salvar("presencas.txt",chave+";"+data.getText().trim()+";"+hora+";-;"+est+";Registo manual RH");
            Auditoria.registar(usuarioLogado.getEmail(),"REGISTOU PRESENÇA MANUAL",chave+" - "+data.getText().trim()+" - "+est);
            msg("Registo de presença guardado."); mostrarGestaoPresencas();
        });
        gerarFaltas.addActionListener(e->{int n=gerarFaltasAutomaticas();msg(n+" falta(s) gerada(s). Férias/licenças aprovadas e fins de semana foram ignorados.");mostrarGestaoPresencas();});
        atualizarMapa.addActionListener(e->mostrarGestaoPresencas());
        topoPresenca.add(marcarManual); topoPresenca.add(gerarFaltas); topoPresenca.add(atualizarMapa); p.add(topoPresenca,BorderLayout.NORTH);

        JTabbedPane tabs=new JTabbedPane();
        String[] cp={"Trabalhador","Departamento","Cargo","Data","Entrada","Saída","Estado","Justificação/Obs."};
        DefaultTableModel mp=new DefaultTableModel(cp,0){public boolean isCellEditable(int r,int c){return false;}};
        for(String l:Arquivo.lerLinhas("presencas.txt")){
            String[] x=l.split(";",-1); if(x.length<5)continue;
            String[] info=dadosTrabalhadorPorChave(x[0]);
            mp.addRow(new Object[]{info[0],info[1],info[2],x[1],x.length>2?x[2]:"-",x.length>3?x[3]:"-",x[4],x.length>5?x[5]:"-"});
        }
        JTable tabelaPresencas=estilizarTabela(new JTable(mp));
        tabelaPresencas.setAutoCreateRowSorter(true);
        tabs.addTab("Mapa de Presenças",new JScrollPane(tabelaPresencas));

        JPanel jp=new JPanel(new BorderLayout(8,8));
        String[] cj={"Trabalhador","Data","Motivo","Estado","Decidido por"};
        DefaultTableModel mj=new DefaultTableModel(cj,0){public boolean isCellEditable(int r,int c){return false;}};
        java.util.List<String> js=Arquivo.lerLinhas("justificacoes.txt");
        for(String l:js){String[]x=l.split(";",-1);if(x.length>=4){String[] info=dadosTrabalhadorPorChave(x[0]);mj.addRow(new Object[]{info[0]+" ["+x[0]+"]",x[1],x[2],x[3],x.length>4?x[4]:"-"});}}
        JTable tj=estilizarTabela(new JTable(mj)); jp.add(new JScrollPane(tj),BorderLayout.CENTER);
        JPanel b=new JPanel(); JButton ap=new JButton("Aprovar");estilizarBotao(ap,verde); JButton re=new JButton("Rejeitar");estilizarBotao(re,vermelho); b.add(ap);b.add(re);jp.add(b,BorderLayout.SOUTH);
        java.awt.event.ActionListener decidir=e->{int r=tj.getSelectedRow();if(r<0){msg("Selecione uma justificação.");return;}String novo=e.getSource()==ap?"JUSTIFICADA":"INJUSTIFICADA";java.util.List<String> linhas=Arquivo.lerLinhas("justificacoes.txt");String exib=mj.getValueAt(r,0).toString(),data=mj.getValueAt(r,1).toString();String chave=exib.substring(exib.lastIndexOf("[")+1,exib.length()-1);for(int i=0;i<linhas.size();i++){String[]x=linhas.get(i).split(";",-1);if(x.length>=4&&x[0].equals(chave)&&x[1].equals(data)){linhas.set(i,x[0]+";"+x[1]+";"+x[2]+";"+novo+";"+usuarioLogado.getEmail()+";"+dataAtual());break;}}Arquivo.substituirTudo("justificacoes.txt",linhas);Auditoria.registar(usuarioLogado.getEmail(),"DECIDIU JUSTIFICAÇÃO",chave+" - "+data+" - "+novo);msg("Justificação atualizada para "+novo+". O cálculo salarial considerará esta decisão.");mostrarGestaoPresencas();};
        ap.addActionListener(decidir);re.addActionListener(decidir);tabs.addTab("Justificações",jp);
        p.add(tabs,BorderLayout.CENTER);conteudo.add(p,BorderLayout.CENTER);atualizar();
    }

    private String chaveTrabalhador(String[] x){
        if(x.length>10 && !x[10].trim().isEmpty() && !x[10].equals("-")) return x[10];
        return "BI:"+(x.length>2?x[2]:"SEM_BI");
    }

    private String[] dadosTrabalhadorPorChave(String chave){
        for(String l:Arquivo.lerLinhas("trabalhadores.txt")){
            String[] x=l.split(";",-1); if(x.length<8)continue;
            if(chaveTrabalhador(x).equalsIgnoreCase(chave)) return new String[]{x[0]+" "+x[1],x[7],x[6]};
        }
        for(Usuario u:DadosSistema.usuarios){
            if(u.getEmail().equalsIgnoreCase(chave)) return new String[]{u.getNome()+" "+u.getApelido(),u.getDepartamento(),u.getCargo()};
        }
        return new String[]{chave,"-","-"};
    }

    private void mostrarSalarios(){
        limparTela("Folha Salarial / Pagamentos");
        JPanel p=corpo();

        String periodoAtual=new SimpleDateFormat("MM/yyyy").format(new Date());
        double folha=0,pago=0; int pendentes=0;
        for(String l:Arquivo.lerLinhas("trabalhadores.txt")){String[]x=l.split(";",-1);if(x.length>=11&&!x[10].equals("-"))folha+=parseDoubleSeguro(x[8]);}
        for(String l:Arquivo.lerLinhas("salarios.txt")){String[]x=l.split(";",-1);if(x.length>=8&&x[1].equals(periodoAtual)){if(x[7].equalsIgnoreCase("PAGO"))pago+=parseDoubleSeguro(x[5]);else pendentes++;}}
        JPanel cards=new JPanel(new GridLayout(1,3,12,12));cards.setOpaque(false);
        cards.add(cardResumo("Folha base do mês",money(folha),azulBotao));
        cards.add(cardResumo("Total pago",money(pago),verde));
        cards.add(cardResumo("Pagamentos pendentes",String.valueOf(pendentes),pendentes>0?laranja:verde));

        JPanel f=formulario(5,2);
        JComboBox<String> trab=new JComboBox<>();
        for(String l:Arquivo.lerLinhas("trabalhadores.txt")){String[]x=l.split(";",-1);if(x.length>=11&&!x[10].equals("-"))trab.addItem(x[10]);}
        JTextField periodo=new JTextField(periodoAtual), bonus=new JTextField("0");
        JButton preparar=new JButton("Preparar Folha");estilizarBotao(preparar,laranja);
        JButton pagar=new JButton("Confirmar Pagamento");estilizarBotao(pagar,verde);
        f.add(labelFormulario("Funcionário (email)"));f.add(trab);addCampo(f,"Período (MM/aaaa)",periodo);addCampo(f,"Bónus/Ajuste (MT)",bonus);
        f.add(new JLabel("Desconto interno: 1.000 MT por falta injustificada"));f.add(preparar);f.add(new JLabel("Pagamento só é concluído após confirmação."));f.add(pagar);

        JPanel norte=new JPanel(new BorderLayout(0,12));norte.setOpaque(false);norte.add(cards,BorderLayout.NORTH);norte.add(f,BorderLayout.CENTER);p.add(norte,BorderLayout.NORTH);
        String[] c={"Funcionário","Período","Base","Descontos","Bónus","Líquido","Data","Estado"};
        DefaultTableModel m=new DefaultTableModel(c,0){public boolean isCellEditable(int r,int c){return false;}};
        for(String l:Arquivo.lerLinhas("salarios.txt")){String[]x=l.split(";",-1);if(x.length>=8)m.addRow(new Object[]{x[0],x[1],money(parseDoubleSeguro(x[2])),money(parseDoubleSeguro(x[3])),money(parseDoubleSeguro(x[4])),money(parseDoubleSeguro(x[5])),x[6],x[7]});}
        JTable t=estilizarTabela(new JTable(m));p.add(new JScrollPane(t),BorderLayout.CENTER);

        preparar.addActionListener(e->{
            if(trab.getSelectedItem()==null)return;String email=trab.getSelectedItem().toString(),per=periodo.getText().trim();
            if(!per.matches("(0[1-9]|1[0-2])/\\d{4}")){msg("Período inválido. Use MM/aaaa.");return;}
            for(String l:Arquivo.lerLinhas("salarios.txt")){String[]x=l.split(";",-1);if(x.length>=2&&x[0].equalsIgnoreCase(email)&&x[1].equals(per)){msg("Já existe uma folha para este funcionário e período.");return;}}
            double base=salarioTrabalhador(email),bon;try{bon=Double.parseDouble(bonus.getText().trim().replace(",","."));}catch(Exception ex){msg("Bónus inválido.");return;}
            int faltas=faltasInjustificadas(email,per); double adiant=adiantamentosAprovados(email,per); double desc=faltas*1000.0+adiant,liq=Math.max(0,base-desc+bon);
            Arquivo.salvar("salarios.txt",new PagamentoSalario(email,per,base,desc,bon,liq,"-","PENDENTE").serializar());
            Auditoria.registar(usuarioLogado.getEmail(),"PREPAROU FOLHA SALARIAL",email+" | "+per+" | líquido "+money(liq));
            msg("Folha preparada.\nBase: "+money(base)+"\nFaltas injustificadas: "+faltas+"\nDesconto total (faltas + adiantamentos): "+money(desc)+"\nLíquido previsto: "+money(liq)+"\n\nO pagamento ainda está PENDENTE.");mostrarSalarios();
        });

        pagar.addActionListener(e->{
            int r=t.getSelectedRow();if(r<0){msg("Selecione na tabela uma folha PENDENTE para pagar.");return;}
            String email=m.getValueAt(r,0).toString(),per=m.getValueAt(r,1).toString(),estado=m.getValueAt(r,7).toString();
            if(!estado.equalsIgnoreCase("PENDENTE")){msg("Este salário já está marcado como pago.");return;}
            java.util.List<String> linhas=Arquivo.lerLinhas("salarios.txt");String pagoLinha=null;
            for(int i=0;i<linhas.size();i++){String[]x=linhas.get(i).split(";",-1);if(x.length>=8&&x[0].equalsIgnoreCase(email)&&x[1].equals(per)){x[6]=dataAtual();x[7]="PAGO";pagoLinha=String.join(";",x);linhas.set(i,pagoLinha);break;}}
            if(pagoLinha==null){msg("Registo salarial não encontrado.");return;}
            Arquivo.substituirTudo("salarios.txt",linhas);String[]x=pagoLinha.split(";",-1);
            Arquivo.salvar("logs.txt",dataAtual()+";Salário pago;"+email+";"+money(parseDoubleSeguro(x[5]))+";"+usuarioLogado.getEmail());
            Auditoria.registar(usuarioLogado.getEmail(),"PAGOU SALÁRIO",email+" | "+per+" | líquido "+money(parseDoubleSeguro(x[5])));
            gerarComprovativoSalario(x);msg("Pagamento confirmado.\nLíquido pago: "+money(parseDoubleSeguro(x[5]))+"\nFoi gerado um comprovativo na pasta comprovativos_salarios.");mostrarSalarios();
        });
        conteudo.add(p,BorderLayout.CENTER);atualizar();
    }

    private void gerarComprovativoSalario(String[] x){
        try{
            File pasta=new File("comprovativos_salarios");if(!pasta.exists())pasta.mkdirs();
            String nome=x[0].replaceAll("[^a-zA-Z0-9._-]","_")+"_"+x[1].replace("/","-")+".txt";
            java.io.PrintWriter pw=new java.io.PrintWriter(new File(pasta,nome),"UTF-8");
            pw.println("SGE - SISTEMA DE GESTÃO DE ESTALEIRO DE OBRAS");pw.println("COMPROVATIVO DE PAGAMENTO DE SALÁRIO");pw.println("-------------------------------------------");
            pw.println("Funcionário: "+x[0]);pw.println("Período: "+x[1]);pw.println("Salário base: "+money(parseDoubleSeguro(x[2])));pw.println("Descontos: "+money(parseDoubleSeguro(x[3])));pw.println("Bónus/Ajustes: "+money(parseDoubleSeguro(x[4])));pw.println("Líquido pago: "+money(parseDoubleSeguro(x[5])));pw.println("Data de pagamento: "+x[6]);pw.println("Estado: "+x[7]);pw.println("Processado por: "+usuarioLogado.getEmail());pw.close();
        }catch(Exception ex){Arquivo.salvar("erros.txt",dataAtual()+";Erro comprovativo salário;"+ex.getMessage());}
    }


    private void mostrarMinhasFerias(){
        limparTela("Minhas Férias e Licenças"); JPanel p=corpo(); JPanel f=formulario(4,2);
        JTextField ini=new JTextField(),fim=new JTextField(); JTextField motivo=new JTextField(); JButton enviar=new JButton("Enviar Pedido"); estilizarBotao(enviar,verde);
        addCampo(f,"Data início (dd/MM/aaaa)",ini); addCampo(f,"Data fim (dd/MM/aaaa)",fim); addCampo(f,"Motivo",motivo); f.add(new JLabel("O pedido será analisado pela administração"));f.add(enviar); p.add(f,BorderLayout.NORTH);
        String[] c={"Início","Fim","Motivo","Estado","Decidido por"}; DefaultTableModel m=new DefaultTableModel(c,0){public boolean isCellEditable(int r,int c){return false;}};
        for(String l:Arquivo.lerLinhas("ferias.txt")){String[]x=l.split(";",-1);if(x.length>=5&&x[0].equalsIgnoreCase(usuarioLogado.getEmail()))m.addRow(new Object[]{x[1],x[2],x[3],x[4],x.length>5?x[5]:"-"});}
        p.add(new JScrollPane(estilizarTabela(new JTable(m))),BorderLayout.CENTER);
        enviar.addActionListener(e->{if(!validarData(ini.getText())||!validarData(fim.getText())){msg("Informe datas válidas.");return;}if(converterData(fim.getText()).isBefore(converterData(ini.getText()))){msg("A data final não pode ser anterior à inicial.");return;}if(motivo.getText().trim().isEmpty()){msg("Informe o motivo.");return;}Arquivo.salvar("ferias.txt",new Ferias(usuarioLogado.getEmail(),ini.getText(),fim.getText(),motivo.getText(),"PENDENTE","-","-").serializar());msg("Pedido enviado para análise.");mostrarMinhasFerias();});
        conteudo.add(p,BorderLayout.CENTER);atualizar();
    }

    private void mostrarGestaoFerias(){
        limparTela("Gestão de Férias e Licenças"); JPanel p=corpo(); String[] c={"Funcionário","Início","Fim","Motivo","Estado","Decidido por"}; DefaultTableModel m=new DefaultTableModel(c,0){public boolean isCellEditable(int r,int c){return false;}};
        for(String l:Arquivo.lerLinhas("ferias.txt")){String[]x=l.split(";",-1);if(x.length>=5)m.addRow(new Object[]{x[0],x[1],x[2],x[3],x[4],x.length>5?x[5]:"-"});} JTable t=estilizarTabela(new JTable(m));p.add(new JScrollPane(t),BorderLayout.CENTER);
        JPanel b=new JPanel(); JButton ap=new JButton("Aprovar");estilizarBotao(ap,verde);JButton re=new JButton("Rejeitar");estilizarBotao(re,vermelho);b.add(ap);b.add(re);p.add(b,BorderLayout.SOUTH);
        java.awt.event.ActionListener ac=e->{int r=t.getSelectedRow();if(r<0){msg("Selecione um pedido.");return;}String email=m.getValueAt(r,0).toString(),ini=m.getValueAt(r,1).toString(),novo=e.getSource()==ap?"APROVADO":"REJEITADO";java.util.List<String> ls=Arquivo.lerLinhas("ferias.txt");for(int i=0;i<ls.size();i++){String[]x=ls.get(i).split(";",-1);if(x.length>=5&&x[0].equals(email)&&x[1].equals(ini)){x[4]=novo;if(x.length<7){ls.set(i,x[0]+";"+x[1]+";"+x[2]+";"+x[3]+";"+novo+";"+usuarioLogado.getEmail()+";"+dataAtual());}else{x[5]=usuarioLogado.getEmail();x[6]=dataAtual();ls.set(i,String.join(";",x));}break;}}Arquivo.substituirTudo("ferias.txt",ls);Arquivo.salvar("logs.txt",dataAtual()+";Férias "+novo+";"+email+";"+usuarioLogado.getEmail());mostrarGestaoFerias();};ap.addActionListener(ac);re.addActionListener(ac);conteudo.add(p,BorderLayout.CENTER);atualizar();
    }

    private void mostrarCompras(){
        limparTela("Compras / Aprovisionamento");
        JPanel p=corpo();
        double totalCompras=0; int numeroCompras=0; int unidadesCompradas=0;
        for(String l:Arquivo.lerLinhas("compras.txt")){String[]x=l.split(";",-1);if(x.length>=6){numeroCompras++;unidadesCompradas+=(int)parseDoubleSeguro(x[3]);totalCompras+=parseDoubleSeguro(x[5]);}}
        JPanel resumoCompras=new JPanel(new GridLayout(1,3,12,12)); resumoCompras.setOpaque(false);
        resumoCompras.add(cardResumo("Compras registadas",String.valueOf(numeroCompras),azulBotao));
        resumoCompras.add(cardResumo("Unidades adquiridas",String.valueOf(unidadesCompradas),verde));
        resumoCompras.add(cardResumo("Total em compras",money(totalCompras),laranja));
        JPanel topoCompras=new JPanel(new BorderLayout(0,12)); topoCompras.setOpaque(false); topoCompras.add(resumoCompras,BorderLayout.NORTH);
        JPanel f=formulario(6,2);
        JComboBox<String> forn=new JComboBox<>();
        for(String l:Arquivo.lerLinhas("fornecedores.txt")){String[]x=l.split(";",-1);if(x.length>0&&!x[0].trim().isEmpty())forn.addItem(x[0]);}
        JComboBox<String> mat=new JComboBox<>(); for(Material x:DadosSistema.materiais)mat.addItem(x.getNome());
        JTextField qtd=new JTextField(), preco=new JTextField();
        JButton reg=new JButton("Registar Compra e Dar Entrada no Stock"); estilizarBotao(reg,verde);
        addCampoCombo(f,"Fornecedor",forn); addCampoCombo(f,"Material",mat); addCampo(f,"Quantidade",qtd); addCampo(f,"Preço unitário de compra",preco);
        f.add(new JLabel("A compra aumenta automaticamente o stock do material.")); f.add(reg); topoCompras.add(f,BorderLayout.CENTER); p.add(topoCompras,BorderLayout.NORTH);
        String[] c={"Data","Fornecedor","Material","Qtd.","Preço Unit.","Total","Responsável"};
        DefaultTableModel m=new DefaultTableModel(c,0){public boolean isCellEditable(int r,int c){return false;}};
        for(String l:Arquivo.lerLinhas("compras.txt")){String[]x=l.split(";",-1);if(x.length>=7)m.addRow(new Object[]{x[0],x[1],x[2],x[3],money(parseDoubleSeguro(x[4])),money(parseDoubleSeguro(x[5])),x[6]});}
        p.add(new JScrollPane(estilizarTabela(new JTable(m))),BorderLayout.CENTER);
        reg.addActionListener(e->{
            if(forn.getSelectedItem()==null){msg("Registe primeiro um fornecedor.");return;} if(mat.getSelectedItem()==null){msg("Não existem materiais registados.");return;}
            int q; double pr; try{q=Integer.parseInt(qtd.getText().trim());pr=Double.parseDouble(preco.getText().trim().replace(",","."));if(q<=0||pr<=0)throw new Exception();}catch(Exception ex){msg("Quantidade ou preço inválido.");return;}
            Material mm=DadosSistema.procurarMaterialPorNome(mat.getSelectedItem().toString()); if(mm==null)return;
            Compra cp=new Compra(dataAtual(),forn.getSelectedItem().toString(),mm.getNome(),q,pr,usuarioLogado.getEmail()); Arquivo.salvar("compras.txt",cp.serializar());
            Arquivo.salvar("logs.txt",dataAtual()+";Compra pendente de receção;"+mm.getNome()+";"+q+";"+usuarioLogado.getEmail());
            Auditoria.registar(usuarioLogado.getEmail(),"REGISTOU COMPRA",mm.getNome()+" | qtd "+q+" | "+forn.getSelectedItem()+" | aguardando armazém");
            msg("Compra registada com sucesso.\n\nO stock NÃO foi aumentado.\nO material ficará disponível apenas depois de o Armazém confirmar a receção física.\nID: "+cp.getId()); mostrarCompras();
        });
        conteudo.add(p,BorderLayout.CENTER); atualizar();
    }

    private double parseDoubleSeguro(String v){try{return Double.parseDouble(v.replace(",","."));}catch(Exception e){return 0;}}

    private void mostrarFornecedores(){
        limparTela("Fornecedores");
        JPanel p=corpo();
        JPanel f=formulario(5,2);
        JTextField nome=new JTextField(),nuit=new JTextField(),cont=new JTextField(),endereco=new JTextField();
        JButton add=new JButton("Registar Fornecedor"), atualizarBtn=new JButton("Atualizar"), removerBtn=new JButton("Remover");
        estilizarBotao(add,verde); estilizarBotao(atualizarBtn,laranja); estilizarBotao(removerBtn,vermelho);
        addCampo(f,"Nome / Empresa",nome); addCampo(f,"NUIT",nuit); addCampo(f,"Contacto",cont); addCampo(f,"Endereço",endereco);
        JPanel acoes=new JPanel(new FlowLayout(FlowLayout.RIGHT)); acoes.setOpaque(false); acoes.add(add); acoes.add(atualizarBtn); acoes.add(removerBtn);
        f.add(new JLabel("CRUD de fornecedores do estaleiro")); f.add(acoes); p.add(f,BorderLayout.NORTH);

        String[] c={"Fornecedor","NUIT","Contacto","Endereço","Compras","Total comprado"};
        DefaultTableModel m=new DefaultTableModel(c,0){public boolean isCellEditable(int r,int c){return false;}};
        for(String l:Arquivo.lerLinhas("fornecedores.txt")){
            String[]x=l.split(";",-1); if(x.length>=4){int compras=0;double total=0;for(String cl:Arquivo.lerLinhas("compras.txt")){String[]cp=cl.split(";",-1);if(cp.length>=6&&cp[1].equalsIgnoreCase(x[0])){compras++;total+=parseDoubleSeguro(cp[5]);}}m.addRow(new Object[]{x[0],x[1],x[2],x[3],compras,money(total)});}
        }
        JTable tabela=estilizarTabela(new JTable(m)); p.add(new JScrollPane(tabela),BorderLayout.CENTER);
        tabela.getSelectionModel().addListSelectionListener(e->{if(!e.getValueIsAdjusting()&&tabela.getSelectedRow()>=0){int r=tabela.getSelectedRow();nome.setText(m.getValueAt(r,0).toString());nuit.setText(m.getValueAt(r,1).toString());cont.setText(m.getValueAt(r,2).toString());endereco.setText(m.getValueAt(r,3).toString());nuit.setEditable(false);}});

        add.addActionListener(e->{
            if(nome.getText().trim().isEmpty()||nuit.getText().trim().isEmpty()){msg("Nome e NUIT são obrigatórios.");return;}
            for(String l:Arquivo.lerLinhas("fornecedores.txt")){String[]x=l.split(";",-1);if(x.length>=2&&x[1].equalsIgnoreCase(nuit.getText().trim())){msg("Já existe fornecedor com este NUIT.");return;}}
            Arquivo.salvar("fornecedores.txt",new Fornecedor(nome.getText().trim(),nuit.getText().trim(),cont.getText().trim(),endereco.getText().trim()).serializar());
            Auditoria.registar(usuarioLogado.getEmail(),"REGISTOU FORNECEDOR",nome.getText().trim()+" | NUIT "+nuit.getText().trim()); msg("Fornecedor registado."); mostrarFornecedores();
        });
        atualizarBtn.addActionListener(e->{
            int r=tabela.getSelectedRow(); if(r<0){msg("Selecione um fornecedor para atualizar.");return;} if(nome.getText().trim().isEmpty()){msg("Informe o nome do fornecedor.");return;}
            String nuitOriginal=m.getValueAt(r,1).toString(); java.util.List<String> linhas=Arquivo.lerLinhas("fornecedores.txt"); boolean ok=false;
            for(int i=0;i<linhas.size();i++){String[]x=linhas.get(i).split(";",-1);if(x.length>=4&&x[1].equalsIgnoreCase(nuitOriginal)){linhas.set(i,new Fornecedor(nome.getText().trim(),nuitOriginal,cont.getText().trim(),endereco.getText().trim()).serializar());ok=true;break;}}
            if(ok){Arquivo.substituirTudo("fornecedores.txt",linhas);Auditoria.registar(usuarioLogado.getEmail(),"ATUALIZOU FORNECEDOR",nome.getText().trim()+" | NUIT "+nuitOriginal);msg("Fornecedor atualizado.");mostrarFornecedores();}
        });
        removerBtn.addActionListener(e->{
            int r=tabela.getSelectedRow(); if(r<0){msg("Selecione um fornecedor para remover.");return;} String fornecedor=m.getValueAt(r,0).toString(),nuitSel=m.getValueAt(r,1).toString();
            int resp=JOptionPane.showConfirmDialog(this,"Remover o fornecedor "+fornecedor+"?\nO histórico de compras será preservado.","Confirmar",JOptionPane.YES_NO_OPTION); if(resp!=JOptionPane.YES_OPTION)return;
            java.util.List<String> linhas=Arquivo.lerLinhas("fornecedores.txt"); linhas.removeIf(l->{String[]x=l.split(";",-1);return x.length>=2&&x[1].equalsIgnoreCase(nuitSel);}); Arquivo.substituirTudo("fornecedores.txt",linhas);
            Auditoria.registar(usuarioLogado.getEmail(),"REMOVEU FORNECEDOR",fornecedor+" | NUIT "+nuitSel);msg("Fornecedor removido. O histórico de compras foi mantido.");mostrarFornecedores();
        });
        conteudo.add(p,BorderLayout.CENTER);atualizar();
    }

    private void mostrarMovimentosStock(){
        limparTela("Movimentos de Stock"); JPanel p=corpo(); JPanel f=formulario(4,2);JComboBox<String> mat=new JComboBox<>();for(Material x:DadosSistema.materiais)mat.addItem(x.getNome());JComboBox<String> tipo=new JComboBox<>(new String[]{"SAÍDA - Obra","SAÍDA - Danificado","SAÍDA - Perda","AJUSTE"});JTextField qtd=new JTextField(),motivo=new JTextField();JButton reg=new JButton("Registar Movimento");estilizarBotao(reg,laranja);addCampoCombo(f,"Material",mat);addCampoCombo(f,"Tipo",tipo);addCampo(f,"Quantidade",qtd);addCampo(f,"Motivo",motivo);p.add(f,BorderLayout.NORTH);JPanel south=new JPanel(new FlowLayout(FlowLayout.RIGHT));south.add(reg);p.add(south,BorderLayout.SOUTH);
        String[] c={"Data","Material","Tipo","Quantidade","Motivo","Responsável"};DefaultTableModel m=new DefaultTableModel(c,0){public boolean isCellEditable(int r,int c){return false;}};for(String l:Arquivo.lerLinhas("movimentos_stock.txt")){String[]x=l.split(";",-1);if(x.length>=6)m.addRow(x);}p.add(new JScrollPane(estilizarTabela(new JTable(m))),BorderLayout.CENTER);
        reg.addActionListener(e->{int q;try{q=Integer.parseInt(qtd.getText());if(q<=0)throw new Exception();}catch(Exception ex){msg("Quantidade inválida.");return;}Material mm=DadosSistema.procurarMaterialPorNome(mat.getSelectedItem().toString());if(mm==null){msg("Material não encontrado.");return;}String tp=tipo.getSelectedItem().toString();if(tp.startsWith("SAÍDA")){if(mm.getQuantidade()<q){msg("Stock insuficiente.");return;}mm.vender(q);}else{msg("Para AJUSTE use uma quantidade de saída nesta versão.");return;}DadosSistema.salvarMateriais();Arquivo.salvar("movimentos_stock.txt",new MovimentoStock(dataAtual(),mm.getNome(),tp,q,motivo.getText(),usuarioLogado.getEmail()).serializar());Arquivo.salvar("logs.txt",dataAtual()+";Movimento stock;"+mm.getNome()+";"+tp+";"+q+";"+usuarioLogado.getEmail());msg("Movimento registado.");mostrarMovimentosStock();});conteudo.add(p,BorderLayout.CENTER);atualizar();
    }

    private void mostrarInventarioFisico(){
        limparTela("Inventário Físico");
        JPanel p=corpo();
        JPanel f=formulario(4,2);
        JComboBox<String> mat=comboMateriais();
        JTextField stockSistema=new JTextField(); stockSistema.setEditable(false);
        JTextField contado=new JTextField();
        JTextField obs=new JTextField();
        addCampoCombo(f,"Material",mat); addCampo(f,"Stock no sistema",stockSistema);
        addCampo(f,"Quantidade contada",contado); addCampo(f,"Observação",obs);
        p.add(f,BorderLayout.NORTH);

        Runnable atualizarStock=()->{Material mm=DadosSistema.procurarMaterialPorNome(String.valueOf(mat.getSelectedItem()));stockSistema.setText(mm==null?"0":String.valueOf(mm.getQuantidade()));};
        mat.addActionListener(e->atualizarStock.run()); atualizarStock.run();

        String[] c={"Data","Material","Stock Sistema","Contado","Diferença","Observação","Responsável"};
        DefaultTableModel model=new DefaultTableModel(c,0){public boolean isCellEditable(int r,int c){return false;}};
        for(String l:Arquivo.lerLinhas("inventarios.txt")){String[]x=l.split(";",-1);if(x.length>=7)model.addRow(x);}
        p.add(new JScrollPane(estilizarTabela(new JTable(model))),BorderLayout.CENTER);

        JButton reg=new JButton("Registar Contagem"); estilizarBotao(reg,azulBotao);
        JButton ajustar=new JButton("Registar e Ajustar Stock"); estilizarBotao(ajustar,laranja);
        JPanel botoes=new JPanel(new FlowLayout(FlowLayout.RIGHT)); botoes.setOpaque(false); botoes.add(reg); botoes.add(ajustar); p.add(botoes,BorderLayout.SOUTH);

        java.awt.event.ActionListener acao=e->{
            Material mm=DadosSistema.procurarMaterialPorNome(String.valueOf(mat.getSelectedItem())); if(mm==null){msg("Material não encontrado.");return;}
            int q; try{q=Integer.parseInt(contado.getText().trim());if(q<0)throw new Exception();}catch(Exception ex){msg("Informe uma quantidade contada válida.");return;}
            int anterior=mm.getQuantidade(); InventarioFisico inv=new InventarioFisico(dataAtual(),mm.getNome(),anterior,q,obs.getText().trim(),usuarioLogado.getEmail());
            Arquivo.salvar("inventarios.txt",inv.serializar());
            boolean aplicar=e.getSource()==ajustar;
            if(aplicar && inv.getDiferenca()!=0){
                mm.setQuantidade(q); DadosSistema.salvarMateriais();
                String tipo=inv.getDiferenca()>0?"AJUSTE + INVENTÁRIO":"AJUSTE - INVENTÁRIO";
                Arquivo.salvar("movimentos_stock.txt",new MovimentoStock(dataAtual(),mm.getNome(),tipo,Math.abs(inv.getDiferenca()),"Inventário físico: "+obs.getText().trim(),usuarioLogado.getEmail()).serializar());
                Auditoria.registar(usuarioLogado.getEmail(),"AJUSTOU STOCK POR INVENTÁRIO",mm.getNome()+" | "+anterior+" -> "+q);
            } else {
                Auditoria.registar(usuarioLogado.getEmail(),"REGISTOU INVENTÁRIO FÍSICO",mm.getNome()+" | Sistema "+anterior+" | Contado "+q);
            }
            msg(aplicar?"Inventário registado e stock reconciliado.":"Contagem física registada sem alterar o stock."); mostrarInventarioFisico();
        };
        reg.addActionListener(acao); ajustar.addActionListener(acao);
        conteudo.add(p,BorderLayout.CENTER); atualizar();
    }

    private void mostrarMeuSalario(){
        limparTela("Meu Salário"); JPanel p=corpo();
        JPanel resumo=new JPanel(new GridLayout(1,3,12,12)); resumo.setOpaque(false);
        resumo.add(cardResumo("Salário base",money(usuarioLogado.getSalarioBase()),azulBotao));
        String periodoAtual=new SimpleDateFormat("MM/yyyy").format(new Date());
        int faltas=faltasInjustificadas(usuarioLogado.getEmail(),periodoAtual);
        resumo.add(cardResumo("Faltas injustificadas",String.valueOf(faltas),faltas>0?vermelho:verde));
        resumo.add(cardResumo("Desconto estimado",money(faltas*1000.0),faltas>0?laranja:verde));
        p.add(resumo,BorderLayout.NORTH);
        String[] c={"Período","Base","Descontos","Bónus","Líquido","Data","Estado"};
        DefaultTableModel m=new DefaultTableModel(c,0){public boolean isCellEditable(int r,int c){return false;}};
        for(String l:Arquivo.lerLinhas("salarios.txt")){String[]x=l.split(";",-1);if(x.length>=8&&x[0].equalsIgnoreCase(usuarioLogado.getEmail()))m.addRow(new Object[]{x[1],money(parseDoubleSeguro(x[2])),money(parseDoubleSeguro(x[3])),money(parseDoubleSeguro(x[4])),money(parseDoubleSeguro(x[5])),x[6],x[7]});}
        p.add(new JScrollPane(estilizarTabela(new JTable(m))),BorderLayout.CENTER);
        conteudo.add(p,BorderLayout.CENTER); atualizar();
    }


    private void mostrarMeuAdiantamento(){
        limparTela("Meu Adiantamento Salarial"); JPanel p=corpo(); JPanel f=formulario(4,2);
        String periodoAtual=new SimpleDateFormat("MM/yyyy").format(new Date());
        JTextField periodo=new JTextField(periodoAtual), valor=new JTextField(), motivo=new JTextField();
        JButton pedir=new JButton("Solicitar Adiantamento"); estilizarBotao(pedir,laranja);
        addCampo(f,"Período de desconto (MM/aaaa)",periodo); addCampo(f,"Valor (MT)",valor); addCampo(f,"Motivo",motivo);
        f.add(new JLabel("O pedido depende de aprovação.")); f.add(pedir); p.add(f,BorderLayout.NORTH);
        String[] c={"Período","Valor","Data do pedido","Motivo","Estado","Decidido por"};
        DefaultTableModel m=new DefaultTableModel(c,0){public boolean isCellEditable(int r,int c){return false;}};
        for(String l:Arquivo.lerLinhas("adiantamentos.txt")){String[]x=l.split(";",-1);if(x.length>=8&&x[0].equalsIgnoreCase(usuarioLogado.getEmail()))m.addRow(new Object[]{x[1],money(parseDoubleSeguro(x[2])),x[3],x[4],x[5],x[6]});}
        p.add(new JScrollPane(estilizarTabela(new JTable(m))),BorderLayout.CENTER);
        pedir.addActionListener(e->{
            String per=periodo.getText().trim(); if(!per.matches("(0[1-9]|1[0-2])/\\d{4}")){msg("Período inválido. Use MM/aaaa.");return;}
            double v;try{v=Double.parseDouble(valor.getText().trim().replace(",","."));}catch(Exception ex){msg("Valor inválido.");return;}
            if(v<=0){msg("O valor deve ser superior a zero.");return;} double base=usuarioLogado.getSalarioBase(); if(base>0&&v>base){msg("O adiantamento não pode ser superior ao salário base.");return;}
            if(motivo.getText().trim().isEmpty()){msg("Informe o motivo do pedido.");return;}
            AdiantamentoSalario a=new AdiantamentoSalario(usuarioLogado.getEmail(),per,v,dataAtual(),motivo.getText().trim(),"PENDENTE","-","-");
            Arquivo.salvar("adiantamentos.txt",a.serializar()); Auditoria.registar(usuarioLogado.getEmail(),"SOLICITOU ADIANTAMENTO",per+" | "+money(v));
            msg("Pedido de adiantamento enviado para aprovação."); mostrarMeuAdiantamento();
        });
        conteudo.add(p,BorderLayout.CENTER); atualizar();
    }

    private void mostrarAdiantamentos(){
        limparTela("Gestão de Adiantamentos Salariais"); JPanel p=corpo();
        String[] c={"Funcionário","Período","Valor","Data do pedido","Motivo","Estado","Decidido por","Data decisão"};
        DefaultTableModel m=new DefaultTableModel(c,0){public boolean isCellEditable(int r,int c){return false;}};
        for(String l:Arquivo.lerLinhas("adiantamentos.txt")){String[]x=l.split(";",-1);if(x.length>=8)m.addRow(new Object[]{x[0],x[1],money(parseDoubleSeguro(x[2])),x[3],x[4],x[5],x[6],x[7]});}
        JTable t=estilizarTabela(new JTable(m)); p.add(new JScrollPane(t),BorderLayout.CENTER);
        JPanel b=new JPanel(); JButton ap=new JButton("Aprovar"); estilizarBotao(ap,verde); JButton re=new JButton("Rejeitar"); estilizarBotao(re,vermelho); b.add(ap);b.add(re);p.add(b,BorderLayout.SOUTH);
        java.awt.event.ActionListener ac=e->{
            int r=t.getSelectedRow(); if(r<0){msg("Selecione um pedido de adiantamento.");return;} String email=m.getValueAt(r,0).toString(),per=m.getValueAt(r,1).toString(),data=m.getValueAt(r,3).toString();
            if(!m.getValueAt(r,5).toString().equalsIgnoreCase("PENDENTE")){msg("Este pedido já foi decidido.");return;}
            String novo=e.getSource()==ap?"APROVADO":"REJEITADO"; java.util.List<String> ls=Arquivo.lerLinhas("adiantamentos.txt");
            for(int i=0;i<ls.size();i++){String[]x=ls.get(i).split(";",-1);if(x.length>=8&&x[0].equalsIgnoreCase(email)&&x[1].equals(per)&&x[3].equals(data)){x[5]=novo;x[6]=usuarioLogado.getEmail();x[7]=dataAtual();ls.set(i,String.join(";",x));break;}}
            Arquivo.substituirTudo("adiantamentos.txt",ls); Auditoria.registar(usuarioLogado.getEmail(),novo+" ADIANTAMENTO",email+" | "+per+" | "+m.getValueAt(r,2));
            msg("Pedido atualizado para "+novo+"."); mostrarAdiantamentos();
        }; ap.addActionListener(ac);re.addActionListener(ac); conteudo.add(p,BorderLayout.CENTER); atualizar();
    }

    private double adiantamentosAprovados(String email,String periodo){
        double total=0; for(String l:Arquivo.lerLinhas("adiantamentos.txt")){String[]x=l.split(";",-1);if(x.length>=8&&x[0].equalsIgnoreCase(email)&&x[1].equals(periodo)&&x[5].equalsIgnoreCase("APROVADO"))total+=parseDoubleSeguro(x[2]);} return total;
    }

    private void mostrarAuditoria(){
        limparTela("Auditoria do Sistema"); JPanel p=corpo();
        String[] c={"Data/Hora","Utilizador","Ação","Detalhe"};
        DefaultTableModel m=new DefaultTableModel(c,0){public boolean isCellEditable(int r,int c){return false;}};
        java.util.List<String> ls=Arquivo.lerLinhas("auditoria.txt");
        for(int i=ls.size()-1;i>=0;i--){String[]x=ls.get(i).split(";",-1);if(x.length>=4)m.addRow(new Object[]{x[0],x[1],x[2],x[3]});}
        JTable t=estilizarTabela(new JTable(m)); p.add(new JScrollPane(t),BorderLayout.CENTER);
        JLabel info=new JLabel("Histórico de ações críticas. Os registos são apenas para consulta."); info.setBorder(BorderFactory.createEmptyBorder(8,4,8,4)); p.add(info,BorderLayout.NORTH);
        conteudo.add(p,BorderLayout.CENTER); atualizar();
    }

    private double salarioTrabalhador(String email){for(String l:Arquivo.lerLinhas("trabalhadores.txt")){String[]x=l.split(";",-1);if(x.length>=11&&x[10].equalsIgnoreCase(email)){try{return Double.parseDouble(x[8]);}catch(Exception ignored){}}}return 0;}
    private int faltasInjustificadas(String email,String periodo){
        int n=0;
        for(String l:Arquivo.lerLinhas("presencas.txt")){
            String[]x=l.split(";",-1);
            if(x.length>=5 && x[0].equalsIgnoreCase(email) && x[4].equalsIgnoreCase("FALTA") && x[1].length()>=10 && x[1].substring(3).equals(periodo)){
                if(!justificacaoAprovada(email,x[1])) n++;
            }
        }
        return n;
    }

    private boolean justificacaoAprovada(String email,String data){
        for(String l:Arquivo.lerLinhas("justificacoes.txt")){String[]x=l.split(";",-1);if(x.length>=4&&x[0].equalsIgnoreCase(email)&&x[1].equals(data)&&x[3].equalsIgnoreCase("JUSTIFICADA"))return true;}
        return false;
    }

    private boolean emFeriasAprovadas(String email, java.time.LocalDate dia){
        for(String l:Arquivo.lerLinhas("ferias.txt")){String[]x=l.split(";",-1);if(x.length>=5&&x[0].equalsIgnoreCase(email)&&x[4].equalsIgnoreCase("APROVADO")){try{java.time.LocalDate ini=converterData(x[1]),fim=converterData(x[2]);if(!dia.isBefore(ini)&&!dia.isAfter(fim))return true;}catch(Exception ignored){}}}
        return false;
    }

    private boolean existePresenca(String email,String data){
        for(String l:Arquivo.lerLinhas("presencas.txt")){String[]x=l.split(";",-1);if(x.length>=2&&x[0].equalsIgnoreCase(email)&&x[1].equals(data))return true;}
        return false;
    }

    private int gerarFaltasAutomaticas(){
        int total=0; java.time.LocalDate hoje=java.time.LocalDate.now(); java.time.LocalDate inicio=hoje.withDayOfMonth(1); java.time.format.DateTimeFormatter fmt=java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
        java.util.Set<String> emails=new java.util.LinkedHashSet<>();
        for(String l:Arquivo.lerLinhas("trabalhadores.txt")){String[]x=l.split(";",-1);if(x.length>=12)emails.add(chaveTrabalhador(x));}
        for(String email:emails){for(java.time.LocalDate d=inicio;d.isBefore(hoje);d=d.plusDays(1)){java.time.DayOfWeek dw=d.getDayOfWeek();if(dw==java.time.DayOfWeek.SATURDAY||dw==java.time.DayOfWeek.SUNDAY)continue;String data=d.format(fmt);if(emFeriasAprovadas(email,d)||existePresenca(email,data))continue;Arquivo.salvar("presencas.txt",email+";"+data+";-;-;FALTA;-");total++;}}
        if(total>0)Arquivo.salvar("logs.txt",dataAtual()+";Faltas automáticas geradas;"+total+";"+usuarioLogado.getEmail()); return total;
    }

    private void mostrarDashboardFuncionario(){
        limparTela("Meu Painel - " + usuarioLogado.getDepartamento()); JPanel p=corpo();
        JPanel cards=new JPanel(new GridLayout(1,4,12,12)); cards.setOpaque(false);
        String hoje=dataAtual(); boolean presente=existePresenca(usuarioLogado.getEmail(),hoje);
        int faltas=faltasInjustificadas(usuarioLogado.getEmail(),new SimpleDateFormat("MM/yyyy").format(new Date()));
        cards.add(cardResumo("Departamento",usuarioLogado.getDepartamento(),azulBotao));
        cards.add(cardResumo("Cargo",usuarioLogado.getCargo(),laranja));
        cards.add(cardResumo("Presença hoje",presente?"Marcada":"Por marcar",presente?verde:vermelho));
        cards.add(cardResumo("Faltas no mês",String.valueOf(faltas),faltas==0?verde:vermelho));
        p.add(cards,BorderLayout.NORTH);

        String dep=usuarioLogado.getDepartamento();
        String resumo;
        if(dep.equalsIgnoreCase("Vendas e Caixa")) resumo="ÁREA DE VENDAS E CAIXA\n\n• Registar novas vendas no Caixa.\n• Consultar materiais disponíveis e preços.\n• Acompanhar vendas realizadas durante o trabalho.\n• Não possui acesso ao processamento de salários.";
        else if(dep.equalsIgnoreCase("Armazém e Stock")) resumo="ÁREA DE ARMAZÉM E STOCK\n\n• Consultar materiais e níveis de stock.\n• Acompanhar materiais com stock reduzido.\n• Executar apenas movimentos autorizados pelo cargo.\n• Comunicar perdas/danos ao responsável.";
        else if(dep.equalsIgnoreCase("Compras e Aprovisionamento")) resumo="ÁREA DE COMPRAS E APROVISIONAMENTO\n\n• Consultar e registar fornecedores.\n• Registar compras autorizadas.\n• As compras confirmadas atualizam o stock.\n• Acompanhar necessidades de reposição.";
        else if(dep.equalsIgnoreCase("Recursos Humanos")) resumo="ÁREA DE RECURSOS HUMANOS\n\n• Acompanhar trabalhadores, presenças e justificações conforme autorização.\n• Apoiar férias/licenças e informação laboral.\n• Pagamentos salariais continuam restritos ao responsável autorizado.";
        else if(dep.equalsIgnoreCase("Financeiro e Contabilidade")) resumo="ÁREA FINANCEIRA E CONTABILIDADE\n\n• Acompanhar informação financeira autorizada.\n• Apoiar processamento e controlo de pagamentos conforme permissões.\n• Consultar o próprio salário sem alterar a folha salarial.";
        else if(dep.equalsIgnoreCase("Logística e Transporte")) resumo="ÁREA DE LOGÍSTICA E TRANSPORTE\n\n• Acompanhar tarefas de transporte e movimentação de materiais.\n• Registar presença e consultar tarefas atribuídas.\n• Comunicar ocorrências ao responsável.";
        else if(dep.equalsIgnoreCase("Operações / Obras")) resumo="ÁREA DE OPERAÇÕES / OBRAS\n\n• Consultar tarefas relacionadas com a obra.\n• Registar presença diariamente.\n• Comunicar utilização, perda ou dano de material ao responsável.";
        else resumo="PAINEL DO FUNCIONÁRIO\n\nUse o menu lateral para marcar presença, consultar férias, salário, notificações e executar apenas as tarefas autorizadas para o seu departamento e cargo.";
        JTextArea a=new JTextArea();a.setEditable(false);a.setFont(new Font("Arial",Font.PLAIN,16));a.setLineWrap(true);a.setWrapStyleWord(true);a.setBorder(BorderFactory.createEmptyBorder(25,25,25,25));a.setText("Bem-vindo, "+usuarioLogado.getNome()+".\n\n"+resumo);
        p.add(a,BorderLayout.CENTER); conteudo.add(p,BorderLayout.CENTER); atualizar();
    }


    private java.util.List<Obra> carregarObras(){
        java.util.List<Obra> lista=new java.util.ArrayList<>();
        for(String l:Arquivo.lerLinhas("obras.txt")){String[]x=l.split(";",-1);if(x.length>=7)lista.add(new Obra(x[0],x[1],x[2],x[3],x[4],x[5],x[6]));}
        return lista;
    }
    private void salvarObras(java.util.List<Obra> lista){java.util.List<String> l=new java.util.ArrayList<>();for(Obra o:lista)l.add(o.serializar());Arquivo.substituirTudo("obras.txt",l);}
    private java.util.List<RequisicaoMaterial> carregarRequisicoes(){
        java.util.List<RequisicaoMaterial> lista=new java.util.ArrayList<>();
        for(String l:Arquivo.lerLinhas("requisicoes_materiais.txt")){String[]x=l.split(";",-1);try{if(x.length>=9)lista.add(new RequisicaoMaterial(x[0],x[1],x[2],Integer.parseInt(x[3]),x[4],x[5],x[6],x[7],x[8]));}catch(Exception ignored){}}
        return lista;
    }
    private void salvarRequisicoes(java.util.List<RequisicaoMaterial> lista){java.util.List<String> l=new java.util.ArrayList<>();for(RequisicaoMaterial r:lista)l.add(r.serializar());Arquivo.substituirTudo("requisicoes_materiais.txt",l);}

    private void mostrarObras(){
        limparTela("Obras / Projectos"); JPanel p=corpo();
        java.util.List<Obra> obras=carregarObras();
        String[] col={"Código","Obra","Local","Responsável","Início","Fim","Estado"}; DefaultTableModel model=new DefaultTableModel(col,0){public boolean isCellEditable(int r,int c){return false;}};
        for(Obra o:obras)model.addRow(new Object[]{o.getCodigo(),o.getNome(),o.getLocal(),o.getResponsavel(),o.getDataInicio(),o.getDataFim(),o.getEstado()});
        JTable tabela=estilizarTabela(new JTable(model)); p.add(new JScrollPane(tabela),BorderLayout.CENTER);
        boolean administra=usuarioLogado.getPerfil().equals("Presidente")||usuarioLogado.getPerfil().equals("GESTOR");
        if(administra){JPanel botoes=new JPanel(new FlowLayout(FlowLayout.RIGHT));JButton novo=new JButton("Nova Obra");JButton estado=new JButton("Alterar Estado");estilizarBotao(novo,laranja);estilizarBotao(estado,azulBotao);botoes.add(estado);botoes.add(novo);p.add(botoes,BorderLayout.SOUTH);
            novo.addActionListener(e->{JTextField nome=new JTextField(),local=new JTextField(),resp=new JTextField(),inicio=new JTextField(new SimpleDateFormat("dd/MM/yyyy").format(new Date())),fim=new JTextField();JPanel f=new JPanel(new GridLayout(5,2,8,8));f.add(new JLabel("Nome da obra"));f.add(nome);f.add(new JLabel("Local"));f.add(local);f.add(new JLabel("Responsável"));f.add(resp);f.add(new JLabel("Data início"));f.add(inicio);f.add(new JLabel("Data fim prevista"));f.add(fim);if(JOptionPane.showConfirmDialog(this,f,"Registar Obra",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){if(nome.getText().trim().isEmpty()||local.getText().trim().isEmpty()){msg("Preencha nome e local da obra.");return;}String cod="OBR-"+String.format("%04d",obras.size()+1);obras.add(new Obra(cod,nome.getText().trim(),local.getText().trim(),resp.getText().trim(),inicio.getText().trim(),fim.getText().trim(),"EM CURSO"));salvarObras(obras);Arquivo.salvar("auditoria.txt",dataAtual()+";OBRA REGISTADA;"+cod+" - "+nome.getText().trim()+";"+usuarioLogado.getEmail());mostrarObras();}});
            estado.addActionListener(e->{int r=tabela.getSelectedRow();if(r<0){msg("Selecione uma obra.");return;}String novoEstado=(String)JOptionPane.showInputDialog(this,"Novo estado:","Estado da Obra",JOptionPane.PLAIN_MESSAGE,null,new String[]{"PLANEADA","EM CURSO","SUSPENSA","CONCLUÍDA"},model.getValueAt(r,6));if(novoEstado!=null){obras.get(r).setEstado(novoEstado);salvarObras(obras);mostrarObras();}});
        }
        conteudo.add(p,BorderLayout.CENTER); atualizar();
    }


    private java.util.List<AlocacaoObra> carregarAlocacoesObra(){
        java.util.List<AlocacaoObra> lista=new java.util.ArrayList<>();
        for(String l:Arquivo.lerLinhas("alocacoes_obras.txt")){String[]x=l.split(";",-1);if(x.length>=7)lista.add(new AlocacaoObra(x[0],x[1],x[2],x[3],x[4],x[5],x[6]));}
        return lista;
    }
    private void salvarAlocacoesObra(java.util.List<AlocacaoObra> lista){java.util.List<String> l=new java.util.ArrayList<>();for(AlocacaoObra a:lista)l.add(a.serializar());Arquivo.substituirTudo("alocacoes_obras.txt",l);}

    private void mostrarEquipaObras(){
        limparTela("Equipa / Trabalhadores por Obra"); JPanel p=corpo();
        java.util.List<AlocacaoObra> alocacoes=carregarAlocacoesObra();
        String[] col={"Obra","Trabalhador","Departamento","Cargo","Data Alocação","Estado"};
        DefaultTableModel model=new DefaultTableModel(col,0){public boolean isCellEditable(int r,int c){return false;}};
        for(AlocacaoObra a:alocacoes)model.addRow(new Object[]{a.getCodigoObra(),a.getTrabalhadorNome(),a.getDepartamento(),a.getCargo(),a.getDataAlocacao(),a.getEstado()});
        JTable tabela=estilizarTabela(new JTable(model)); p.add(new JScrollPane(tabela),BorderLayout.CENTER);
        boolean administra=usuarioLogado.getPerfil().equals("Presidente")||usuarioLogado.getPerfil().equals("GESTOR");
        if(administra){
            JPanel b=new JPanel(new FlowLayout(FlowLayout.RIGHT)); JButton remover=new JButton("Retirar da Obra"), adicionar=new JButton("Alocar Trabalhador"); estilizarBotao(remover,azulBotao); estilizarBotao(adicionar,laranja); b.add(remover);b.add(adicionar);p.add(b,BorderLayout.SOUTH);
            adicionar.addActionListener(e->{
                java.util.List<Obra> obras=carregarObras(); if(obras.isEmpty()){msg("Registe primeiro uma obra.");return;}
                JComboBox<String> obra=new JComboBox<>(), trab=new JComboBox<>();
                for(Obra o:obras)if(!o.getEstado().equals("CONCLUÍDA"))obra.addItem(o.getCodigo()+" - "+o.getNome());
                for(Usuario u:DadosSistema.usuarios)if(!u.getPerfil().equals("Presidente")&&!u.getPerfil().equals("REMOVIDO"))trab.addItem(u.getEmail()+" | "+u.getNome()+" "+u.getApelido());
                JPanel f=new JPanel(new GridLayout(2,2,8,8));f.add(new JLabel("Obra"));f.add(obra);f.add(new JLabel("Trabalhador"));f.add(trab);
                if(JOptionPane.showConfirmDialog(this,f,"Alocar Trabalhador à Obra",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
                    if(obra.getSelectedItem()==null||trab.getSelectedItem()==null)return; String cod=String.valueOf(obra.getSelectedItem()).split(" - ")[0]; String email=String.valueOf(trab.getSelectedItem()).split(" \\| ")[0]; Usuario u=DadosSistema.procurarUsuarioPorEmail(email); if(u==null)return;
                    for(AlocacaoObra a:alocacoes)if(a.getCodigoObra().equals(cod)&&a.getTrabalhadorEmail().equalsIgnoreCase(email)&&a.getEstado().equals("ATIVO")){msg("Este trabalhador já está alocado nesta obra.");return;}
                    alocacoes.add(new AlocacaoObra(cod,email,u.getNome()+" "+u.getApelido(),u.getDepartamento(),u.getCargo(),dataAtual(),"ATIVO")); salvarAlocacoesObra(alocacoes); Arquivo.salvar("auditoria.txt",dataAtual()+";TRABALHADOR ALOCADO À OBRA;"+cod+" | "+email+";"+usuarioLogado.getEmail()); mostrarEquipaObras();
                }
            });
            remover.addActionListener(e->{int i=tabela.getSelectedRow();if(i<0){msg("Selecione um trabalhador alocado.");return;}AlocacaoObra a=alocacoes.get(i);if(!a.getEstado().equals("ATIVO")){msg("Esta alocação já está encerrada.");return;}a.setEstado("ENCERRADO");salvarAlocacoesObra(alocacoes);Arquivo.salvar("auditoria.txt",dataAtual()+";TRABALHADOR RETIRADO DA OBRA;"+a.getCodigoObra()+" | "+a.getTrabalhadorEmail()+";"+usuarioLogado.getEmail());mostrarEquipaObras();});
        }
        conteudo.add(p,BorderLayout.CENTER); atualizar();
    }

    private void mostrarCustosObras(){
        limparTela("Custos / Consumo das Obras"); JPanel p=corpo();
        java.util.List<Obra> obras=carregarObras();
        java.util.List<AlocacaoObra> alocacoes=carregarAlocacoesObra();
        java.util.List<RequisicaoMaterial> reqs=carregarRequisicoes();
        String[] col={"Código","Obra","Estado","Equipa Ativa","Requisições Entregues","Unidades Consumidas","Custo Estimado"};
        DefaultTableModel model=new DefaultTableModel(col,0){public boolean isCellEditable(int r,int c){return false;}};
        boolean admin=usuarioLogado.getPerfil().equals("Presidente")||usuarioLogado.getPerfil().equals("GESTOR");
        for(Obra o:obras){
            if(!admin && usuarioLogado.getDepartamento().equalsIgnoreCase("Operações / Obras")){
                boolean pertence=false; for(AlocacaoObra a:alocacoes) if(a.getCodigoObra().equals(o.getCodigo()) && a.getTrabalhadorEmail().equalsIgnoreCase(usuarioLogado.getEmail()) && a.getEstado().equals("ATIVO")){pertence=true;break;}
                if(!pertence) continue;
            }
            int equipa=0, entregues=0, unidades=0; double custo=0;
            for(AlocacaoObra a:alocacoes) if(a.getCodigoObra().equals(o.getCodigo())&&a.getEstado().equals("ATIVO")) equipa++;
            for(RequisicaoMaterial r:reqs){
                String obraReq=r.getObra();
                if(obraReq!=null && obraReq.startsWith(o.getCodigo()) && r.getEstado().equals("ENTREGUE")){
                    entregues++; unidades+=r.getQuantidade(); Material m=DadosSistema.procurarMaterialPorNome(r.getMaterial()); if(m!=null)custo+=m.getPreco()*r.getQuantidade();
                }
            }
            model.addRow(new Object[]{o.getCodigo(),o.getNome(),o.getEstado(),equipa,entregues,unidades,money(custo)});
        }
        JTable tabela=estilizarTabela(new JTable(model)); p.add(new JScrollPane(tabela),BorderLayout.CENTER);
        JLabel nota=new JLabel("Custo estimado = quantidade de material entregue à obra × preço atual registado do material."); nota.setBorder(BorderFactory.createEmptyBorder(10,5,5,5)); nota.setForeground(new Color(100,110,120)); p.add(nota,BorderLayout.SOUTH);
        conteudo.add(p,BorderLayout.CENTER); atualizar();
    }

    private void mostrarRequisicoesMaterial(){
        limparTela("Requisições de Material para Obras"); JPanel p=corpo(); java.util.List<RequisicaoMaterial> reqs=carregarRequisicoes();
        String[] col={"Código","Obra","Material","Qtd.","Solicitante","Data","Estado","Observação","Decisor"};DefaultTableModel model=new DefaultTableModel(col,0){public boolean isCellEditable(int r,int c){return false;}};
        for(RequisicaoMaterial r:reqs)model.addRow(new Object[]{r.getCodigo(),r.getObra(),r.getMaterial(),r.getQuantidade(),r.getSolicitante(),r.getData(),r.getEstado(),r.getObservacao(),r.getDecisor()});JTable tabela=estilizarTabela(new JTable(model));p.add(new JScrollPane(tabela),BorderLayout.CENTER);
        JPanel b=new JPanel(new FlowLayout(FlowLayout.RIGHT)); boolean podeSolicitar=usuarioLogado.getPerfil().equals("Presidente")||usuarioLogado.getPerfil().equals("GESTOR")||usuarioLogado.getDepartamento().equalsIgnoreCase("Operações / Obras");boolean podeDecidir=usuarioLogado.getPerfil().equals("Presidente")||usuarioLogado.getPerfil().equals("GESTOR")||usuarioLogado.getDepartamento().equalsIgnoreCase("Armazém e Stock");
        if(podeSolicitar){JButton nova=new JButton("Nova Requisição");estilizarBotao(nova,laranja);b.add(nova);nova.addActionListener(e->{java.util.List<Obra> obras=carregarObras();if(obras.isEmpty()){msg("Registe primeiro uma obra.");return;}JComboBox<String> obra=new JComboBox<>(),mat=comboMateriais();for(Obra o:obras)if(!o.getEstado().equals("CONCLUÍDA"))obra.addItem(o.getCodigo()+" - "+o.getNome());JTextField qtd=new JTextField(),obs=new JTextField();JPanel f=new JPanel(new GridLayout(4,2,8,8));f.add(new JLabel("Obra"));f.add(obra);f.add(new JLabel("Material"));f.add(mat);f.add(new JLabel("Quantidade"));f.add(qtd);f.add(new JLabel("Observação"));f.add(obs);if(JOptionPane.showConfirmDialog(this,f,"Nova Requisição",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){int q;try{q=Integer.parseInt(qtd.getText());if(q<=0)throw new Exception();}catch(Exception ex){msg("Quantidade inválida.");return;}String cod="REQ-"+String.format("%05d",reqs.size()+1);reqs.add(new RequisicaoMaterial(cod,String.valueOf(obra.getSelectedItem()),String.valueOf(mat.getSelectedItem()),q,usuarioLogado.getEmail(),dataAtual(),"PENDENTE",obs.getText().trim(),"-"));salvarRequisicoes(reqs);Arquivo.salvar("auditoria.txt",dataAtual()+";REQUISIÇÃO CRIADA;"+cod+";"+usuarioLogado.getEmail());mostrarRequisicoesMaterial();}});}
        if(podeDecidir){JButton aprovar=new JButton("Aprovar");JButton rejeitar=new JButton("Rejeitar");JButton entregar=new JButton("Entregar Material");estilizarBotao(aprovar,verde);estilizarBotao(rejeitar,vermelho);estilizarBotao(entregar,azulBotao);b.add(aprovar);b.add(rejeitar);b.add(entregar);
            aprovar.addActionListener(e->{int i=tabela.getSelectedRow();if(i<0){msg("Selecione uma requisição.");return;}RequisicaoMaterial r=reqs.get(i);if(!r.getEstado().equals("PENDENTE")){msg("Apenas requisições pendentes podem ser aprovadas.");return;}r.setEstado("APROVADA");r.setDecisor(usuarioLogado.getEmail());salvarRequisicoes(reqs);mostrarRequisicoesMaterial();});
            rejeitar.addActionListener(e->{int i=tabela.getSelectedRow();if(i<0){msg("Selecione uma requisição.");return;}RequisicaoMaterial r=reqs.get(i);if(r.getEstado().equals("ENTREGUE")){msg("Material já entregue.");return;}r.setEstado("REJEITADA");r.setDecisor(usuarioLogado.getEmail());salvarRequisicoes(reqs);mostrarRequisicoesMaterial();});
            entregar.addActionListener(e->{int i=tabela.getSelectedRow();if(i<0){msg("Selecione uma requisição.");return;}RequisicaoMaterial r=reqs.get(i);if(!r.getEstado().equals("APROVADA")){msg("A requisição deve estar APROVADA antes da entrega.");return;}Material m=DadosSistema.procurarMaterialPorNome(r.getMaterial());if(m==null){msg("Material não encontrado.");return;}if(m.getQuantidade()<r.getQuantidade()){msg("Stock insuficiente. Disponível: "+m.getQuantidade());return;}m.vender(r.getQuantidade());DadosSistema.salvarMateriais();r.setEstado("ENTREGUE");r.setDecisor(usuarioLogado.getEmail());salvarRequisicoes(reqs);Arquivo.salvar("movimentos_stock.txt",new MovimentoStock(dataAtual(),m.getNome(),"SAÍDA - Obra",r.getQuantidade(),r.getCodigo()+" | "+r.getObra(),usuarioLogado.getEmail()).serializar());Arquivo.salvar("auditoria.txt",dataAtual()+";MATERIAL ENTREGUE À OBRA;"+r.getCodigo()+";"+usuarioLogado.getEmail());msg("Material entregue e stock actualizado.");mostrarRequisicoesMaterial();});
        }
        p.add(b,BorderLayout.SOUTH);conteudo.add(p,BorderLayout.CENTER);atualizar();
    }

    private void mostrarContratos(){
        limparTela("Gestão de Contratos");
        JPanel p=corpo(); p.setLayout(new BorderLayout(12,12));
        String[] col={"Código","Trabalhador","Departamento","Cargo","Tipo","Entrada","Fim","Estado"};
        DefaultTableModel m=new DefaultTableModel(col,0){public boolean isCellEditable(int r,int c){return false;}};
        int ativos=0, expiram=0, expirados=0, indeterminados=0;
        LocalDate hoje=LocalDate.now();
        for(String l:Arquivo.lerLinhas("trabalhadores.txt")){
            String[] x=l.split(";",-1); if(x.length<20) continue;
            String tipo=x[17], fim=x[18], estado="ATIVO";
            if(tipo.equalsIgnoreCase("Indeterminado") || fim.equals("-") || fim.trim().isEmpty()){estado="INDETERMINADO";indeterminados++;}
            else try{
                LocalDate f=converterData(fim); long dias=ChronoUnit.DAYS.between(hoje,f);
                if(dias<0){estado="EXPIRADO";expirados++;}
                else if(dias<=30){estado="EXPIRA EM "+dias+" DIA(S)";expiram++;ativos++;}
                else {estado="ATIVO";ativos++;}
            }catch(Exception ex){estado="DATA INVÁLIDA";}
            m.addRow(new Object[]{x[19],x[0]+" "+x[1],x[7],x[6],tipo,x[9],fim,estado});
        }
        JPanel cards=new JPanel(new GridLayout(1,4,12,12)); cards.setOpaque(false);
        cards.add(cardResumoMaterial("Ativos",String.valueOf(ativos),"Contratos em vigor",verde));
        cards.add(cardResumoMaterial("A expirar",String.valueOf(expiram),"Próximos 30 dias",laranja));
        cards.add(cardResumoMaterial("Expirados",String.valueOf(expirados),"Requer atenção",vermelho));
        cards.add(cardResumoMaterial("Indeterminados",String.valueOf(indeterminados),"Sem data final",azulBotao));
        p.add(cards,BorderLayout.NORTH);
        p.add(new JScrollPane(estilizarTabela(new JTable(m))),BorderLayout.CENTER);
        conteudo.add(p,BorderLayout.CENTER); atualizar();
    }

    private void mostrarNotificacoes(){
        limparTela("Centro de Notificações"); JPanel p=corpo();
        DefaultListModel<String> model=new DefaultListModel<>();
        String periodo=new SimpleDateFormat("MM/yyyy").format(new Date());
        int faltas=faltasInjustificadas(usuarioLogado.getEmail(),periodo);
        if(!existePresenca(usuarioLogado.getEmail(),dataAtual()) && usuarioLogado.getPerfil().equals("FUNCIONARIO")) model.addElement("Presença de hoje ainda não foi marcada.");
        if(faltas>0) model.addElement("Tem "+faltas+" falta(s) injustificada(s) no período "+periodo+".");
        for(Material mat:DadosSistema.materiais) if(mat.getQuantidade()<=10 && (usuarioLogado.getPerfil().equals("Presidente") || usuarioLogado.getPerfil().equals("GESTOR") || usuarioLogado.getDepartamento().equalsIgnoreCase("Armazém e Stock") || usuarioLogado.getDepartamento().equalsIgnoreCase("Compras e Aprovisionamento"))) model.addElement("Stock baixo: "+mat.getNome()+" ("+mat.getQuantidade()+" unidades).");
        if(usuarioLogado.getPerfil().equals("Presidente")){
            int pend=0; for(String l:Arquivo.lerLinhas("salarios.txt")){String[]x=l.split(";",-1);if(x.length>=8&&x[7].equalsIgnoreCase("PENDENTE"))pend++;} if(pend>0)model.addElement("Existem "+pend+" pagamento(s) salarial(is) pendente(s).");
            int just=0; for(String l:Arquivo.lerLinhas("justificacoes.txt")){String[]x=l.split(";",-1);if(x.length>=4&&x[3].equalsIgnoreCase("PENDENTE"))just++;} if(just>0)model.addElement("Existem "+just+" justificação(ões) de falta por analisar.");
            int contratosExpirar=0; LocalDate hoje=LocalDate.now(); for(String l:Arquivo.lerLinhas("trabalhadores.txt")){String[]x=l.split(";",-1);if(x.length>=20&&!x[18].equals("-")&&!x[18].trim().isEmpty()){try{long d=ChronoUnit.DAYS.between(hoje,converterData(x[18]));if(d>=0&&d<=30)contratosExpirar++;}catch(Exception ignored){}}} if(contratosExpirar>0)model.addElement("Existem "+contratosExpirar+" contrato(s) a expirar nos próximos 30 dias.");
        }
        if(model.isEmpty()) model.addElement("Não existem notificações importantes neste momento.");
        JList<String> lista=new JList<>(model); lista.setFont(new Font("Arial",Font.PLAIN,15)); lista.setFixedCellHeight(42); lista.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        p.add(new JScrollPane(lista),BorderLayout.CENTER); conteudo.add(p,BorderLayout.CENTER); atualizar();
    }


    private void mostrarDevolucoes() {
        limparTela("Devoluções de Vendas");
        JPanel p = corpo();
        p.setLayout(new BorderLayout(14,14));

        String[] cols = {"#", "Data", "Cliente", "Material", "Qtd vendida", "Qtd devolvida", "Disponível p/ devolver", "Total venda", "Vendedor"};
        DefaultTableModel model = new DefaultTableModel(cols,0){ public boolean isCellEditable(int r,int c){ return false; } };
        for (int i=0;i<DadosSistema.vendas.size();i++) {
            Venda v = DadosSistema.vendas.get(i);
            int devolvida = quantidadeJaDevolvida(v);
            model.addRow(new Object[]{i+1,v.getData(),v.getCliente(),v.getMaterial(),v.getQuantidade(),devolvida,Math.max(0,v.getQuantidade()-devolvida),money(v.getTotal()),v.getVendedor()});
        }
        JTable tabela = estilizarTabela(new JTable(model));
        p.add(new JScrollPane(tabela),BorderLayout.CENTER);

        RoundedPanel acao = new RoundedPanel(20,Color.WHITE);
        acao.setLayout(new FlowLayout(FlowLayout.LEFT,12,12));
        JLabel info = new JLabel("Selecione uma venda e informe a quantidade devolvida:");
        info.setFont(new Font("Arial",Font.BOLD,13));
        JSpinner qtd = new JSpinner(new SpinnerNumberModel(1,1,100000,1));
        JTextField motivo = new JTextField(22);
        motivo.setToolTipText("Motivo da devolução");
        estilizarCampoFormulario(motivo);
        JButton btn = new JButton("Confirmar devolução");
        estilizarBotao(btn,laranja);
        acao.add(info); acao.add(new JLabel("Qtd:")); acao.add(qtd); acao.add(new JLabel("Motivo:")); acao.add(motivo); acao.add(btn);
        btn.addActionListener(e -> {
            int row=tabela.getSelectedRow();
            if(row<0){msg("Selecione a venda que será devolvida.");return;}
            Venda v=DadosSistema.vendas.get(row);
            int q=(Integer)qtd.getValue();
            int ja=quantidadeJaDevolvida(v);
            int disponivel=v.getQuantidade()-ja;
            if(q<=0 || q>disponivel){msg("Quantidade inválida. Pode devolver no máximo "+disponivel+" unidade(s).");return;}
            if(!validarTextoObrigatorio(motivo.getText())){msg("Informe o motivo da devolução.");return;}
            Material mat=procurarMaterial(v.getMaterial());
            if(mat==null){msg("O material da venda já não existe no cadastro.");return;}
            double unit=v.getQuantidade()>0?v.getTotal()/v.getQuantidade():0;
            double valor=q*unit;
            mat.setQuantidade(mat.getQuantidade()+q);
            mat.setDisponivelCaixa(mat.getQuantidade()>0 && mat.getPreco()>0);
            String id=v.getData()+"|"+v.getCliente()+"|"+v.getMaterial()+"|"+v.getVendedor();
            DevolucaoVenda d=new DevolucaoVenda(dataAtual(),id,v.getCliente(),v.getMaterial(),q,valor,motivo.getText().trim(),usuarioLogado.getEmail());
            Arquivo.salvar("devolucoes_vendas.txt",d.serializar());
            Arquivo.salvar("movimentos_stock.txt",new MovimentoStock(dataAtual(),mat.getNome(),"ENTRADA - Devolução",q,"Devolução de venda - "+motivo.getText().trim(),usuarioLogado.getEmail()).serializar());
            Auditoria.registar(usuarioLogado.getEmail(),"DEVOLUÇÃO DE VENDA",mat.getNome()+" | Qtd: "+q+" | Cliente: "+v.getCliente()+" | Valor: "+money(valor));
            DadosSistema.salvarMateriais();
            msg("Devolução registada com sucesso.\nStock reposto: "+q+" unidade(s).\nValor da devolução: "+money(valor));
            mostrarDevolucoes();
        });
        p.add(acao,BorderLayout.SOUTH);
        conteudo.add(p,BorderLayout.CENTER); atualizar();
    }

    private int quantidadeJaDevolvida(Venda v){
        int total=0;
        String id=v.getData()+"|"+v.getCliente()+"|"+v.getMaterial()+"|"+v.getVendedor();
        for(String l:Arquivo.lerLinhas("devolucoes_vendas.txt")){
            String[] x=l.split(";",-1);
            if(x.length>=8 && x[1].equals(id)){ try{total+=Integer.parseInt(x[4]);}catch(Exception ignored){} }
        }
        return total;
    }

    private JTable tabelaRelatorioSalarios(){
        String[] c={"Funcionário","Período","Base","Descontos","Bónus","Líquido","Data","Estado"}; DefaultTableModel m=new DefaultTableModel(c,0){public boolean isCellEditable(int r,int c){return false;}};
        for(String l:Arquivo.lerLinhas("salarios.txt")){String[]x=l.split(";",-1);if(x.length>=8)m.addRow(new Object[]{x[0],x[1],money(parseDoubleSeguro(x[2])),money(parseDoubleSeguro(x[3])),money(parseDoubleSeguro(x[4])),money(parseDoubleSeguro(x[5])),x[6],x[7]});} return estilizarTabela(new JTable(m));
    }
    private JTable tabelaRelatorioPresencas(){
        String[] c={"Funcionário","Data","Entrada","Saída","Estado","Observação"}; DefaultTableModel m=new DefaultTableModel(c,0){public boolean isCellEditable(int r,int c){return false;}};
        for(String l:Arquivo.lerLinhas("presencas.txt")){String[]x=l.split(";",-1);if(x.length>=5)m.addRow(new Object[]{x[0],x[1],x.length>2?x[2]:"-",x.length>3?x[3]:"-",x[4],x.length>5?x[5]:"-"});} return estilizarTabela(new JTable(m));
    }
    private JTable tabelaRelatorioDepartamentos(){
        String[] c={"Departamento","Trabalhadores","Folha Base"}; DefaultTableModel m=new DefaultTableModel(c,0){public boolean isCellEditable(int r,int c){return false;}};
        for(Departamento d:Departamento.padroes()){int n=0;double total=0;for(Usuario u:DadosSistema.usuarios)if(u.getDepartamento()!=null&&u.getDepartamento().equalsIgnoreCase(d.getNome())){n++;total+=u.getSalarioBase();}m.addRow(new Object[]{d.getNome(),n,money(total)});} return estilizarTabela(new JTable(m));
    }

    private JPanel cardResumo(String titulo,String valor,Color cor){
        RoundedPanel r=new RoundedPanel(20,Color.WHITE);r.setLayout(new BorderLayout());r.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));JLabel t=new JLabel(titulo);t.setForeground(new Color(100,110,120));JLabel v=new JLabel(valor);v.setFont(new Font("Arial",Font.BOLD,18));v.setForeground(cor);r.add(t,BorderLayout.NORTH);r.add(v,BorderLayout.CENTER);return r;
    }

    private int contarFuncionarios() { int total=0; for(Usuario u:DadosSistema.usuarios) if(u.getPerfil().equals("FUNCIONARIO")) total++; return total; }
    private boolean validarBI(String bi) { return bi != null && bi.matches("\\d{12}[A-Z]"); }
    private boolean validarNome(String texto) { return texto != null && texto.matches("[a-zA-ZÀ-ÿ ]+"); }
    private boolean validarContacto(String contacto) {
        return contacto != null && contacto.matches("(82|83|84|85|86|87)\\d{7}");
    }
    private boolean validarTextoObrigatorio(String texto) { return texto != null && !texto.trim().isEmpty(); }
    private boolean validarValorPositivo(String valor) { try { return Double.parseDouble(valor) >= 0; } catch(Exception e){ return false; } }
    private boolean validarValorMinimoSalario(String valor) { try { return Double.parseDouble(valor) >= 8000; } catch(Exception e){ return false; } }
    private boolean validarValorPositivoMaiorQueZero(String valor) { try { return Double.parseDouble(valor) > 0; } catch(Exception e){ return false; } }
    private boolean validarInteiroPositivoMaiorQueZero(String valor) { try { return Integer.parseInt(valor) > 0; } catch(Exception e){ return false; } }
    private boolean validarData(String data) { try { LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT)); return true; } catch(Exception e){ return false; } }
    private LocalDate converterData(String data) { return LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT)); }
    private boolean validarMaiorDe18(String dataNascimento) { try { return Period.between(converterData(dataNascimento), LocalDate.now()).getYears() >= 18; } catch(Exception e){ return false; } }
    private boolean validarDataNaoFutura(String data) { try { return !converterData(data).isAfter(LocalDate.now()); } catch(Exception e){ return false; } }
    private void atualizar() { conteudo.revalidate(); conteudo.repaint(); }

    private JPanel corpo() { JPanel p = new JPanel(new BorderLayout(18,18)); p.setBackground(fundo); p.setBorder(BorderFactory.createEmptyBorder(10,28,28,28)); return p; }
    private JPanel formulario(int rows, int cols) { RoundedPanel p = new RoundedPanel(24, Color.WHITE); p.setLayout(new GridLayout(rows, cols, 12, 12)); p.setBorder(BorderFactory.createEmptyBorder(22,22,22,22)); return p; }
    private void addCampo(JPanel p, String label, JTextField campo) { JLabel l = labelFormulario(label); p.add(l); estilizarCampoFormulario(campo); p.add(campo); }
    private void addCampoCombo(JPanel p, String label, JComboBox<String> combo) { JLabel l = labelFormulario(label); p.add(l); combo.setFont(new Font("Arial", Font.PLAIN, 14)); combo.setBackground(Color.WHITE); p.add(combo); }
    private void addCampoSpinner(JPanel p, String label, JSpinner spinner) { JLabel l = labelFormulario(label); p.add(l); spinner.setFont(new Font("Arial", Font.PLAIN, 14)); p.add(spinner); }
    private JLabel labelFormulario(String label) { JLabel l = new JLabel(label); l.setFont(new Font("Arial", Font.BOLD, 13)); l.setForeground(texto); return l; }
    private JTextField campoTextoNormal() {
        JTextField campo = new JTextField(30);
        campo.setText("");
        campo.setToolTipText(null);
        return campo;
    }
    private void estilizarCampoFormulario(JTextField campo) {
        // Campos normais do sistema devem sempre apresentar o texto digitado.
        // Apenas campos de senha (JPasswordField, usados no login) podem ocultar caracteres.
        campo.setFont(new Font("Arial", Font.PLAIN, 14));
        campo.setForeground(new Color(20, 32, 48));
        campo.setCaretColor(new Color(20, 32, 48));
        campo.setBackground(Color.WHITE);
        campo.setOpaque(true);
        campo.setHorizontalAlignment(JTextField.LEFT);
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(215,222,232)),
                BorderFactory.createEmptyBorder(7,10,7,10)));
    }
    private JComboBox<String> comboMateriais() { JComboBox<String> combo = new JComboBox<>(); for (Material m : DadosSistema.materiais) if (validarTextoObrigatorio(m.getNome())) combo.addItem(m.getNome()); combo.setBackground(Color.WHITE); return combo; }
    private JComboBox<String> comboMateriaisComEntrada() { JComboBox<String> combo = new JComboBox<>(); for (Material m : DadosSistema.materiais) if (validarTextoObrigatorio(m.getNome()) && m.getQuantidade() > 0) combo.addItem(m.getNome()); combo.setBackground(Color.WHITE); return combo; }
    private JComboBox<String> comboMateriaisDisponiveis() { JComboBox<String> combo = new JComboBox<>(); for (Material m : DadosSistema.materiais) if (validarTextoObrigatorio(m.getNome()) && m.getQuantidade() > 0 && m.getPreco() > 0 && m.isDisponivelCaixa()) combo.addItem(m.getNome()); combo.setBackground(Color.WHITE); return combo; }
    private String formatarDataSpinner(JSpinner spinner) { return new SimpleDateFormat("dd/MM/yyyy").format((Date) spinner.getValue()); }
    private JTable estilizarTabela(JTable tabela) { tabela.setRowHeight(38); tabela.setFont(new Font("Arial", Font.PLAIN, 13)); tabela.setSelectionBackground(new Color(214,232,255)); tabela.setSelectionForeground(texto); tabela.setShowVerticalLines(false); tabela.setGridColor(new Color(235,238,242)); tabela.setIntercellSpacing(new Dimension(0,1)); tabela.setDefaultEditor(Object.class, null); JTableHeader h=tabela.getTableHeader(); h.setPreferredSize(new Dimension(h.getWidth(),40)); h.setBackground(azulEscuro); h.setForeground(Color.WHITE); h.setFont(new Font("Arial", Font.BOLD, 13)); DefaultTableCellRenderer r=new DefaultTableCellRenderer(); r.setBorder(BorderFactory.createEmptyBorder(0,10,0,10)); tabela.setDefaultRenderer(Object.class,r); return tabela; }
    private String money(double v) { return String.format("%,.2f MT", v).replace(',', ' '); }
    private String nvl(String s) { return s == null || s.trim().isEmpty() ? "-" : s; }
    private void msg(String s) { JOptionPane.showMessageDialog(this, s); }

    static class RoundedPanel extends JPanel {
        int radius; Color bg; RoundedPanel(int radius, Color bg) { this.radius=radius; this.bg=bg; setOpaque(false); }
        @Override protected void paintComponent(Graphics g) { Graphics2D g2=(Graphics2D)g.create(); g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); g2.setColor(new Color(0,0,0,25)); g2.fill(new RoundRectangle2D.Double(4,6,getWidth()-8,getHeight()-10,radius,radius)); g2.setColor(bg); g2.fill(new RoundRectangle2D.Double(0,0,getWidth()-8,getHeight()-8,radius,radius)); g2.dispose(); super.paintComponent(g); }
    }
}
