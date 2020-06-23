package br.com.reignited.yumfood.api.v1.assembler;

import br.com.reignited.yumfood.api.v1.YumLinks;
import br.com.reignited.yumfood.api.v1.controller.RestauranteProdutoController;
import br.com.reignited.yumfood.api.v1.model.ProdutoModel;
import br.com.reignited.yumfood.core.security.YumSecurity;
import br.com.reignited.yumfood.domain.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class ProdutoModelAssembler extends RepresentationModelAssemblerSupport<Produto, ProdutoModel> {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private YumLinks yumLinks;

    @Autowired
    private YumSecurity yumSecurity;

    public ProdutoModelAssembler() {
        super(RestauranteProdutoController.class, ProdutoModel.class);
    }

    @Override
    public ProdutoModel toModel(Produto source) {
        ProdutoModel model = createModelWithId(source.getId(), source, source.getRestaurante().getId());
        mapper.map(source, model);

        if (yumSecurity.podeConsultarRestaurantes()) {
            model.add(yumLinks.linkToProdutos(source.getRestaurante().getId(), "produtos"));
            model.add(yumLinks.linkToProdutoFoto(source.getRestaurante().getId(), source.getId(), "foto"));
        }

        return model;
    }

}
