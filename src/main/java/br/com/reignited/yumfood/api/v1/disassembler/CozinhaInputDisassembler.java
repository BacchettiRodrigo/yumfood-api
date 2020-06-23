package br.com.reignited.yumfood.api.v1.disassembler;

import br.com.reignited.yumfood.api.v1.model.input.CozinhaInput;
import br.com.reignited.yumfood.domain.model.Cozinha;
import org.springframework.stereotype.Component;

@Component
public class CozinhaInputDisassembler extends Disassembler<Cozinha, CozinhaInput>{

    @Override
    public Cozinha toDomainModel(CozinhaInput source) {
        return mapper.map(source, Cozinha.class);
    }

    @Override
    public void copyToDomainObject(CozinhaInput cozinhaInput, Cozinha cozinha) {
        mapper.map(cozinhaInput, cozinha);
    }
}
