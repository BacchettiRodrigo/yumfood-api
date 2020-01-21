package br.com.reignited.yumfood.api.controller;

import br.com.reignited.yumfood.api.assembler.PermissaoModelAssembler;
import br.com.reignited.yumfood.api.model.PermissaoModel;
import br.com.reignited.yumfood.domain.model.Grupo;
import br.com.reignited.yumfood.domain.service.GrupoService;
import br.com.reignited.yumfood.domain.service.PermissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grupos/{grupoId}/permissoes")
public class GrupoPermissaoController {

    @Autowired
    private GrupoService grupoService;

    @Autowired
    private PermissaoModelAssembler permissaoModelAssembler;

    @GetMapping
    public List<PermissaoModel> listar(@PathVariable Long grupoId) {
        Grupo grupo = grupoService.buscar(grupoId);
        return permissaoModelAssembler.toCollectionModel(grupo.getPermissoes());
    }

    @DeleteMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
        grupoService.desassociar(grupoId, permissaoId);
    }

    @PutMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
        grupoService.associar(grupoId, permissaoId);
    }
}
