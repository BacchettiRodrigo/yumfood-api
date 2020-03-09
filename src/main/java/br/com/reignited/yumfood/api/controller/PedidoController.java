package br.com.reignited.yumfood.api.controller;

import br.com.reignited.yumfood.api.assembler.PedidoModelAssembler;
import br.com.reignited.yumfood.api.assembler.PedidoResumoModelAssembler;
import br.com.reignited.yumfood.api.disassembler.PedidoInputDisassembler;
import br.com.reignited.yumfood.api.model.PedidoModel;
import br.com.reignited.yumfood.api.model.PedidoResumoModel;
import br.com.reignited.yumfood.api.model.input.PedidoInput;
import br.com.reignited.yumfood.api.openapi.controller.PedidoControllerOpenApi;
import br.com.reignited.yumfood.core.data.PageableTranslator;
import br.com.reignited.yumfood.domain.exception.EntidadeNaoEncontradaException;
import br.com.reignited.yumfood.domain.exception.NegocioException;
import br.com.reignited.yumfood.domain.model.Pedido;
import br.com.reignited.yumfood.domain.model.Usuario;
import br.com.reignited.yumfood.domain.repository.PedidoRepository;
import br.com.reignited.yumfood.domain.filter.PedidoFilter;
import br.com.reignited.yumfood.domain.service.PedidoService;
import br.com.reignited.yumfood.infrastructure.repository.specs.PedidoSpecs;
import com.google.common.collect.ImmutableMap;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController implements PedidoControllerOpenApi {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoModelAssembler pedidoModelAssembler;

    @Autowired
    private PedidoResumoModelAssembler pedidoResumoModelAssembler;

    @Autowired
    private PedidoInputDisassembler pedidoInputDisassembler;

    @ApiImplicitParams({
            @ApiImplicitParam(value = "Nomes das propriedades para filtrar a resposta, separados por vírgula",
                    name = "campos", paramType = "query", type = "string")
    })
    @GetMapping
    public Page<PedidoResumoModel> pesquisar(PedidoFilter filtro, @PageableDefault(size = 1) Pageable pageable) {
        pageable = traduzirPageable(pageable);
        Page<Pedido> pedidos = pedidoRepository.findAll(PedidoSpecs.usandoFiltro(filtro), pageable);
        List<PedidoResumoModel> pedidoResumo = pedidoResumoModelAssembler.toCollectionModel(pedidos.getContent());
        return new PageImpl<>(pedidoResumo, pageable, pedidos.getTotalElements());
    }

    @GetMapping("/{codigoPedido}")
    public PedidoModel buscar(@PathVariable String codigoPedido) {
        return pedidoModelAssembler.toModel(pedidoService.buscar(codigoPedido));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoModel emissaoPedido(@Valid @RequestBody PedidoInput input) {
        try {
            Pedido pedido = pedidoInputDisassembler.toDomainModel(input);

            //TODO: Capturar o usuário autenticado
            Usuario usuario = new Usuario();
            usuario.setId(1L);

            pedido.setCliente(usuario);

            pedidoService.emitirPedido(pedido);

            return pedidoModelAssembler.toModel(pedido);

        } catch (EntidadeNaoEncontradaException ex) {
            throw new NegocioException(ex.getMessage(), ex);
        }
    }

    @PutMapping("/{codigoPedido}/confirmacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirmar(@PathVariable String codigoPedido) {
        pedidoService.confirmar(codigoPedido);
    }

    @PutMapping("/{codigoPedido}/entrega")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void entregar(@PathVariable String codigoPedido) {
        pedidoService.entregar(codigoPedido);
    }

    @PutMapping("/{codigoPedido}/cancelamento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelar(@PathVariable String codigoPedido) {
        pedidoService.cancelar(codigoPedido);
    }

    private Pageable traduzirPageable(Pageable apiPageable) {
        var mapeamanto = ImmutableMap.of(
                "codigo", "codigo",
                "restaurante.nome", "restaurante.nome",
                "cliente.nome", "cliente.nome",
                "valorTotal", "valorTotal"
        );
        return PageableTranslator.translate(apiPageable, mapeamanto);
    }

}
