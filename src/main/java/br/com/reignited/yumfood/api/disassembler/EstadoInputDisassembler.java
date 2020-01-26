package br.com.reignited.yumfood.api.disassembler;

import br.com.reignited.yumfood.api.model.input.EstadoInput;
import br.com.reignited.yumfood.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
