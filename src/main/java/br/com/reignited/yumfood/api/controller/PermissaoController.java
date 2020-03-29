package br.com.reignited.yumfood.api.controller;

import br.com.reignited.yumfood.api.assembler.PermissaoModelAssembler;
import br.com.reignited.yumfood.api.model.PermissaoModel;
import br.com.reignited.yumfood.api.openapi.controller.PermissaoControllerOpenApi;
import br.com.reignited.yumfood.domain.service.PermissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/permissoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class PermissaoController implements PermissaoControllerOpenApi {

    @Autowired
    private PermissaoService permissaoService;

    @Autowired
    private PermissaoModelAssembler permissaoModelAssembler;

    @GetMapping
    public CollectionModel<PermissaoModel> listar() {
        return permissaoModelAssembler.toCollectionModel(permissaoService.listar());
    }
}
