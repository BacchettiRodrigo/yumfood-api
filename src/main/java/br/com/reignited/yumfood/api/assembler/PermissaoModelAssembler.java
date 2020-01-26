package br.com.reignited.yumfood.api.assembler;

import br.com.reignited.yumfood.api.model.PermissaoModel;
import br.com.reignited.yumfood.domain.model.Permissao;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PermissaoModelAssembler extends Assembler<PermissaoModel, Permissao> {

    @Override
    public PermissaoModel toModel(Permissao source) {
        return mapper.map(source, PermissaoModel.class);
    }

    @Override
    public List<PermissaoModel> toCollectionModel(Collection<Permissao> source) {
        return source.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
