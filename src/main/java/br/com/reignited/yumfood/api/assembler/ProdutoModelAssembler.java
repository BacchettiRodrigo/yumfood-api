package br.com.reignited.yumfood.api.assembler;

import br.com.reignited.yumfood.api.model.ProdutoModel;
import br.com.reignited.yumfood.domain.model.Produto;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProdutoModelAssembler extends Assembler<ProdutoModel, Produto> {

    @Override
    public ProdutoModel toModel(Produto source) {
        return mapper.map(source, ProdutoModel.class);
    }

    @Override
    public List<ProdutoModel> toCollectionModel(Collection<Produto> source) {
        return source.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
