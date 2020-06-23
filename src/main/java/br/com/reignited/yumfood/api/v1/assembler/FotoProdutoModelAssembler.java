package br.com.reignited.yumfood.api.v1.assembler;

import br.com.reignited.yumfood.api.v1.YumLinks;
import br.com.reignited.yumfood.api.v1.controller.RestauranteProdutoFotoController;
import br.com.reignited.yumfood.api.v1.model.FotoProdutoModel;
import br.com.reignited.yumfood.core.security.YumSecurity;
import br.com.reignited.yumfood.domain.model.FotoProduto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class FotoProdutoModelAssembler extends RepresentationModelAssemblerSupport<FotoProduto, FotoProdutoModel> {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private YumLinks yumLinks;

    @Autowired
    private YumSecurity yumSecurity;

    public FotoProdutoModelAssembler() {
        super(RestauranteProdutoFotoController.class, FotoProdutoModel.class);
    }

    @Override
    public FotoProdutoModel toModel(FotoProduto source) {
        FotoProdutoModel model = createModelWithId(source.getId(), source, source.getRestauranteId());
        mapper.map(source, model);

        if (yumSecurity.podeConsultarRestaurantes()) {
            model.add(yumLinks.linkToRestauranteProduto(
                    source.getRestauranteId(), source.getProduto().getId(), "produto"));
        }

        return model;
    }

}
