package br.com.reignited.yumfood.api.assembler;

import br.com.reignited.yumfood.api.model.FotoProdutoModel;
import br.com.reignited.yumfood.domain.model.FotoProduto;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class FotoProdutoModelAssembler extends Assembler<FotoProdutoModel, FotoProduto> {

    @Override
    public FotoProdutoModel toModel(FotoProduto source) {
        return mapper.map(source, FotoProdutoModel.class);
    }

    @Override
    public List<FotoProdutoModel> toCollectionModel(Collection<FotoProduto> source) {
        return null;
    }
}
