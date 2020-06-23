package br.com.reignited.yumfood.api.v1.openapi.controller;

import br.com.reignited.yumfood.api.exceptionhandler.Problem;
import br.com.reignited.yumfood.api.v1.model.RestauranteApenasNome;
import br.com.reignited.yumfood.api.v1.model.RestauranteBasicoModel;
import br.com.reignited.yumfood.api.v1.model.RestauranteModel;
import br.com.reignited.yumfood.api.v1.model.input.RestauranteInput;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Api(tags = "Restaurantes")
public interface RestauranteControllerOpenApi {

    @ApiOperation(value = "Lista restaurantes")
    @ApiImplicitParams({@ApiImplicitParam(value = "Nome da projeção de pedidos", allowableValues = "apenas-nome",
                    name = "projecao", paramType = "query", type = "string")})
    CollectionModel<RestauranteBasicoModel> listar();

    @ApiIgnore
    @ApiOperation(value = "Lista restaurantes", hidden = true)
    CollectionModel<RestauranteApenasNome> listarApenasNome();

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
    ResponseEntity<Void> ativar(@ApiParam(value = "ID de um restaurante", example = "1", required = true) Long restauranteId);

    @ApiOperation(value = "Inativar restaurante por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID do restaurante inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    ResponseEntity<Void> inativar(@ApiParam(value = "ID de um restaurante", example = "1", required = true) Long restauranteId);

    @ApiOperation(value = "Abrir restaurante por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID do restaurante inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    ResponseEntity<Void> abrir(@ApiParam(value = "ID de um restaurante", example = "1", required = true) Long restauranteId);

    @ApiOperation(value = "Fechar restaurante por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID do restaurante inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    ResponseEntity<Void> fechar(@ApiParam(value = "ID de um restaurante", example = "1", required = true) Long restauranteId);

    @ApiOperation(value = "Ativar multiplos restaurantes por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID do restaurante inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    ResponseEntity<Void> ativarMultiplos(@ApiParam(name = "corpo", value = "Lista de IDs para ativação", required = true) List<Long> restauranteIds);

    @ApiOperation(value = "Inativar multiplos restaurantes por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID do restaurante inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    ResponseEntity<Void> inativarMultiplos(@ApiParam(name = "corpo", value = "Lista de IDs para ativação", required = true) List<Long> restauranteIds);
}
