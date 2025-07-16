package br.com.mirante.sge.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class EventoDTO {

	private Long id;
	
	@NotBlank(message = "O Título é obrigatório")
	@Size(max = 100, message = "O título não deve exceder 100 caracteres")
	private String titulo;

	@NotBlank(message = "A Descrição é obrigatória")
	@Size(max = 1000, message = "A Descrição não deve exceder 1000 caracteres")
	private String descricao;

	@NotNull(message = "A Data do Evento é obrigatória")
	@FutureOrPresent(message = "A data do evento deve ser igual ou maior que a data atual")
	private LocalDateTime dataHoraEvento;

	@NotBlank(message = "O Local do Evento é obrigatório")
	@Size(max = 200, message = "O local do evento não deve exceder 200 caracteres")
	private String local;

	private LocalDateTime criadoEm;
	
	private LocalDateTime atualizadoEm;
}