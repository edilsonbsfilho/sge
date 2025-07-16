package br.com.mirante.sge.exception;

public class EventoException extends SgeException {

	private static final long serialVersionUID = 5822358057495017571L;
	
	private Long eventoId;

	public EventoException(String mensagem) {
		super(mensagem);
	}

	public EventoException(String mensagem, Throwable ex) {
		super(mensagem, ex);
	}
	
	public EventoException(Long eventoId, String mensagem) {		
		super(mensagem);
		this.eventoId = eventoId;
	}
	
	public Long getEventoId() {
        return eventoId;
    }

}
