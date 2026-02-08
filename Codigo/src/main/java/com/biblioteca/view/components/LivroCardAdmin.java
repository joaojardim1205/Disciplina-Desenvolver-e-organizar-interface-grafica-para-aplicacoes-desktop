package com.biblioteca.view.components;

import com.biblioteca.model.Livro;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class LivroCardAdmin extends JPanel {
    
    private Livro livro;
    private Runnable onClickAction;
    
    private static final Color COR_FUNDO = Color.WHITE;
    private static final Color COR_FUNDO_HOVER = new Color(249, 250, 251);
    private static final Color COR_BORDA = new Color(229, 231, 235);
    private static final Color COR_BORDA_HOVER = new Color(99, 102, 241);
    private static final Color COR_IMAGEM_FUNDO = new Color(243, 244, 246);
    private static final Color COR_TEXTO_PRINCIPAL = new Color(17, 24, 39);
    private static final Color COR_TEXTO_SECUNDARIO = new Color(107, 114, 128);
    private static final Color COR_TEXTO_TERCIARIO = new Color(156, 163, 175);
    private static final Color COR_TAG_GENERO_FUNDO = new Color(238, 242, 255);
    private static final Color COR_TAG_GENERO_TEXTO = new Color(79, 70, 229);
    private static final Color COR_TAG_ANO_FUNDO = new Color(243, 244, 246);
    private static final Color COR_TAG_ANO_TEXTO = new Color(107, 114, 128);
    private static final Color COR_BADGE_ADMIN = new Color(99, 102, 241);
    
    public LivroCardAdmin(Livro livro, Runnable onClickAction) {
        this.livro = livro;
        this.onClickAction = onClickAction;
        initComponents();
    }
    
    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(COR_FUNDO);
        setPreferredSize(new Dimension(260, 400));
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
        
        JLabel badgeAdmin = criarBadgeAdmin();
        badgeAdmin.setBounds(10, 10, 60, 24);
        painelOverlay.add(badgeAdmin);
        
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
    
    private JLabel criarBadgeAdmin() {
        JLabel badge = new JLabel("EDIT");
        badge.setFont(new Font("Segoe UI", Font.BOLD, 10));
        badge.setForeground(Color.WHITE);
        badge.setOpaque(true);
        badge.setBackground(COR_BADGE_ADMIN);
        badge.setHorizontalAlignment(SwingConstants.CENTER);
        badge.setBorder(new EmptyBorder(4, 8, 4, 8));
        return badge;
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
        painel.add(Box.createVerticalStrut(12));
        
        JLabel lblId = new JLabel("ID: " + livro.getIdLivro());
        lblId.setFont(new Font("Segoe UI Mono", Font.PLAIN, 11));
        lblId.setForeground(COR_TEXTO_TERCIARIO);
        lblId.setAlignmentX(Component.LEFT_ALIGNMENT);
        painel.add(lblId);
        painel.add(Box.createVerticalStrut(10));
        
        JPanel painelTags = criarPainelTags();
        painelTags.setAlignmentX(Component.LEFT_ALIGNMENT);
        painel.add(painelTags);
        painel.add(Box.createVerticalStrut(10));
        
        JPanel painelStats = criarPainelEstatisticas();
        painelStats.setAlignmentX(Component.LEFT_ALIGNMENT);
        painel.add(painelStats);
        painel.add(Box.createVerticalStrut(8));
        
        JLabel lblAcao = new JLabel("Clique para editar →");
        lblAcao.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        lblAcao.setForeground(COR_BADGE_ADMIN);
        lblAcao.setAlignmentX(Component.LEFT_ALIGNMENT);
        painel.add(lblAcao);
        
        return painel;
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
    
    private JPanel criarPainelEstatisticas() {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 0));
        painel.setBackground(COR_FUNDO);
        
        JLabel lblFavoritos = new JLabel("♥ " + livro.getTotalFavoritos() + " favoritos");
        lblFavoritos.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblFavoritos.setForeground(COR_TEXTO_TERCIARIO);
        painel.add(lblFavoritos);
        
        JLabel lblAcessos = new JLabel("👁 " + livro.getTotalAcessos() + " views");
        lblAcessos.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblAcessos.setForeground(COR_TEXTO_TERCIARIO);
        painel.add(lblAcessos);
        
        return painel;
    }
    
    private void configurarInteracoes() {
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                setBackground(COR_FUNDO_HOVER);
                setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(COR_BORDA_HOVER, 2),
                    new EmptyBorder(14, 14, 14, 14)
                ));
            }
            public void mouseExited(MouseEvent e) {
                setBackground(COR_FUNDO);
                setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(COR_BORDA, 1),
                    new EmptyBorder(15, 15, 15, 15)
                ));
            }
            public void mouseClicked(MouseEvent e) {
                if (onClickAction != null) {
                    onClickAction.run();
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
