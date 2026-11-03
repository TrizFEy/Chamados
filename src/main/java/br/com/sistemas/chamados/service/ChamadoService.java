package br.com.sistemas.chamados.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sistemas.chamados.dto.ChamadoRequest;
import br.com.sistemas.chamados.dto.ChamadoResponse;
import br.com.sistemas.chamados.entity.Categoria;
import br.com.sistemas.chamados.entity.Chamado;
import br.com.sistemas.chamados.entity.Cliente;
import br.com.sistemas.chamados.entity.Tecnico;
import br.com.sistemas.chamados.entity.enums.StatusChamado;
import br.com.sistemas.chamados.exception.RecursoNaoEncontradoException;
import br.com.sistemas.chamados.repository.CategoriaRepository;
import br.com.sistemas.chamados.repository.ChamadoRepository;
import br.com.sistemas.chamados.repository.ClienteRepository;
import br.com.sistemas.chamados.repository.TecnicoRepository;

@Service
public class ChamadoService {

    private final ChamadoRepository chamadoRepository;
    private final ClienteRepository clienteRepository;
    private final CategoriaRepository categoriaRepository;
    private final TecnicoRepository tecnicoRepository;

    public ChamadoService(ChamadoRepository chamadoRepository,
                          ClienteRepository clienteRepository,
                          CategoriaRepository categoriaRepository,
                          TecnicoRepository tecnicoRepository) {
        this.chamadoRepository = chamadoRepository;
        this.clienteRepository = clienteRepository;
        this.categoriaRepository = categoriaRepository;
        this.tecnicoRepository = tecnicoRepository;
    }

    @Transactional
    public ChamadoResponse criar(ChamadoRequest dto) {
        Cliente cliente = buscarCliente(dto.clienteId());
        Categoria categoria = buscarCategoria(dto.categoriaId());
        Tecnico tecnico = dto.tecnicoId() != null ? buscarTecnico(dto.tecnicoId()) : null;

        Chamado chamado = new Chamado();
        chamado.setTitulo(dto.titulo());
        chamado.setDescricao(dto.descricao());
        chamado.setPrioridade(dto.prioridade());
        chamado.setCliente(cliente);
        chamado.setCategoria(categoria);
        chamado.setTecnico(tecnico);
        chamado.setStatus(StatusChamado.ABERTO);
        chamado.setDataAbertura(LocalDateTime.now());

        Chamado salvo = chamadoRepository.save(chamado);
        return ChamadoResponse.de(salvo);
    }

    @Transactional(readOnly = true)
    public List<ChamadoResponse> listar() {
        return chamadoRepository.findAll().stream().map(ChamadoResponse::de).toList();
    }

    @Transactional(readOnly = true)
    public ChamadoResponse buscar(Long id) {
        return ChamadoResponse.de(buscarEntity(id));
    }

    @Transactional
    public ChamadoResponse atualizar(Long id, ChamadoRequest dto) {
        Chamado chamado = buscarEntity(id);

        Cliente cliente = buscarCliente(dto.clienteId());
        Categoria categoria = buscarCategoria(dto.categoriaId());
        Tecnico tecnico = dto.tecnicoId() != null ? buscarTecnico(dto.tecnicoId()) : null;

        chamado.setTitulo(dto.titulo());
        chamado.setDescricao(dto.descricao());
        chamado.setPrioridade(dto.prioridade());
        chamado.setCliente(cliente);
        chamado.setCategoria(categoria);
        chamado.setTecnico(tecnico);

        return ChamadoResponse.de(chamado);
    }

    @Transactional
    public void excluir(Long id) {
        chamadoRepository.delete(buscarEntity(id));
    }

    private Chamado buscarEntity(Long id) {
        return chamadoRepository.findById(id)
            .orElseThrow(() -> new RecursoNaoEncontradoException(
                "Chamado " + id + " não encontrado"));
    }

    private Cliente buscarCliente(Long id) {
        return clienteRepository.findById(id)
            .orElseThrow(() -> new RecursoNaoEncontradoException(
                "Cliente " + id + " não encontrado"));
    }

    private Categoria buscarCategoria(Long id) {
        return categoriaRepository.findById(id)
            .orElseThrow(() -> new RecursoNaoEncontradoException(
                "Categoria " + id + " não encontrada"));
    }

    private Tecnico buscarTecnico(Long id) {
        return tecnicoRepository.findById(id)
            .orElseThrow(() -> new RecursoNaoEncontradoException(
                "Técnico " + id + " não encontrado"));
    }
}