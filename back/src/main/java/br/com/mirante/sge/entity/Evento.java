package br.com.mirante.sge.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "evento")
@Data
public class Evento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@CreationTimestamp
	private LocalDateTime criadoEm;

	@UpdateTimestamp
	private LocalDateTime atualizadoEm;

	private boolean excluido = false;
}