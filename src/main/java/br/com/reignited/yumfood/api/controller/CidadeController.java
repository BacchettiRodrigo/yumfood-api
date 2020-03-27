package br.com.reignited.yumfood.api.controller;

import br.com.reignited.yumfood.api.ResourceUriHelper;
import br.com.reignited.yumfood.api.assembler.CidadeModelAssembler;
import br.com.reignited.yumfood.api.disassembler.CidadeInputDisassembler;
import br.com.reignited.yumfood.api.model.CidadeModel;
import br.com.reignited.yumfood.api.model.input.CidadeInput;
import br.com.reignited.yumfood.api.openapi.controller.CidadeControllerOpenApi;
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
@RequestMapping(path = "/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeController implements CidadeControllerOpenApi {

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private CidadeModelAssembler cidadeModelAssembler;

    @Autowired
    private CidadeInputDisassembler cidadeInputDisassembler;

    @GetMapping
    public CollectionModel<CidadeModel> listar() {
        List<Cidade> cidades = cidadeService.listar();
        return cidadeModelAssembler.toCollectionModel(cidades);
    }

    @GetMapping("/{cidadeId}")
    public CidadeModel buscar(@PathVariable Long cidadeId) {
        Cidade cidade = cidadeService.buscar(cidadeId);
        return cidadeModelAssembler.toModel(cidade);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeModel adicionar(@Valid @RequestBody CidadeInput cidadeInput) {
        try {
            Cidade cidade = cidadeInputDisassembler.toDomainModel(cidadeInput);
            CidadeModel cidadeModel = cidadeModelAssembler.toModel(cidadeService.salvar(cidade));
            ResourceUriHelper.addUriInResponseHeader(cidadeModel.getId());
            return cidadeModel;
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
