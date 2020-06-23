package br.com.reignited.yumfood.api.v1.assembler;

import br.com.reignited.yumfood.api.v1.YumLinks;
import br.com.reignited.yumfood.api.v1.controller.CidadeController;
import br.com.reignited.yumfood.api.v1.model.CidadeModel;
import br.com.reignited.yumfood.core.security.YumSecurity;
import br.com.reignited.yumfood.domain.model.Cidade;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class CidadeModelAssembler extends RepresentationModelAssemblerSupport<Cidade, CidadeModel> {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private YumLinks yumLinks;

    @Autowired
    private YumSecurity yumSecurity;

    public CidadeModelAssembler() {
        super(CidadeController.class, CidadeModel.class);
    }

    @Override
    public CidadeModel toModel(Cidade source) {
        CidadeModel cidadeModel = createModelWithId(source.getId(), source);
        mapper.map(source, cidadeModel);

        if (yumSecurity.podeConsultarCidades()) {
            cidadeModel.add(yumLinks.linkToCidades("cidades"));
        }
        if (yumSecurity.podeConsultarEstados()) {
            cidadeModel.getEstado().add(yumLinks.linkToEstado(cidadeModel.getEstado().getId()));
        }

        return cidadeModel;
    }

    @Override
    public CollectionModel<CidadeModel> toCollectionModel(Iterable<? extends Cidade> entities) {
        CollectionModel<CidadeModel> collectionModel = super.toCollectionModel(entities);

        if (yumSecurity.podeConsultarCidades()) {
            collectionModel.add(linkTo(CidadeController.class).withSelfRel());
        }

        return collectionModel;

    }
}
