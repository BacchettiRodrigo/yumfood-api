package br.com.reignited.yumfood.api.v1.controller;

import br.com.reignited.yumfood.api.v1.disassembler.FormaPagamentoInputDisassembler;
import br.com.reignited.yumfood.api.v1.assembler.FormaPagamentoModelAssembler;
import br.com.reignited.yumfood.api.v1.model.FormaPagamentoModel;
import br.com.reignited.yumfood.api.v1.model.input.FormaPagamentoInput;
import br.com.reignited.yumfood.api.v1.openapi.controller.FormaPagamentoOpenApi;
import br.com.reignited.yumfood.core.security.CheckSecurity;
import br.com.reignited.yumfood.domain.model.FormaPagamento;
import br.com.reignited.yumfood.domain.service.FormaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/v1/formas-pagamento")
public class FormaPagamentoController implements FormaPagamentoOpenApi {

    @Autowired
    private FormaPagamentoService formaPagamentoService;

    @Autowired
    private FormaPagamentoModelAssembler assembler;

    @Autowired
    private FormaPagamentoInputDisassembler disassembler;

    @CheckSecurity.FormaPagamento.PodeConsultar
    @GetMapping
    public ResponseEntity<CollectionModel<FormaPagamentoModel>> listar(ServletWebRequest request) {

        ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());

        String eTag = "0";

        OffsetDateTime dataUltimaAtualizacao = formaPagamentoService.dataUltimaAtualizacao();

        if (dataUltimaAtualizacao != null) {
            eTag = String.valueOf(dataUltimaAtualizacao.toEpochSecond());
        }

        if (request.checkNotModified(eTag)) {
            return null;
        }

        List<FormaPagamento> formaPagamentos = formaPagamentoService.listar();

        CollectionModel<FormaPagamentoModel> pagamentoModelList = assembler.toCollectionModel(formaPagamentos);

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePublic())
                .eTag(eTag)
                .body(pagamentoModelList);
    }

    @CheckSecurity.FormaPagamento.PodeConsultar
    @GetMapping("/{formaPagamentoId}")
    public ResponseEntity<FormaPagamentoModel> buscar(@PathVariable Long formaPagamentoId, ServletWebRequest request) {
        ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());

        String eTag = "0";

        OffsetDateTime dataUltimaAtualizacao = formaPagamentoService.dataUltimaAtualizacaoById(formaPagamentoId);

        if (dataUltimaAtualizacao != null) {
            eTag = String.valueOf(dataUltimaAtualizacao.toEpochSecond());
        }

        if (request.checkNotModified(eTag)) {
            return null;
        }

        FormaPagamento formaPagamento = formaPagamentoService.buscar(formaPagamentoId);

        FormaPagamentoModel formaPagamentoModel = assembler.toModel(formaPagamento);

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePublic())
                .eTag(eTag)
                .body(formaPagamentoModel);
    }

    @CheckSecurity.FormaPagamento.PodeEditar
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FormaPagamentoModel adicionar(@Valid @RequestBody FormaPagamentoInput input) {
        FormaPagamento formaPagamento = disassembler.toDomainModel(input);
        return assembler.toModel(formaPagamentoService.salvar(formaPagamento));
    }

    @CheckSecurity.FormaPagamento.PodeEditar
    @PutMapping("{formaPagamentoId}")
    public FormaPagamentoModel atualizar
            (@PathVariable Long formaPagamentoId, @Valid @RequestBody FormaPagamentoInput input) {

        FormaPagamento formaPagamento = formaPagamentoService.buscar(formaPagamentoId);

        disassembler.copyToDomainObject(input, formaPagamento);

        return assembler.toModel(formaPagamentoService.salvar(formaPagamento));
    }

    @CheckSecurity.FormaPagamento.PodeEditar
    @DeleteMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long formaPagamentoId) {
        formaPagamentoService.excluir(formaPagamentoId);
    }
}
