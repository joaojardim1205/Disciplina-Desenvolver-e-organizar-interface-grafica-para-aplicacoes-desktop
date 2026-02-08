package com.biblioteca.controller;

import com.biblioteca.model.Livro;
import com.biblioteca.repository.LivroRepository;
import java.util.List;

public class LivroController {
    
    public boolean salvarLivro(Livro livro) {
        if (livro.getIdLivro() == 0) {
            LivroRepository.salvar(livro);
        } else {
            LivroRepository.atualizar(livro);
        }
        return true;
    }
    
    public boolean atualizarLivro(Livro livro) {
        LivroRepository.atualizar(livro);
        return true;
    }
    
    public boolean removerLivro(int id) {
        LivroRepository.removerPorId(id);
        return true;
    }
    
    public List<Livro> listarTodosLivros() {
        return LivroRepository.listar();
    }
    
    public Livro buscarLivroPorId(int id) {
        return LivroRepository.buscarPorId(id);
    }
    
    public List<Livro> buscarLivros(String filtro) {
        if (filtro == null || filtro.trim().isEmpty()) {
            return listarTodosLivros();
        }
        return LivroRepository.buscar(filtro);
    }
}
