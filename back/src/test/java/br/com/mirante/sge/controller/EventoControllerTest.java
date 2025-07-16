package br.com.mirante.sge.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import br.com.mirante.sge.entity.Evento;
import br.com.mirante.sge.repository.EventoRepository;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class EventoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private EventoRepository eventoRepository;

	@BeforeEach
	void setUp() {

		eventoRepository.deleteAll();

		Evento evento1 = new Evento();
		evento1.setTitulo("Evento Real 1");
		evento1.setDescricao("Descricao teste 1");
		evento1.setDataHoraEvento(LocalDateTime.now().plusDays(2));
		evento1.setLocal("Local teste 1");
		eventoRepository.save(evento1);

		Evento evento2 = new Evento();
		evento2.setTitulo("Evento Real 2");
		evento2.setDescricao("Descricao teste 2");
		evento2.setLocal("Local teste 2");
		evento2.setDataHoraEvento(LocalDateTime.now().plusDays(3));
		eventoRepository.save(evento2);
	}

	@Test
	void deveRetornarListaDeEventosDoH2AoChamarListarTodos() throws Exception {
		mockMvc.perform(get("/api/events").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.content").isArray()).andExpect(jsonPath("$.content.length()").value(2))
				.andExpect(jsonPath("$.content[0].titulo").value("Evento Real 1"))
				.andExpect(jsonPath("$.content[1].titulo").value("Evento Real 2"));
	}
}