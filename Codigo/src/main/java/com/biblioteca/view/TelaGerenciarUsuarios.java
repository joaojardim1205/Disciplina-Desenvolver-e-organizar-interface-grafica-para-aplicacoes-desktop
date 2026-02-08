package com.biblioteca.view;

import com.biblioteca.controller.UsuarioController;
import com.biblioteca.model.Usuario;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Tela de Gerenciamento de Usuarios - Design Moderno
 */
public class TelaGerenciarUsuarios extends JFrame {
    
    private UsuarioController usuarioController;
    private JTextField txtBusca;
    private JButton btnBuscar, btnRemover, btnAtualizar, btnFechar;
    private JTable tabelaUsuarios;
    private DefaultTableModel modeloTabela;
    
    public TelaGerenciarUsuarios() {
        usuarioController = new UsuarioController();
        initComponents();
        carregarUsuarios();
    }
    
    private void initComponents() {
        setTitle("Gerenciar Usuarios");
        setSize(900, 600);
        setLocationRelativeTo(null);
        
        JPanel painelPrincipal = new JPanel(new BorderLayout(0, 0));
        painelPrincipal.setBackground(new Color(248, 249, 250));
        
        // Header
        JPanel header = new JPanel(new BorderLayout(15, 0));
        header.setBackground(Color.WHITE);
        header.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220, 220, 220)),
            new EmptyBorder(15, 20, 15, 20)
        ));
        
        JLabel lblTitulo = new JLabel("Gerenciar Usuarios");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(40, 40, 40));
        header.add(lblTitulo, BorderLayout.WEST);
        
        JPanel headerDireito = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        headerDireito.setBackground(Color.WHITE);
        
        btnFechar = criarBotaoModerno("Fechar", new Color(108, 117, 125), 90, 36);
        btnFechar.addActionListener(e -> dispose());
        headerDireito.add(btnFechar);
        
        header.add(headerDireito, BorderLayout.EAST);
        
        painelPrincipal.add(header, BorderLayout.NORTH);
        
        // Conteudo
        JPanel painelConteudo = new JPanel(new BorderLayout(0, 15));
        painelConteudo.setBackground(new Color(248, 249, 250));
        painelConteudo.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        // Painel de busca e acoes
        JPanel painelAcoes = new JPanel(new BorderLayout(15, 0));
        painelAcoes.setBackground(Color.WHITE);
        painelAcoes.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
            new EmptyBorder(12, 15, 12, 15)
        ));
        
        JPanel painelBusca = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 0));
        painelBusca.setBackground(Color.WHITE);
        
        JLabel lblBusca = new JLabel("Buscar:");
        lblBusca.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblBusca.setForeground(new Color(80, 80, 80));
        painelBusca.add(lblBusca);
        
        txtBusca = new JTextField(30);
        txtBusca.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtBusca.setPreferredSize(new Dimension(300, 32));
        txtBusca.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            new EmptyBorder(5, 10, 5, 10)
        ));
        painelBusca.add(txtBusca);
        
        btnBuscar = criarBotaoModerno("Buscar", new Color(0, 123, 255), 90, 32);
        btnBuscar.addActionListener(e -> buscarUsuarios());
        painelBusca.add(btnBuscar);
        
        btnAtualizar = criarBotaoModerno("Atualizar", new Color(108, 117, 125), 90, 32);
        btnAtualizar.addActionListener(e -> carregarUsuarios());
        painelBusca.add(btnAtualizar);
        
        painelAcoes.add(painelBusca, BorderLayout.WEST);
        
        JPanel painelBotoesAcao = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        painelBotoesAcao.setBackground(Color.WHITE);
        
        btnRemover = criarBotaoModerno("Remover Selecionado", new Color(220, 53, 69), 160, 32);
        btnRemover.addActionListener(e -> removerUsuario());
        painelBotoesAcao.add(btnRemover);
        
        painelAcoes.add(painelBotoesAcao, BorderLayout.EAST);
        
        painelConteudo.add(painelAcoes, BorderLayout.NORTH);
        
        // Tabela
        String[] colunas = {"ID", "Nome", "Email"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tabelaUsuarios = new JTable(modeloTabela);
        tabelaUsuarios.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tabelaUsuarios.setRowHeight(32);
        tabelaUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaUsuarios.setShowVerticalLines(false);
        tabelaUsuarios.setIntercellSpacing(new Dimension(0, 1));
        tabelaUsuarios.setBackground(Color.WHITE);
        tabelaUsuarios.setSelectionBackground(new Color(232, 240, 254));
        tabelaUsuarios.setSelectionForeground(new Color(0, 0, 0));
        
        tabelaUsuarios.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tabelaUsuarios.getTableHeader().setBackground(new Color(248, 249, 250));
        tabelaUsuarios.getTableHeader().setForeground(new Color(60, 60, 60));
        tabelaUsuarios.getTableHeader().setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220, 220, 220)));
        
        tabelaUsuarios.getColumnModel().getColumn(0).setPreferredWidth(80);
        tabelaUsuarios.getColumnModel().getColumn(1).setPreferredWidth(300);
        tabelaUsuarios.getColumnModel().getColumn(2).setPreferredWidth(350);
        
        JScrollPane scrollPane = new JScrollPane(tabelaUsuarios);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));
        scrollPane.setBackground(Color.WHITE);
        scrollPane.getViewport().setBackground(Color.WHITE);
        
        painelConteudo.add(scrollPane, BorderLayout.CENTER);
        
        painelPrincipal.add(painelConteudo, BorderLayout.CENTER);
        
        txtBusca.addActionListener(e -> buscarUsuarios());
        
        add(painelPrincipal);
    }
    
    private JButton criarBotaoModerno(String texto, Color cor, int largura, int altura) {
        JButton btn = new JButton();
        btn.setText(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
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
    
    private void carregarUsuarios() {
        modeloTabela.setRowCount(0);
        List<Usuario> usuarios = usuarioController.listarTodosUsuarios();
        
        for (Usuario usuario : usuarios) {
            Object[] linha = {
                usuario.getIdUsuario(),
                usuario.getNome(),
                usuario.getEmail()
            };
            modeloTabela.addRow(linha);
        }
    }
    
    private void buscarUsuarios() {
        String filtro = txtBusca.getText().trim().toLowerCase();
        modeloTabela.setRowCount(0);
        
        List<Usuario> usuarios = usuarioController.listarTodosUsuarios();
        
        for (Usuario usuario : usuarios) {
            if (filtro.isEmpty() || 
                usuario.getNome().toLowerCase().contains(filtro) ||
                usuario.getEmail().toLowerCase().contains(filtro)) {
                
                Object[] linha = {
                    usuario.getIdUsuario(),
                    usuario.getNome(),
                    usuario.getEmail()
                };
                modeloTabela.addRow(linha);
            }
        }
    }
    
    private void removerUsuario() {
        int linhaSelecionada = tabelaUsuarios.getSelectedRow();
        
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this,
                "Selecione um usuario para remover!",
                "Aviso",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int id = (int) modeloTabela.getValueAt(linhaSelecionada, 0);
        String nome = (String) modeloTabela.getValueAt(linhaSelecionada, 1);
        
        int resposta = JOptionPane.showConfirmDialog(this,
            "Tem certeza que deseja remover o usuario " + nome + "?",
            "Confirmar Remocao",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);
        
        if (resposta == JOptionPane.YES_OPTION) {
            if (usuarioController.removerUsuario(id)) {
                JOptionPane.showMessageDialog(this,
                    "Usuario removido com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
                carregarUsuarios();
            } else {
                JOptionPane.showMessageDialog(this,
                    "Erro ao remover usuario!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
