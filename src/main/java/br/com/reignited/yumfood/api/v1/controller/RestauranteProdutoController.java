package br.com.reignited.yumfood.api.v1.controller;

import br.com.reignited.yumfood.api.v1.YumLinks;
import br.com.reignited.yumfood.api.v1.disassembler.ProdutoInputDisassembler;
import br.com.reignited.yumfood.api.v1.assembler.ProdutoModelAssembler;
import br.com.reignited.yumfood.api.v1.model.ProdutoModel;
import br.com.reignited.yumfood.api.v1.model.input.ProdutoInput;
import br.com.reignited.yumfood.api.v1.openapi.controller.RestauranteProdutoControllerOpenApi;
import br.com.reignited.yumfood.core.security.CheckSecurity;
import br.com.reignited.yumfood.domain.model.Produto;
import br.com.reignited.yumfood.domain.model.Restaurante;
import br.com.reignited.yumfood.domain.service.ProdutoService;
import br.com.reignited.yumfood.domain.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/restaurantes/{restauranteId}/produtos")
public class RestauranteProdutoController implements RestauranteProdutoControllerOpenApi {

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ProdutoModelAssembler produtoModelAssembler;

    @Autowired
    private ProdutoInputDisassembler produtoInputDisassembler;

    @Autowired
    private YumLinks yumLinks;

    @CheckSecurity.Restaurantes.PodeConsultar
    @GetMapping
    public CollectionModel<ProdutoModel> listar(
            @PathVariable Long restauranteId,
            @RequestParam(name = "incluir-inativos", defaultValue = "false", required = false) Boolean incluirInativos) {

        Restaurante restaurante = restauranteService.buscar(restauranteId);
        List<Produto> produtos;

        if (incluirInativos) {
            produtos = produtoService.buscarProdutosPorRestaurante(restaurante);
        } else {
            produtos = produtoService.buscarAtivosPorRestaurante(restaurante);
        }

        return produtoModelAssembler.toCollectionModel(produtos).add(yumLinks.linkToProdutos(restauranteId));
    }

    @CheckSecurity.Restaurantes.PodeConsultar
    @GetMapping("/{produtoId}")
    public ProdutoModel buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
        Produto produto = produtoService.buscar(restauranteId, produtoId);
        return produtoModelAssembler.toModel(produto);
    }

    @CheckSecurity.Restaurantes.PodeEditar
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoModel adicionar(@PathVariable Long restauranteId, @RequestBody @Valid ProdutoInput produtoInput) {
        Restaurante restaurante = restauranteService.buscar(restauranteId);

        Produto produto = produtoInputDisassembler.toDomainModel(produtoInput);
        produto.setRestaurante(restaurante);
        produto = produtoService.salvar(produto);

        return produtoModelAssembler.toModel(produto);
    }

    @CheckSecurity.Restaurantes.PodeEditar
    @PutMapping("/{produtoId}")
    public ProdutoModel atualizar(
            @PathVariable Long restauranteId,
            @PathVariable Long produtoId,
            @RequestBody @Valid ProdutoInput produtoInput) {

        Produto produtoAtual = produtoService.buscar(restauranteId, produtoId);
        produtoInputDisassembler.copyToDomainObject(produtoInput, produtoAtual);

        produtoService.salvar(produtoAtual);
        return produtoModelAssembler.toModel(produtoAtual);
    }
}
