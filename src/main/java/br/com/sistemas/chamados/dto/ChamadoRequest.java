package br.com.sistemas.chamados.dto;

import br.com.sistemas.chamados.entity.enums.Prioridade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ChamadoRequest(
    @NotBlank(message = "Titulo é obrigatório")
    @Size(max = 100, message = "Titulo deve ter no máximo 100 caracteres")
    String titulo,

    @NotBlank(message = "Descrição é obrigatória")
    @Size(max = 500,message = "Descrição deve ter no máximo 500 caracteres")
    String descricao,

    @NotNull(message = "Prioridade é obrigatória")
    Prioridade prioridade,

    @NotNull(message = "Categoria é obrigatória")
    Long categoriaId,

    @NotNull(message = "Cliente é obrigatório")
    Long clienteId,

    Long tecnicoId

){}
