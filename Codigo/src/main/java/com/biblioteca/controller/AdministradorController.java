package com.biblioteca.controller;

import com.biblioteca.model.Administrador;
import com.biblioteca.repository.AdministradorRepository;

public class AdministradorController {
    
    public Administrador autenticar(String email, String senha) {
        Administrador admin = AdministradorRepository.buscarPorEmail(email);
        if (admin != null && admin.getSenha().equals(senha)) {
            return admin;
        }
        return null;
    }
    
    public boolean salvarAdministrador(Administrador admin) {
        if (AdministradorRepository.emailJaExiste(admin.getEmail())) {
            return false;
        }
        AdministradorRepository.salvar(admin);
        return true;
    }

    public Administrador buscarPorEmail(String email) {
        return AdministradorRepository.buscarPorEmail(email);
    }
}
