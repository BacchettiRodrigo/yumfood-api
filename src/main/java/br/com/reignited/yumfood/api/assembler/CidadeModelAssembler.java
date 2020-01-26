package br.com.reignited.yumfood.api.assembler;

import br.com.reignited.yumfood.api.model.CidadeModel;
import br.com.reignited.yumfood.domain.model.Cidade;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CidadeModelAssembler extends Assembler<CidadeModel, Cidade> {

    @Override
    public CidadeModel toModel(Cidade source) {
        return mapper.map(source, CidadeModel.class);
    }

    @Override
    public List<CidadeModel> toCollectionModel(Collection<Cidade> source) {
        return source.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
