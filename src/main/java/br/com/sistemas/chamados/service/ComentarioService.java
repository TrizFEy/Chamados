package br.com.sistemas.chamados.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sistemas.chamados.dto.ComentarioRequest;
import br.com.sistemas.chamados.dto.ComentarioResponse;
import br.com.sistemas.chamados.entity.Comentario;
import br.com.sistemas.chamados.exception.RecursoNaoEncontradoException;
import br.com.sistemas.chamados.repository.ComentarioRepository;
import java.time.LocalDateTime;
import br.com.sistemas.chamados.entity.Chamado;
import br.com.sistemas.chamados.repository.ChamadoRepository;

@Service
public class ComentarioService {

    private final ComentarioRepository repository;
    private final ChamadoRepository chamadoRepository;

    public ComentarioService(ComentarioRepository repository, ChamadoRepository chamadoRepository) {
        this.repository = repository;
        this.chamadoRepository = chamadoRepository;
    }

    @Transactional
    public ComentarioResponse adicionar(Long chamadoId, ComentarioRequest dto) {
        Chamado chamado = buscarChamado(chamadoId);
        Comentario comentario = new Comentario(dto.texto(), LocalDateTime.now(), dto.autor(), chamado);
        repository.save(comentario);
        return ComentarioResponse.de(comentario);
    }

    @Transactional(readOnly = true)
    public List<ComentarioResponse> listar(Long chamadoId) {
        buscarChamado(chamadoId); // Verifica se o chamado existe
        return repository.findByChamadoIdOrderByDataAsc(chamadoId).stream().map(ComentarioResponse::de).toList();
    }

    private Chamado buscarChamado(Long id) {
        return chamadoRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Chamado não encontrado com id: " + id));
    }

}