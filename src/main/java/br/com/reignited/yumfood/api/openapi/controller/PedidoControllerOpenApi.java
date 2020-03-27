package br.com.reignited.yumfood.api.openapi.controller;

import br.com.reignited.yumfood.api.exceptionhandler.Problem;
import br.com.reignited.yumfood.api.model.PedidoModel;
import br.com.reignited.yumfood.api.model.PedidoResumoModel;
import br.com.reignited.yumfood.api.model.input.PedidoInput;
import br.com.reignited.yumfood.domain.filter.PedidoFilter;
import io.swagger.annotations.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;

@Api(tags = "Pedidos")
public interface PedidoControllerOpenApi {

    @ApiOperation("Listar pedidos")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "Nomes das propriedades para filtrar a resposta, separados por vírgula",
                    name = "campos", paramType = "query", type = "string")
    })
    PagedModel<PedidoResumoModel> pesquisar(PedidoFilter filtro, @PageableDefault(size = 1) Pageable pageable);

    @ApiOperation("Buscar pedido por código")
    @ApiResponses({@ApiResponse(code = 404, message = "Pedido não encontrado", response = Problem.class)})
    PedidoModel buscar(
            @ApiParam(value = "Código de um pedido", example = "6063650e-5e03-4c74-9ad5-04ec2c6d5f04", required = true) String codigoPedido);

    @ApiOperation("Emitir um pedido")
    @ApiResponses({@ApiResponse(code = 201, message = "Pedido emitido")})
    PedidoModel emissaoPedido(
            @ApiParam(name = "corpo", value = "Representação de um pedido com os novos dados") PedidoInput input);

    @ApiOperation("Confirmar um pedido por código")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Pedido confirmado"),
            @ApiResponse(code = 404, message = "Pedido não encontrado", response = Problem.class)
    })
    ResponseEntity<Void> confirmar(
            @ApiParam(value = "Código de um pedido", example = "6063650e-5e03-4c74-9ad5-04ec2c6d5f04", required = true) String codigoPedido);

    @ApiOperation("Entregar um pedido por código")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Pedido entregue"),
            @ApiResponse(code = 404, message = "Pedido não encontrado", response = Problem.class)
    })
    ResponseEntity<Void> entregar(
            @ApiParam(value = "Código de um pedido", example = "6063650e-5e03-4c74-9ad5-04ec2c6d5f04", required = true) String codigoPedido);

    @ApiOperation("Cancelar um pedido por código")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Pedido cancelado"),
            @ApiResponse(code = 404, message = "Pedido não encontrado", response = Problem.class)
    })
    ResponseEntity<Void> cancelar(
            @ApiParam(value = "Código de um pedido", example = "6063650e-5e03-4c74-9ad5-04ec2c6d5f04", required = true) String codigoPedido);

}
