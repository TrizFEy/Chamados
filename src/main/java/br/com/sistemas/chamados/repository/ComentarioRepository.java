package br.com.sistemas.chamados.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.sistemas.chamados.entity.Comentario;
import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    List<Comentario> findByChamadoIdOrderByDataAsc(Long chamadoId);
}