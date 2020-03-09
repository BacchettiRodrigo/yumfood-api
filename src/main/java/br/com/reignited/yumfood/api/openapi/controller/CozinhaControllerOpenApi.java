package br.com.reignited.yumfood.api.openapi.controller;

import br.com.reignited.yumfood.api.exceptionhandler.Problem;
import br.com.reignited.yumfood.api.model.CozinhaModel;
import br.com.reignited.yumfood.api.model.input.CozinhaInput;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

@Api(tags = "Cozinhas")
public interface CozinhaControllerOpenApi {

    @ApiOperation("Lista as cozinhas")
    Page<CozinhaModel> listar(@PageableDefault(size = 2) Pageable pageable);

    @ApiOperation("Buscar uma cozinha por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID da cozinha inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class)
    })
    CozinhaModel buscar(@ApiParam(value = "ID de uma cozinha", example = "1", required = true) Long cozinhaId);

    @ApiOperation("Cadastrar cozinha")
    @ApiResponses({@ApiResponse(code = 201, message = "Cozinha cadastrada")})
    CozinhaModel adicionar(@ApiParam(name = "corpo", value = "Representação de uma cozinha com os novos dados") CozinhaInput cozinhaInput);

    @ApiOperation("Atualizar uma cozinha por ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cozinha atualizada"),
            @ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class)
    })
    CozinhaModel atualizar(@ApiParam(value = "ID de uma cozinha", example = "1", required = true) Long cozinhaId,
                           @ApiParam(name = "corpo", value = "Representação de uma cozinha com os novos dados") CozinhaInput cozinha);

    @ApiOperation("Excluir uma cozinha por ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Cozinha excluída"),
            @ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class)
    })
    void remover(@ApiParam(value = "ID de uma cozinha", example = "1", required = true) Long cozinhaId);

}
