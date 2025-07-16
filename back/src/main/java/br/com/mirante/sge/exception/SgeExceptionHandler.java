package br.com.mirante.sge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class SgeExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException ex) {
		Map<String, String> error = new HashMap<>();
		error.put("Erro inesperado", ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(EventoException.class)
	public ResponseEntity<ExceptionResponse> handleEventoException(EventoException ex) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.NOT_FOUND.name(), ex.getMessage(),
				" ID do Evento: " + ex.getEventoId());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
}