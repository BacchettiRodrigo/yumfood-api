package br.com.reignited.yumfood.api.openapi.controller;

import br.com.reignited.yumfood.api.exceptionhandler.Problem;
import br.com.reignited.yumfood.api.model.EstadoModel;
import br.com.reignited.yumfood.api.model.input.EstadoInput;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;

import java.util.List;

@Api(tags = "Estados")
public interface EstadoControllerOpenApi {

    @ApiOperation("Listar estados")
    CollectionModel<EstadoModel> listar();

    @ApiOperation("Buscar estado por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID do estado inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Estado não encontrado", response = Problem.class)
    })
    EstadoModel buscar(@ApiParam(value = "ID de um estado", example = "1", required = true) Long estadoId);

    @ApiOperation("Cadastrar um estado")
    @ApiResponses({@ApiResponse(code = 201, message = "Estado cadastrado")})
    EstadoModel adicionar(@ApiParam(name = "corpo", value = "Representação de um novo estado") EstadoInput estadoInput);

    @ApiOperation("Atualizar um estado por ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Estado atualizado"),
            @ApiResponse(code = 404, message = "Estado não encontrado", response = Problem.class)
    })
    EstadoModel atualizar(@ApiParam(value = "ID de um estado", example = "1", required = true) Long estadoId,
                          @ApiParam(name = "corpo", value = "Representação de um novo estado") EstadoInput estado);

    @ApiOperation("Excluir um estado por ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "ID do estado inválido"),
            @ApiResponse(code = 404, message = "Estado não encontrado", response = Problem.class)
    })
    void remover(@ApiParam(value = "ID de um estado", example = "1", required = true) Long estadoId);
}
