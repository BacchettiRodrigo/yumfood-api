package br.com.reignited.yumfood.domain.exception;

public class EntidadeEmUsoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EntidadeEmUsoException(String mensagem) {
		super(mensagem);
	}

	public EntidadeEmUsoException(Long id) {
		this(String.format("Entidade de código %d não pode ser deletada pois está em uso", id));
	}

}
