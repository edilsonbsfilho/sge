package br.com.mirante.sge.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.mirante.sge.entity.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long> {
	
	Page<Evento> findByExcluidoFalse(Pageable pageable);

	Evento findByIdAndExcluidoFalse(Long id);
}
