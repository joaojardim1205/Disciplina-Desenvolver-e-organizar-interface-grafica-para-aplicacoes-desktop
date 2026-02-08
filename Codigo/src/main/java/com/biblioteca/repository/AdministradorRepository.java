package com.biblioteca.repository;

import com.biblioteca.model.Administrador;
import java.util.ArrayList;
import java.util.List;

public class AdministradorRepository {

    private static final List<Administrador> administradores = new ArrayList<>();
    private static int nextId = 1;

    // Inicializar com admin padrão
    static {
        Administrador adminPadrao = new Administrador();
        adminPadrao.setIdAdmin(nextId++);
        adminPadrao.setNome("Administrador");
        adminPadrao.setEmail("admin@biblioteca.com");
        adminPadrao.setSenha("admin123");
        administradores.add(adminPadrao);
    }

    public static void salvar(Administrador administrador) {
        administrador.setIdAdmin(nextId++);
        administradores.add(administrador);
    }

    public static List<Administrador> listar() {
        return new ArrayList<>(administradores);
    }

    public static void atualizar(Administrador adminAtualizado) {
        for (int i = 0; i < administradores.size(); i++) {
            if (administradores.get(i).getIdAdmin() == adminAtualizado.getIdAdmin()) {
                administradores.set(i, adminAtualizado);
                return;
            }
        }
    }

    public static Administrador buscarPorId(int id) {
        for (Administrador admin : administradores) {
            if (admin.getIdAdmin() == id) {
                return admin;
            }
        }
        return null;
    }

    public static Administrador buscarPorEmail(String email) {
        for (Administrador admin : administradores) {
            if (admin.getEmail().equals(email)) {
                return admin;
            }
        }
        return null;
    }

    public static void remover(Administrador administrador) {
        administradores.remove(administrador);
    }

    public static boolean emailJaExiste(String email) {
        return buscarPorEmail(email) != null;
    }
}
