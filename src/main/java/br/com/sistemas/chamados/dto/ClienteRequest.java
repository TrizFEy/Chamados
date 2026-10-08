package br.com.sistemas.chamados.dto;

import jakarta.validation.contraints.Email;
import jakarta.validation.contraints.NotBlank;
import jakarta.validation.contraints.Size;

public record ClienteRequest(
    @NotBlank(message = "Nome é obrgatório")
    @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
    String nome,

    @NotBlank(message = "E-mail é obrigatório")
    @Email(message = "E-mail inválido")
    String email,

    @Size(max = 20, message = "Telefone deve ter no máximo 20 caracteres")
    String telefone
){}