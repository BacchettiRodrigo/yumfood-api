package br.com.reignited.yumfood.api.v2.disassembler;

import br.com.reignited.yumfood.api.v1.disassembler.Disassembler;
import br.com.reignited.yumfood.api.v1.model.input.CidadeInput;
import br.com.reignited.yumfood.api.v2.model.input.CidadeInputV2;
import br.com.reignited.yumfood.domain.model.Cidade;
import br.com.reignited.yumfood.domain.model.Estado;
import org.springframework.stereotype.Component;

@Component
public class CidadeInputDisassemblerV2 extends Disassembler<Cidade, CidadeInputV2> {

    @Override
    public Cidade toDomainModel(CidadeInputV2 source) {
        return mapper.map(source, Cidade.class);
    }

    @Override
    public void copyToDomainObject(CidadeInputV2 cidadeInput, Cidade cidade) {
        cidade.setEstado(new Estado());
        mapper.map(cidadeInput, cidade);
    }
}
