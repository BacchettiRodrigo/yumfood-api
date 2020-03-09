package br.com.reignited.yumfood.api.openapi.controller;

import br.com.reignited.yumfood.api.exceptionhandler.Problem;
import br.com.reignited.yumfood.api.model.UsuarioModel;
import io.swagger.annotations.*;

import java.util.List;

@Api(tags = "Restaurantes")
public interface RestauranteUsuarioResponsavelControllerOpenApi {

    @ApiOperation("Listar usuários responsáveis do restaurante")
    @ApiResponses({@ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)})
    List<UsuarioModel> listar(
            @ApiParam(value = "ID de um restaurante", example = "1", required = true) Long restauranteId);

    @ApiOperation("Associação de restaurante com usuário")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Associação realizada com sucesso"),
            @ApiResponse(code = 404, message = "Restaurante ou usuário não encontrado", response = Problem.class)
    })
    void associar(@ApiParam(value = "ID de um restaurante", example = "1", required = true) Long restauranteId,
                  @ApiParam(value = "ID de um usuário", example = "1", required = true) Long usuarioId);

    @ApiOperation("Desassociação de restaurante com usuário")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Desassociação realizada com sucesso"),
            @ApiResponse(code = 404, message = "Restaurante ou usuário não encontrado", response = Problem.class)
    })
    void desassociar(@ApiParam(value = "ID de um restaurante", example = "1", required = true) Long restauranteId,
                     @ApiParam(value = "ID de um usuário", example = "1", required = true) Long usuarioId);
}
