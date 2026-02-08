package com.biblioteca.view;

import com.biblioteca.model.Administrador;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Tela Principal do Administrador - Design Moderno
 */
public class TelaPrincipalAdmin extends JFrame {
    
    private Administrador adminLogado;
    
    public TelaPrincipalAdmin(Administrador admin) {
        this.adminLogado = admin;
        initComponents();
    }
    
    private void initComponents() {
        setTitle("Painel Administrativo - Sistema de Biblioteca Digital");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBackground(new Color(248, 249, 250));
        
        // Header moderno
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(0, 123, 255));
        header.setBorder(new EmptyBorder(25, 30, 25, 30));
        
        JPanel headerConteudo = new JPanel(new BorderLayout());
        headerConteudo.setBackground(new Color(0, 123, 255));
        
        JLabel lblBoasVindas = new JLabel("Olá, " + adminLogado.getNome() + "!");
        lblBoasVindas.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblBoasVindas.setForeground(Color.WHITE);
        headerConteudo.add(lblBoasVindas, BorderLayout.WEST);
        
        JLabel lblSubtitulo = new JLabel("Painel Administrativo");
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblSubtitulo.setForeground(new Color(220, 235, 255));
        headerConteudo.add(lblSubtitulo, BorderLayout.SOUTH);
        
        header.add(headerConteudo, BorderLayout.CENTER);
        
        // Botão Sair no header
        JButton btnSair = criarBotaoHeader("Sair");
        btnSair.addActionListener(e -> sair());
        header.add(btnSair, BorderLayout.EAST);
        
        painelPrincipal.add(header, BorderLayout.NORTH);
        
        // Conteúdo central com cards
        JPanel painelConteudo = new JPanel(new GridBagLayout());
        painelConteudo.setBackground(new Color(248, 249, 250));
        painelConteudo.setBorder(new EmptyBorder(40, 50, 40, 50));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        
        // Card Cadastrar Livro
        gbc.gridx = 0;
        gbc.gridy = 0;
        painelConteudo.add(criarCard(
            "Cadastrar Livro",
            "Adicione novos livros ao acervo da biblioteca",
            new Color(40, 167, 69),
            e -> abrirTelaCadastroLivro()
        ), gbc);
        
        // Card Gerenciar Livros
        gbc.gridx = 1;
        gbc.gridy = 0;
        painelConteudo.add(criarCard(
            "Gerenciar Livros",
            "Visualize, edite e organize o acervo completo",
            new Color(0, 123, 255),
            e -> abrirTelaGerenciarLivros()
        ), gbc);
        
        // Card Gerenciar Usuários
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        painelConteudo.add(criarCard(
            "Gerenciar Usuários",
            "Gerencie os usuários cadastrados no sistema",
            new Color(123, 31, 162),
            e -> abrirTelaGerenciarUsuarios()
        ), gbc);
        
        painelPrincipal.add(painelConteudo, BorderLayout.CENTER);
        
        add(painelPrincipal);
    }
    
    private JPanel criarCard(String titulo, String descricao, Color cor, java.awt.event.ActionListener action) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout(10, 10));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
            new EmptyBorder(25, 25, 25, 25)
        ));
        card.setPreferredSize(new Dimension(360, 180));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Ícone/Título
        JPanel headerCard = new JPanel(new BorderLayout());
        headerCard.setBackground(Color.WHITE);
        
        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setForeground(cor);
        headerCard.add(lblTitulo, BorderLayout.WEST);
        
        card.add(headerCard, BorderLayout.NORTH);
        
        // Descrição
        JTextArea txtDescricao = new JTextArea(descricao);
        txtDescricao.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtDescricao.setForeground(new Color(100, 100, 100));
        txtDescricao.setBackground(Color.WHITE);
        txtDescricao.setLineWrap(true);
        txtDescricao.setWrapStyleWord(true);
        txtDescricao.setEditable(false);
        txtDescricao.setFocusable(false);
        txtDescricao.setBorder(new EmptyBorder(5, 0, 0, 0));
        card.add(txtDescricao, BorderLayout.CENTER);
        
        // Botão de ação
        JPanel painelBotao = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        painelBotao.setBackground(Color.WHITE);
        
        JButton btnAcao = new JButton("Acessar →");
        btnAcao.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnAcao.setBackground(cor);
        btnAcao.setForeground(Color.WHITE);
        btnAcao.setFocusPainted(false);
        btnAcao.setBorderPainted(false);
        btnAcao.setOpaque(true);
        btnAcao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAcao.setPreferredSize(new Dimension(120, 36));
        btnAcao.addActionListener(action);
        
        // Efeito hover no botão
        btnAcao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAcao.setBackground(cor.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAcao.setBackground(cor);
            }
        });
        
        painelBotao.add(btnAcao);
        card.add(painelBotao, BorderLayout.SOUTH);
        
        // Efeito hover no card
        card.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                card.setBackground(new Color(248, 249, 250));
                txtDescricao.setBackground(new Color(248, 249, 250));
                headerCard.setBackground(new Color(248, 249, 250));
                painelBotao.setBackground(new Color(248, 249, 250));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                card.setBackground(Color.WHITE);
                txtDescricao.setBackground(Color.WHITE);
                headerCard.setBackground(Color.WHITE);
                painelBotao.setBackground(Color.WHITE);
            }
        });
        
        return card;
    }
    
    private JButton criarBotaoHeader(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBackground(new Color(220, 53, 69));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(100, 40));
        
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(200, 35, 51));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(220, 53, 69));
            }
        });
        
        return btn;
    }
    
    private void abrirTelaCadastroLivro() {
        TelaCadastroLivro tela = new TelaCadastroLivro();
        tela.setVisible(true);
    }
    
    private void abrirTelaGerenciarLivros() {
        TelaGerenciarLivros tela = new TelaGerenciarLivros();
        tela.setVisible(true);
    }
    
    private void abrirTelaGerenciarUsuarios() {
        TelaGerenciarUsuarios tela = new TelaGerenciarUsuarios();
        tela.setVisible(true);
    }
    
    private void sair() {
        int opcao = JOptionPane.showConfirmDialog(this,
                "Deseja realmente sair do sistema?",
                "Confirmar Saida",
                JOptionPane.YES_NO_OPTION);
        
        if (opcao == JOptionPane.YES_OPTION) {
            TelaInicial telaInicial = new TelaInicial();
            telaInicial.setVisible(true);
            this.dispose();
        }
    }
}
