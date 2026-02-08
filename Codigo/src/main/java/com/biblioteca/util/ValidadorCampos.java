package com.biblioteca.util;

import javax.swing.JOptionPane;

/**
 * Classe utilitária para validação de campos de formulário
 */
public class ValidadorCampos {
    
    /**
     * Valida se um campo de texto está vazio
     * @param texto Texto a ser validado
     * @param nomeCampo Nome do campo para mensagem de erro
     * @return true se válido, false caso contrário
     */
    public static boolean validarCampoVazio(String texto, String nomeCampo) {
        if (texto == null || texto.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, 
                "O campo " + nomeCampo + " não pode estar vazio!", 
                "Erro de Validação", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    /**
     * Valida se um email está em formato válido
     * @param email Email a ser validado
     * @return true se válido, false caso contrário
     */
    public static boolean validarEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, 
                "O email não pode estar vazio!", 
                "Erro de Validação", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        if (!email.matches(emailRegex)) {
            JOptionPane.showMessageDialog(null, 
                "Email inválido! Use o formato: exemplo@email.com", 
                "Erro de Validação", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    /**
     * Valida se um ano é válido
     * @param ano Ano a ser validado
     * @return true se válido, false caso contrário
     */
    public static boolean validarAno(int ano) {
        int anoAtual = java.time.Year.now().getValue();
        
        if (ano < 1000 || ano > anoAtual) {
            JOptionPane.showMessageDialog(null, 
                "Ano inválido! Deve estar entre 1000 e " + anoAtual, 
                "Erro de Validação", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    /**
     * Valida se uma senha tem tamanho mínimo
     * @param senha Senha a ser validada
     * @param tamanhoMinimo Tamanho mínimo da senha
     * @return true se válida, false caso contrário
     */
    public static boolean validarSenha(String senha, int tamanhoMinimo) {
        if (senha == null || senha.length() < tamanhoMinimo) {
            JOptionPane.showMessageDialog(null, 
                "A senha deve ter no mínimo " + tamanhoMinimo + " caracteres!", 
                "Erro de Validação", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    /**
     * Exibe mensagem de sucesso
     * @param mensagem Mensagem a ser exibida
     */
    public static void mensagemSucesso(String mensagem) {
        JOptionPane.showMessageDialog(null, 
            mensagem, 
            "Sucesso", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Exibe mensagem de erro
     * @param mensagem Mensagem a ser exibida
     */
    public static void mensagemErro(String mensagem) {
        JOptionPane.showMessageDialog(null, 
            mensagem, 
            "Erro", 
            JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Exibe mensagem de confirmação
     * @param mensagem Mensagem a ser exibida
     * @return true se confirmado, false caso contrário
     */
    public static boolean confirmar(String mensagem) {
        int opcao = JOptionPane.showConfirmDialog(null, 
            mensagem, 
            "Confirmação", 
            JOptionPane.YES_NO_OPTION);
        
        return opcao == JOptionPane.YES_OPTION;
    }
}
