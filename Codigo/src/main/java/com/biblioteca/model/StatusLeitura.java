package com.biblioteca.model;

/**
 * Enum que representa o status de leitura de um livro
 */
public enum StatusLeitura {
    QUERO_LER("Quero Ler"),
    LENDO("Lendo Atualmente"),
    JA_LI("Ja Li"),
    NENHUM("Nenhum");
    
    private final String descricao;
    
    StatusLeitura(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public static StatusLeitura fromString(String texto) {
        for (StatusLeitura status : StatusLeitura.values()) {
            if (status.descricao.equalsIgnoreCase(texto)) {
                return status;
            }
        }
        return NENHUM;
    }
    
    @Override
    public String toString() {
        return descricao;
    }
}
