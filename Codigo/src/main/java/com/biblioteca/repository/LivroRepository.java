package com.biblioteca.repository;

import com.biblioteca.model.Livro;
import java.util.ArrayList;
import java.util.List;

public class LivroRepository {

    private static final List<Livro> livros = new ArrayList<>();
    private static int nextId = 1;

    // Inicializar com livros de exemplo
    static {
        Livro livro1 = new Livro();
        livro1.setIdLivro(nextId++);
        livro1.setTitulo("1984");
        livro1.setAutor("George Orwell");
        livro1.setGenero("Ficção Científica");
        livro1.setSinopse("Um romance distópico sobre um regime totalitário.");
        livro1.setAnoPublicacao(1949);
        livro1.setTotalFavoritos(0);
        livro1.setTotalAcessos(0);
        livro1.setCaminhoImagem("images/1984.jpg");
        livros.add(livro1);

        Livro livro2 = new Livro();
        livro2.setIdLivro(nextId++);
        livro2.setTitulo("Dom Casmurro");
        livro2.setAutor("Machado de Assis");
        livro2.setGenero("Romance");
        livro2.setSinopse("Romance brasileiro clássico sobre traição e ciúmes.");
        livro2.setAnoPublicacao(1899);
        livro2.setTotalFavoritos(0);
        livro2.setTotalAcessos(0);
        livro2.setCaminhoImagem("images/dom_casmurro.jpg");
        livros.add(livro2);

        Livro livro3 = new Livro();
        livro3.setIdLivro(nextId++);
        livro3.setTitulo("O Pequeno Príncipe");
        livro3.setAutor("Antoine de Saint-Exupéry");
        livro3.setGenero("Infantil");
        livro3.setSinopse("Fábula poética sobre amor e amizade.");
        livro3.setAnoPublicacao(1943);
        livro3.setTotalFavoritos(0);
        livro3.setTotalAcessos(0);
        livro3.setCaminhoImagem("images/pequeno_principe.jpg");
        livros.add(livro3);
    }

    public static void salvar(Livro livro) {
        livro.setIdLivro(nextId++);
        livros.add(livro);
    }

    public static List<Livro> listar() {
        return new ArrayList<>(livros);
    }

    public static void atualizar(Livro livroAtualizado) {
        for (int i = 0; i < livros.size(); i++) {
            if (livros.get(i).getIdLivro() == livroAtualizado.getIdLivro()) {
                livros.set(i, livroAtualizado);
                return;
            }
        }
    }

    public static Livro buscarPorId(int id) {
        for (Livro livro : livros) {
            if (livro.getIdLivro() == id) {
                return livro;
            }
        }
        return null;
    }

    public static List<Livro> buscar(String filtro) {
        List<Livro> resultados = new ArrayList<>();
        if (filtro == null || filtro.trim().isEmpty()) {
            return listar();
        }
        
        String filtroLower = filtro.toLowerCase();
        for (Livro livro : livros) {
            if (livro.getTitulo().toLowerCase().contains(filtroLower) ||
                livro.getAutor().toLowerCase().contains(filtroLower) ||
                (livro.getGenero() != null && livro.getGenero().toLowerCase().contains(filtroLower))) {
                resultados.add(livro);
            }
        }
        return resultados;
    }

    public static void remover(Livro livro) {
        livros.remove(livro);
    }

    public static void removerPorId(int id) {
        livros.removeIf(livro -> livro.getIdLivro() == id);
    }

    public static int contarTotal() {
        return livros.size();
    }
}
