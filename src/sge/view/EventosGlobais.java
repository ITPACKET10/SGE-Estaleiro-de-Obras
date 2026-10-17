package sge.view;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.JTextComponent;
import javax.swing.border.Border;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/** Eventos de acessibilidade e interacção para todas as telas Swing.
 * Não substitui os listeners de negócio existentes (CRUD, autenticação, vendas).
 */
public final class EventosGlobais {
    private EventosGlobais() { }
    private static final String INSTALADO = "sge.eventos.instalados";

    /** Activa os eventos globais da janela, incluindo ajuda contextual e atalhos. */
    public static void activar(JFrame janela) {
        instalarRecursivamente(janela.getContentPane());
        // Balão de ajuda contextual junto ao campo seleccionado.
        JPopupMenu ajudaCampo = new JPopupMenu();
        ajudaCampo.setFocusable(false);
        JLabel mensagemAjuda = new JLabel();
        mensagemAjuda.setFont(new Font("Arial", Font.PLAIN, 13));
        mensagemAjuda.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        ajudaCampo.add(mensagemAjuda);
        KeyboardFocusManager.getCurrentKeyboardFocusManager()
            .addPropertyChangeListener("focusOwner", evt -> {
                Component foco = evt.getNewValue() instanceof Component
                    ? (Component) evt.getNewValue() : null;
                ajudaCampo.setVisible(false);
                if (foco == null || SwingUtilities.getWindowAncestor(foco) != janela
                    || !foco.isShowing()) return;
                String mensagem = null;
                if (foco instanceof JTextComponent && ((JTextComponent) foco).isEditable()) {
                    String nome = foco.getName();
                    if (nome == null || nome.trim().isEmpty())
                        nome = foco instanceof JTextField
                            ? descobrirDescricao((JTextField) foco) : "o texto solicitado";
                    mensagem = "Digite " + nome.replace("*", "").trim();
                } else if (foco instanceof JComboBox) {
                    String nome = foco.getName();
                    mensagem = "Seleccione " + (nome == null ? "uma opção" : nome);
                } else if (foco instanceof JSpinner) {
                    String nome = foco.getName();
                    mensagem = "Indique " + (nome == null ? "a data ou valor" : nome);
                }
                if (mensagem != null && foco instanceof JComponent) {
                    mensagemAjuda.setText(mensagem);
                    JComponent alvo = (JComponent) foco;
                    SwingUtilities.invokeLater(() -> {
                        if (alvo.isShowing() && alvo.isFocusOwner())
                            ajudaCampo.show(alvo, 0, alvo.getHeight() + 3);
                    });
                }
            });

        // Os formulários são criados dinamicamente: observar novas telas e componentes.
        janela.getContentPane().addContainerListener(new ContainerAdapter() {
            @Override public void componentAdded(ContainerEvent e) { instalarRecursivamente(e.getChild()); }
        });
        JRootPane raiz = janela.getRootPane();
        raiz.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), "ajudaSGE");
        raiz.getActionMap().put("ajudaSGE", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(janela,
                    "Use Tab para avançar entre campos, Enter para confirmar e Esc para fechar diálogos.\n" +
                    "Passe o rato sobre um campo para ver a sua descrição.\n" +
                    "Nas tabelas, clique num cabeçalho para ordenar quando disponível.",
                    "Ajuda — Eventos do SGE", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    private static void instalarRecursivamente(Component c) {
        if (c instanceof JComponent) {
            JComponent jc = (JComponent)c;
            if (Boolean.TRUE.equals(jc.getClientProperty(INSTALADO))) return;
            jc.putClientProperty(INSTALADO, true);
            if (c instanceof JTextField) prepararCampo((JTextField)c);
            if (c instanceof JTextArea) prepararArea((JTextArea)c);
            if (c instanceof AbstractButton) prepararBotao((AbstractButton)c);
            if (c instanceof JComboBox) prepararCombo((JComboBox<?>)c);
            if (c instanceof JTable) prepararTabela((JTable)c);
        }
        if (c instanceof Container) {
            Container cont = (Container)c;
            for (Component filho : cont.getComponents()) instalarRecursivamente(filho);
            cont.addContainerListener(new ContainerAdapter() {
                @Override public void componentAdded(ContainerEvent e) { instalarRecursivamente(e.getChild()); }
            });
        }
    }

    private static void prepararCampo(JTextField campo) {
        String descricao = descobrirDescricao(campo);
        campo.setToolTipText("Digite " + descricao);
        Border original = campo.getBorder();
        campo.addFocusListener(new FocusAdapter() {
            @Override public void focusGained(FocusEvent e) {
                campo.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(0, 128, 155), 2),
                    BorderFactory.createEmptyBorder(2, 4, 2, 4)));
                campo.setToolTipText("Digite " + descobrirDescricao(campo).replace("*", "").trim());
                campo.selectAll();
            }
            @Override public void focusLost(FocusEvent e) { campo.setBorder(original); }
        });
        // Um campo vazio tem sempre indicação acessível (tooltip) sem alterar o seu valor.
        campo.getDocument().addDocumentListener(new DocumentListener() {
            private void actualizar() { campo.putClientProperty("sge.preenchido", !campo.getText().trim().isEmpty()); }
            public void insertUpdate(DocumentEvent e) { actualizar(); }
            public void removeUpdate(DocumentEvent e) { actualizar(); }
            public void changedUpdate(DocumentEvent e) { actualizar(); }
        });
    }

    private static String descobrirDescricao(JTextField campo) {
        if (campo.getName() != null && !campo.getName().trim().isEmpty()) return campo.getName();
        Container pai = campo.getParent();
        if (pai != null) {
            Component[] filhos = pai.getComponents();
            for (int i = 0; i < filhos.length; i++) if (filhos[i] == campo) {
                for (int j = i - 1; j >= 0 && j >= i - 3; j--) {
                    if (filhos[j] instanceof JLabel) {
                        String texto = ((JLabel)filhos[j]).getText().replaceAll("<[^>]*>", "").trim();
                        if (!texto.isEmpty()) return texto.toLowerCase();
                    }
                }
            }
        }
        return "a informação solicitada";
    }

    private static void prepararArea(JTextArea area) {
        if (area.getToolTipText() == null && area.isEditable()) area.setToolTipText("Escreva o texto neste campo");
    }
    private static void prepararBotao(AbstractButton botao) {
        if (botao.getToolTipText() == null && botao.getText() != null && !botao.getText().isEmpty())
            botao.setToolTipText("Clique para " + botao.getText().toLowerCase());
        if (botao instanceof JButton) {
            JButton b = (JButton)botao;
            // Corrige automaticamente botões claros com letras brancas e vice-versa.
            actualizarLegibilidade(b);
            b.addPropertyChangeListener("background", e -> actualizarLegibilidade(b));
            b.addMouseListener(new MouseAdapter() {
                @Override public void mouseEntered(MouseEvent e) { b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); }
                @Override public void mouseExited(MouseEvent e) { b.setCursor(Cursor.getDefaultCursor()); }
            });
        }
    }
    private static void actualizarLegibilidade(JButton b) {
        Color fundo = b.getBackground();
        if (fundo == null) return;
        // Limiar de luminância perceptual: texto escuro nos fundos claros.
        double l = (0.2126 * fundo.getRed() + 0.7152 * fundo.getGreen()
            + 0.0722 * fundo.getBlue()) / 255.0;
        Color texto = l > 0.56 ? new Color(32, 43, 56) : Color.WHITE;
        if (!texto.equals(b.getForeground())) b.setForeground(texto);
    }

    private static void prepararCombo(JComboBox<?> combo) {
        if (combo.getToolTipText() == null) combo.setToolTipText("Escolha uma opção da lista");
        combo.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) combo.putClientProperty("sge.opcaoSeleccionada", e.getItem());
        });
    }
    private static void prepararTabela(JTable tabela) {
        tabela.setAutoCreateRowSorter(true);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.setToolTipText("Clique para seleccionar; clique nos cabeçalhos para ordenar");
        tabela.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) tabela.putClientProperty("sge.linhaSeleccionada", tabela.getSelectedRow());
        });
    }

}
