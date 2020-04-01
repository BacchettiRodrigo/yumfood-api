package br.com.reignited.yumfood.api.v2.controller;

import br.com.reignited.yumfood.api.v2.assembler.CozinhaModelAssemblerV2;
import br.com.reignited.yumfood.api.v2.disassembler.CozinhaInputDisassemblerV2;
import br.com.reignited.yumfood.api.v2.model.CozinhaModelV2;
import br.com.reignited.yumfood.api.v2.model.input.CozinhaInputV2;
import br.com.reignited.yumfood.api.v2.openapi.controller.CozinhaControllerV2OpenApi;
import br.com.reignited.yumfood.domain.model.Cozinha;
import br.com.reignited.yumfood.domain.service.CozinhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/v2/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaControllerV2 implements CozinhaControllerV2OpenApi {

    @Autowired
    private CozinhaService cozinhaService;

    @Autowired
    private CozinhaModelAssemblerV2 cozinhaModelAssembler;

    @Autowired
    private CozinhaInputDisassemblerV2 cozinhaInputDisassembler;

    @Autowired
    private PagedResourcesAssembler<Cozinha> pagedResourcesAssembler;

    @GetMapping
    public PagedModel<CozinhaModelV2> listar(@PageableDefault(size = 2) Pageable pageable) {
        Page<Cozinha> cozinhaPage = cozinhaService.listar(pageable);
        return pagedResourcesAssembler.toModel(cozinhaPage, cozinhaModelAssembler);
    }

    @GetMapping("/{cozinhaId}")
    public CozinhaModelV2 buscar(@PathVariable Long cozinhaId) {
        return cozinhaModelAssembler.toModel(cozinhaService.buscar(cozinhaId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CozinhaModelV2 adicionar(@Valid @RequestBody CozinhaInputV2 cozinhaInput) {
        Cozinha cozinha = cozinhaInputDisassembler.toDomainModel(cozinhaInput);
        return cozinhaModelAssembler.toModel(cozinhaService.salvar(cozinha));
    }

    @PutMapping("/{cozinhaId}")
    public CozinhaModelV2 atualizar(@PathVariable Long cozinhaId, @Valid @RequestBody CozinhaInputV2 cozinha) {
        Cozinha cozinhaAtual = cozinhaService.buscar(cozinhaId);
        cozinhaInputDisassembler.copyToDomainObject(cozinha, cozinhaAtual);
        return cozinhaModelAssembler.toModel(cozinhaService.salvar(cozinhaAtual));
    }

    @DeleteMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cozinhaId) {
        cozinhaService.excluir(cozinhaId);
    }
}
