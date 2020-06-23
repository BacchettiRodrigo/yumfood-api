package br.com.reignited.yumfood.domain.exception;

public class FotoProdutoNaoEncontrada extends EntidadeNaoEncontradaException {

    public FotoProdutoNaoEncontrada(String mensagem) {
        super(mensagem);
    }

    public FotoProdutoNaoEncontrada(Long restauranteId, Long produtoId) {
        this(String.format("Não foi encontrada a foto do produto de código %d no restaurante de código %d.",
                produtoId, restauranteId));
    }
}
