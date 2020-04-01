package br.com.reignited.yumfood.api.v1.disassembler;

import br.com.reignited.yumfood.api.v1.model.input.EstadoInput;
import br.com.reignited.yumfood.domain.model.Estado;
import org.springframework.stereotype.Component;

@Component
public class EstadoInputDisassembler extends Disassembler<Estado, EstadoInput>{

    @Override
    public Estado toDomainModel(EstadoInput source) {
        return mapper.map(source, Estado.class);
    }

    @Override
    public void copyToDomainObject(EstadoInput source, Estado target) {
        mapper.map(source, target);
    }
}
