package br.com.reignited.yumfood.api.v1.controller;

import br.com.reignited.yumfood.api.v1.assembler.PedidoModelAssembler;
import br.com.reignited.yumfood.api.v1.assembler.PedidoResumoModelAssembler;
import br.com.reignited.yumfood.api.v1.disassembler.PedidoInputDisassembler;
import br.com.reignited.yumfood.api.v1.model.PedidoModel;
import br.com.reignited.yumfood.api.v1.model.PedidoResumoModel;
import br.com.reignited.yumfood.api.v1.model.input.PedidoInput;
import br.com.reignited.yumfood.api.v1.openapi.controller.PedidoControllerOpenApi;
import br.com.reignited.yumfood.core.data.PageWrapper;
import br.com.reignited.yumfood.core.data.PageableTranslator;
import br.com.reignited.yumfood.core.security.YumSecurity;
import br.com.reignited.yumfood.domain.exception.EntidadeNaoEncontradaException;
import br.com.reignited.yumfood.domain.exception.NegocioException;
import br.com.reignited.yumfood.domain.model.Pedido;
import br.com.reignited.yumfood.domain.model.Usuario;
import br.com.reignited.yumfood.domain.repository.PedidoRepository;
import br.com.reignited.yumfood.domain.filter.PedidoFilter;
import br.com.reignited.yumfood.domain.service.PedidoService;
import br.com.reignited.yumfood.infrastructure.repository.specs.PedidoSpecs;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/pedidos")
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

    @Autowired
    private PagedResourcesAssembler<Pedido> pagedResourcesAssembler;

    @Autowired
    private YumSecurity yumSecurity;

    @GetMapping
    public PagedModel<PedidoResumoModel> pesquisar(PedidoFilter filtro, @PageableDefault(size = 1) Pageable pageable) {
        Pageable pageableTraduzido = traduzirPageable(pageable);

        Page<Pedido> pedidos = pedidoRepository.findAll(PedidoSpecs.usandoFiltro(filtro), pageableTraduzido);

        pedidos = new PageWrapper<>(pedidos, pageable);

        return pagedResourcesAssembler.toModel(pedidos, pedidoResumoModelAssembler);
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

            Usuario usuario = new Usuario();
            usuario.setId(yumSecurity.getUsuarioId());
            pedido.setCliente(usuario);

            pedidoService.emitirPedido(pedido);

            return pedidoModelAssembler.toModel(pedido);

        } catch (EntidadeNaoEncontradaException ex) {
            throw new NegocioException(ex.getMessage(), ex);
        }
    }

    @PutMapping("/{codigoPedido}/confirmacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> confirmar(@PathVariable String codigoPedido) {
        pedidoService.confirmar(codigoPedido);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{codigoPedido}/entrega")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> entregar(@PathVariable String codigoPedido) {
        pedidoService.entregar(codigoPedido);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{codigoPedido}/cancelamento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> cancelar(@PathVariable String codigoPedido) {
        pedidoService.cancelar(codigoPedido);
        return ResponseEntity.noContent().build();
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
