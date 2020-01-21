package br.com.reignited.yumfood.api.disassembler;

import br.com.reignited.yumfood.api.model.input.CidadeInput;
import br.com.reignited.yumfood.api.model.input.ProdutoInput;
import br.com.reignited.yumfood.domain.model.Cidade;
import br.com.reignited.yumfood.domain.model.Estado;
import br.com.reignited.yumfood.domain.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CidadeInputDisassembler extends Disassembler<Cidade, CidadeInput> {

    @Override
    public Cidade toDomainModel(CidadeInput source) {
        return mapper.map(source, Cidade.class);
    }

    @Override
    public void copyToDomainObject(CidadeInput cidadeInput, Cidade cidade) {
        cidade.setEstado(new Estado());

        mapper.map(cidadeInput, cidade);
    }
}
