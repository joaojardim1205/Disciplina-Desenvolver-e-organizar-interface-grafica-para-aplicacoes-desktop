package com.biblioteca.view;

import com.biblioteca.model.Livro;
import com.biblioteca.model.Usuario;
import com.biblioteca.model.StatusLeitura;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;

public class TelaDetalhesLivroUsuario extends JDialog {
    
    // Componentes
    private JButton btnFavorito;
    private JPanel painelEstrelas;
    private JComboBox<String> cbStatus;
    private JLabel lblImagemCapa;
    
    // Dados e callbacks
    private Livro livro;
    private Usuario usuario;
    private Runnable onUpdateCallback;
    
    // Paleta de cores consistente
    private static final Color COR_FUNDO = new Color(248, 249, 250);
    private static final Color COR_BRANCO = Color.WHITE;
    private static final Color COR_BORDA = new Color(220, 220, 220);
    private static final Color COR_TEXTO_PRINCIPAL = new Color(40, 40, 40);
    private static final Color COR_TEXTO_SECUNDARIO = new Color(80, 80, 80);
    private static final Color COR_TEXTO_TERCIARIO = new Color(120, 120, 120);
    private static final Color COR_BOTAO_PERIGO = new Color(220, 53, 69);
    private static final Color COR_BOTAO_SECUNDARIO = new Color(108, 117, 125);
    private static final Color COR_PREVIEW_FUNDO = new Color(240, 240, 240);
    private static final Color COR_ESTRELA_ATIVA = new Color(255, 193, 7);
    private static final Color COR_ESTRELA_INATIVA = new Color(200, 200, 200);
    
    public TelaDetalhesLivroUsuario(Livro livro, Usuario usuario, Runnable onUpdateCallback) {
        this.livro = livro;
        this.usuario = usuario;
        this.onUpdateCallback = onUpdateCallback;
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
        setTitle(livro.getTitulo());
        setSize(900, 650);
        setModal(true);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }
    
