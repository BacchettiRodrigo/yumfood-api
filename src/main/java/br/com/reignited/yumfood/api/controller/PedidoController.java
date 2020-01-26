package br.com.reignited.yumfood.api.controller;

import br.com.reignited.yumfood.api.assembler.PedidoModelAssembler;
import br.com.reignited.yumfood.api.assembler.PedidoResumoModelAssembler;
import br.com.reignited.yumfood.api.disassembler.PedidoInputDisassembler;
import br.com.reignited.yumfood.api.model.PedidoModel;
import br.com.reignited.yumfood.api.model.PedidoResumoModel;
import br.com.reignited.yumfood.api.model.input.PedidoInput;
import br.com.reignited.yumfood.domain.exception.EntidadeNaoEncontradaException;
import br.com.reignited.yumfood.domain.exception.NegocioException;
import br.com.reignited.yumfood.domain.model.Pedido;
import br.com.reignited.yumfood.domain.model.Usuario;
import br.com.reignited.yumfood.domain.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoModelAssembler pedidoModelAssembler;

    @Autowired
    private PedidoResumoModelAssembler pedidoResumoModelAssembler;

    @Autowired
    private PedidoInputDisassembler pedidoInputDisassembler;

    @GetMapping
    public List<PedidoResumoModel> listar () {
        return pedidoResumoModelAssembler.toCollectionModel(pedidoService.listar());
    }

    @GetMapping("/{pedidoId}")
    public PedidoModel buscar (@PathVariable Long pedidoId) {
        return pedidoModelAssembler.toModel(pedidoService.buscar(pedidoId));
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

    @PutMapping("/{pedidoId}/confirmacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirmar(@PathVariable Long pedidoId) {
        pedidoService.confirmar(pedidoId);
    }

    @PutMapping("/{pedidoId}/entrega")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void entregar(@PathVariable Long pedidoId) {
        pedidoService.confirmar(pedidoId);
    }

    @PutMapping("/{pedidoId}/cancelamento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelar(@PathVariable Long pedidoId) {
        pedidoService.confirmar(pedidoId);
    }
}
