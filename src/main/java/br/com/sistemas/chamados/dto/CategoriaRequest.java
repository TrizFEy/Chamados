package br.com.sistemas.chamados.dto;

import jakarta.validation.contraints.Email;
import jakarta.validation.contraints.NotBlank;
import jakarta.validation.contraints.Size;

public record CategoriaRequest(
    @NotBlank(message = "Nome é obrgatório")
    @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
    String nome,

    @NotBlank(message = "Descrição é obrigatória")
    @Email(message = "Descrição inválida")
    String descricao,

){}