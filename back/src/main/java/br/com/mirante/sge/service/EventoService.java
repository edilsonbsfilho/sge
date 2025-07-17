package br.com.mirante.sge.service;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.mirante.sge.dto.EventoDTO;
import br.com.mirante.sge.entity.Evento;
import br.com.mirante.sge.exception.EventoException;
import br.com.mirante.sge.repository.EventoRepository;

@Service
public class EventoService {

	private static final String MSG_EVENTO_NAO_ENCONTRADO = "Evento não encontrado";
	private final AtomicLong contador = new AtomicLong(0);

	private final EventoRepository eventoRepository;

	public EventoService(EventoRepository eventoRepository) {
		this.eventoRepository = eventoRepository;
	}

	public Page<EventoDTO> listarTodos(Pageable pageable) {
		return eventoRepository.findByExcluidoFalse(pageable).map(this::converterParaDTO);
	}

	public EventoDTO obterPorId(Long id) {
		Evento evento = findByIdAndExcluidoFalse(id);
		
		return converterParaDTO(evento);
	}

	public EventoDTO criar(EventoDTO eventoDTO) {
		Evento evento = new Evento();
		
		evento.setTitulo(eventoDTO.getTitulo());
		evento.setDescricao(eventoDTO.getDescricao());
		evento.setDataHoraEvento(eventoDTO.getDataHoraEvento());
		evento.setLocal(eventoDTO.getLocal());
		
		incrementarContador();
		
		return converterParaDTO(eventoRepository.save(evento));
	}

	public EventoDTO atualizar(EventoDTO eventoDTO) {
		Evento evento = findByIdAndExcluidoFalse(eventoDTO.getId());
		
		evento.setTitulo(eventoDTO.getTitulo());
		evento.setDescricao(eventoDTO.getDescricao());
		evento.setDataHoraEvento(eventoDTO.getDataHoraEvento());
		evento.setLocal(eventoDTO.getLocal());
		
		return converterParaDTO(eventoRepository.save(evento));
	}

	public void excluir(Long id) {
		Evento evento = findByIdAndExcluidoFalse(id);
		
		evento.setExcluido(true);
		eventoRepository.save(evento);
	}

	private EventoDTO converterParaDTO(Evento evento) {
		EventoDTO dto = new EventoDTO();
		
		dto.setId(evento.getId());
		dto.setTitulo(evento.getTitulo());
		dto.setDescricao(evento.getDescricao());
		dto.setDataHoraEvento(evento.getDataHoraEvento());
		dto.setLocal(evento.getLocal());
		dto.setCriadoEm(evento.getCriadoEm());
		dto.setAtualizadoEm(evento.getAtualizadoEm());
		
		return dto;
	}
	
	private Evento findByIdAndExcluidoFalse(Long id) {
		Evento evento = eventoRepository.findByIdAndExcluidoFalse(id);
		if (evento == null) {
			throw new EventoException(id, MSG_EVENTO_NAO_ENCONTRADO);
		}
		return evento;
	}
	
	private long incrementarContador() {
		return contador.incrementAndGet();
	}
	
	public long getContador() {
		return contador.get();
	}
}