package br.com.reignited.yumfood.api.v1.assembler;

import br.com.reignited.yumfood.api.v1.YumLinks;
import br.com.reignited.yumfood.api.v1.controller.*;
import br.com.reignited.yumfood.api.v1.model.PedidoModel;
import br.com.reignited.yumfood.core.security.YumSecurity;
import br.com.reignited.yumfood.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class PedidoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoModel> {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private YumLinks yumLinks;

    @Autowired
    private YumSecurity yumSecurity;

    public PedidoModelAssembler() {
        super(PedidoController.class, PedidoModel.class);
    }

    @Override
    public PedidoModel toModel(Pedido source) {
        PedidoModel pedidoModel = createModelWithId(source.getCodigo(), source);
        mapper.map(source, pedidoModel);

        if (yumSecurity.podeConsultarUsuariosGruposPermissoes()) {
            pedidoModel.getCliente().add(yumLinks.linkToUsuario(pedidoModel.getCliente().getId()));
        }
        if (yumSecurity.podeConsultarFormasPagamento()) {
            pedidoModel.getFormaPagamento().add(yumLinks.linkToFormaPagamento(pedidoModel.getFormaPagamento().getId()));
        }
        if (yumSecurity.podeConsultarCidades()) {
            pedidoModel.getEnderecoEntrega().getCidade()
                    .add(yumLinks.linkToCidade(pedidoModel.getEnderecoEntrega().getCidade().getId()));
        }
        if (yumSecurity.podeConsultarRestaurantes()) {

            pedidoModel.getItens().forEach(item -> {
                item.add(yumLinks.linkToRestauranteProduto(pedidoModel.getRestaurante().getId(), item.getProdutoId()));
            });

            pedidoModel.getRestaurante().add(yumLinks.linkToRestaurante(pedidoModel.getRestaurante().getId()));
        }

        if (yumSecurity.podePesquisarPedidos()) {
            pedidoModel.add(yumLinks.linkToPedidos("pedidos"));
        }

        if (yumSecurity.podeGerenciarPedido(source.getCodigo())) {

            if (source.podeSerConfirmado()) {
                pedidoModel.add(yumLinks.linkToConfirmacaoPedido(pedidoModel.getCodigo(), "confirmar"));
            }

            if (source.podeSerCancelado()) {
                pedidoModel.add(yumLinks.linkToCancelamentoPedido(pedidoModel.getCodigo(), "cancelar"));
            }

            if (source.podeSerEntregue()) {
                pedidoModel.add(yumLinks.linkToEntregaPedido(pedidoModel.getCodigo(), "entregar"));
            }
        }

        return pedidoModel;
    }


}
