package br.com.reignited.yumfood.api.v2.assembler;

import br.com.reignited.yumfood.api.v1.YumLinks;
import br.com.reignited.yumfood.api.v1.controller.CidadeController;
import br.com.reignited.yumfood.api.v1.model.CidadeModel;
import br.com.reignited.yumfood.api.v2.YumLinksV2;
import br.com.reignited.yumfood.api.v2.controller.CidadeControllerV2;
import br.com.reignited.yumfood.api.v2.model.CidadeModelV2;
import br.com.reignited.yumfood.domain.model.Cidade;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class CidadeModelAssemblerV2 extends RepresentationModelAssemblerSupport<Cidade, CidadeModelV2> {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private YumLinksV2 yumLinks;

    public CidadeModelAssemblerV2() {
        super(CidadeControllerV2.class, CidadeModelV2.class);
    }

    @Override
    public CidadeModelV2 toModel(Cidade source) {
        CidadeModelV2 cidadeModel = createModelWithId(source.getId(), source);
        mapper.map(source, cidadeModel);

        cidadeModel.add(yumLinks.linkToCidades("cidades"));

        return cidadeModel;
    }

    @Override
    public CollectionModel<CidadeModelV2> toCollectionModel(Iterable<? extends Cidade> entities) {
        return super.toCollectionModel(entities)
                .add(linkTo(CidadeControllerV2.class).withSelfRel());
    }
}
