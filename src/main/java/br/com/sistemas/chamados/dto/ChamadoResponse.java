package br.com.sistemas.chamados.dto;

import br.com.sistemas.chamados.entity.Chamado;
import br.com.sistemas.chamados.entity.enums.Prioridade;
import br.com.sistemas.chamados.entity.enums.StatusChamado;
import java.time.LocalDateTime;

public record ChamadoResponse(Long id, String titulo, String descricao, Prioridade prioridade, StatusChamado status, Long clienteId, Long categoriaId, LocalDateTime dataAbertura, LocalDateTime dataFechamento, Long tecnicoId) {
    public static ChamadoResponse de(Chamado c) {
        return new ChamadoResponse(c.getId(), c.getTitulo(), c.getDescricao(), c.getPrioridade(), c.getStatus(), c.getCliente().getId(), c.getCategoria().getId(), c.getDataAbertura(), c.getDataFechamento(), c.getTecnico() == null ? null : c.getTecnico().getId());
    }
}