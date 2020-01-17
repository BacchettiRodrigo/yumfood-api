package br.com.reignited.yumfood.domain.exception;

public class UsuarioNaoEncontradoException extends EntidadeNaoEncontradaException {

    public UsuarioNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public UsuarioNaoEncontradoException(Long id) {
        super(String.format("Não foi encontrado cadastro de usuário com o código %d", id));
    }
}
