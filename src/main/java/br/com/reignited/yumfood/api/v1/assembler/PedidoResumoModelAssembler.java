package br.com.reignited.yumfood.api.v1.assembler;

import br.com.reignited.yumfood.api.v1.YumLinks;
import br.com.reignited.yumfood.api.v1.controller.PedidoController;
import br.com.reignited.yumfood.api.v1.model.PedidoResumoModel;
import br.com.reignited.yumfood.core.security.YumSecurity;
import br.com.reignited.yumfood.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class PedidoResumoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoResumoModel> {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private YumLinks yumLinks;

    @Autowired
    private YumSecurity yumSecurity;

    public PedidoResumoModelAssembler() {
        super(PedidoController.class, PedidoResumoModel.class);
    }

    @Override
    public PedidoResumoModel toModel(Pedido source) {
        PedidoResumoModel pedidoResumoModel = createModelWithId(source.getCodigo(), source);
        mapper.map(source, pedidoResumoModel);

        if (yumSecurity.podeConsultarUsuariosGruposPermissoes()) {
            pedidoResumoModel.getCliente().add(yumLinks.linkToUsuario(pedidoResumoModel.getCliente().getId()));
        }
        if (yumSecurity.podeConsultarRestaurantes()) {
            pedidoResumoModel.getRestaurante().add(yumLinks.linkToRestaurante(pedidoResumoModel.getRestaurante().getId()));
        }
        if (yumSecurity.podePesquisarPedidos()) {
            pedidoResumoModel.add(yumLinks.linkToPedidos("pedidos"));
        }

        return pedidoResumoModel;
    }


}
