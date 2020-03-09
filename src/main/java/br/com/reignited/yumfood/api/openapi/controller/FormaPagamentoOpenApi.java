package br.com.reignited.yumfood.api.openapi.controller;

import br.com.reignited.yumfood.api.exceptionhandler.Problem;
import br.com.reignited.yumfood.api.model.FormaPagamentoModel;
import br.com.reignited.yumfood.api.model.input.FormaPagamentoInput;
import br.com.reignited.yumfood.domain.model.FormaPagamento;
import io.swagger.annotations.*;
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

@Api(tags = "Formas de Pagamento")
public interface FormaPagamentoOpenApi {

    @ApiOperation("Listar formas de pagamento")
    ResponseEntity<List<FormaPagamentoModel>> listar(ServletWebRequest request);

    @ApiOperation("Buscar forma de pagamento por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID da forma de pagamento inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Forma de pagamento não encontrada", response = Problem.class)
    })
    ResponseEntity<FormaPagamentoModel> buscar(
            @ApiParam(value = "ID de uma forma de pagamento", example = "1", required = true) Long formaPagamentoId,
            ServletWebRequest request);

    @ApiOperation("Cadastrar forma de pagamento")
    @ApiResponses({@ApiResponse(code = 201, message = "Forma de pagamento cadastrada")})
    FormaPagamentoModel adicionar(
            @ApiParam(name = "corpo", value = "Representação de uma forma de pagamento com os novos dados") FormaPagamentoInput input);

    @ApiOperation("Atualizar uma forma de pagamento por ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Forma de pagamento atualizada"),
            @ApiResponse(code = 404, message = "Forma de pagamento não encontrada", response = Problem.class)
    })
    FormaPagamentoModel atualizar(
            @ApiParam(value = "ID de uma forma de pagamento", example = "1", required = true) Long formaPagamentoId,
            @ApiParam(name = "corpo", value = "Representação de uma forma de pagamento com os novos dados") FormaPagamentoInput input);

    @ApiOperation("Excluir forma de pagamento")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Forma de pagamento excluída"),
            @ApiResponse(code = 404, message = "Forma de pagamento não encontrada", response = Problem.class)
    })
    void deletar(@ApiParam(value = "ID de uma forma de pagamento", example = "1", required = true)  Long formaPagamentoId);
}
