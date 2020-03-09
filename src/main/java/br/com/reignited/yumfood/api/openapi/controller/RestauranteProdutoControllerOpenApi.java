package br.com.reignited.yumfood.api.openapi.controller;

import br.com.reignited.yumfood.api.exceptionhandler.Problem;
import br.com.reignited.yumfood.api.model.ProdutoModel;
import br.com.reignited.yumfood.api.model.input.ProdutoInput;
import io.swagger.annotations.*;

import java.util.List;

@Api(tags = "Produtos")
public interface RestauranteProdutoControllerOpenApi {

    @ApiOperation("Listar produtos de um restaurante")
    @ApiResponses({@ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)})
    List<ProdutoModel> listar(
            @ApiParam(value = "ID de um restaurante", example = "1", required = true) Long restauranteId,
            @ApiParam(value = "Indica se deve ou não incluir produtos inativos no resultado da listagem",
                    example = "false", defaultValue = "false") boolean incluirInativos);

    @ApiOperation("Buscar um produto de um restaurante por ID")
    @ApiResponses({@ApiResponse(code = 404, message = "Restaurante ou produto não encontrado", response = Problem.class)})
    ProdutoModel buscar(@ApiParam(value = "ID de um restaurante", example = "1", required = true) Long restauranteId,
                        @ApiParam(value = "ID de um produto", example = "1", required = true) Long produtoId);

    @ApiOperation("Cadastrar um produto em um restaurante")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Produto cadastrado"),
            @ApiResponse(code = 404, message = "Restaurante ou produto não encontrado", response = Problem.class)
    })
    ProdutoModel adicionar(@ApiParam(value = "ID de um restaurante", example = "1", required = true) Long restauranteId,
                           @ApiParam(name = "corpo", value = "Representação de um produto com os novos dados") ProdutoInput produtoInput);

    @ApiOperation("Atualizar um produto de um restaurante por ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Produto atualizado"),
            @ApiResponse(code = 404, message = "Restaurante ou produto não encontrado", response = Problem.class)
    })
    ProdutoModel atualizar(
            @ApiParam(value = "ID de um restaurante", example = "1", required = true) Long restauranteId,
            @ApiParam(value = "ID de um produto", example = "1", required = true) Long produtoId,
            @ApiParam(name = "corpo", value = "Representação de um produto com os novos dados") ProdutoInput produtoInput);
}
