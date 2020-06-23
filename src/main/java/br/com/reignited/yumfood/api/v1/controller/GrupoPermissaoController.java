package br.com.reignited.yumfood.api.v1.controller;

import br.com.reignited.yumfood.api.v1.YumLinks;
import br.com.reignited.yumfood.api.v1.assembler.PermissaoModelAssembler;
import br.com.reignited.yumfood.api.v1.model.PermissaoModel;
import br.com.reignited.yumfood.api.v1.openapi.controller.GrupoPermissaoControllerOpenApi;
import br.com.reignited.yumfood.core.security.CheckSecurity;
import br.com.reignited.yumfood.core.security.YumSecurity;
import br.com.reignited.yumfood.domain.model.Grupo;
import br.com.reignited.yumfood.domain.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/grupos/{grupoId}/permissoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoPermissaoController implements GrupoPermissaoControllerOpenApi {

    @Autowired
    private GrupoService grupoService;

    @Autowired
    private PermissaoModelAssembler permissaoModelAssembler;

    @Autowired
    private YumLinks yumLinks;

    @Autowired
    private YumSecurity yumSecurity;

    @CheckSecurity.UsuarioGruposPermissoes.PodeConsultar
    @GetMapping
    public CollectionModel<PermissaoModel> listar(@PathVariable Long grupoId) {
        Grupo grupo = grupoService.buscar(grupoId);
        CollectionModel<PermissaoModel> permissaoModels = permissaoModelAssembler
                .toCollectionModel(grupo.getPermissoes()).removeLinks();

        permissaoModels.add(yumLinks.linkToGruposPermissoes(grupoId));

        if (yumSecurity.podeEditarUsuariosGruposPermissoes()) {

            permissaoModels.add(yumLinks.linkToGruposPermissaoAssociar(grupoId, null, "associar"));

            permissaoModels.getContent().forEach(permissaoModel -> {
                permissaoModel.add(
                        yumLinks.linkToGruposPermissaoDesassociar(grupoId, permissaoModel.getId(), "desassociar"));
            });
        }

        return permissaoModels;
    }

    @CheckSecurity.UsuarioGruposPermissoes.PodeEditar
    @DeleteMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> desassociar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
        grupoService.desassociar(grupoId, permissaoId);
        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.UsuarioGruposPermissoes.PodeEditar
    @PutMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
        grupoService.associar(grupoId, permissaoId);
        return ResponseEntity.noContent().build();
    }
}
