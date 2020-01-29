package br.com.reignited.yumfood.domain.service;

import br.com.reignited.yumfood.domain.exception.NegocioException;
import br.com.reignited.yumfood.domain.exception.PedidoNaoEncontradoException;
import br.com.reignited.yumfood.domain.model.*;
import br.com.reignited.yumfood.domain.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private FormaPagamentoService formaPagamentoService;

    @Autowired
    private ProdutoService produtoService;

    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    public Pedido buscar(String codigo) {
        return pedidoRepository.findByCodigo(codigo)
                .orElseThrow(() -> new PedidoNaoEncontradoException(codigo));
    }

    @Transactional
    public Pedido emitirPedido(Pedido pedido) {
        validarPedido(pedido);
        validarItens(pedido);

        pedido.setTaxaFrete(pedido.getRestaurante().getTaxaFrete());
        pedido.calcularValorTotal();

        return pedidoRepository.save(pedido);
    }

    @Transactional
    public void confirmar(String codigo) {
        Pedido pedido = buscar(codigo);
        pedido.confirmar();
    }

    @Transactional
    public void entregar(String codigo) {
        Pedido pedido = buscar(codigo);
        pedido.entregar();
    }

    @Transactional
    public void cancelar(String codigo) {
        Pedido pedido = buscar(codigo);
        pedido.cancelar();
    }

    public void validarPedido(Pedido pedido) {
        Cidade cidade = cidadeService.buscar(pedido.getEnderecoEntrega().getCidade().getId());
        Restaurante restaurante = restauranteService.buscar(pedido.getRestaurante().getId());
        FormaPagamento formaPagamento = formaPagamentoService.buscar(pedido.getFormaPagamento().getId());
        Usuario cliente = usuarioService.buscar(pedido.getCliente().getId());

        pedido.getEnderecoEntrega().setCidade(cidade);
        pedido.setCliente(cliente);
        pedido.setRestaurante(restaurante);
        pedido.setFormaPagamento(formaPagamento);

        if (!restaurante.aceitaFormaPagamento(formaPagamento)) {
            throw new NegocioException(
                    String.format("A forma de pagamento '%s' não é aceita por esse restaurante.",
                            formaPagamento.getDescricao()));
        }

    }

    public void validarItens(Pedido pedido) {
        pedido.getItens().forEach(item -> {
            Produto produto = produtoService.buscar(
                    pedido.getRestaurante().getId(), item.getProduto().getId());

            item.setPedido(pedido);
            item.setProduto(produto);
            item.setPrecoUnitario(produto.getPreco());
        });
    }
}
