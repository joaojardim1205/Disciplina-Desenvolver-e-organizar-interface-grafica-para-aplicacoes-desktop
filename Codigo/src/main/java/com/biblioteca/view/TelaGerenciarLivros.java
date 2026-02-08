package com.biblioteca.view;

import com.biblioteca.controller.LivroController;
import com.biblioteca.model.Livro;
import com.biblioteca.view.components.LivroCardAdmin;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class TelaGerenciarLivros extends JFrame {
    
    private LivroController livroController;
    private JPanel painelCards;
    private JComboBox<String> cbTipoFiltro;
    private JTextField txtFiltro;
    private JButton btnBuscar, btnLimpar, btnNovo, btnFechar;
    
    public TelaGerenciarLivros() {
        this.livroController = new LivroController();
        initComponents();
        carregarLivros();
    }
    
    private void initComponents() {
        setTitle("Gerenciar Livros");
        setSize(1100, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBackground(new Color(248, 249, 250));
        
        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Color.WHITE);
        header.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220, 220, 220)),
            new EmptyBorder(15, 20, 15, 20)
        ));
        
        JLabel lblTitulo = new JLabel("Gerenciar Livros");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(40, 40, 40));
        header.add(lblTitulo, BorderLayout.WEST);
        
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        painelBotoes.setBackground(Color.WHITE);
        
        btnNovo = criarBotao("Novo Livro", new Color(40, 167, 69), 120, 36);
        btnNovo.addActionListener(e -> abrirCadastroLivro());
        painelBotoes.add(btnNovo);
        
        btnFechar = criarBotao("Fechar", new Color(108, 117, 125), 90, 36);
        btnFechar.addActionListener(e -> dispose());
        painelBotoes.add(btnFechar);
        
        header.add(painelBotoes, BorderLayout.EAST);
        
        painelPrincipal.add(header, BorderLayout.NORTH);
        
        // Painel de filtros
        JPanel painelFiltros = criarPainelFiltros();
        painelPrincipal.add(painelFiltros, BorderLayout.WEST);
        
        // Painel de cards
        painelCards = new JPanel();
        painelCards.setLayout(new GridLayout(0, 4, 15, 15));
        painelCards.setBackground(new Color(248, 249, 250));
        painelCards.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        JScrollPane scrollPane = new JScrollPane(painelCards);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBackground(new Color(248, 249, 250));
        
        painelPrincipal.add(scrollPane, BorderLayout.CENTER);
        
        add(painelPrincipal);
    }
    
    private JPanel criarPainelFiltros() {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBackground(Color.WHITE);
        painel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(220, 220, 220)),
            new EmptyBorder(20, 15, 20, 15)
        ));
        painel.setPreferredSize(new Dimension(250, 0));
        
        JLabel lblFiltros = new JLabel("Filtros");
        lblFiltros.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblFiltros.setForeground(new Color(60, 60, 60));
        lblFiltros.setAlignmentX(Component.LEFT_ALIGNMENT);
        painel.add(lblFiltros);
        
        painel.add(Box.createVerticalStrut(15));
        
        JLabel lblTipo = new JLabel("Filtrar por:");
        lblTipo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblTipo.setForeground(new Color(80, 80, 80));
        lblTipo.setAlignmentX(Component.LEFT_ALIGNMENT);
        painel.add(lblTipo);
        
        painel.add(Box.createVerticalStrut(6));
        
        String[] opcoesFiltro = {"Todos", "ID", "Titulo", "Autor", "Genero", "Ano"};
        cbTipoFiltro = new JComboBox<>(opcoesFiltro);
        cbTipoFiltro.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        cbTipoFiltro.setMaximumSize(new Dimension(220, 32));
        cbTipoFiltro.setAlignmentX(Component.LEFT_ALIGNMENT);
        painel.add(cbTipoFiltro);
        
        painel.add(Box.createVerticalStrut(15));
        
        JLabel lblValor = new JLabel("Valor:");
        lblValor.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblValor.setForeground(new Color(80, 80, 80));
        lblValor.setAlignmentX(Component.LEFT_ALIGNMENT);
        painel.add(lblValor);
        
        painel.add(Box.createVerticalStrut(6));
        
        txtFiltro = new JTextField();
        txtFiltro.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtFiltro.setMaximumSize(new Dimension(220, 32));
        txtFiltro.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            new EmptyBorder(5, 10, 5, 10)
        ));
        txtFiltro.setAlignmentX(Component.LEFT_ALIGNMENT);
        txtFiltro.addActionListener(e -> buscarLivros());
        painel.add(txtFiltro);
        
        painel.add(Box.createVerticalStrut(15));
        
        btnBuscar = criarBotao("Buscar", new Color(0, 123, 255), 220, 36);
        btnBuscar.addActionListener(e -> buscarLivros());
        btnBuscar.setAlignmentX(Component.LEFT_ALIGNMENT);
        painel.add(btnBuscar);
        
        painel.add(Box.createVerticalStrut(10));
        
        btnLimpar = criarBotao("Limpar", new Color(108, 117, 125), 220, 36);
        btnLimpar.addActionListener(e -> limparFiltros());
        btnLimpar.setAlignmentX(Component.LEFT_ALIGNMENT);
        painel.add(btnLimpar);
        
        return painel;
    }
    
    private JButton criarBotao(String texto, Color cor, int largura, int altura) {
        JButton btn = new JButton();
        btn.setText(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setBackground(cor);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(largura, altura));
        btn.setMaximumSize(new Dimension(largura, altura));
        
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
    
    private void carregarLivros() {
        List<Livro> livros = livroController.listarTodosLivros();
        atualizarCards(livros);
    }
    
    private void buscarLivros() {
        String tipoFiltro = (String) cbTipoFiltro.getSelectedItem();
        String valorFiltro = txtFiltro.getText().trim();
        
        if ("Todos".equals(tipoFiltro) || valorFiltro.isEmpty()) {
            carregarLivros();
            return;
        }
        
        List<Livro> livros = livroController.listarTodosLivros();
        List<Livro> livrosFiltrados;
        
        try {
            switch (tipoFiltro) {
                case "ID":
                    int id = Integer.parseInt(valorFiltro);
                    livrosFiltrados = livros.stream()
                        .filter(l -> l.getIdLivro() == id)
                        .collect(Collectors.toList());
                    break;
                case "Titulo":
                    livrosFiltrados = livros.stream()
                        .filter(l -> l.getTitulo().toLowerCase().contains(valorFiltro.toLowerCase()))
                        .collect(Collectors.toList());
                    break;
                case "Autor":
                    livrosFiltrados = livros.stream()
                        .filter(l -> l.getAutor().toLowerCase().contains(valorFiltro.toLowerCase()))
                        .collect(Collectors.toList());
                    break;
                case "Genero":
                    livrosFiltrados = livros.stream()
                        .filter(l -> l.getGenero() != null && l.getGenero().toLowerCase().contains(valorFiltro.toLowerCase()))
                        .collect(Collectors.toList());
                    break;
                case "Ano":
                    int ano = Integer.parseInt(valorFiltro);
                    livrosFiltrados = livros.stream()
                        .filter(l -> l.getAnoPublicacao() == ano)
                        .collect(Collectors.toList());
                    break;
                default:
                    livrosFiltrados = livros;
            }
            
            atualizarCards(livrosFiltrados);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Valor invalido para o filtro selecionado!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void limparFiltros() {
        cbTipoFiltro.setSelectedIndex(0);
        txtFiltro.setText("");
        carregarLivros();
    }
    
    private void atualizarCards(List<Livro> livros) {
        painelCards.removeAll();
        
        if (livros.isEmpty()) {
            JLabel lblVazio = new JLabel("Nenhum livro encontrado");
            lblVazio.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            lblVazio.setForeground(new Color(150, 150, 150));
            painelCards.add(lblVazio);
        } else {
            for (Livro livro : livros) {
                LivroCardAdmin card = new LivroCardAdmin(livro, () -> abrirDetalhesLivro(livro));
                painelCards.add(card);
            }
        }
        
        painelCards.revalidate();
        painelCards.repaint();
    }
    
    private void abrirDetalhesLivro(Livro livro) {
        TelaDetalhesLivro telaDetalhes = new TelaDetalhesLivro(livro, this::carregarLivros);
        telaDetalhes.setVisible(true);
    }
    
    private void abrirCadastroLivro() {
        TelaCadastroLivro telaCadastro = new TelaCadastroLivro(this::carregarLivros);
        telaCadastro.setVisible(true);
    }
}
