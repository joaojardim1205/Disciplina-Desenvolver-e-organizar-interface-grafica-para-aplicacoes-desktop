package com.biblioteca.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Tela Inicial - Design Moderno
 */
public class TelaInicial extends JFrame {
    
    private JButton btnLoginAdmin, btnLoginUsuario, btnCadastroAdmin, btnCadastroUsuario;
    
    public TelaInicial() {
        initComponents();
    }
    
    private void initComponents() {
        setTitle("Sistema de Biblioteca Digital");
        setSize(500, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        // Painel principal
        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBackground(new Color(248, 249, 250));
        
        // Header
        JPanel header = new JPanel();
        header.setBackground(new Color(40, 40, 40));
        header.setPreferredSize(new Dimension(500, 120));
        header.setLayout(new GridBagLayout());
        
        JLabel lblTitulo = new JLabel("Biblioteca Digital");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 32));
        lblTitulo.setForeground(Color.WHITE);
        header.add(lblTitulo);
        
        painelPrincipal.add(header, BorderLayout.NORTH);
        
        // Conteudo
        JPanel painelConteudo = new JPanel();
        painelConteudo.setLayout(new BoxLayout(painelConteudo, BoxLayout.Y_AXIS));
        painelConteudo.setBackground(new Color(248, 249, 250));
        painelConteudo.setBorder(new EmptyBorder(30, 50, 30, 50));
        
        // Subtitulo
        JLabel lblSubtitulo = new JLabel("Bem-vindo! Escolha uma opcao:");
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lblSubtitulo.setForeground(new Color(100, 100, 100));
        lblSubtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelConteudo.add(lblSubtitulo);
        
        painelConteudo.add(Box.createVerticalStrut(25));
        
        // Secao Admin
        JLabel lblAdmin = new JLabel("Administrador");
        lblAdmin.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblAdmin.setForeground(new Color(60, 60, 60));
        lblAdmin.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelConteudo.add(lblAdmin);
        
        painelConteudo.add(Box.createVerticalStrut(12));
        
        btnLoginAdmin = criarBotao("Login Admin", new Color(0, 123, 255));
        btnLoginAdmin.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLoginAdmin.addActionListener(e -> abrirLoginAdmin());
        painelConteudo.add(btnLoginAdmin);
        
        painelConteudo.add(Box.createVerticalStrut(10));
        
        btnCadastroAdmin = criarBotaoSecundario("Cadastrar Admin");
        btnCadastroAdmin.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCadastroAdmin.addActionListener(e -> abrirCadastroAdmin());
        painelConteudo.add(btnCadastroAdmin);
        
        painelConteudo.add(Box.createVerticalStrut(30));
        
        // Secao Usuario
        JLabel lblUsuario = new JLabel("Usuario");
        lblUsuario.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblUsuario.setForeground(new Color(60, 60, 60));
        lblUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelConteudo.add(lblUsuario);
        
        painelConteudo.add(Box.createVerticalStrut(12));
        
        btnLoginUsuario = criarBotao("Login Usuario", new Color(40, 167, 69));
        btnLoginUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLoginUsuario.addActionListener(e -> abrirLoginUsuario());
        painelConteudo.add(btnLoginUsuario);
        
        painelConteudo.add(Box.createVerticalStrut(10));
        
        btnCadastroUsuario = criarBotaoSecundario("Cadastrar Usuario");
        btnCadastroUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCadastroUsuario.addActionListener(e -> abrirCadastroUsuario());
        painelConteudo.add(btnCadastroUsuario);
        
        painelPrincipal.add(painelConteudo, BorderLayout.CENTER);
        
        add(painelPrincipal);
    }
    
    private JButton criarBotao(String texto, Color cor) {
        JButton btn = new JButton();
        btn.setText(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBackground(cor);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setMaximumSize(new Dimension(350, 45));
        btn.setPreferredSize(new Dimension(350, 45));
        
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(cor.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(cor);
            }
        });
        
        return btn;
    }
    
    private JButton criarBotaoSecundario(String texto) {
        JButton btn = new JButton();
        btn.setText(texto);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btn.setBackground(Color.WHITE);
        btn.setForeground(new Color(100, 100, 100));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));
        btn.setOpaque(true);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setMaximumSize(new Dimension(350, 40));
        btn.setPreferredSize(new Dimension(350, 40));
        
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(245, 245, 245));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(Color.WHITE);
            }
        });
        
        return btn;
    }
    
    private void abrirLoginAdmin() {
        TelaLogin telaLogin = new TelaLogin();
        telaLogin.setVisible(true);
        this.dispose();
    }
    
    private void abrirCadastroAdmin() {
        TelaCadastroAdmin telaCadastro = new TelaCadastroAdmin();
        telaCadastro.setVisible(true);
        this.dispose();
    }
    
    private void abrirLoginUsuario() {
        TelaLoginUsuario telaLogin = new TelaLoginUsuario();
        telaLogin.setVisible(true);
        this.dispose();
    }
    
    private void abrirCadastroUsuario() {
        TelaCadastroUsuario telaCadastro = new TelaCadastroUsuario();
        telaCadastro.setVisible(true);
        this.dispose();
    }
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            TelaInicial tela = new TelaInicial();
            tela.setVisible(true);
        });
    }
}