    private JPanel criarHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(COR_BRANCO);
        header.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, COR_BORDA),
            new EmptyBorder(25, 30, 25, 30)
        ));
        
        JLabel lblTitulo = new JLabel("Detalhes do Livro");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setForeground(COR_TEXTO_PRINCIPAL);
        header.add(lblTitulo, BorderLayout.WEST);
        
        btnFavorito = criarBotaoFavorito();
        header.add(btnFavorito, BorderLayout.EAST);
        
        return header;
    }
    
    private JPanel criarConteudo() {
        JPanel painelConteudo = new JPanel(new GridBagLayout());
        painelConteudo.setBackground(COR_FUNDO);
        painelConteudo.setBorder(new EmptyBorder(40, 40, 40, 40));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 0, 0);
        
        // Painel esquerdo - Imagem (30%)
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.30;
        gbc.weighty = 1.0;
        painelConteudo.add(criarPainelImagem(), gbc);
        
        // Espacamento entre paineis
        gbc.gridx = 1;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        painelConteudo.add(Box.createHorizontalStrut(40), gbc);
        
        // Painel direito - Informacoes (70%)
        gbc.gridx = 2;
        gbc.weightx = 0.70;
        gbc.fill = GridBagConstraints.BOTH;
        painelConteudo.add(criarPainelInformacoes(), gbc);
        
        return painelConteudo;
    }
    
    private JPanel criarPainelImagem() {
        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBackground(COR_FUNDO);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        
        JPanel containerImagem = new JPanel(new BorderLayout());
        containerImagem.setBackground(COR_PREVIEW_FUNDO);
        containerImagem.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COR_BORDA, 2),
            new EmptyBorder(10, 10, 10, 10)
        ));
        containerImagem.setPreferredSize(new Dimension(240, 360));
        
        lblImagemCapa = new JLabel();
        lblImagemCapa.setHorizontalAlignment(JLabel.CENTER);
        lblImagemCapa.setVerticalAlignment(JLabel.CENTER);
        
        carregarImagemCapa();
        
        containerImagem.add(lblImagemCapa, BorderLayout.CENTER);
        painel.add(containerImagem, gbc);
        
        return painel;
    }
    
    private void carregarImagemCapa() {
        if (livro.getCaminhoImagem() != null && !livro.getCaminhoImagem().isEmpty()) {
            File imgFile = new File(livro.getCaminhoImagem());
            if (imgFile.exists()) {
                ImageIcon icon = new ImageIcon(livro.getCaminhoImagem());
                Image img = icon.getImage().getScaledInstance(220, 340, Image.SCALE_SMOOTH);
                lblImagemCapa.setIcon(new ImageIcon(img));
            } else {
                lblImagemCapa.setText("Sem Imagem");
                lblImagemCapa.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                lblImagemCapa.setForeground(Color.GRAY);
            }
        } else {
            lblImagemCapa.setText("Sem Imagem");
            lblImagemCapa.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            lblImagemCapa.setForeground(Color.GRAY);
        }
    }
    
    private JPanel criarPainelInformacoes() {
        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBackground(COR_FUNDO);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(0, 0, 15, 0);
        
        // Titulo do livro
        JLabel lblTituloLivro = new JLabel(livro.getTitulo());
        lblTituloLivro.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTituloLivro.setForeground(COR_TEXTO_PRINCIPAL);
        painel.add(lblTituloLivro, gbc);
        
        // Autor
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 25, 0);
        JLabel lblAutor = new JLabel("por " + livro.getAutor());
        lblAutor.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblAutor.setForeground(COR_TEXTO_SECUNDARIO);
        painel.add(lblAutor, gbc);
        
        // Secao Avaliacao
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 20, 0);
        painel.add(criarSecaoAvaliacao(), gbc);
        
        // Secao Status de Leitura
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 25, 0);
        painel.add(criarSecaoStatus(), gbc);
        
        // Metadata (Genero e Ano)
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 20, 0);
        JLabel lblMeta = new JLabel(String.format("Gênero: %s  •  Ano: %d", 
            livro.getGenero() != null ? livro.getGenero() : "N/A", 
            livro.getAnoPublicacao()));
        lblMeta.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblMeta.setForeground(COR_TEXTO_TERCIARIO);
        painel.add(lblMeta, gbc);
        
        // Sinopse
        gbc.gridy++;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 20, 0);
        painel.add(criarSecaoSinopse(), gbc);
        
        // Estatisticas
        gbc.gridy++;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 25, 0);
        JLabel lblStats = new JLabel(String.format("♥ %d Favoritos  •  👁 %d Visualizações", 
            livro.getTotalFavoritos(), 
            livro.getTotalAcessos()));
        lblStats.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblStats.setForeground(COR_TEXTO_TERCIARIO);
        painel.add(lblStats, gbc);
        
        // Botao Fechar
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        JButton btnFechar = criarBotao("Fechar", COR_BOTAO_SECUNDARIO, 130, 42);
        btnFechar.addActionListener(e -> dispose());
        painel.add(btnFechar, gbc);
        
        return painel;
    }
    
    private JPanel criarSecaoAvaliacao() {
        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBackground(COR_FUNDO);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 0, 10, 0);
        
        JLabel lblTitulo = new JLabel("Sua Avaliação");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblTitulo.setForeground(COR_TEXTO_SECUNDARIO);
        painel.add(lblTitulo, gbc);
        
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        painelEstrelas = criarPainelEstrelas();
        painel.add(painelEstrelas, gbc);
        
        return painel;
    }
    
    private JPanel criarSecaoStatus() {
        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBackground(COR_FUNDO);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 0, 10, 0);
        
        JLabel lblTitulo = new JLabel("Status de Leitura");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblTitulo.setForeground(COR_TEXTO_SECUNDARIO);
        painel.add(lblTitulo, gbc);
        
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        
        String[] opcoes = {"Nenhum", "Quero Ler", "Lendo Atualmente", "Já Li"};
        cbStatus = new JComboBox<>(opcoes);
        cbStatus.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cbStatus.setPreferredSize(new Dimension(0, 40));
        cbStatus.setMaximumSize(new Dimension(350, 40));
        
        StatusLeitura statusAtual = usuario.getStatusLeitura(livro.getIdLivro());
        cbStatus.setSelectedItem(statusAtual.getDescricao());
        
        cbStatus.addActionListener(e -> {
            String sel = (String) cbStatus.getSelectedItem();
            StatusLeitura novoStatus = StatusLeitura.fromString(sel);
            usuario.definirStatusLeitura(livro.getIdLivro(), novoStatus);
            if (onUpdateCallback != null) {
                onUpdateCallback.run();
            }
        });
        
        painel.add(cbStatus, gbc);
        
        return painel;
    }
    
    private JPanel criarSecaoSinopse() {
        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBackground(COR_FUNDO);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 0, 10, 0);
        
        JLabel lblTitulo = new JLabel("Sinopse");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblTitulo.setForeground(COR_TEXTO_SECUNDARIO);
        painel.add(lblTitulo, gbc);
        
        gbc.gridy = 1;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 0, 0);
        
        JTextArea txtSinopse = new JTextArea(
            livro.getSinopse() != null ? livro.getSinopse() : "Sem sinopse disponível");
        txtSinopse.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtSinopse.setForeground(COR_TEXTO_SECUNDARIO);
        txtSinopse.setLineWrap(true);
        txtSinopse.setWrapStyleWord(true);
        txtSinopse.setEditable(false);
        txtSinopse.setBackground(COR_FUNDO);
        
        JScrollPane scroll = new JScrollPane(txtSinopse);
        scroll.setBorder(null);
        scroll.setBackground(COR_FUNDO);
        painel.add(scroll, gbc);
        
        return painel;
    }
    
    private JPanel criarPainelEstrelas() {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        painel.setBackground(COR_FUNDO);
        
        int notaUsuario = usuario.getNotaLivro(livro.getIdLivro());
        
        for (int i = 1; i <= 5; i++) {
            final int nota = i;
            JLabel estrela = new JLabel(i <= notaUsuario ? "★" : "☆");
            estrela.setFont(new Font("Segoe UI", Font.PLAIN, 32));
            estrela.setForeground(i <= notaUsuario ? COR_ESTRELA_ATIVA : COR_ESTRELA_INATIVA);
            estrela.setCursor(new Cursor(Cursor.HAND_CURSOR));
            
            estrela.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    usuario.avaliarLivro(livro.getIdLivro(), nota);
                    atualizarEstrelas();
                    if (onUpdateCallback != null) {
                        onUpdateCallback.run();
                    }
                }
                
                @Override
                public void mouseEntered(java.awt.event.MouseEvent e) {
                    estrela.setForeground(COR_ESTRELA_ATIVA);
                }
                
                @Override
                public void mouseExited(java.awt.event.MouseEvent e) {
                    int notaAtual = usuario.getNotaLivro(livro.getIdLivro());
                    estrela.setForeground(nota <= notaAtual ? COR_ESTRELA_ATIVA : COR_ESTRELA_INATIVA);
                }
            });
            
            painel.add(estrela);
        }
        
        return painel;
    }
    
    private void atualizarEstrelas() {
        painelEstrelas.removeAll();
        
        int notaUsuario = usuario.getNotaLivro(livro.getIdLivro());
        
        for (int i = 1; i <= 5; i++) {
            final int nota = i;
            JLabel estrela = new JLabel(i <= notaUsuario ? "★" : "☆");
            estrela.setFont(new Font("Segoe UI", Font.PLAIN, 32));
            estrela.setForeground(i <= notaUsuario ? COR_ESTRELA_ATIVA : COR_ESTRELA_INATIVA);
            estrela.setCursor(new Cursor(Cursor.HAND_CURSOR));
            
            estrela.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    usuario.avaliarLivro(livro.getIdLivro(), nota);
                    atualizarEstrelas();
                    if (onUpdateCallback != null) {
                        onUpdateCallback.run();
                    }
                }
                
                @Override
                public void mouseEntered(java.awt.event.MouseEvent e) {
                    estrela.setForeground(COR_ESTRELA_ATIVA);
                }
                
                @Override
                public void mouseExited(java.awt.event.MouseEvent e) {
                    int notaAtual = usuario.getNotaLivro(livro.getIdLivro());
                    estrela.setForeground(nota <= notaAtual ? COR_ESTRELA_ATIVA : COR_ESTRELA_INATIVA);
                }
            });
            
            painelEstrelas.add(estrela);
        }
        
        painelEstrelas.revalidate();
        painelEstrelas.repaint();
    }
    
    private JButton criarBotaoFavorito() {
        boolean isFavorito = usuario.isFavorito(livro.getIdLivro());
        JButton btn = new JButton(isFavorito ? "♥ Favorito" : "♡ Favoritar");
        btn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btn.setBackground(isFavorito ? COR_BOTAO_PERIGO : COR_BOTAO_SECUNDARIO);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(150, 42));
        
        btn.addActionListener(e -> {
            if (usuario.isFavorito(livro.getIdLivro())) {
                usuario.removerFavorito(livro);
                btn.setText("♡ Favoritar");
                btn.setBackground(COR_BOTAO_SECUNDARIO);
            } else {
                usuario.adicionarFavorito(livro);
                btn.setText("♥ Favorito");
                btn.setBackground(COR_BOTAO_PERIGO);
            }
            if (onUpdateCallback != null) {
                onUpdateCallback.run();
            }
        });
        
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(btn.getBackground().darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(usuario.isFavorito(livro.getIdLivro()) ? 
                    COR_BOTAO_PERIGO : COR_BOTAO_SECUNDARIO);
            }
        });
        
        return btn;
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
}
