package br.com.reignited.yumfood.api.v2.controller;

import br.com.reignited.yumfood.api.ResourceUriHelper;
import br.com.reignited.yumfood.api.v2.assembler.CidadeModelAssemblerV2;
import br.com.reignited.yumfood.api.v2.disassembler.CidadeInputDisassemblerV2;
import br.com.reignited.yumfood.api.v2.model.CidadeModelV2;
import br.com.reignited.yumfood.api.v2.model.input.CidadeInputV2;
import br.com.reignited.yumfood.api.v2.openapi.controller.CidadeControllerV2OpenApi;
import br.com.reignited.yumfood.domain.exception.EstadoNaoEncontradoException;
import br.com.reignited.yumfood.domain.exception.NegocioException;
import br.com.reignited.yumfood.domain.model.Cidade;
import br.com.reignited.yumfood.domain.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/v2/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeControllerV2 implements CidadeControllerV2OpenApi {

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private CidadeModelAssemblerV2 cidadeModelAssembler;

    @Autowired
    private CidadeInputDisassemblerV2 cidadeInputDisassembler;

    @GetMapping
    public CollectionModel<CidadeModelV2> listar() {
        List<Cidade> cidades = cidadeService.listar();
        return cidadeModelAssembler.toCollectionModel(cidades);
    }

    @GetMapping("/{cidadeId}")
    public CidadeModelV2 buscar(@PathVariable Long cidadeId) {
        Cidade cidade = cidadeService.buscar(cidadeId);
        return cidadeModelAssembler.toModel(cidade);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeModelV2 adicionar(@Valid @RequestBody CidadeInputV2 cidadeInput) {
        try {
            Cidade cidade = cidadeInputDisassembler.toDomainModel(cidadeInput);
            CidadeModelV2 cidadeModel = cidadeModelAssembler.toModel(cidadeService.salvar(cidade));
            ResourceUriHelper.addUriInResponseHeader(cidadeModel.getIdCidade());
            return cidadeModel;
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping("/{cidadeId}")
    public CidadeModelV2 atualizar(@PathVariable Long cidadeId, @Valid @RequestBody CidadeInputV2 cidade) {
        try {
            Cidade cidadeAtual = cidadeService.buscar(cidadeId);
            cidadeInputDisassembler.copyToDomainObject(cidade, cidadeAtual);
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
