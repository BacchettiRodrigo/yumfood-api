package br.com.reignited.yumfood.api.v1.controller;

import br.com.reignited.yumfood.api.v1.YumLinks;
import br.com.reignited.yumfood.api.v1.assembler.GrupoModelAssembler;
import br.com.reignited.yumfood.api.v1.model.GrupoModel;
import br.com.reignited.yumfood.api.v1.openapi.controller.UsuarioGrupoControllerOpenApi;
import br.com.reignited.yumfood.domain.model.Usuario;
import br.com.reignited.yumfood.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/usuarios/{usuarioId}/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioGrupoController implements UsuarioGrupoControllerOpenApi {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private GrupoModelAssembler grupoModelAssembler;

    @Autowired
    private YumLinks yumLinks;

    @GetMapping
    public CollectionModel<GrupoModel> listar(@PathVariable Long usuarioId) {
        Usuario usuario = usuarioService.buscar(usuarioId);
        CollectionModel<GrupoModel> grupoModels = grupoModelAssembler.toCollectionModel(usuario.getGrupos());

        grupoModels.getContent().forEach(grupoModel -> {
            grupoModel.add(yumLinks.linkToUsuarioGrupoDesassociar(usuarioId, grupoModel.getId(), "desassociar"));
        });

        return grupoModels
                .removeLinks()
                .add(yumLinks.linkToUsuarioGrupos(usuarioId))
                .add(yumLinks.linkToUsuarioGrupoAssociar(usuarioId, null, "associar"));
    }

    @PutMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associarGrupo(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        usuarioService.associarGrupo(usuarioId, grupoId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> desassociarGrupo(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        usuarioService.desassociarGrupo(usuarioId, grupoId);
        return ResponseEntity.noContent().build();
    }
}
