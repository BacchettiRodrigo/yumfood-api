package br.com.reignited.yumfood.api.v1.openapi.controller;

import br.com.reignited.yumfood.api.exceptionhandler.Problem;
import br.com.reignited.yumfood.api.v1.model.PermissaoModel;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@Api(tags = "Grupos")
public interface GrupoPermissaoControllerOpenApi {

    @ApiOperation("Listar permissões de um grupo")
    @ApiResponses({ @ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)})
    CollectionModel<PermissaoModel> listar(@ApiParam(value = "ID de um grupo", example = "1", required = true) Long grupoId);

    @ApiOperation("Desassociação de permissão com grupo")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Desassociação realizada com sucesso"),
            @ApiResponse(code = 404, message = "Grupo ou permissão não encontrado", response = Problem.class)
    })
    ResponseEntity<Void> desassociar(@ApiParam(value = "ID de um grupo", example = "1", required = true) Long grupoId,
                     @ApiParam(value = "ID de uma permissão", example = "1", required = true) Long permissaoId);

    @ApiOperation("Associação de permissão com grupo")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Associação realizada com sucesso"),
            @ApiResponse(code = 404, message = "Grupo ou permissão não encontrado", response = Problem.class)
    })
    ResponseEntity<Void> associar(@ApiParam(value = "ID de um grupo", example = "1", required = true) Long grupoId,
                  @ApiParam(value = "ID de uma permissão", example = "1", required = true) Long permissaoId);
}
