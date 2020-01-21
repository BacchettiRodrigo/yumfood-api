package br.com.reignited.yumfood.api.controller;

import br.com.reignited.yumfood.api.disassembler.CozinhaInputDisassembler;
import br.com.reignited.yumfood.api.assembler.CozinhaModelAssembler;
import br.com.reignited.yumfood.api.model.CozinhaModel;
import br.com.reignited.yumfood.api.model.input.CozinhaInput;
import br.com.reignited.yumfood.domain.model.Cozinha;
import br.com.reignited.yumfood.domain.service.CozinhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaService cozinhaService;

    @Autowired
    private CozinhaModelAssembler assembler;

    @Autowired
    private CozinhaInputDisassembler cozinhaInputDisassembler;

    @GetMapping
    public List<CozinhaModel> listar() {
        return assembler.toCollectionModel(cozinhaService.listar());
    }

    @GetMapping("/{cozinhaId}")
    public CozinhaModel buscar(@PathVariable Long cozinhaId) {
        return assembler.toModel(cozinhaService.buscar(cozinhaId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CozinhaModel adicionar(@Valid @RequestBody CozinhaInput cozinhaInput) {
        Cozinha cozinha = cozinhaInputDisassembler.toDomainModel(cozinhaInput);
        return assembler.toModel(cozinhaService.salvar(cozinha));
    }

    @PutMapping("/{cozinhaId}")
    public CozinhaModel atualizar(@PathVariable Long cozinhaId, @Valid @RequestBody CozinhaInput cozinha) {
        Cozinha cozinhaAtual = cozinhaService.buscar(cozinhaId);

        cozinhaInputDisassembler.copyToDomainObject(cozinha, cozinhaAtual);

        //BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");

        return assembler.toModel(cozinhaService.salvar(cozinhaAtual));
    }

    @DeleteMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cozinhaId) {
        cozinhaService.excluir(cozinhaId);
    }
}
