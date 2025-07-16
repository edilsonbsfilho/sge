package br.com.mirante.sge.exception;

public class SgeException extends RuntimeException {

	private static final long serialVersionUID = -2878545831652329798L;
	
	private static final String TITULO_SISTEMA = "SGU: ";
	
	public SgeException(String mensagem) {
		super(TITULO_SISTEMA.concat(mensagem));
	}
	
	public SgeException(String mensagem, Throwable ex) {
		super(TITULO_SISTEMA.concat(mensagem), ex);
	}

}
