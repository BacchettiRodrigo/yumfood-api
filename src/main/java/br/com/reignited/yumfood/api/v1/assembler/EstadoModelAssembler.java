package br.com.reignited.yumfood.api.v1.assembler;

import br.com.reignited.yumfood.api.v1.YumLinks;
import br.com.reignited.yumfood.api.v1.controller.EstadoController;
import br.com.reignited.yumfood.api.v1.model.EstadoModel;
import br.com.reignited.yumfood.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class EstadoModelAssembler extends RepresentationModelAssemblerSupport<Estado, EstadoModel> {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private YumLinks yumLinks;

    public EstadoModelAssembler() {
        super(EstadoController.class, EstadoModel.class);
    }

    @Override
    public EstadoModel toModel(Estado source) {
        EstadoModel estadoModel = createModelWithId(source.getId(), source);
        mapper.map(source, estadoModel);

        estadoModel.add(yumLinks.linkToEstados("estados"));

        return estadoModel;
    }

    @Override
    public CollectionModel<EstadoModel> toCollectionModel(Iterable<? extends Estado> entities) {
        return super.toCollectionModel(entities)
                .add(linkTo(EstadoController.class).withSelfRel());
    }
}
