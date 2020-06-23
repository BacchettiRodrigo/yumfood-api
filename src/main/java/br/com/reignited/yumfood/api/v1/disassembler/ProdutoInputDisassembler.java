package br.com.reignited.yumfood.api.v1.disassembler;

import br.com.reignited.yumfood.api.v1.model.input.ProdutoInput;
import br.com.reignited.yumfood.domain.model.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoInputDisassembler extends Disassembler<Produto, ProdutoInput> {

    @Override
    public Produto toDomainModel(ProdutoInput source) {
        return mapper.map(source, Produto.class);
    }

    @Override
    public void copyToDomainObject(ProdutoInput source, Produto target) {
        mapper.map(source, target);
    }
}
