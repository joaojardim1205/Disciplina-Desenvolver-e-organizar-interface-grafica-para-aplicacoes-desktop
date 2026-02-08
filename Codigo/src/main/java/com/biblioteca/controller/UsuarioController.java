package com.biblioteca.controller;

import com.biblioteca.model.Usuario;
import com.biblioteca.repository.UsuarioRepository;
import java.util.List;

public class UsuarioController {
    
    public Usuario autenticar(String email, String senha) {
        Usuario usuario = UsuarioRepository.buscarPorEmail(email);
        if (usuario != null && usuario.getSenha().equals(senha)) {
            return usuario;
        }
        return null;
    }
    
    public boolean salvarUsuario(Usuario usuario) {
        if (UsuarioRepository.emailJaExiste(usuario.getEmail())) {
            return false;
        }
        UsuarioRepository.salvar(usuario);
        return true;
    }
    
    public boolean atualizarUsuario(Usuario usuario) {
        UsuarioRepository.atualizar(usuario);
        return true;
    }
    
    public boolean removerUsuario(int id) {
        Usuario usuario = UsuarioRepository.buscarPorId(id);
        if (usuario != null) {
            UsuarioRepository.remover(usuario);
            return true;
        }
        return false;
    }
    
    public List<Usuario> listarTodosUsuarios() {
        return UsuarioRepository.listar();
    }
    
    public Usuario buscarUsuarioPorId(int id) {
        return UsuarioRepository.buscarPorId(id);
    }
    
    public Usuario buscarPorEmail(String email) {
        return UsuarioRepository.buscarPorEmail(email);
    }
    
    public List<Usuario> buscarUsuariosPorNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            return listarTodosUsuarios();
        }
        return UsuarioRepository.buscarPorNome(nome);
    }
    
    public boolean emailExiste(String email) {
        return UsuarioRepository.emailJaExiste(email);
    }
}
