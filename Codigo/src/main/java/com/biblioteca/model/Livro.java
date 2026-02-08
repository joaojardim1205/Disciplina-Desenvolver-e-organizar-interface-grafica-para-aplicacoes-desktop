package com.biblioteca.model;

public class Livro {
    
    private int idLivro;
    private String titulo;
    private String autor;
    private String genero;
    private String sinopse;
    private int anoPublicacao;
    private int totalFavoritos;
    private int totalAcessos;
    private String caminhoImagem;
    
    public Livro() {
        this.totalFavoritos = 0;
        this.totalAcessos = 0;
    }
    
    public Livro(String titulo, String autor, String genero, String sinopse, int anoPublicacao) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.sinopse = sinopse;
        this.anoPublicacao = anoPublicacao;
        this.totalFavoritos = 0;
        this.totalAcessos = 0;
    }
    
    public int getIdLivro() {
        return idLivro;
    }
    
    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }
    
    public String getTitulo() {
        return titulo;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public String getAutor() {
        return autor;
    }
    
    public void setAutor(String autor) {
        this.autor = autor;
    }
    
    public String getGenero() {
        return genero;
    }
    
    public void setGenero(String genero) {
        this.genero = genero;
    }
    
    public String getSinopse() {
        return sinopse;
    }
    
    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }
    
    public int getAnoPublicacao() {
        return anoPublicacao;
    }
    
    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }
    
    public int getTotalFavoritos() {
        return totalFavoritos;
    }
    
    public void setTotalFavoritos(int totalFavoritos) {
        this.totalFavoritos = totalFavoritos;
    }
    
    public int getTotalAcessos() {
        return totalAcessos;
    }
    
    public void setTotalAcessos(int totalAcessos) {
        this.totalAcessos = totalAcessos;
    }
    
    public String getCaminhoImagem() {
        return caminhoImagem;
    }
    
    public void setCaminhoImagem(String caminhoImagem) {
        this.caminhoImagem = caminhoImagem;
    }
    
    public void incrementarFavoritos() {
        this.totalFavoritos++;
    }
    
    public void decrementarFavoritos() {
        if (this.totalFavoritos > 0) {
            this.totalFavoritos--;
        }
    }
    
    public void incrementarAcessos() {
        this.totalAcessos++;
    }
    
    @Override
    public String toString() {
        return titulo + " - " + autor;
    }
}
