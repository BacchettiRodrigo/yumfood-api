package br.com.reignited.yumfood.api.openapi.controller;

import br.com.reignited.yumfood.api.exceptionhandler.Problem;
import br.com.reignited.yumfood.api.model.GrupoModel;
import br.com.reignited.yumfood.api.model.input.GrupoInput;
import io.swagger.annotations.*;

import java.util.List;

@Api(tags = "Grupos")
public interface GrupoControllerOpenApi {

    @ApiOperation("Lista os grupos")
    List<GrupoModel> listar();

    @ApiOperation("Buscar um grupo por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID do grupo inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
    })
    GrupoModel buscar(@ApiParam(value = "ID de um grupo", example = "1", required = true) Long grupoId);

    @ApiOperation("Cadastra um grupo")
    @ApiResponses({@ApiResponse(code = 201, message = "Grupo cadastrado")})
    GrupoModel adicionar(@ApiParam(name = "corpo", value = "Representação de um novo grupo") GrupoInput grupoInput);

    @ApiOperation("Atualiza um grupo por ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Grupo atualizado"),
            @ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
    })
    GrupoModel atualizar(@ApiParam(value = "ID de um grupo", example = "1", required = true) Long grupoId,
                         @ApiParam(name = "corpo", value = "Representação de um novo grupo") GrupoInput grupoInput);

    @ApiOperation("Exclui um grupo por ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Grupo excluído"),
            @ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
    })
    void remover(@ApiParam(value = "ID de um grupo", example = "1", required = true) Long grupoId);

}
