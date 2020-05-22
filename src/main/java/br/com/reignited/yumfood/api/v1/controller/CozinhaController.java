package br.com.reignited.yumfood.api.v1.controller;

import br.com.reignited.yumfood.api.v1.disassembler.CozinhaInputDisassembler;
import br.com.reignited.yumfood.api.v1.assembler.CozinhaModelAssembler;
import br.com.reignited.yumfood.api.v1.model.CozinhaModel;
import br.com.reignited.yumfood.api.v1.model.input.CozinhaInput;
import br.com.reignited.yumfood.api.v1.openapi.controller.CozinhaControllerOpenApi;
import br.com.reignited.yumfood.domain.model.Cozinha;
import br.com.reignited.yumfood.domain.service.CozinhaService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/v1/cozinhas")
public class CozinhaController implements CozinhaControllerOpenApi {

    @Autowired
    private CozinhaService cozinhaService;

    @Autowired
    private CozinhaModelAssembler cozinhaModelAssembler;

    @Autowired
    private CozinhaInputDisassembler cozinhaInputDisassembler;

    @Autowired
    private PagedResourcesAssembler<Cozinha> pagedResourcesAssembler;

    @PreAuthorize(value = "isAuthenticated()")
    @GetMapping
    public PagedModel<CozinhaModel> listar(@PageableDefault(size = 2) Pageable pageable) {
        Page<Cozinha> cozinhaPage = cozinhaService.listar(pageable);
        return pagedResourcesAssembler.toModel(cozinhaPage, cozinhaModelAssembler);
    }

    @PreAuthorize(value = "isAuthenticated()")
    @GetMapping("/{cozinhaId}")
    public CozinhaModel buscar(@PathVariable Long cozinhaId) {
        return cozinhaModelAssembler.toModel(cozinhaService.buscar(cozinhaId));
    }

    @PreAuthorize(value = "hasAuthority('EDITAR_COZINHAS')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CozinhaModel adicionar(@Valid @RequestBody CozinhaInput cozinhaInput) {
        Cozinha cozinha = cozinhaInputDisassembler.toDomainModel(cozinhaInput);
        return cozinhaModelAssembler.toModel(cozinhaService.salvar(cozinha));
    }

    @PreAuthorize(value = "hasAuthority('EDITAR_COZINHAS')")
    @PutMapping("/{cozinhaId}")
    public CozinhaModel atualizar(@PathVariable Long cozinhaId, @Valid @RequestBody CozinhaInput cozinha) {
        Cozinha cozinhaAtual = cozinhaService.buscar(cozinhaId);

        cozinhaInputDisassembler.copyToDomainObject(cozinha, cozinhaAtual);

        //BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");

        return cozinhaModelAssembler.toModel(cozinhaService.salvar(cozinhaAtual));
    }

    @PreAuthorize(value = "hasAuthority('EDITAR_COZINHAS')")
    @DeleteMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cozinhaId) {
        cozinhaService.excluir(cozinhaId);
    }
}
