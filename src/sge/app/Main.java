package sge.app;

import sge.model.*;
import sge.controller.*;
import sge.view.*;
import sge.repository.*;

import javax.swing.*;

/**
 * Ponto de entrada da aplicação. Inicializa a interface Swing e o fluxo de arranque.
 *
 * <p>Projecto académico SGE: documentação das responsabilidades desta classe.</p>
 */
public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Não foi possível aplicar o tema visual: " + e.getMessage());
        }
        sge.view.TemaVisual.aplicar();
        SwingUtilities.invokeLater(() -> {
            DadosSistema.carregarDados();
            new TelaLogin();
        });
    }
}
