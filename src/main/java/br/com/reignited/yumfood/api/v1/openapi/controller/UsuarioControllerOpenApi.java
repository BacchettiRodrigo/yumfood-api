package br.com.reignited.yumfood.api.v1.openapi.controller;

import br.com.reignited.yumfood.api.exceptionhandler.Problem;
import br.com.reignited.yumfood.api.v1.model.UsuarioModel;
import br.com.reignited.yumfood.api.v1.model.input.SenhaInput;
import br.com.reignited.yumfood.api.v1.model.input.UsuarioComSenhaInput;
import br.com.reignited.yumfood.api.v1.model.input.UsuarioInput;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;

@Api(tags = "Usuários")
public interface UsuarioControllerOpenApi {

    @ApiOperation("Listar usuários")
    CollectionModel<UsuarioModel> listar();

    @ApiOperation("Busca um usuário por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID do usuário inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
    })
    UsuarioModel buscar(@ApiParam(value = "ID de um usuário", example = "1", required = true) Long usuarioId);

    @ApiOperation("Cadastrar um usuário")
    @ApiResponses({@ApiResponse(code = 201, message = "Usuário cadastrado")})
    UsuarioModel adicionar(@ApiParam(name = "corpo", value = "Representação de uma novo usuário") UsuarioComSenhaInput input);

    @ApiOperation("Atualiza um usuário por ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Usuário atualizado"),
            @ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
    })
    UsuarioModel atualizar(@ApiParam(value = "ID de um usuário", example = "1", required = true) Long usuarioId,
                           @ApiParam(name = "corpo", value = "Representação de um usuário") UsuarioInput input);

    @ApiOperation("Alterar a senha de um usuário por ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Senha alterado com sucesso"),
            @ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
    })
    void alterarSenha(@ApiParam(value = "ID de um usuário", example = "1", required = true) Long usuarioId,
                      @ApiParam(name = "corpo", value = "Representação da antiga e da nova senha") SenhaInput senha);
}
