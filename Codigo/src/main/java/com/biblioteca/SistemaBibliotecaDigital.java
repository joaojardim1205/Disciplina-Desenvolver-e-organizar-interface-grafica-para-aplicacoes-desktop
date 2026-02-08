package com.biblioteca;

import com.biblioteca.view.TelaInicial;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Classe Principal do Sistema de Biblioteca Digital
 * Versao 3.0 - Interface Moderna
 */
public class SistemaBibliotecaDigital {
    
    public static void main(String[] args) {
        try {
            // Define o look and feel do sistema
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Inicia a aplicacao
        SwingUtilities.invokeLater(() -> {
            TelaInicial telaInicial = new TelaInicial();
            telaInicial.setVisible(true);
        });
    }
}
