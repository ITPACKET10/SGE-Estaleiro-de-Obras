package sge.view;

import sge.model.*;
import sge.controller.*;
import sge.view.*;
import sge.repository.*;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.event.*;

/**
 * Janela de autenticação. Recolhe as credenciais e encaminha o utilizador para o sistema.
 *
 * <p>Projecto académico SGE: documentação das responsabilidades desta classe.</p>
 */
public class TelaLogin extends JFrame {

    private JTextField campoUsuario;
    private JPasswordField campoSenha;

    private final Color azulObra = TemaVisual.MARINHO;
    private final Color amareloObra = TemaVisual.DESTAQUE;
    private final Color verdeObra = new Color(0, 146, 93);

    public TelaLogin() {
        setTitle("Login - SGE Estaleiro de Obras");
        setSize(1080, 650);
        setMinimumSize(new Dimension(950, 580));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        add(new FundoLogin(), BorderLayout.CENTER);

        EventosGlobais.activar(this);
        getRootPane().setDefaultButton(encontrarBotaoEntrar(getContentPane()));
        setVisible(true);
    }

    private JButton encontrarBotaoEntrar(Container painel) {
        for (Component c : painel.getComponents()) {
            if (c instanceof JButton && "CONECTAR AO ESTALEIRO".equals(((JButton)c).getText())) return (JButton)c;
            if (c instanceof Container) {
                JButton encontrado = encontrarBotaoEntrar((Container)c);
                if (encontrado != null) return encontrado;
            }
        }
        return null;
    }

    private class FundoLogin extends JPanel {

        private Image imagem;

        public FundoLogin() {
            setLayout(new GridBagLayout());

            try {
                imagem = new ImageIcon(
                        getClass().getResource("/imagem/construcao.png")
                ).getImage();
            } catch (Exception e) {
                imagem = null;
            }

            GridBagConstraints gbc = new GridBagConstraints();

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1;
            gbc.weighty = 1;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.insets = new Insets(40, 60, 40, 20);

            add(hero(), gbc);

            gbc.gridx = 1;
            gbc.weightx = 0;
            gbc.fill = GridBagConstraints.NONE;
            gbc.insets = new Insets(40, 20, 40, 70);

            add(criarCaixaLogin(), gbc);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            if (imagem != null) {
                g2.drawImage(imagem, 0, 0, getWidth(), getHeight(), this);
            } else {
                GradientPaint gp = new GradientPaint(
                        0,
                        0,
                        azulObra,
                        getWidth(),
                        getHeight(),
                        new Color(44, 59, 74)
                );

                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }

            g2.setColor(new Color(0, 0, 0, 145));
            g2.fillRect(0, 0, getWidth(), getHeight());

            g2.dispose();
        }
    }

