package br.com.reignited.yumfood.api.controller;

import br.com.reignited.yumfood.api.assembler.GrupoInputDisassembler;
import br.com.reignited.yumfood.api.assembler.GrupoModelAssembler;
import br.com.reignited.yumfood.api.model.GrupoModel;
import br.com.reignited.yumfood.api.model.input.GrupoInput;
import br.com.reignited.yumfood.domain.model.Grupo;
import br.com.reignited.yumfood.domain.service.GrupoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

    @Autowired
    private GrupoService grupoService;

    @Autowired
    private GrupoModelAssembler grupoModelAssembler;

    @Autowired
    private GrupoInputDisassembler grupoInputDisassembler;

    @GetMapping
    public List<GrupoModel> listar() {
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
        Grupo grupo = grupoInputDisassembler.toDomainObject(grupoInput);
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
