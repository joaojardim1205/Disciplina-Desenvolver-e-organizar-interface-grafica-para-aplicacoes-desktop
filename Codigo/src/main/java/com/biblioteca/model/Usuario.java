package com.biblioteca.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe que representa um Usuario do sistema
 */
public class Usuario {
    
    private int idUsuario;
    private String nome;
    private String email;
    private String senha;
    private List<Livro> livrosFavoritos;
    private List<Livro> historicoLeitura;
    private Map<Integer, StatusLeitura> statusLeituraPorLivro; // idLivro -> Status
    private Map<Integer, Avaliacao> avaliacoesPorLivro; // idLivro -> Avaliacao
    
    // Construtores
    public Usuario() {
        this.livrosFavoritos = new ArrayList<>();
        this.historicoLeitura = new ArrayList<>();
        this.statusLeituraPorLivro = new HashMap<>();
        this.avaliacoesPorLivro = new HashMap<>();
    }
    
    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.livrosFavoritos = new ArrayList<>();
        this.historicoLeitura = new ArrayList<>();
        this.statusLeituraPorLivro = new HashMap<>();
        this.avaliacoesPorLivro = new HashMap<>();
    }
    
    public Usuario(int idUsuario, String nome, String email, String senha) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.livrosFavoritos = new ArrayList<>();
        this.historicoLeitura = new ArrayList<>();
        this.statusLeituraPorLivro = new HashMap<>();
        this.avaliacoesPorLivro = new HashMap<>();
    }
    
    // Getters e Setters
    public int getIdUsuario() {
        return idUsuario;
    }
    
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getSenha() {
        return senha;
    }
    
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public List<Livro> getLivrosFavoritos() {
        return livrosFavoritos;
    }
    
    public void setLivrosFavoritos(List<Livro> livrosFavoritos) {
        this.livrosFavoritos = livrosFavoritos;
    }
    
    public List<Livro> getHistoricoLeitura() {
        return historicoLeitura;
    }
    
    public void setHistoricoLeitura(List<Livro> historicoLeitura) {
        this.historicoLeitura = historicoLeitura;
    }
    
    public Map<Integer, StatusLeitura> getStatusLeituraPorLivro() {
        return statusLeituraPorLivro;
    }
    
    public void setStatusLeituraPorLivro(Map<Integer, StatusLeitura> statusLeituraPorLivro) {
        this.statusLeituraPorLivro = statusLeituraPorLivro;
    }
    
    public Map<Integer, Avaliacao> getAvaliacoesPorLivro() {
        return avaliacoesPorLivro;
    }
    
    public void setAvaliacoesPorLivro(Map<Integer, Avaliacao> avaliacoesPorLivro) {
        this.avaliacoesPorLivro = avaliacoesPorLivro;
    }
    
    // Metodos de negocio - Favoritos
    public void adicionarFavorito(Livro livro) {
        if (!livrosFavoritos.contains(livro)) {
            livrosFavoritos.add(livro);
            livro.incrementarFavoritos();
        }
    }
    
    public void removerFavorito(Livro livro) {
        if (livrosFavoritos.remove(livro)) {
            livro.decrementarFavoritos();
        }
    }
    
    public boolean isFavorito(int idLivro) {
        return livrosFavoritos.stream().anyMatch(l -> l.getIdLivro() == idLivro);
    }
    
    // Metodos de negocio - Historico
    public void registrarLeitura(Livro livro) {
        if (!historicoLeitura.contains(livro)) {
            historicoLeitura.add(livro);
        }
        livro.incrementarAcessos();
    }
    
    // Metodos de negocio - Status de Leitura
    public void definirStatusLeitura(int idLivro, StatusLeitura status) {
        statusLeituraPorLivro.put(idLivro, status);
    }
    
    public StatusLeitura getStatusLeitura(int idLivro) {
        return statusLeituraPorLivro.getOrDefault(idLivro, StatusLeitura.NENHUM);
    }
    
    public void removerStatusLeitura(int idLivro) {
        statusLeituraPorLivro.remove(idLivro);
    }
    
    // Metodos de negocio - Avaliacoes
    public void avaliarLivro(int idLivro, int nota) {
        Avaliacao avaliacao = new Avaliacao(this.idUsuario, idLivro, nota);
        avaliacoesPorLivro.put(idLivro, avaliacao);
    }
    
    public void avaliarLivro(int idLivro, int nota, String comentario) {
        Avaliacao avaliacao = new Avaliacao(this.idUsuario, idLivro, nota, comentario);
        avaliacoesPorLivro.put(idLivro, avaliacao);
    }
    
    public Avaliacao getAvaliacao(int idLivro) {
        return avaliacoesPorLivro.get(idLivro);
    }
    
    public int getNotaLivro(int idLivro) {
        Avaliacao avaliacao = avaliacoesPorLivro.get(idLivro);
        return avaliacao != null ? avaliacao.getNota() : 0;
    }
    
    public void removerAvaliacao(int idLivro) {
        avaliacoesPorLivro.remove(idLivro);
    }
    
    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
