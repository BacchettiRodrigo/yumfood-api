package br.com.reignited.yumfood.api.assembler;

import br.com.reignited.yumfood.api.model.GrupoModel;
import br.com.reignited.yumfood.domain.model.Grupo;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GrupoModelAssembler extends Assembler<GrupoModel, Grupo> {

    @Override
    public GrupoModel toModel(Grupo source) {
        return mapper.map(source, GrupoModel.class);
    }

    @Override
    public List<GrupoModel> toCollectionModel(Collection<Grupo> source) {
        return source.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
