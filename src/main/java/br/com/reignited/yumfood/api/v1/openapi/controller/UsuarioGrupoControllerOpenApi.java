package br.com.reignited.yumfood.api.v1.openapi.controller;

import br.com.reignited.yumfood.api.exceptionhandler.Problem;
import br.com.reignited.yumfood.api.v1.model.GrupoModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Usuários")
public interface UsuarioGrupoControllerOpenApi {

    @ApiOperation("Listar grupos do usuário")
    @ApiResponses({@ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)})
    CollectionModel<GrupoModel> listar(@PathVariable Long usuarioId);

    @ApiOperation("Associação de grupo com usuário")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Associação realizada com sucesso"),
            @ApiResponse(code = 404, message = "Usuário ou grupo não encontrado", response = Problem.class)
    })
    ResponseEntity<Void> associarGrupo(@PathVariable Long usuarioId, @PathVariable Long grupoId);

    @ApiOperation("Desassociação de grupo com usuário")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Desassociação realizada com sucesso"),
            @ApiResponse(code = 404, message = "Usuário ou grupo não encontrado", response = Problem.class)
    })
    ResponseEntity<Void> desassociarGrupo(@PathVariable Long usuarioId, @PathVariable Long grupoId);
}
