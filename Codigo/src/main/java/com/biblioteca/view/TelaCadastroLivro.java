package com.biblioteca.view;

import com.biblioteca.controller.LivroController;
import com.biblioteca.model.Livro;
import com.biblioteca.util.ValidadorCampos;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

public class TelaCadastroLivro extends JFrame {
    
    // Componentes do formulario
    private JTextField txtTitulo, txtAutor, txtGenero, txtAno;
    private JTextArea txtSinopse;
    private JLabel lblImagemPreview;
    private JButton btnSalvar, btnCancelar, btnSelecionarImagem;
    
    // Controller e dados
    private LivroController livroController;
    private String caminhoImagemSelecionada;
    private Runnable onSaveCallback;
    
    // Paleta de cores consistente
    private static final Color COR_FUNDO = new Color(248, 249, 250);
    private static final Color COR_BRANCO = Color.WHITE;
    private static final Color COR_BORDA = new Color(220, 220, 220);
    private static final Color COR_BORDA_INPUT = new Color(206, 212, 218);
    private static final Color COR_TEXTO_PRINCIPAL = new Color(40, 40, 40);
    private static final Color COR_TEXTO_SECUNDARIO = new Color(80, 80, 80);
    private static final Color COR_BOTAO_PRIMARIO = new Color(40, 167, 69);
    private static final Color COR_BOTAO_SECUNDARIO = new Color(108, 117, 125);
    private static final Color COR_PREVIEW_FUNDO = new Color(240, 240, 240);
    
    public TelaCadastroLivro() {
        this(null);
    }
    
    public TelaCadastroLivro(Runnable onSaveCallback) {
        livroController = new LivroController();
        caminhoImagemSelecionada = null;
        this.onSaveCallback = onSaveCallback;
        initComponents();
    }
    
    private void initComponents() {
        configurarJanela();
        
        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBackground(COR_FUNDO);
        
        painelPrincipal.add(criarHeader(), BorderLayout.NORTH);
        painelPrincipal.add(criarConteudo(), BorderLayout.CENTER);
        
        add(painelPrincipal);
    }
    
