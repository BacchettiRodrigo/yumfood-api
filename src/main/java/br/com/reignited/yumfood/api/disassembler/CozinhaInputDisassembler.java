package br.com.reignited.yumfood.api.disassembler;

import br.com.reignited.yumfood.api.model.input.CozinhaInput;
import br.com.reignited.yumfood.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
