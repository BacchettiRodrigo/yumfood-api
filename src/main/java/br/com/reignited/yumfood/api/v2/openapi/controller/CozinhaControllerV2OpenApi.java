package br.com.reignited.yumfood.api.v2.openapi.controller;

import br.com.reignited.yumfood.api.exceptionhandler.Problem;
import br.com.reignited.yumfood.api.v2.model.CozinhaModelV2;
import br.com.reignited.yumfood.api.v2.model.input.CozinhaInputV2;
import io.swagger.annotations.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.PagedModel;

@Api(tags = "Cozinhas")
public interface CozinhaControllerV2OpenApi {

    @ApiOperation("Lista as cozinhas")
    PagedModel<CozinhaModelV2> listar(@PageableDefault(size = 2) Pageable pageable);

    @ApiOperation("Buscar uma cozinha por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID da cozinha inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class)
    })
    CozinhaModelV2 buscar(@ApiParam(value = "ID de uma cozinha", example = "1", required = true) Long cozinhaId);

    @ApiOperation("Cadastrar cozinha")
    @ApiResponses({@ApiResponse(code = 201, message = "Cozinha cadastrada")})
    CozinhaModelV2 adicionar(@ApiParam(name = "corpo", value = "Representação de uma cozinha com os novos dados") CozinhaInputV2 cozinhaInput);

    @ApiOperation("Atualizar uma cozinha por ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cozinha atualizada"),
            @ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class)
    })
    CozinhaModelV2 atualizar(@ApiParam(value = "ID de uma cozinha", example = "1", required = true) Long cozinhaId,
                             @ApiParam(name = "corpo", value = "Representação de uma cozinha com os novos dados") CozinhaInputV2 cozinha);

    @ApiOperation("Excluir uma cozinha por ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Cozinha excluída"),
            @ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class)
    })
    void remover(@ApiParam(value = "ID de uma cozinha", example = "1", required = true) Long cozinhaId);
}
