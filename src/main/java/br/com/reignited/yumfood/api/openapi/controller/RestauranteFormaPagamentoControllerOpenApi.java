package br.com.reignited.yumfood.api.openapi.controller;

import br.com.reignited.yumfood.api.exceptionhandler.Problem;
import br.com.reignited.yumfood.api.model.FormaPagamentoModel;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Api(tags = "Restaurantes")
public interface RestauranteFormaPagamentoControllerOpenApi {

    @ApiOperation("Listar formas de pagamento do restaurante")
    @ApiResponses({@ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)})
    List<FormaPagamentoModel> listar(
            @ApiParam(value = "ID de um restaurante", example = "1", required = true) Long restauranteId);

    @ApiOperation("Desassociação de restaurante com forma de pagamento")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Desassociação realizada com sucesso"),
            @ApiResponse(code = 404, message = "Restaurante ou forma de pagamento não encontrado", response = Problem.class)
    })
    void desassociar(@ApiParam(value = "ID de um restaurante", example = "1", required = true) Long restauranteId,
                     @ApiParam(value = "ID de uma forma de pagamento", example = "1", required = true) Long formaPagamentoId);

    @ApiOperation("Associação de restaurante com forma de pagamento")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Associação realizada com sucesso"),
            @ApiResponse(code = 404, message = "Restaurante ou forma de pagamento não encontrado", response = Problem.class)
    })
    void associar(@ApiParam(value = "ID de um restaurante", example = "1", required = true) Long restauranteId,
                  @ApiParam(value = "ID de uma forma de pagamento", example = "1", required = true) Long formaPagamentoId);
}
