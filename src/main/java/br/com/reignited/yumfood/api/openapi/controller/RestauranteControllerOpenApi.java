package br.com.reignited.yumfood.api.openapi.controller;

import br.com.reignited.yumfood.api.exceptionhandler.Problem;
import br.com.reignited.yumfood.api.model.RestauranteModel;
import br.com.reignited.yumfood.api.model.input.RestauranteInput;
import br.com.reignited.yumfood.api.openapi.model.RestauranteBasicoModelOpenApi;
import io.swagger.annotations.*;

import java.util.List;

@Api(tags = "Restaurantes")
public interface RestauranteControllerOpenApi {

    @ApiOperation(value = "Lista restaurantes", response = RestauranteBasicoModelOpenApi.class)
    @ApiImplicitParams({@ApiImplicitParam(value = "Nome da projeção de pedidos", allowableValues = "apenas-nome",
                    name = "projecao", paramType = "query", type = "string")})
    @ApiResponses({@ApiResponse(code = 400, message = "Parametros inválidos", response = Problem.class)})
    List<RestauranteModel> listar();

    @ApiOperation(value = "Lista restaurantes", hidden = true)
    List<RestauranteModel> listarApenasNome();

    @ApiOperation(value = "Buscar restaurante por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID do restaurante inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    RestauranteModel buscar(@ApiParam(value = "ID de um restaurante", example = "1", required = true) Long restauranteId);

    @ApiOperation(value = "Cadastrar restaurante")
    @ApiResponses({@ApiResponse(code = 201, message = "Restaurante cadastrado")})
    RestauranteModel adicionar(
            @ApiParam(name = "corpo", value = "Representação de um novo restaurante") RestauranteInput restauranteInput);

    @ApiOperation(value = "Atualizar restaurante por ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Restaurante atualizado"),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    RestauranteModel atualizar(@ApiParam(value = "ID de um restaurante", example = "1", required = true) Long restauranteId,
                               @ApiParam(name = "corpo", value = "Representação de um novo restaurante") RestauranteInput restauranteInput);

    @ApiOperation(value = "Ativar restaurante por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID do restaurante inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    void ativar(@ApiParam(value = "ID de um restaurante", example = "1", required = true) Long restauranteId);

    @ApiOperation(value = "Inativar restaurante por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID do restaurante inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    void inativar(@ApiParam(value = "ID de um restaurante", example = "1", required = true) Long restauranteId);

    @ApiOperation(value = "Abrir restaurante por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID do restaurante inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    void abrir(@ApiParam(value = "ID de um restaurante", example = "1", required = true) Long restauranteId);

    @ApiOperation(value = "Fechar restaurante por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID do restaurante inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    void fechar(@ApiParam(value = "ID de um restaurante", example = "1", required = true) Long restauranteId);

    @ApiOperation(value = "Ativar multiplos restaurantes por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID do restaurante inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    void ativarMultiplos(@ApiParam(name = "corpo", value = "Lista de IDs para ativação", required = true) List<Long> restauranteIds);

    @ApiOperation(value = "Inativar multiplos restaurantes por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID do restaurante inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    void inativarMultiplos(@ApiParam(name = "corpo", value = "Lista de IDs para ativação", required = true) List<Long> restauranteIds);
}
