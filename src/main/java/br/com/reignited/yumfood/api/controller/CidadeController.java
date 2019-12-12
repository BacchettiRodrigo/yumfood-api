package br.com.reignited.yumfood.api.controller;

import br.com.reignited.yumfood.api.assembler.CidadeInputDisassembler;
import br.com.reignited.yumfood.api.assembler.CidadeModelAssembler;
import br.com.reignited.yumfood.api.model.CidadeModel;
import br.com.reignited.yumfood.api.model.input.CidadeInput;
import br.com.reignited.yumfood.domain.exception.EstadoNaoEncontradoException;
import br.com.reignited.yumfood.domain.exception.NegocioException;
import br.com.reignited.yumfood.domain.model.Cidade;
import br.com.reignited.yumfood.domain.service.CidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private CidadeModelAssembler cidadeModelAssembler;

    @Autowired
    private CidadeInputDisassembler cidadeInputDisassembler;

    @GetMapping
    public List<CidadeModel> listar() {
        return cidadeModelAssembler.toCollectionModel(cidadeService.listar());
    }

    @GetMapping("/{cidadeId}")
    public CidadeModel buscar(@PathVariable Long cidadeId) {
        return cidadeModelAssembler.toModel(cidadeService.buscar(cidadeId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeModel adicionar(@Valid @RequestBody CidadeInput cidadeInput) {
        try {
            Cidade cidade = cidadeInputDisassembler.toDomainObject(cidadeInput);
            return cidadeModelAssembler.toModel(cidadeService.salvar(cidade));
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping("/{cidadeId}")
    public CidadeModel atualizar(@PathVariable Long cidadeId, @Valid @RequestBody CidadeInput cidade) {
        try {
            Cidade cidadeAtual = cidadeService.buscar(cidadeId);

            cidadeInputDisassembler.copyToDomainObject(cidade, cidadeAtual);
            //BeanUtils.copyProperties(cidade, cidadeAtual, "id");

            return cidadeModelAssembler.toModel(cidadeService.salvar(cidadeAtual));
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cidadeId) {
        cidadeService.remover(cidadeId);
    }
}
