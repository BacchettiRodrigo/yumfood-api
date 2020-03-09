package br.com.reignited.yumfood.api.openapi.controller;

import br.com.reignited.yumfood.api.exceptionhandler.Problem;
import br.com.reignited.yumfood.api.model.PermissaoModel;
import br.com.reignited.yumfood.domain.model.Grupo;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Grupos")
public interface GrupoPermissaoControllerOpenApi {

    @ApiOperation("Listar permissões de um grupo")
    @ApiResponses({ @ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)})
    List<PermissaoModel> listar(@ApiParam(value = "ID de um grupo", example = "1", required = true) Long grupoId);

    @ApiOperation("Desassociação de permissão com grupo")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Desassociação realizada com sucesso"),
            @ApiResponse(code = 404, message = "Grupo ou permissão não encontrado", response = Problem.class)
    })
    void desassociar(@ApiParam(value = "ID de um grupo", example = "1", required = true) Long grupoId,
                     @ApiParam(value = "ID de uma permissão", example = "1", required = true) Long permissaoId);

    @ApiOperation("Associação de permissão com grupo")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Associação realizada com sucesso"),
            @ApiResponse(code = 404, message = "Grupo ou permissão não encontrado", response = Problem.class)
    })
    void associar(@ApiParam(value = "ID de um grupo", example = "1", required = true) Long grupoId,
                  @ApiParam(value = "ID de uma permissão", example = "1", required = true) Long permissaoId);
}
