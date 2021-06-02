package com.algaworks.algafood.domain.exception;

public class NegociosException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public NegociosException(String mensagem) {
		super(mensagem);
	}
	
	public NegociosException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
