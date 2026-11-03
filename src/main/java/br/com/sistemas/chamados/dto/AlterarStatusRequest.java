package br.com.sistemas.chamados.dto;

import jakarta.validation.constraints.NotNull;
import br.com.sistemas.chamados.entity.enums.StatusChamado;

public record AlterarStatusRequest(

    @NotNull(message = "Status é obrigatório")
    StatusChamado status

){}