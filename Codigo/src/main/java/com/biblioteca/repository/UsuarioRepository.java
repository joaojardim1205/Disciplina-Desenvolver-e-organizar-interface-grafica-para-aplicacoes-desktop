package com.biblioteca.repository;

import com.biblioteca.model.Usuario;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {

    private static final List<Usuario> usuarios = new ArrayList<>();
    private static int nextId = 1;

    // Inicializar com usuário de exemplo
    static {
        Usuario usuarioPadrao = new Usuario();
        usuarioPadrao.setIdUsuario(nextId++);
        usuarioPadrao.setNome("Usuário Teste");
        usuarioPadrao.setEmail("usuario@biblioteca.com");
        usuarioPadrao.setSenha("user123");
        usuarios.add(usuarioPadrao);
    }

    public static void salvar(Usuario usuario) {
        usuario.setIdUsuario(nextId++);
        usuarios.add(usuario);
    }

    public static List<Usuario> listar() {
        return new ArrayList<>(usuarios);
    }

    public static void atualizar(Usuario usuarioAtualizado) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getIdUsuario() == usuarioAtualizado.getIdUsuario()) {
                usuarios.set(i, usuarioAtualizado);
                return;
            }
        }
    }

    public static Usuario buscarPorId(int id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getIdUsuario() == id) {
                return usuario;
            }
        }
        return null;
    }

    public static Usuario buscarPorEmail(String email) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email)) {
                return usuario;
            }
        }
        return null;
    }

    public static List<Usuario> buscarPorNome(String nome) {
        List<Usuario> resultados = new ArrayList<>();
        String nomeLower = nome.toLowerCase();
        for (Usuario usuario : usuarios) {
            if (usuario.getNome().toLowerCase().contains(nomeLower)) {
                resultados.add(usuario);
            }
        }
        return resultados;
    }

    public static void remover(Usuario usuario) {
        usuarios.remove(usuario);
    }

    public static boolean emailJaExiste(String email) {
        return buscarPorEmail(email) != null;
    }
}
