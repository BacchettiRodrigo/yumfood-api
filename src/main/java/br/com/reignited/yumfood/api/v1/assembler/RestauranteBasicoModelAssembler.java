package br.com.reignited.yumfood.api.v1.assembler;

import br.com.reignited.yumfood.api.v1.YumLinks;
import br.com.reignited.yumfood.api.v1.controller.RestauranteController;
import br.com.reignited.yumfood.api.v1.model.RestauranteBasicoModel;
import br.com.reignited.yumfood.core.security.YumSecurity;
import br.com.reignited.yumfood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class RestauranteBasicoModelAssembler extends RepresentationModelAssemblerSupport<Restaurante, RestauranteBasicoModel> {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private YumLinks yumLinks;

    @Autowired
    private YumSecurity yumSecurity;

    public RestauranteBasicoModelAssembler() {
        super(RestauranteController.class, RestauranteBasicoModel.class);
    }

    @Override
    public RestauranteBasicoModel toModel(Restaurante source) {
        RestauranteBasicoModel model = createModelWithId(source.getId(), source);
        mapper.map(source, model);

        if (yumSecurity.podeConsultarRestaurantes()) {
            model.add(yumLinks.linkToRestaurantes("restaurantes"));
        }

        if (yumSecurity.podeConsultarCozinhas()) {
            model.getCozinha().add(yumLinks.linkToCozinha(model.getCozinha().getId()));
        }

        return model;
    }

    @Override
    public CollectionModel<RestauranteBasicoModel> toCollectionModel(Iterable<? extends Restaurante> entities) {
        CollectionModel<RestauranteBasicoModel> collection = super.toCollectionModel(entities);

        if (yumSecurity.podeConsultarRestaurantes()) {
            collection.add(yumLinks.linkToRestaurantes());
        }

        return collection;
    }
}
