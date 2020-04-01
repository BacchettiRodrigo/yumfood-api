package br.com.reignited.yumfood.api.v1.controller;

import br.com.reignited.yumfood.api.v1.assembler.EstadoModelAssembler;
import br.com.reignited.yumfood.api.v1.disassembler.EstadoInputDisassembler;
import br.com.reignited.yumfood.api.v1.model.EstadoModel;
import br.com.reignited.yumfood.api.v1.model.input.EstadoInput;
import br.com.reignited.yumfood.api.v1.openapi.controller.EstadoControllerOpenApi;
import br.com.reignited.yumfood.domain.model.Estado;
import br.com.reignited.yumfood.domain.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/estados")
public class EstadoController implements EstadoControllerOpenApi {

    @Autowired
    private EstadoService estadoService;

    @Autowired
    private EstadoModelAssembler estadoModelAssembler;

    @Autowired
    private EstadoInputDisassembler estadoInputDisassembler;

    @GetMapping
    public CollectionModel<EstadoModel> listar() {
        return estadoModelAssembler.toCollectionModel(estadoService.listar());
    }

    @GetMapping("/{estadoId}")
    public EstadoModel buscar(@PathVariable Long estadoId) {
        return estadoModelAssembler.toModel(estadoService.buscar(estadoId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstadoModel adicionar(@Valid @RequestBody EstadoInput estadoInput) {
        Estado estado = estadoInputDisassembler.toDomainModel(estadoInput);
        return estadoModelAssembler.toModel(estadoService.salvar(estado));
    }

    @PutMapping("/{estadoId}")
    public EstadoModel atualizar(@PathVariable Long estadoId, @Valid @RequestBody EstadoInput estado) {
        Estado estadoAtual = estadoService.buscar(estadoId);

        estadoInputDisassembler.copyToDomainObject(estado, estadoAtual);

        //BeanUtils.copyProperties(estado, estadoAtual, "id");
        return estadoModelAssembler.toModel(estadoService.salvar(estadoAtual));
    }

    @DeleteMapping("/{estadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long estadoId) {
        estadoService.remover(estadoId);
    }
}