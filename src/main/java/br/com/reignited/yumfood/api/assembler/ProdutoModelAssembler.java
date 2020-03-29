package br.com.reignited.yumfood.api.assembler;

import br.com.reignited.yumfood.api.YumLinks;
import br.com.reignited.yumfood.api.controller.RestauranteProdutoController;
import br.com.reignited.yumfood.api.model.ProdutoModel;
import br.com.reignited.yumfood.domain.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProdutoModelAssembler extends RepresentationModelAssemblerSupport<Produto, ProdutoModel> {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private YumLinks yumLinks;

    public ProdutoModelAssembler() {
        super(RestauranteProdutoController.class, ProdutoModel.class);
    }

    @Override
    public ProdutoModel toModel(Produto source) {
        ProdutoModel model = createModelWithId(source.getId(), source, source.getRestaurante().getId());
        mapper.map(source, model);

        model.add(yumLinks.linkToProdutos(source.getRestaurante().getId(), "produtos"));
        model.add(yumLinks.linkToProdutoFoto(source.getRestaurante().getId(), source.getId(), "foto"));

        return model;
    }

}
