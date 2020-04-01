package br.com.reignited.yumfood.api.v1.assembler;

import br.com.reignited.yumfood.api.v1.YumLinks;
import br.com.reignited.yumfood.api.v1.controller.GrupoController;
import br.com.reignited.yumfood.api.v1.model.GrupoModel;
import br.com.reignited.yumfood.domain.model.Grupo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class GrupoModelAssembler extends RepresentationModelAssemblerSupport<Grupo, GrupoModel> {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private YumLinks yumLinks;

    public GrupoModelAssembler() {
        super(GrupoController.class, GrupoModel.class);
    }

    @Override
    public GrupoModel toModel(Grupo source) {
        GrupoModel model = createModelWithId(source.getId(), source);
        mapper.map(source, model);

        model.add(yumLinks.linkToGrupos("grupos"));
        model.add(yumLinks.linkToGruposPermissoes(source.getId(), "permissoes"));

        return model;
    }

    @Override
    public CollectionModel<GrupoModel> toCollectionModel(Iterable<? extends Grupo> entities) {
        return super.toCollectionModel(entities).add(yumLinks.linkToGrupos());
    }
}
