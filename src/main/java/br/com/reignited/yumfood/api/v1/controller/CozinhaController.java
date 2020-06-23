package br.com.reignited.yumfood.api.v1.controller;

import br.com.reignited.yumfood.api.v1.disassembler.CozinhaInputDisassembler;
import br.com.reignited.yumfood.api.v1.assembler.CozinhaModelAssembler;
import br.com.reignited.yumfood.api.v1.model.CozinhaModel;
import br.com.reignited.yumfood.api.v1.model.input.CozinhaInput;
import br.com.reignited.yumfood.api.v1.openapi.controller.CozinhaControllerOpenApi;
import br.com.reignited.yumfood.core.security.CheckSecurity;
import br.com.reignited.yumfood.domain.model.Cozinha;
import br.com.reignited.yumfood.domain.service.CozinhaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @CheckSecurity.Cozinhas.PodeConsultar
    @GetMapping
    public PagedModel<CozinhaModel> listar(@PageableDefault(size = 2) Pageable pageable) {
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        Page<Cozinha> cozinhaPage = cozinhaService.listar(pageable);
        return pagedResourcesAssembler.toModel(cozinhaPage, cozinhaModelAssembler);
    }

    @CheckSecurity.Cozinhas.PodeConsultar
    @GetMapping("/{cozinhaId}")
    public CozinhaModel buscar(@PathVariable Long cozinhaId) {
        return cozinhaModelAssembler.toModel(cozinhaService.buscar(cozinhaId));
    }

    @CheckSecurity.Cozinhas.PodeEditar
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CozinhaModel adicionar(@Valid @RequestBody CozinhaInput cozinhaInput) {
        Cozinha cozinha = cozinhaInputDisassembler.toDomainModel(cozinhaInput);
        return cozinhaModelAssembler.toModel(cozinhaService.salvar(cozinha));
    }

    @CheckSecurity.Cozinhas.PodeEditar
    @PutMapping("/{cozinhaId}")
    public CozinhaModel atualizar(@PathVariable Long cozinhaId, @Valid @RequestBody CozinhaInput cozinha) {
        Cozinha cozinhaAtual = cozinhaService.buscar(cozinhaId);

        cozinhaInputDisassembler.copyToDomainObject(cozinha, cozinhaAtual);

        return cozinhaModelAssembler.toModel(cozinhaService.salvar(cozinhaAtual));
    }

    @CheckSecurity.Cozinhas.PodeEditar
    @DeleteMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cozinhaId) {
        cozinhaService.excluir(cozinhaId);
    }
}
