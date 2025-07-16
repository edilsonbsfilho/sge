package br.com.mirante.sge.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import br.com.mirante.sge.dto.EventoDTO;
import br.com.mirante.sge.entity.Evento;
import br.com.mirante.sge.exception.EventoException;
import br.com.mirante.sge.repository.EventoRepository;
import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EventoServiceTest {

	@Mock
	private EventoRepository eventoRepository;

	@InjectMocks
	private EventoService eventoService;

	@Test
	void deveCriarEventoComSucesso() {

		EventoDTO eventoDTO = new EventoDTO();
		eventoDTO.setTitulo("Evento de Teste");
		eventoDTO.setDescricao("Descrição de Teste");
		eventoDTO.setDataHoraEvento(LocalDateTime.now().plusDays(1));
		eventoDTO.setLocal("Local de teste");

		Evento evento = new Evento();
		evento.setId(1L);
		evento.setTitulo(eventoDTO.getTitulo());
		evento.setDescricao(eventoDTO.getDescricao());
		evento.setDataHoraEvento(eventoDTO.getDataHoraEvento());
		evento.setLocal(eventoDTO.getLocal());

		when(eventoRepository.save(any(Evento.class))).thenReturn(evento);

		EventoDTO result = eventoService.criar(eventoDTO);

		assertNotNull(result);
		assertEquals(eventoDTO.getTitulo(), result.getTitulo());
		verify(eventoRepository, times(1)).save(any(Evento.class));
	}

	@Test
	void deveListarTodosEventosNaoExcluidos() {

		PageRequest pageable = PageRequest.of(0, 10);
		Evento evento = new Evento();
		evento.setId(1L);
		evento.setTitulo("Evento listado teste");
		Page<Evento> page = new PageImpl<>(Collections.singletonList(evento));

		when(eventoRepository.findByExcluidoFalse(pageable)).thenReturn(page);

		Page<EventoDTO> resultado = eventoService.listarTodos(pageable);

		assertEquals(1, resultado.getTotalElements());
		verify(eventoRepository, times(1)).findByExcluidoFalse(pageable);
	}

	@Test
	void deveLancarEventoExceptionQuandoEventoNaoEncontradoPorId() {

		when(eventoRepository.findByIdAndExcluidoFalse(1L)).thenReturn(null);

		assertThrows(EventoException.class, () -> eventoService.obterPorId(1L));
	}
}