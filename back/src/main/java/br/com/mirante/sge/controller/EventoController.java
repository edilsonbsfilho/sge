package br.com.mirante.sge.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.mirante.sge.dto.EventoDTO;
import br.com.mirante.sge.service.EventoService;

@RestController
@RequestMapping("/api/events")
public class EventoController {

	private final EventoService eventoService;

	public EventoController(EventoService eventoService) {
		this.eventoService = eventoService;
	}

	@GetMapping
	public Page<EventoDTO> listarTodos(Pageable pageable) {
		return eventoService.listarTodos(pageable);
	}

	@GetMapping("/{id}")
	public EventoDTO obterPorId(@PathVariable Long id) {
		return eventoService.obterPorId(id);
	}

	@PostMapping
	public ResponseEntity<EventoDTO> criar(@Valid @RequestBody EventoDTO eventoDTO) {
		return ResponseEntity.ok(eventoService.criar(eventoDTO));
	}

	@PutMapping
	public ResponseEntity<EventoDTO> atualizar(@Valid @RequestBody EventoDTO eventoDTO) {
		return ResponseEntity.ok(eventoService.atualizar(eventoDTO));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		eventoService.excluir(id);
		return ResponseEntity.noContent().build();
	}
}