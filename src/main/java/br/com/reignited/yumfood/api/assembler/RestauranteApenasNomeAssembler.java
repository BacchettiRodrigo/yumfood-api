package br.com.reignited.yumfood.api.assembler;

import br.com.reignited.yumfood.api.YumLinks;
import br.com.reignited.yumfood.api.controller.RestauranteController;
import br.com.reignited.yumfood.api.model.RestauranteApenasNome;
import br.com.reignited.yumfood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class RestauranteApenasNomeAssembler extends RepresentationModelAssemblerSupport<Restaurante, RestauranteApenasNome> {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private YumLinks yumLinks;

    public RestauranteApenasNomeAssembler() {
        super(RestauranteController.class, RestauranteApenasNome.class);
    }

    @Override
    public RestauranteApenasNome toModel(Restaurante source) {
        RestauranteApenasNome model = createModelWithId(source.getId(), source);
        mapper.map(source, model);

        model.add(yumLinks.linkToRestaurantes("restaurantes"));

        return model;
    }

    @Override
    public CollectionModel<RestauranteApenasNome> toCollectionModel(Iterable<? extends Restaurante> entities) {
        return super.toCollectionModel(entities).add(yumLinks.linkToRestaurantes());
    }
}
