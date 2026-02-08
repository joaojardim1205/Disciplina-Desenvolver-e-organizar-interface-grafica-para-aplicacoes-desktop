package com.biblioteca.view.components;

import com.biblioteca.model.Livro;
import com.biblioteca.model.Usuario;
import com.biblioteca.model.StatusLeitura;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class LivroCard extends JPanel {
    
    private Livro livro;
    private Usuario usuarioLogado;
    private Runnable onClickAction;
    private Runnable onUpdateCallback;
    private JButton btnFavorito;
    private JPanel painelEstrelas;
    
    private static final Color COR_FUNDO = Color.WHITE;
    private static final Color COR_FUNDO_HOVER = new Color(249, 250, 251);
    private static final Color COR_BORDA = new Color(229, 231, 235);
    private static final Color COR_BORDA_HOVER = new Color(156, 163, 175);
    private static final Color COR_IMAGEM_FUNDO = new Color(243, 244, 246);
    private static final Color COR_TEXTO_PRINCIPAL = new Color(17, 24, 39);
    private static final Color COR_TEXTO_SECUNDARIO = new Color(107, 114, 128);
    private static final Color COR_TEXTO_TERCIARIO = new Color(156, 163, 175);
    private static final Color COR_FAVORITO = new Color(239, 68, 68);
    private static final Color COR_FAVORITO_INATIVO = new Color(209, 213, 219);
    private static final Color COR_ESTRELA = new Color(251, 191, 36);
    private static final Color COR_ESTRELA_INATIVA = new Color(209, 213, 219);
    private static final Color COR_TAG_GENERO_FUNDO = new Color(238, 242, 255);
    private static final Color COR_TAG_GENERO_TEXTO = new Color(79, 70, 229);
    private static final Color COR_TAG_ANO_FUNDO = new Color(243, 244, 246);
    private static final Color COR_TAG_ANO_TEXTO = new Color(107, 114, 128);
    private static final Color COR_STATUS = new Color(16, 185, 129);
    
    public LivroCard(Livro livro, Usuario usuario, Runnable onClickAction, Runnable onUpdateCallback) {
        this.livro = livro;
        this.usuarioLogado = usuario;
        this.onClickAction = onClickAction;
        this.onUpdateCallback = onUpdateCallback;
        initComponents();
    }
    
    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(COR_FUNDO);
        setPreferredSize(new Dimension(260, 420));
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COR_BORDA, 1),
            new EmptyBorder(15, 15, 15, 15)
        ));
        
        add(criarPainelImagem(), BorderLayout.NORTH);
        add(criarPainelInformacoes(), BorderLayout.CENTER);
        configurarInteracoes();
    }
    
    private JPanel criarPainelImagem() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBackground(COR_IMAGEM_FUNDO);
        painel.setPreferredSize(new Dimension(230, 280));
        painel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COR_BORDA, 1),
            new EmptyBorder(10, 10, 10, 10)
        ));
        
        JLabel lblImagem = new JLabel();
        lblImagem.setHorizontalAlignment(JLabel.CENTER);
        lblImagem.setVerticalAlignment(JLabel.CENTER);
        carregarImagem(lblImagem);
        painel.add(lblImagem, BorderLayout.CENTER);
        
        JPanel painelOverlay = new JPanel(null);
        painelOverlay.setOpaque(false);
        painelOverlay.setPreferredSize(new Dimension(230, 280));
        
        btnFavorito = criarBotaoFavorito();
        btnFavorito.setBounds(190, 10, 32, 32);
        painelOverlay.add(btnFavorito);
        
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(230, 280));
        layeredPane.add(painel, Integer.valueOf(0));
        layeredPane.add(painelOverlay, Integer.valueOf(1));
        painel.setBounds(0, 0, 230, 280);
        
        JPanel container = new JPanel(new BorderLayout());
        container.setBackground(COR_FUNDO);
        container.add(layeredPane, BorderLayout.CENTER);
        
        return container;
    }
    
    private void carregarImagem(JLabel lblImagem) {
        if (livro.getCaminhoImagem() != null && !livro.getCaminhoImagem().isEmpty()) {
            File imgFile = new File(livro.getCaminhoImagem());
            if (imgFile.exists()) {
                try {
                    ImageIcon icon = new ImageIcon(livro.getCaminhoImagem());
                    Image img = icon.getImage().getScaledInstance(210, 260, Image.SCALE_SMOOTH);
                    lblImagem.setIcon(new ImageIcon(img));
                } catch (Exception e) {
                    exibirIconePadrao(lblImagem);
                }
            } else {
                exibirIconePadrao(lblImagem);
            }
        } else {
            exibirIconePadrao(lblImagem);
        }
    }
    
    private void exibirIconePadrao(JLabel lblImagem) {
        lblImagem.setText("📚");
        lblImagem.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 60));
        lblImagem.setForeground(COR_TEXTO_TERCIARIO);
    }
    
    private JPanel criarPainelInformacoes() {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBackground(COR_FUNDO);
        painel.setBorder(new EmptyBorder(12, 0, 0, 0));
        
        JLabel lblTitulo = new JLabel(truncate(livro.getTitulo(), 24));
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblTitulo.setForeground(COR_TEXTO_PRINCIPAL);
        lblTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblTitulo.setToolTipText(livro.getTitulo());
        painel.add(lblTitulo);
        painel.add(Box.createVerticalStrut(4));
        
        JLabel lblAutor = new JLabel(truncate(livro.getAutor(), 30));
        lblAutor.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblAutor.setForeground(COR_TEXTO_SECUNDARIO);
        lblAutor.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblAutor.setToolTipText(livro.getAutor());
        painel.add(lblAutor);
        painel.add(Box.createVerticalStrut(10));
        
        painelEstrelas = criarPainelEstrelas();
        painelEstrelas.setAlignmentX(Component.LEFT_ALIGNMENT);
        painel.add(painelEstrelas);
        painel.add(Box.createVerticalStrut(10));
        
        JPanel painelTags = criarPainelTags();
        painelTags.setAlignmentX(Component.LEFT_ALIGNMENT);
        painel.add(painelTags);
        painel.add(Box.createVerticalStrut(8));
        
        StatusLeitura status = usuarioLogado.getStatusLeitura(livro.getIdLivro());
        if (status != StatusLeitura.NENHUM) {
            JPanel painelStatus = criarPainelStatus(status);
            painelStatus.setAlignmentX(Component.LEFT_ALIGNMENT);
            painel.add(painelStatus);
            painel.add(Box.createVerticalStrut(8));
        }
        
        JPanel painelStats = criarPainelEstatisticas();
        painelStats.setAlignmentX(Component.LEFT_ALIGNMENT);
        painel.add(painelStats);
        
        return painel;
    }
    
    private JButton criarBotaoFavorito() {
        boolean isFavorito = usuarioLogado.isFavorito(livro.getIdLivro());
        JButton btn = new JButton(isFavorito ? "♥" : "♡");
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        btn.setForeground(isFavorito ? COR_FAVORITO : COR_FAVORITO_INATIVO);
        btn.setBackground(new Color(255, 255, 255, 230));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(32, 32));
        btn.setToolTipText(isFavorito ? "Remover dos favoritos" : "Adicionar aos favoritos");
        
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btn.setFont(new Font("Segoe UI", Font.PLAIN, 22));
            }
            public void mouseExited(MouseEvent e) {
                btn.setFont(new Font("Segoe UI", Font.PLAIN, 20));
            }
        });
        
        btn.addActionListener(e -> {
            if (usuarioLogado.isFavorito(livro.getIdLivro())) {
                usuarioLogado.removerFavorito(livro);
                btn.setText("♡");
                btn.setForeground(COR_FAVORITO_INATIVO);
                btn.setToolTipText("Adicionar aos favoritos");
            } else {
                usuarioLogado.adicionarFavorito(livro);
                btn.setText("♥");
                btn.setForeground(COR_FAVORITO);
                btn.setToolTipText("Remover dos favoritos");
            }
            if (onUpdateCallback != null) {
                onUpdateCallback.run();
            }
        });
        
        return btn;
    }
    
    private JPanel criarPainelEstrelas() {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.LEFT, 3, 0));
        painel.setBackground(COR_FUNDO);
        
        int notaUsuario = usuarioLogado.getNotaLivro(livro.getIdLivro());
        
        for (int i = 1; i <= 5; i++) {
            final int nota = i;
            JLabel estrela = new JLabel(i <= notaUsuario ? "★" : "☆");
            estrela.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            estrela.setForeground(i <= notaUsuario ? COR_ESTRELA : COR_ESTRELA_INATIVA);
            estrela.setCursor(new Cursor(Cursor.HAND_CURSOR));
            estrela.setToolTipText("Avaliar: " + nota + " estrela" + (nota > 1 ? "s" : ""));
            
            estrela.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    usuarioLogado.avaliarLivro(livro.getIdLivro(), nota);
                    atualizarEstrelas();
                    if (onUpdateCallback != null) {
                        onUpdateCallback.run();
                    }
                }
                public void mouseEntered(MouseEvent e) {
                    estrela.setForeground(COR_ESTRELA);
                    estrela.setFont(new Font("Segoe UI", Font.PLAIN, 20));
                }
                public void mouseExited(MouseEvent e) {
                    estrela.setFont(new Font("Segoe UI", Font.PLAIN, 18));
                    estrela.setForeground(nota <= usuarioLogado.getNotaLivro(livro.getIdLivro()) ? 
                        COR_ESTRELA : COR_ESTRELA_INATIVA);
                }
            });
            
            painel.add(estrela);
        }
        
        return painel;
    }
    
    private void atualizarEstrelas() {
        painelEstrelas.removeAll();
        int notaUsuario = usuarioLogado.getNotaLivro(livro.getIdLivro());
        
        for (int i = 1; i <= 5; i++) {
            final int nota = i;
            JLabel estrela = new JLabel(i <= notaUsuario ? "★" : "☆");
            estrela.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            estrela.setForeground(i <= notaUsuario ? COR_ESTRELA : COR_ESTRELA_INATIVA);
            estrela.setCursor(new Cursor(Cursor.HAND_CURSOR));
            estrela.setToolTipText("Avaliar: " + nota + " estrela" + (nota > 1 ? "s" : ""));
            
            estrela.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    usuarioLogado.avaliarLivro(livro.getIdLivro(), nota);
                    atualizarEstrelas();
                    if (onUpdateCallback != null) {
                        onUpdateCallback.run();
                    }
                }
                public void mouseEntered(MouseEvent e) {
                    estrela.setForeground(COR_ESTRELA);
                    estrela.setFont(new Font("Segoe UI", Font.PLAIN, 20));
                }
                public void mouseExited(MouseEvent e) {
                    estrela.setFont(new Font("Segoe UI", Font.PLAIN, 18));
                    estrela.setForeground(nota <= usuarioLogado.getNotaLivro(livro.getIdLivro()) ? 
                        COR_ESTRELA : COR_ESTRELA_INATIVA);
                }
            });
            
            painelEstrelas.add(estrela);
        }
        
        painelEstrelas.revalidate();
        painelEstrelas.repaint();
    }
    
    private JPanel criarPainelTags() {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
        painel.setBackground(COR_FUNDO);
        
        String genero = livro.getGenero() != null ? livro.getGenero() : "N/A";
        JLabel lblGenero = criarTag(truncate(genero, 12), COR_TAG_GENERO_FUNDO, COR_TAG_GENERO_TEXTO);
        painel.add(lblGenero);
        
        JLabel lblAno = criarTag(String.valueOf(livro.getAnoPublicacao()), 
            COR_TAG_ANO_FUNDO, COR_TAG_ANO_TEXTO);
        painel.add(lblAno);
        
        return painel;
    }
    
    private JLabel criarTag(String texto, Color corFundo, Color corTexto) {
        JLabel tag = new JLabel(texto);
        tag.setFont(new Font("Segoe UI", Font.BOLD, 11));
        tag.setForeground(corTexto);
        tag.setOpaque(true);
        tag.setBackground(corFundo);
        tag.setBorder(new EmptyBorder(3, 8, 3, 8));
        return tag;
    }
    
    private JPanel criarPainelStatus(StatusLeitura status) {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        painel.setBackground(COR_FUNDO);
        
        JLabel lblStatus = new JLabel("● " + status.getDescricao());
        lblStatus.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblStatus.setForeground(COR_STATUS);
        painel.add(lblStatus);
        
        return painel;
    }
    
    private JPanel criarPainelEstatisticas() {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 0));
        painel.setBackground(COR_FUNDO);
        
        JLabel lblFavoritos = new JLabel("♥ " + livro.getTotalFavoritos());
        lblFavoritos.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblFavoritos.setForeground(COR_TEXTO_TERCIARIO);
        painel.add(lblFavoritos);
        
        JLabel lblAcessos = new JLabel("👁 " + livro.getTotalAcessos());
        lblAcessos.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblAcessos.setForeground(COR_TEXTO_TERCIARIO);
        painel.add(lblAcessos);
        
        return painel;
    }
    
    private void configurarInteracoes() {
        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                setBackground(COR_FUNDO_HOVER);
                setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(COR_BORDA_HOVER, 1),
                    new EmptyBorder(15, 15, 15, 15)
                ));
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            public void mouseExited(MouseEvent e) {
                setBackground(COR_FUNDO);
                setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(COR_BORDA, 1),
                    new EmptyBorder(15, 15, 15, 15)
                ));
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            public void mouseClicked(MouseEvent e) {
                if (!btnFavorito.getBounds().contains(e.getPoint())) {
                    if (onClickAction != null) {
                        onClickAction.run();
                    }
                }
            }
        });
    }
    
    private String truncate(String text, int maxLength) {
        if (text == null) return "";
        if (text.length() <= maxLength) return text;
        return text.substring(0, maxLength - 3) + "...";
    }
    
    public Livro getLivro() {
        return livro;
    }
}
