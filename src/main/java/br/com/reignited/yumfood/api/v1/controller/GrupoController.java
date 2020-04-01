package br.com.reignited.yumfood.api.v1.controller;

import br.com.reignited.yumfood.api.v1.openapi.controller.GrupoControllerOpenApi;
import br.com.reignited.yumfood.api.v1.disassembler.GrupoInputDisassembler;
import br.com.reignited.yumfood.api.v1.assembler.GrupoModelAssembler;
import br.com.reignited.yumfood.api.v1.model.GrupoModel;
import br.com.reignited.yumfood.api.v1.model.input.GrupoInput;
import br.com.reignited.yumfood.domain.model.Grupo;
import br.com.reignited.yumfood.domain.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/v1/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoController implements GrupoControllerOpenApi {

    @Autowired
    private GrupoService grupoService;

    @Autowired
    private GrupoModelAssembler grupoModelAssembler;

    @Autowired
    private GrupoInputDisassembler grupoInputDisassembler;

    @GetMapping
    public CollectionModel<GrupoModel> listar() {
        return grupoModelAssembler.toCollectionModel(grupoService.listar());
    }

    @GetMapping("/{grupoId}")
    public GrupoModel buscar(@PathVariable Long grupoId) {
        Grupo grupo = grupoService.buscar(grupoId);
        return grupoModelAssembler.toModel(grupo);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GrupoModel adicionar(@Valid @RequestBody GrupoInput grupoInput) {
        Grupo grupo = grupoInputDisassembler.toDomainModel(grupoInput);
        return grupoModelAssembler.toModel(grupoService.salvar(grupo));
    }

    @PutMapping("/{grupoId}")
    public GrupoModel atualizar(@PathVariable Long grupoId, @Valid @RequestBody GrupoInput grupoInput) {
        Grupo grupoAtual = grupoService.buscar(grupoId);
        //BeanUtils.copyProperties(grupo, grupoAtual, "id");

        grupoInputDisassembler.copyToDomainObject(grupoInput, grupoAtual);

        return grupoModelAssembler.toModel(grupoService.salvar(grupoAtual));
    }

    @DeleteMapping("{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long grupoId) {
        grupoService.exluir(grupoId);
    }

}
