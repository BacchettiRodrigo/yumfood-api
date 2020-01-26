package br.com.reignited.yumfood.domain.exception;

public class PedidoNaoEncontradoException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public PedidoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public PedidoNaoEncontradoException(Long id) {
        this(String.format("Não existe um registro de pedido com o código %d.", id));
    }
}
