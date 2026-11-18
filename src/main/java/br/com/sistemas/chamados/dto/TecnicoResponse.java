package br.com.sistemas.chamados.dto;

import br.com.sistemas.chamados.entity.Tecnico;

public record TecnicoResponse(Long id, String nome, String email, String perfil) {
    public static TecnicoResponse de(Tecnico c) {
        return new TecnicoResponse(c.getId(), c.getNome(), c.getEmail(), c.getPerfil());
    }
}