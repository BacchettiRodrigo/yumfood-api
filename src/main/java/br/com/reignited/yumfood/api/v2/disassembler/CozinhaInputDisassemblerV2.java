package br.com.reignited.yumfood.api.v2.disassembler;

import br.com.reignited.yumfood.api.v1.disassembler.Disassembler;
import br.com.reignited.yumfood.api.v1.model.input.CozinhaInput;
import br.com.reignited.yumfood.api.v2.model.input.CozinhaInputV2;
import br.com.reignited.yumfood.domain.model.Cozinha;
import org.springframework.stereotype.Component;

@Component
public class CozinhaInputDisassemblerV2 extends Disassembler<Cozinha, CozinhaInputV2>{

    @Override
    public Cozinha toDomainModel(CozinhaInputV2 source) {
        return mapper.map(source, Cozinha.class);
    }

    @Override
    public void copyToDomainObject(CozinhaInputV2 cozinhaInput, Cozinha cozinha) {
        mapper.map(cozinhaInput, cozinha);
    }
}
