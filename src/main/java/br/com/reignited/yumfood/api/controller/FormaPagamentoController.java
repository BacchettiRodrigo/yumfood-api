package br.com.reignited.yumfood.api.controller;

import br.com.reignited.yumfood.api.disassembler.FormaPagamentoInputDisassembler;
import br.com.reignited.yumfood.api.assembler.FormaPagamentoModelAssembler;
import br.com.reignited.yumfood.api.model.FormaPagamentoModel;
import br.com.reignited.yumfood.api.model.input.FormaPagamentoInput;
import br.com.reignited.yumfood.domain.model.FormaPagamento;
import br.com.reignited.yumfood.domain.service.FormaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/formasPagamento")
public class FormaPagamentoController {

    @Autowired
    private FormaPagamentoService service;

    @Autowired
    private FormaPagamentoModelAssembler assembler;

    @Autowired
    private FormaPagamentoInputDisassembler disassembler;

    @GetMapping
    public List<FormaPagamentoModel> listar() {
        return assembler.toCollectionModel(service.listar());
    }

    @GetMapping("/{formaPagamentoId}")
    public FormaPagamentoModel buscar(@PathVariable Long formaPagamentoId) {
        return assembler.toModel(service.buscar(formaPagamentoId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FormaPagamentoModel adicionar(@Valid @RequestBody FormaPagamentoInput input) {
        FormaPagamento formaPagamento = disassembler.toDomainModel(input);
        return assembler.toModel(service.salvar(formaPagamento));
    }

    @PutMapping("{formaPagamentoId}")
    public FormaPagamentoModel atualizar
            (@PathVariable Long formaPagamentoId, @Valid @RequestBody FormaPagamentoInput input) {

        FormaPagamento formaPagamento = service.buscar(formaPagamentoId);

        disassembler.copyToDomainObject(input, formaPagamento);

        return assembler.toModel(service.salvar(formaPagamento));
    }

    @DeleteMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long formaPagamentoId) {
        service.excluir(formaPagamentoId);
    }
}
