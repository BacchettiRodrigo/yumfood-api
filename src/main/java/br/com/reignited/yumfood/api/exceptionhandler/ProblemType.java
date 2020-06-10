package br.com.reignited.yumfood.api.exceptionhandler;

public enum ProblemType {

    ERRO_INTERNO_SISTEMA("/erro-interno-sistema", "Erro interno no sistema"),
    RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
    ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
    PARAMETRO_INVALIDO("/parametro-invalido", "Parametro inválido"),
    ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),
    MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem Incompreensível"),
    DADOS_INVALIDOS("/dados-invalidos", "Dados Inválidos"),
    ACESSO_NEGADO("/acesso-negado", "Acesso Negado");

    private String title;
    private String uri;

    ProblemType(String path, String title) {
        this.uri = "https://yumfood.com.br" + path;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getUri() {
        return uri;
    }
}
