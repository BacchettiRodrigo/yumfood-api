package br.com.reignited.yumfood.api.controller;

import br.com.reignited.yumfood.api.assembler.EstadoInputDisassembler;
import br.com.reignited.yumfood.api.assembler.EstadoModelAssembler;
import br.com.reignited.yumfood.api.model.EstadoModel;
import br.com.reignited.yumfood.api.model.input.EstadoInput;
import br.com.reignited.yumfood.domain.exception.EntidadeEmUsoException;
import br.com.reignited.yumfood.domain.exception.EntidadeNaoEncontradaException;
import br.com.reignited.yumfood.domain.model.Estado;
import br.com.reignited.yumfood.domain.service.EstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoService estadoService;

    @Autowired
    private EstadoModelAssembler estadoModelAssembler;

    @Autowired
    private EstadoInputDisassembler estadoInputDisassembler;

    @GetMapping
    public List<EstadoModel> listar() {
        return estadoModelAssembler.toCollectionModel(estadoService.listar());
    }

    @GetMapping("/{estadoId}")
    public EstadoModel buscar(@PathVariable Long estadoId) {
        return estadoModelAssembler.toModel(estadoService.buscar(estadoId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstadoModel adicionar(@Valid @RequestBody EstadoInput estadoInput) {
        Estado estado = estadoInputDisassembler.toDomainObject(estadoInput);
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
