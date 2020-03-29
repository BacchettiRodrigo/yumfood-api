package br.com.reignited.yumfood.api.openapi.controller;

import br.com.reignited.yumfood.api.model.PermissaoModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.hateoas.CollectionModel;

@Api(tags = "Permissoes")
public interface PermissaoControllerOpenApi {

    @ApiOperation("Lista as permissoes")
    CollectionModel<PermissaoModel> listar();

}
