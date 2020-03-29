package br.com.reignited.yumfood.api.controller;

import br.com.reignited.yumfood.api.YumLinks;
import br.com.reignited.yumfood.api.assembler.PermissaoModelAssembler;
import br.com.reignited.yumfood.api.model.PermissaoModel;
import br.com.reignited.yumfood.api.openapi.controller.GrupoPermissaoControllerOpenApi;
import br.com.reignited.yumfood.domain.model.Grupo;
import br.com.reignited.yumfood.domain.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/grupos/{grupoId}/permissoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoPermissaoController implements GrupoPermissaoControllerOpenApi {

    @Autowired
    private GrupoService grupoService;

    @Autowired
    private PermissaoModelAssembler permissaoModelAssembler;

    @Autowired
    private YumLinks yumLinks;

    @GetMapping
    public CollectionModel<PermissaoModel> listar(@PathVariable Long grupoId) {
        Grupo grupo = grupoService.buscar(grupoId);
        CollectionModel<PermissaoModel> permissaoModels = permissaoModelAssembler.toCollectionModel(grupo.getPermissoes());

        permissaoModels.getContent().forEach(permissaoModel -> {
            permissaoModel.add(yumLinks.linkToGruposPermissaoDesassociar(grupoId, permissaoModel.getId(), "desassociar"));
        });

        return permissaoModels
                .removeLinks()
                .add(yumLinks.linkToGruposPermissoes(grupoId))
                .add(yumLinks.linkToGruposPermissaoAssociar(grupoId, null, "associar"));
    }

    @DeleteMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> desassociar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
        grupoService.desassociar(grupoId, permissaoId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
        grupoService.associar(grupoId, permissaoId);
        return ResponseEntity.noContent().build();
    }
}