    private JPanel hero() {
        JPanel p = new JPanel();
        p.setOpaque(false);
        p.setLayout(new GridBagLayout());
        p.setPreferredSize(new Dimension(520, 430));
        p.setMinimumSize(new Dimension(420, 360));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        JLabel marca = new JLabel("SGE - ESTALEIRO DE OBRAS");
        marca.setForeground(amareloObra);
        marca.setFont(new Font("Segoe UI", Font.BOLD, 18));

        JLabel titulo1 = new JLabel("Gestao de");
        titulo1.setForeground(Color.WHITE);
        titulo1.setFont(new Font("Segoe UI", Font.BOLD, 44));

        JLabel titulo2 = new JLabel("materiais, stock");
        titulo2.setForeground(Color.WHITE);
        titulo2.setFont(new Font("Segoe UI", Font.BOLD, 44));

        JLabel titulo3 = new JLabel("e vendas");
        titulo3.setForeground(Color.WHITE);
        titulo3.setFont(new Font("Segoe UI", Font.BOLD, 44));

        JTextArea texto = new JTextArea(
                "Controle materiais de construcao, entradas, vendas, funcionarios e relatorios de forma simples e segura."
        );
        texto.setOpaque(false);
        texto.setEditable(false);
        texto.setLineWrap(true);
        texto.setWrapStyleWord(true);
        texto.setForeground(new Color(225, 231, 238));
        texto.setFont(new Font("Segoe UI", Font.PLAIN, 17));

        gbc.gridy = 0;
        p.add(marca, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(25, 0, 0, 0);
        p.add(titulo1, gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets(4, 0, 0, 0);
        p.add(titulo2, gbc);

        gbc.gridy = 3;
        p.add(titulo3, gbc);

        gbc.gridy = 4;
        gbc.insets = new Insets(25, 0, 0, 0);
        p.add(texto, gbc);

        return p;
    }

    private JPanel criarCaixaLogin() {
        RoundedPanel painel = new RoundedPanel(28, new Color(255, 255, 255, 238));
        painel.setPreferredSize(new Dimension(380, 485));
        painel.setMinimumSize(new Dimension(380, 485));
        painel.setLayout(new GridBagLayout());
        painel.setBorder(BorderFactory.createEmptyBorder(25, 35, 25, 35));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        JLabel titulo = new JLabel("Entrar no Sistema", JLabel.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 25));
        titulo.setForeground(azulObra);

        JLabel info = new JLabel("Acesso seguro ao estaleiro", JLabel.CENTER);
        info.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        info.setForeground(new Color(100, 110, 120));

        JLabel labelEmail = label("Email");
        campoUsuario = campo();
        campoUsuario.setName("o email");
        campoUsuario.setToolTipText("Digite o seu email de acesso");

        JLabel labelSenha = label("Senha");
        campoSenha = new SenhaComDica("Digite a sua senha");
        estilizarCampo(campoSenha);
        campoSenha.setName("a senha");
        campoSenha.setToolTipText("Introduza a sua senha de acesso");


        JCheckBox mostrarSenha = new JCheckBox("Mostrar senha");
        mostrarSenha.setOpaque(false);
        mostrarSenha.setForeground(new Color(70, 80, 90));
        mostrarSenha.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        mostrarSenha.addActionListener(e -> {
            if (mostrarSenha.isSelected()) {
                campoSenha.setEchoChar((char) 0);
            } else {
                campoSenha.setEchoChar('•');
            }
        });

        JButton botaoEntrar = new JButton("CONECTAR AO ESTALEIRO");
        estilizarBotao(botaoEntrar, verdeObra);
        botaoEntrar.addActionListener(e -> fazerLogin());

        JLabel dica = new JLabel("Use o email e a senha cadastrados", JLabel.CENTER);
        dica.setForeground(new Color(120, 125, 130));
        dica.setFont(new Font("Segoe UI", Font.PLAIN, 11));

        gbc.gridy = 0;
        painel.add(titulo, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(8, 0, 25, 0);
        painel.add(info, gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 5, 0);
        painel.add(labelEmail, gbc);

        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 18, 0);
        painel.add(campoUsuario, gbc);

        gbc.gridy = 4;
        gbc.insets = new Insets(0, 0, 5, 0);
        painel.add(labelSenha, gbc);

        gbc.gridy = 5;
        gbc.insets = new Insets(0, 0, 8, 0);
        painel.add(campoSenha, gbc);

        gbc.gridy = 6;
        gbc.insets = new Insets(0, 0, 14, 0);
        painel.add(mostrarSenha, gbc);

        JButton recuperar = new JButton("RECUPERAR ACESSO");
        estilizarBotao(recuperar, TemaVisual.AZUL);
        recuperar.addActionListener(e -> recuperarAcesso());

        gbc.gridy = 7;
        gbc.insets = new Insets(0, 0, 10, 0);
        painel.add(botaoEntrar, gbc);

        gbc.gridy = 8;
        gbc.insets = new Insets(0, 0, 12, 0);
        painel.add(recuperar, gbc);

        gbc.gridy = 9;
        gbc.insets = new Insets(0, 0, 0, 0);
        painel.add(dica, gbc);

        return painel;
    }

    private JLabel label(String texto) {
        JLabel l = new JLabel(texto);
        l.setForeground(azulObra);
        l.setFont(new Font("Segoe UI", Font.BOLD, 13));
        return l;
    }

    private JTextField campo() {
        JTextField c = new CampoComDica("Digite o seu email");
        estilizarCampo(c);
        return c;
    }

    private void estilizarCampo(JTextField c) {
        c.setPreferredSize(new Dimension(300, 42));
        c.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        c.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(220, 225, 230)),
                        BorderFactory.createEmptyBorder(8, 12, 8, 12)
                )
        );
    }

    private void estilizarBotao(JButton b, Color cor) {
        b.setPreferredSize(new Dimension(300, 48));
        b.setBackground(cor);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setFont(new Font("Segoe UI", Font.BOLD, 14));
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b.setOpaque(true);
        b.setContentAreaFilled(true);
    }

    // O texto de ajuda é desenhado no campo vazio: não é enviado como dado de login.
    private static class CampoComDica extends JTextField {
        private final String dica;
        CampoComDica(String dica) { this.dica = dica; }
        @Override protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (getText().isEmpty() && !isFocusOwner()) {
                g.setColor(new Color(130, 138, 145));
                g.setFont(getFont());
                g.drawString(dica, getInsets().left + 2, (getHeight() + g.getFontMetrics().getAscent()) / 2 - 2);
            }
        }
    }
    private static class SenhaComDica extends JPasswordField {
        private final String dica;
        SenhaComDica(String dica) { this.dica = dica; }
        @Override protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (getPassword().length == 0 && !isFocusOwner()) {
                g.setColor(new Color(130, 138, 145));
                g.setFont(getFont());
                g.drawString(dica, getInsets().left + 2, (getHeight() + g.getFontMetrics().getAscent()) / 2 - 2);
            }
        }
    }

    private void fazerLogin() {
        if (campoUsuario.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite o seu email para entrar.", "Campo obrigatório", JOptionPane.WARNING_MESSAGE);
            campoUsuario.requestFocusInWindow(); return;
        }
        if (campoSenha.getPassword().length == 0) {
            JOptionPane.showMessageDialog(this, "Digite a sua senha para entrar.", "Campo obrigatório", JOptionPane.WARNING_MESSAGE);
            campoSenha.requestFocusInWindow(); return;
        }
        String email = campoUsuario.getText().trim();
        String senha = new String(campoSenha.getPassword());
        for (Usuario u : DadosSistema.usuarios) {
            if (u.autenticar(email, senha)) {
                if (u.getPerfil().equals("REMOVIDO") || u.getPerfil().equals("SEM_ACESSO")) {
                    JOptionPane.showMessageDialog(this, "Este utilizador já não tem acesso ao sistema.", "Acesso negado", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Arquivo.salvar("logs.txt", "Login realizado: " + email + " - Perfil: " + u.getPerfil());
                dispose();
                new TelaMenu(u);
                return;
            }
        }

        Arquivo.salvar("erros.txt", "Erro de login: " + email);

        JOptionPane.showMessageDialog(
                this,
                "Email ou senha incorretos!",
                "Acesso negado",
                JOptionPane.ERROR_MESSAGE
        );
    }

    private void recuperarAcesso() {
        String email = campoUsuario.getText().trim();
        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite o seu email no campo Email e clique novamente em Esqueci a senha.");
            return;
        }
        Usuario u = DadosSistema.procurarUsuarioPorEmail(email);
        if (u == null || u.getPerfil().equals("REMOVIDO") || u.getPerfil().equals("SEM_ACESSO")) {
            JOptionPane.showMessageDialog(this, "Conta não encontrada ou sem acesso ao sistema.");
            return;
        }

        JTextField bi = new JTextField();
        JTextField telefone = new JTextField();
        JPasswordField nova = new JPasswordField();
        JPasswordField confirmar = new JPasswordField();
        JPanel painel = new JPanel(new GridLayout(0,1,6,6));
        painel.add(new JLabel("Confirme os seus dados para redefinir a própria senha:"));
        painel.add(new JLabel("BI:")); painel.add(bi);
        painel.add(new JLabel("Contacto registado:")); painel.add(telefone);
        painel.add(new JLabel("Nova senha (mínimo 6 caracteres):")); painel.add(nova);
        painel.add(new JLabel("Confirmar nova senha:")); painel.add(confirmar);
        int op = JOptionPane.showConfirmDialog(this, painel, "Recuperar a minha senha", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (op != JOptionPane.OK_OPTION) return;
        String n1 = new String(nova.getPassword()), n2 = new String(confirmar.getPassword());
        String telInformado = telefone.getText().replaceAll("\\D", "");
        String telRegistado = u.getTelefone().replaceAll("\\D", "");
        if (!u.getBi().equalsIgnoreCase(bi.getText().trim()) || !telRegistado.equals(telInformado)) {
            Arquivo.salvar("erros.txt", java.time.LocalDateTime.now()+";Falha recuperação própria;"+email);
            JOptionPane.showMessageDialog(this, "BI ou contacto não correspondem aos dados desta conta.", "Verificação falhou", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (n1.length() < 6 || !n1.equals(n2)) {
            JOptionPane.showMessageDialog(this, "As senhas não coincidem ou têm menos de 6 caracteres.");
            return;
        }
        u.setSenha(n1);
        DadosSistema.salvarUsuarios();
        Arquivo.salvar("logs.txt", java.time.LocalDateTime.now()+";Senha redefinida pelo próprio utilizador;"+email);
        JOptionPane.showMessageDialog(this, "Senha redefinida com sucesso. Já pode entrar com a nova senha.", "Recuperação concluída", JOptionPane.INFORMATION_MESSAGE);
        campoSenha.setText("");
    }

    static class RoundedPanel extends JPanel {
        private int radius;
        private Color bg;

        RoundedPanel(int radius, Color bg) {
            this.radius = radius;
            this.bg = bg;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            g2.setColor(new Color(0, 0, 0, 55));
            g2.fill(
                    new RoundRectangle2D.Double(
                            8,
                            8,
                            getWidth() - 12,
                            getHeight() - 12,
                            radius,
                            radius
                    )
            );

            g2.setColor(bg);
            g2.fill(
                    new RoundRectangle2D.Double(
                            0,
                            0,
                            getWidth() - 14,
                            getHeight() - 14,
                            radius,
                            radius
                    )
            );

            g2.dispose();
            super.paintComponent(g);
        }
    }
}