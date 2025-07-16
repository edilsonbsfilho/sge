package br.com.mirante.sge.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExceptionResponse {
	
	private String codigo;
	private String mensagem;
	private String detalhes; 
}