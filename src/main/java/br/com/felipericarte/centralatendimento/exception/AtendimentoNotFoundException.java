package br.com.felipericarte.centralatendimento.exception;

public class AtendimentoNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -8013456149613997159L;
	
	public AtendimentoNotFoundException(String message) {
        super(message);
    }

}
