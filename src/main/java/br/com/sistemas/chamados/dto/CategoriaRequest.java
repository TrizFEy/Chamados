package br.com.sistemas.chamados.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoriaRequest(
    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
    String nome,

    @NotBlank(message = "Descrição é obrigatória")
    @Size(max = 500, message = "Descrição inválida")
    String descricao,

){}