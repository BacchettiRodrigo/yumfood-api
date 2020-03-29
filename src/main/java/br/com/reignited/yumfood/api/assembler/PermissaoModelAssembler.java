package br.com.reignited.yumfood.api.assembler;

import br.com.reignited.yumfood.api.YumLinks;
import br.com.reignited.yumfood.api.controller.GrupoPermissaoController;
import br.com.reignited.yumfood.api.model.PermissaoModel;
import br.com.reignited.yumfood.domain.model.Permissao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PermissaoModelAssembler extends RepresentationModelAssemblerSupport<Permissao, PermissaoModel> {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private YumLinks yumLinks;

    public PermissaoModelAssembler() {
        super(GrupoPermissaoController.class, PermissaoModel.class);
    }

    @Override
    public PermissaoModel toModel(Permissao source) {
        return mapper.map(source, PermissaoModel.class);
    }

    @Override
    public CollectionModel<PermissaoModel> toCollectionModel(Iterable<? extends Permissao> entities) {
        return super.toCollectionModel(entities).add(yumLinks.linkToPermissoes());
    }
}
