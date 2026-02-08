package com.biblioteca.view;

import com.biblioteca.controller.AdministradorController;
import com.biblioteca.model.Administrador;
import com.biblioteca.util.ValidadorCampos;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TelaCadastroAdmin extends JFrame {
    
    private JTextField txtNome, txtEmail;
    private JPasswordField txtSenha, txtConfirmarSenha;
    private JButton btnCadastrar, btnVoltar;
    private AdministradorController adminController;
    
    public TelaCadastroAdmin() {
        adminController = new AdministradorController();
        initComponents();
    }
    
    private void initComponents() {
        setTitle("Cadastro de Administrador");
        setSize(500, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBackground(new Color(248, 249, 250));
        
        JPanel header = new JPanel();
        header.setBackground(Color.WHITE);
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220, 220, 220)),
            new EmptyBorder(25, 20, 20, 20)
        ));
        
        JLabel lblTitulo = new JLabel("Criar Conta");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitulo.setForeground(new Color(40, 40, 40));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        header.add(lblTitulo);
        
        header.add(Box.createVerticalStrut(6));
        
        JLabel lblSubtitulo = new JLabel("Administrador");
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSubtitulo.setForeground(new Color(120, 120, 120));
        lblSubtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        header.add(lblSubtitulo);
        
        painelPrincipal.add(header, BorderLayout.NORTH);
        
        JPanel painelForm = new JPanel();
        painelForm.setLayout(new BoxLayout(painelForm, BoxLayout.Y_AXIS));
        painelForm.setBackground(new Color(248, 249, 250));
        painelForm.setBorder(new EmptyBorder(25, 40, 25, 40));
        
        painelForm.add(criarCampo("Nome Completo", txtNome = new JTextField()));
        painelForm.add(Box.createVerticalStrut(15));
        painelForm.add(criarCampo("Email", txtEmail = new JTextField()));
        painelForm.add(Box.createVerticalStrut(15));
        painelForm.add(criarCampo("Senha", txtSenha = new JPasswordField()));
        painelForm.add(Box.createVerticalStrut(15));
        painelForm.add(criarCampo("Confirmar Senha", txtConfirmarSenha = new JPasswordField()));
        
        painelForm.add(Box.createVerticalStrut(25));
        
        btnCadastrar = criarBotao("Cadastrar", new Color(0, 123, 255));
        btnCadastrar.addActionListener(this::btnCadastrarActionPerformed);
        btnCadastrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelForm.add(btnCadastrar);
        
        painelForm.add(Box.createVerticalStrut(10));
        
        btnVoltar = criarBotaoSecundario("Voltar");
        btnVoltar.addActionListener(e -> voltarTelaInicial());
        btnVoltar.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelForm.add(btnVoltar);
        
        painelPrincipal.add(painelForm, BorderLayout.CENTER);
        
        txtConfirmarSenha.addActionListener(this::btnCadastrarActionPerformed);
        
        add(painelPrincipal);
    }
    
    private JPanel criarCampo(String label, JTextField campo) {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBackground(new Color(248, 249, 250));
        painel.setMaximumSize(new Dimension(400, 70));
        painel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lbl.setForeground(new Color(80, 80, 80));
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        painel.add(lbl);
        
        painel.add(Box.createVerticalStrut(6));
        
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        campo.setMaximumSize(new Dimension(400, 38));
        campo.setPreferredSize(new Dimension(400, 38));
        campo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(206, 212, 218), 1),
            new EmptyBorder(8, 12, 8, 12)
        ));
        campo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painel.add(campo);
        
        return painel;
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
        btn.setMaximumSize(new Dimension(400, 42));
        
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
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btn.setBackground(Color.WHITE);
        btn.setForeground(new Color(108, 117, 125));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(new Color(206, 212, 218), 1));
        btn.setOpaque(true);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setMaximumSize(new Dimension(400, 42));
        btn.setPreferredSize(new Dimension(400, 42));
        
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(248, 249, 250));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(Color.WHITE);
            }
        });
        
        return btn;
    }
    
    private void btnCadastrarActionPerformed(ActionEvent evt) {
        String nome = txtNome.getText().trim();
        String email = txtEmail.getText().trim();
        String senha = new String(txtSenha.getPassword());
        String confirmarSenha = new String(txtConfirmarSenha.getPassword());
        
        // Validações de campos vazios
        if (!ValidadorCampos.validarCampoVazio(nome, "Nome")) return;
        if (!ValidadorCampos.validarCampoVazio(email, "Email")) return;
        if (!ValidadorCampos.validarCampoVazio(senha, "Senha")) return;
        if (!ValidadorCampos.validarCampoVazio(confirmarSenha, "Confirmar Senha")) return;
        
        // Validação de formato de email
        if (!ValidadorCampos.validarEmail(email)) return;
        
        // Validação de tamanho mínimo da senha
        if (!ValidadorCampos.validarSenha(senha, 6)) return;
        
        // Validação de senhas coincidentes
        if (!senha.equals(confirmarSenha)) {
            ValidadorCampos.mensagemErro("As senhas nao coincidem!");
            return;
        }
        
        // Validação de email já existente
        if (adminController.buscarPorEmail(email) != null) {
            ValidadorCampos.mensagemErro("Email ja cadastrado! Por favor, use outro email.");
            return;
        }
        
        // Criar e salvar administrador
        Administrador admin = new Administrador();
        admin.setNome(nome);
        admin.setEmail(email);
        admin.setSenha(senha);
        
        if (adminController.salvarAdministrador(admin)) {
            ValidadorCampos.mensagemSucesso("Administrador cadastrado com sucesso!");
            voltarTelaInicial();
        } else {
            ValidadorCampos.mensagemErro("Erro ao cadastrar administrador! Email ja existe.");
        }
    }
    
    private void voltarTelaInicial() {
        TelaInicial telaInicial = new TelaInicial();
        telaInicial.setVisible(true);
        this.dispose();
    }
}
