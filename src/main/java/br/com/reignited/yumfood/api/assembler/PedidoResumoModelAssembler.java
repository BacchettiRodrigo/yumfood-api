package br.com.reignited.yumfood.api.assembler;

import br.com.reignited.yumfood.api.YumLinks;
import br.com.reignited.yumfood.api.controller.PedidoController;
import br.com.reignited.yumfood.api.controller.RestauranteController;
import br.com.reignited.yumfood.api.controller.UsuarioController;
import br.com.reignited.yumfood.api.model.PedidoModel;
import br.com.reignited.yumfood.api.model.PedidoResumoModel;
import br.com.reignited.yumfood.domain.model.Pedido;
import br.com.reignited.yumfood.domain.model.Restaurante;
import br.com.reignited.yumfood.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class PedidoResumoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoResumoModel> {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private YumLinks yumLinks;

    public PedidoResumoModelAssembler() {
        super(PedidoController.class, PedidoResumoModel.class);
    }

    @Override
    public PedidoResumoModel toModel(Pedido source) {
        PedidoResumoModel pedidoResumoModel = createModelWithId(source.getCodigo(), source);
        mapper.map(source, pedidoResumoModel);

        pedidoResumoModel.getCliente().add(yumLinks.linkToUsuario(pedidoResumoModel.getCliente().getId()));
        pedidoResumoModel.getRestaurante().add(yumLinks.linkToRestaurante(pedidoResumoModel.getRestaurante().getId()));

        return pedidoResumoModel;
    }


}
