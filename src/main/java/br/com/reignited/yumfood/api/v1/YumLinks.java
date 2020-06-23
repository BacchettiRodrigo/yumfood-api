package br.com.reignited.yumfood.api.v1;

import br.com.reignited.yumfood.api.v1.controller.*;
import org.springframework.hateoas.*;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class YumLinks {

    public static final TemplateVariables PAGE_VARIABLES = new TemplateVariables(
            new TemplateVariable("page", TemplateVariable.VariableType.REQUEST_PARAM),
            new TemplateVariable("size", TemplateVariable.VariableType.REQUEST_PARAM),
            new TemplateVariable("sort", TemplateVariable.VariableType.REQUEST_PARAM));

    public Link linkToPedidos(String rel) {
        TemplateVariables filterVariables = new TemplateVariables(
                new TemplateVariable("clienteId", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("restauranteId", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("dataCriacaoInicio", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("dataCriacaoFim", TemplateVariable.VariableType.REQUEST_PARAM)
        );

        String pedidosUrl = linkTo(PedidoController.class).toUri().toString();
        return new Link(UriTemplate.of(pedidosUrl, PAGE_VARIABLES.concat(filterVariables)), rel);
    }

    public Link linkToConfirmacaoPedido(String codigoPedido, String rel) {
        return linkTo(methodOn(PedidoController.class).confirmar(codigoPedido)).withRel(rel);
    }

    public Link linkToCancelamentoPedido(String codigoPedido, String rel) {
        return linkTo(methodOn(PedidoController.class).cancelar(codigoPedido)).withRel(rel);
    }

    public Link linkToEntregaPedido(String codigoPedido, String rel) {
        return linkTo(methodOn(PedidoController.class).entregar(codigoPedido)).withRel(rel);
    }

    public Link linkToEstado(Long estadoId, String rel) {
        return linkTo(methodOn(EstadoController.class).buscar(estadoId)).withRel(rel);
    }

    public Link linkToEstado(Long estadoId) {
        return linkToEstado(estadoId, IanaLinkRelations.SELF.value());
    }

    public Link linkToEstados(String rel) {
        return linkTo(EstadoController.class).withRel(rel);
    }

    public Link linkToRestaurante(Long restauranteId, String rel) {
        return linkTo(methodOn(RestauranteController.class).buscar(restauranteId)).withRel(rel);
    }

    public Link linkToRestaurante(Long restauranteId) {
        return linkToRestaurante(restauranteId, IanaLinkRelations.SELF.value());
    }

    public Link linkToRestaurantes(String rel) {
        TemplateVariables variables = new TemplateVariables(
                new TemplateVariable("projecao", TemplateVariable.VariableType.REQUEST_PARAM));

        String urlRestaurantes = linkTo(RestauranteController.class).toUri().toString();
        return new Link(UriTemplate.of(urlRestaurantes, variables), rel);
    }

    public Link linkToRestaurantes() {
        return linkToRestaurantes(IanaLinkRelations.SELF.value());
    }

    public Link linkToAbrirRestaurante(Long restuaranteId, String rel) {
        return linkTo(methodOn(RestauranteController.class).abrir(restuaranteId)).withRel(rel);
    }

    public Link linkToFecharRestaurante(Long restuaranteId, String rel) {
        return linkTo(methodOn(RestauranteController.class).fechar(restuaranteId)).withRel(rel);
    }

    public Link linkToAtivarRestaurante(Long restuaranteId, String rel) {
        return linkTo(methodOn(RestauranteController.class).ativar(restuaranteId)).withRel(rel);
    }

    public Link linkToInativarRestaurante(Long restuaranteId, String rel) {
        return linkTo(methodOn(RestauranteController.class).inativar(restuaranteId)).withRel(rel);
    }

    public Link linkToRestauranteFormasPagamento(Long restauranteId, String rel) {
        return linkTo(methodOn(RestauranteFormaPagamentoController.class).listar(restauranteId)).withRel(rel);
    }

    public Link linkToRestauranteFormasPagamento(Long restauranteId) {
        return linkToRestauranteFormasPagamento(restauranteId, IanaLinkRelations.SELF.value());
    }

    public Link linkToRestauranteFormaPagamentoDesassociacao(Long restauranteId, Long formaPagamentoId, String rel) {
        return linkTo(methodOn(RestauranteFormaPagamentoController.class)
                .desassociar(restauranteId, formaPagamentoId)).withRel(rel);
    }

    public Link linkToRestauranteFormaPagamentoAssociacao(Long restauranteId, String rel) {
        return linkTo(methodOn(RestauranteFormaPagamentoController.class)
                .associar(restauranteId, null)).withRel(rel);
    }

    public Link linkToRestauranteResponsavel(Long restauranteId, String rel) {
        return linkTo(methodOn(RestauranteUsuarioResponsavelController.class).listar(restauranteId)).withRel(rel);
    }

    public Link linkToRestauranteResponsavel(Long restauranteId) {
        return linkToRestauranteResponsavel(restauranteId, IanaLinkRelations.SELF.value());
    }

    public Link linkToRestauranteResponsavelDesassociacao(Long restauranteId, Long usuarioId, String rel) {
        return linkTo(methodOn(RestauranteUsuarioResponsavelController.class)
                .desassociar(restauranteId, usuarioId)).withRel(rel);
    }

    public Link linkToRestauranteResponsavelAssociacao(Long restauranteId, String rel) {
        return linkTo(methodOn(RestauranteUsuarioResponsavelController.class)
                .associar(restauranteId, null)).withRel(rel);
    }

    public Link linkToCidade(Long cidadeId, String rel) {
        return linkTo(methodOn(CidadeController.class).buscar(cidadeId)).withRel(rel);
    }

    public Link linkToCidade(Long cidadeId) {
        return linkToCidade(cidadeId, IanaLinkRelations.SELF.value());
    }

    public Link linkToCidades(String rel) {
        return linkTo(CidadeController.class).withRel(rel);
    }

    public Link linkToCozinha(Long cozinhaId, String rel) {
        return linkTo(methodOn(CozinhaController.class).buscar(cozinhaId)).withRel(rel);
    }

    public Link linkToCozinha(Long cozinhaId) {
        return linkToCozinha(cozinhaId, IanaLinkRelations.SELF.value());
    }

    public Link linkToCozinhas(String rel) {
        return linkTo(CozinhaController.class).withRel(rel);
    }

    public Link linkToUsuario(Long usuarioId, String rel) {
        return linkTo(methodOn(UsuarioController.class).buscar(usuarioId)).withRel(rel);
    }

    public Link linkToUsuario(Long usuarioId) {
        return linkToUsuario(usuarioId, IanaLinkRelations.SELF.value());
    }

    public Link linkToUsuarios(String rel) {
        return linkTo(UsuarioController.class).withRel(rel);
    }

    public Link linkToUsuarioGrupos(Long usuarioId, String rel) {
        return linkTo(methodOn(UsuarioGrupoController.class).listar(usuarioId)).withRel(rel);
    }

    public Link linkToUsuarioGrupos(Long usuarioId) {
        return linkToUsuarioGrupos(usuarioId, IanaLinkRelations.SELF.value());
    }

    public Link linkToUsuarioGrupoDesassociar(Long usuarioId, Long grupoId, String rel) {
        return linkTo(methodOn(UsuarioGrupoController.class).desassociarGrupo(usuarioId, grupoId)).withRel(rel);
    }

    public Link linkToUsuarioGrupoAssociar(Long usuarioId, Long grupoId, String rel) {
        return linkTo(methodOn(UsuarioGrupoController.class).associarGrupo(usuarioId, grupoId)).withRel(rel);
    }

    public Link linkToFormaPagamento(Long formaPagamentoId, String rel) {
        return linkTo(methodOn(FormaPagamentoController.class).buscar(formaPagamentoId, null)).withRel(rel);
    }

    public Link linkToFormaPagamento(Long formaPagamentoId) {
        return linkToFormaPagamento(formaPagamentoId, IanaLinkRelations.SELF.value());
    }

    public Link linkToFormasPagamento(String rel) {
        return linkTo(FormaPagamentoController.class).withRel(rel);
    }

    public Link linkToFormasPagamento() {
        return linkToFormasPagamento(IanaLinkRelations.SELF.value());
    }

    public Link linkToRestauranteProduto(Long restauranteId, Long produtoId, String rel) {
        return linkTo(methodOn(RestauranteProdutoController.class).buscar(restauranteId, produtoId)).withRel(rel);
    }

    public Link linkToRestauranteProduto(Long restauranteId, Long produtoId) {
        return linkToRestauranteProduto(restauranteId, produtoId, IanaLinkRelations.SELF.value());
    }

    public Link linkToProdutos(Long restauranteId, String rel) {
        return linkTo(methodOn(RestauranteProdutoController.class)
                .listar(restauranteId, null)).withRel(rel);
    }

    public Link linkToProdutos(Long restauranteId) {
        return linkToProdutos(restauranteId, IanaLinkRelations.SELF.value());
    }

    public Link linkToProdutoFoto(Long restauranteId, Long produtoId, String rel) {
        return linkTo(methodOn(RestauranteProdutoFotoController.class).buscar(restauranteId, produtoId)).withRel(rel);
    }

    public Link linkToProdutoFoto(Long restauranteId, Long produtoId) {
        return linkToProdutoFoto(restauranteId, produtoId, IanaLinkRelations.SELF.value());
    }

    public Link linkToGrupo(Long grupoId, String rel) {
        return linkTo(methodOn(GrupoController.class).buscar(grupoId)).withRel(rel);
    }

    public Link linkToGrupo(Long grupoId) {
        return linkToGrupo(grupoId, IanaLinkRelations.SELF.value());
    }

    public Link linkToGrupos(String rel) {
        return linkTo(GrupoController.class).withRel(rel);
    }

    public Link linkToGrupos() {
        return linkToGrupos(IanaLinkRelations.SELF.value());
    }

    public Link linkToGruposPermissaoDesassociar(Long grupoId, Long permissaoId, String rel) {
        return linkTo(methodOn(GrupoPermissaoController.class).desassociar(grupoId, permissaoId)).withRel(rel);
    }

    public Link linkToGruposPermissaoAssociar(Long grupoId, Long permissaoId, String rel) {
        return linkTo(methodOn(GrupoPermissaoController.class).associar(grupoId, permissaoId)).withRel(rel);
    }

    public Link linkToGruposPermissoes(Long grupoId, String rel) {
        return linkTo(methodOn(GrupoPermissaoController.class).listar(grupoId)).withRel(rel);
    }

    public Link linkToGruposPermissoes(Long grupoId) {
        return linkToGruposPermissoes(grupoId, IanaLinkRelations.SELF.value());
    }

    public Link linkToPermissoes(String rel) {
        return linkTo(methodOn(PermissaoController.class).listar()).withRel(rel);
    }

    public Link linkToPermissoes() {
        return linkToPermissoes(IanaLinkRelations.SELF.value());
    }

    public Link linkToEstatisticasVendasDiarias(String rel) {
        TemplateVariables variables = new TemplateVariables(
                new TemplateVariable("timeOffSet", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("restauranteId", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("dataCriacaoInicio", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("dataCriacaoFim", TemplateVariable.VariableType.REQUEST_PARAM)
        );

        String urlEstatisticas = linkTo(EstatisticasController.class).toUri().toString();
        return new Link(UriTemplate.of(urlEstatisticas, variables), rel);
    }

    public Link linkToEstatisticas(String rel) {
        return linkTo(EstatisticasController.class).withRel(rel);
    }

}
