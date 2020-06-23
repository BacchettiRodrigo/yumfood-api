package br.com.reignited.yumfood.api.v1.assembler;

import br.com.reignited.yumfood.api.v1.YumLinks;
import br.com.reignited.yumfood.api.v1.controller.GrupoPermissaoController;
import br.com.reignited.yumfood.api.v1.model.PermissaoModel;
import br.com.reignited.yumfood.core.security.YumSecurity;
import br.com.reignited.yumfood.domain.model.Permissao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class PermissaoModelAssembler extends RepresentationModelAssemblerSupport<Permissao, PermissaoModel> {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private YumLinks yumLinks;

    @Autowired
    private YumSecurity yumSecurity;

    public PermissaoModelAssembler() {
        super(GrupoPermissaoController.class, PermissaoModel.class);
    }

    @Override
    public PermissaoModel toModel(Permissao source) {
        return mapper.map(source, PermissaoModel.class);
    }

    @Override
    public CollectionModel<PermissaoModel> toCollectionModel(Iterable<? extends Permissao> entities) {
        CollectionModel<PermissaoModel> collectionModel = super.toCollectionModel(entities);

        if (yumSecurity.podeConsultarUsuariosGruposPermissoes()) {
            collectionModel.add(yumLinks.linkToPermissoes());
        }

        return collectionModel;
    }
}
