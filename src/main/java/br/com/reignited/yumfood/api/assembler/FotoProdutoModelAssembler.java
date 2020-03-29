package br.com.reignited.yumfood.api.assembler;

import br.com.reignited.yumfood.api.YumLinks;
import br.com.reignited.yumfood.api.controller.RestauranteProdutoFotoController;
import br.com.reignited.yumfood.api.model.FotoProdutoModel;
import br.com.reignited.yumfood.domain.model.FotoProduto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class FotoProdutoModelAssembler extends RepresentationModelAssemblerSupport<FotoProduto, FotoProdutoModel> {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private YumLinks yumLinks;

    public FotoProdutoModelAssembler() {
        super(RestauranteProdutoFotoController.class, FotoProdutoModel.class);
    }

    @Override
    public FotoProdutoModel toModel(FotoProduto source) {
        FotoProdutoModel model = createModelWithId(source.getId(), source, source.getRestauranteId());
        mapper.map(source, model);

        model.add(yumLinks.linkToRestauranteProduto(
                source.getRestauranteId(), source.getProduto().getId(), "produto"));

        return model;
    }

}