    private void configurarJanela() {
        setTitle("Cadastrar Novo Livro");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    private JPanel criarHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(COR_BRANCO);
        header.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, COR_BORDA),
            new EmptyBorder(25, 30, 25, 30)
        ));
        
        JLabel lblTitulo = new JLabel("Cadastrar Novo Livro");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setForeground(COR_TEXTO_PRINCIPAL);
        header.add(lblTitulo, BorderLayout.WEST);
        
        return header;
    }
    
    private JPanel criarConteudo() {
        JPanel painelConteudo = new JPanel(new GridBagLayout());
        painelConteudo.setBackground(COR_FUNDO);
        painelConteudo.setBorder(new EmptyBorder(40, 50, 40, 50));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 0, 0);
        
        // Painel esquerdo - Preview da imagem (35%)
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.35;
        gbc.weighty = 1.0;
        painelConteudo.add(criarPainelImagem(), gbc);
        
        // Espacamento entre paineis
        gbc.gridx = 1;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        painelConteudo.add(Box.createHorizontalStrut(40), gbc);
        
        // Painel direito - Formulario (65%)
        gbc.gridx = 2;
        gbc.weightx = 0.65;
        gbc.fill = GridBagConstraints.BOTH;
        painelConteudo.add(criarPainelFormulario(), gbc);
        
        return painelConteudo;
    }
    
    private JPanel criarPainelImagem() {
        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBackground(COR_FUNDO);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 20, 0);
        
        // Titulo da secao
        JLabel lblTituloSecao = new JLabel("Capa do Livro");
        lblTituloSecao.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTituloSecao.setForeground(COR_TEXTO_SECUNDARIO);
        painel.add(lblTituloSecao, gbc);
        
        // Container da imagem
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 25, 0);
        
        JPanel containerImagem = new JPanel(new BorderLayout());
        containerImagem.setBackground(COR_PREVIEW_FUNDO);
        containerImagem.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COR_BORDA, 2),
            new EmptyBorder(10, 10, 10, 10)
        ));
        containerImagem.setPreferredSize(new Dimension(320, 450));
        
        lblImagemPreview = new JLabel("Nenhuma imagem selecionada");
        lblImagemPreview.setHorizontalAlignment(JLabel.CENTER);
        lblImagemPreview.setVerticalAlignment(JLabel.CENTER);
        lblImagemPreview.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblImagemPreview.setForeground(Color.GRAY);
        containerImagem.add(lblImagemPreview, BorderLayout.CENTER);
        
        painel.add(containerImagem, gbc);
        
        // Botao selecionar imagem
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 0, 0);
        
        btnSelecionarImagem = criarBotao("Selecionar Imagem", COR_BOTAO_SECUNDARIO, 220, 42);
        btnSelecionarImagem.addActionListener(e -> selecionarImagem());
        painel.add(btnSelecionarImagem, gbc);
        
        return painel;
    }
    
    private JPanel criarPainelFormulario() {
        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBackground(COR_FUNDO);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(0, 0, 20, 0);
        
        // Titulo
        painel.add(criarCampoTexto("Título *", txtTitulo = new JTextField()), gbc);
        
        // Autor
        gbc.gridy++;
        painel.add(criarCampoTexto("Autor *", txtAutor = new JTextField()), gbc);
        
        // Grid para Ano e Genero (2 colunas)
        gbc.gridy++;
        JPanel painelDuasColunas = new JPanel(new GridBagLayout());
        painelDuasColunas.setBackground(COR_FUNDO);
        
        GridBagConstraints gbcCol = new GridBagConstraints();
        gbcCol.gridy = 0;
        gbcCol.fill = GridBagConstraints.HORIZONTAL;
        gbcCol.weightx = 0.48;
        
        // Ano
        gbcCol.gridx = 0;
        painelDuasColunas.add(criarCampoTexto("Ano de Publicação *", txtAno = new JTextField()), gbcCol);
        
        // Espacamento
        gbcCol.gridx = 1;
        gbcCol.weightx = 0.04;
        gbcCol.fill = GridBagConstraints.NONE;
        painelDuasColunas.add(Box.createHorizontalStrut(20), gbcCol);
        
        // Genero
        gbcCol.gridx = 2;
        gbcCol.weightx = 0.48;
        gbcCol.fill = GridBagConstraints.HORIZONTAL;
        painelDuasColunas.add(criarCampoTexto("Gênero", txtGenero = new JTextField()), gbcCol);
        
        painel.add(painelDuasColunas, gbc);
        
        // Sinopse
        gbc.gridy++;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        painel.add(criarCampoSinopse(), gbc);
        
        // Painel de botoes
        gbc.gridy++;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(30, 0, 0, 0);
        painel.add(criarPainelBotoes(), gbc);
        
        return painel;
    }
    
    private JPanel criarCampoTexto(String label, JTextField campo) {
        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBackground(COR_FUNDO);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 0, 8, 0);
        
        // Label
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbl.setForeground(COR_TEXTO_SECUNDARIO);
        painel.add(lbl, gbc);
        
        // Campo
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        campo.setPreferredSize(new Dimension(0, 44));
        campo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COR_BORDA_INPUT, 1),
            new EmptyBorder(10, 14, 10, 14)
        ));
        painel.add(campo, gbc);
        
        return painel;
    }
    
    private JPanel criarCampoSinopse() {
        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBackground(COR_FUNDO);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 0, 8, 0);
        
        // Label
        JLabel lbl = new JLabel("Sinopse");
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbl.setForeground(COR_TEXTO_SECUNDARIO);
        painel.add(lbl, gbc);
        
        // TextArea
        gbc.gridy = 1;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 0, 0);
        
        txtSinopse = new JTextArea();
        txtSinopse.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtSinopse.setLineWrap(true);
        txtSinopse.setWrapStyleWord(true);
        txtSinopse.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COR_BORDA_INPUT, 1),
            new EmptyBorder(10, 14, 10, 14)
        ));
        
        JScrollPane scroll = new JScrollPane(txtSinopse);
        scroll.setBorder(null);
        scroll.setPreferredSize(new Dimension(0, 150));
        painel.add(scroll, gbc);
        
        return painel;
    }
    
    private JPanel criarPainelBotoes() {
        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBackground(COR_FUNDO);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 15);
        
        // Espacador para empurrar botoes para direita
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        painel.add(Box.createHorizontalGlue(), gbc);
        
        // Botao Cancelar
        gbc.gridx = 1;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        btnCancelar = criarBotao("Cancelar", COR_BOTAO_SECUNDARIO, 130, 42);
        btnCancelar.addActionListener(e -> dispose());
        painel.add(btnCancelar, gbc);
        
        // Botao Salvar
        gbc.gridx = 2;
        gbc.insets = new Insets(0, 0, 0, 0);
        btnSalvar = criarBotao("Salvar Livro", COR_BOTAO_PRIMARIO, 160, 42);
        btnSalvar.addActionListener(e -> salvarLivro());
        painel.add(btnSalvar, gbc);
        
        return painel;
    }
    
    private JButton criarBotao(String texto, Color cor, int largura, int altura) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBackground(cor);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(largura, altura));
        
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
    
    private void selecionarImagem() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Imagens (*.jpg, *.png, *.jpeg)", "jpg", "png", "jpeg");
        fileChooser.setFileFilter(filter);
        
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            caminhoImagemSelecionada = selectedFile.getAbsolutePath();
            
            ImageIcon icon = new ImageIcon(caminhoImagemSelecionada);
            Image img = icon.getImage().getScaledInstance(300, 430, Image.SCALE_SMOOTH);
            lblImagemPreview.setIcon(new ImageIcon(img));
            lblImagemPreview.setText("");
        }
    }
    
    private void salvarLivro() {
        String titulo = txtTitulo.getText().trim();
        String autor = txtAutor.getText().trim();
        String genero = txtGenero.getText().trim();
        String anoStr = txtAno.getText().trim();
        String sinopse = txtSinopse.getText().trim();
        
        if (!ValidadorCampos.validarCampoVazio(titulo, "Titulo")) return;
        if (!ValidadorCampos.validarCampoVazio(autor, "Autor")) return;
        
        int ano = 0;
        try {
            ano = Integer.parseInt(anoStr);
        } catch (NumberFormatException e) {
            ValidadorCampos.mensagemErro("Ano invalido!");
            return;
        }
        
        Livro livro = new Livro();
        livro.setTitulo(titulo);
        livro.setAutor(autor);
        livro.setGenero(genero);
        livro.setAnoPublicacao(ano);
        livro.setSinopse(sinopse);
        livro.setCaminhoImagem(caminhoImagemSelecionada);
        
        if (livroController.salvarLivro(livro)) {
            ValidadorCampos.mensagemSucesso("Livro cadastrado com sucesso!");
            if (onSaveCallback != null) {
                onSaveCallback.run();
            }
            limparCampos();
        } else {
            ValidadorCampos.mensagemErro("Erro ao cadastrar livro!");
        }
    }
    
    private void limparCampos() {
        txtTitulo.setText("");
        txtAutor.setText("");
        txtGenero.setText("");
        txtAno.setText("");
        txtSinopse.setText("");
        caminhoImagemSelecionada = null;
        lblImagemPreview.setIcon(null);
        lblImagemPreview.setText("Nenhuma imagem selecionada");
        txtTitulo.requestFocus();
    }
}
