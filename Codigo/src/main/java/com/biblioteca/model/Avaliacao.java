package com.biblioteca.model;

/**
 * Classe que representa uma Avaliacao de um livro por um usuario
 */
public class Avaliacao {
    
    private int idAvaliacao;
    private int idUsuario;
    private int idLivro;
    private int nota; // 1 a 5 estrelas
    private String comentario;
    
    // Construtores
    public Avaliacao() {
    }
    
    public Avaliacao(int idUsuario, int idLivro, int nota) {
        this.idUsuario = idUsuario;
        this.idLivro = idLivro;
        this.nota = nota;
    }
    
    public Avaliacao(int idUsuario, int idLivro, int nota, String comentario) {
        this.idUsuario = idUsuario;
        this.idLivro = idLivro;
        this.nota = nota;
        this.comentario = comentario;
    }
    
    // Getters e Setters
    public int getIdAvaliacao() {
        return idAvaliacao;
    }
    
    public void setIdAvaliacao(int idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    }
    
    public int getIdUsuario() {
        return idUsuario;
    }
    
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public int getIdLivro() {
        return idLivro;
    }
    
    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }
    
    public int getNota() {
        return nota;
    }
    
    public void setNota(int nota) {
        if (nota >= 1 && nota <= 5) {
            this.nota = nota;
        }
    }
    
    public String getComentario() {
        return comentario;
    }
    
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
    @Override
    public String toString() {
        return "Avaliacao{" +
                "nota=" + nota +
                ", comentario='" + comentario + '\'' +
                '}';
    }
}
