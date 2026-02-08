package com.biblioteca.view;

import com.biblioteca.controller.UsuarioController;
import com.biblioteca.model.Usuario;
import com.biblioteca.util.ValidadorCampos;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TelaLoginUsuario extends JFrame {
    
    private JTextField txtEmail;
    private JPasswordField txtSenha;
    private JButton btnEntrar, btnVoltar;
    private UsuarioController usuarioController;
    
    public TelaLoginUsuario() {
        usuarioController = new UsuarioController();
        initComponents();
    }
    
    private void initComponents() {
        setTitle("Login - Usuario");
        setSize(450, 500);
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
            new EmptyBorder(30, 20, 25, 20)
        ));
        
        JLabel lblTitulo = new JLabel("Bem-vindo");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitulo.setForeground(new Color(40, 40, 40));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        header.add(lblTitulo);
        
        header.add(Box.createVerticalStrut(8));
        
        JLabel lblSubtitulo = new JLabel("Login de Usuario");
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSubtitulo.setForeground(new Color(120, 120, 120));
        lblSubtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        header.add(lblSubtitulo);
        
        painelPrincipal.add(header, BorderLayout.NORTH);
        
        JPanel painelForm = new JPanel();
        painelForm.setLayout(new BoxLayout(painelForm, BoxLayout.Y_AXIS));
        painelForm.setBackground(new Color(248, 249, 250));
        painelForm.setBorder(new EmptyBorder(30, 40, 30, 40));
        
        painelForm.add(criarCampo("Email", txtEmail = new JTextField()));
        painelForm.add(Box.createVerticalStrut(18));
        painelForm.add(criarCampo("Senha", txtSenha = new JPasswordField()));
        
        painelForm.add(Box.createVerticalStrut(25));
        
        btnEntrar = criarBotao("Entrar", new Color(40, 167, 69));
        btnEntrar.addActionListener(this::btnEntrarActionPerformed);
        btnEntrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelForm.add(btnEntrar);
        
        painelForm.add(Box.createVerticalStrut(12));
        
        btnVoltar = criarBotaoSecundario("Voltar");
        btnVoltar.addActionListener(e -> voltarTelaInicial());
        btnVoltar.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelForm.add(btnVoltar);
        
        painelPrincipal.add(painelForm, BorderLayout.CENTER);
        
        txtSenha.addActionListener(this::btnEntrarActionPerformed);
        
        add(painelPrincipal);
    }
    
    private JPanel criarCampo(String label, JTextField campo) {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBackground(new Color(248, 249, 250));
        painel.setMaximumSize(new Dimension(350, 70));
        painel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lbl.setForeground(new Color(80, 80, 80));
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        painel.add(lbl);
        
        painel.add(Box.createVerticalStrut(6));
        
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        campo.setMaximumSize(new Dimension(350, 38));
        campo.setPreferredSize(new Dimension(350, 38));
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
        btn.setMaximumSize(new Dimension(350, 42));
        btn.setPreferredSize(new Dimension(350, 42));
        
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
        btn.setForeground(new Color(108, 117, 125));
        btn.setFocusPainted(false);
        btn.setBorderPainted(true);
        btn.setBorder(BorderFactory.createLineBorder(new Color(206, 212, 218), 1));
        btn.setOpaque(true);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setMaximumSize(new Dimension(350, 38));
        btn.setPreferredSize(new Dimension(350, 38));
        
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
    
    private void btnEntrarActionPerformed(ActionEvent evt) {
        String email = txtEmail.getText().trim();
        String senha = new String(txtSenha.getPassword());
        
        if (!ValidadorCampos.validarCampoVazio(email, "Email")) return;
        if (!ValidadorCampos.validarCampoVazio(senha, "Senha")) return;
        
        Usuario usuario = usuarioController.autenticar(email, senha);
        
        if (usuario != null) {
            ValidadorCampos.mensagemSucesso("Bem-vindo, " + usuario.getNome() + "!");
            TelaPrincipalUsuario telaPrincipal = new TelaPrincipalUsuario(usuario);
            telaPrincipal.setVisible(true);
            this.dispose();
        } else {
            ValidadorCampos.mensagemErro("Email ou senha incorretos!");
            txtSenha.setText("");
            txtEmail.requestFocus();
        }
    }
    
    private void voltarTelaInicial() {
        TelaInicial telaInicial = new TelaInicial();
        telaInicial.setVisible(true);
        this.dispose();
    }
}
