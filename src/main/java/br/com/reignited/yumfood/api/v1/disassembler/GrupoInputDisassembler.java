package br.com.reignited.yumfood.api.v1.disassembler;

import br.com.reignited.yumfood.api.v1.model.input.GrupoInput;
import br.com.reignited.yumfood.domain.model.Grupo;
import org.springframework.stereotype.Component;

@Component
public class GrupoInputDisassembler extends Disassembler<Grupo, GrupoInput> {

    @Override
    public Grupo toDomainModel(GrupoInput source) {
        return mapper.map(source, Grupo.class);
    }

    @Override
    public void copyToDomainObject(GrupoInput source, Grupo target) {
        mapper.map(source, target);
    }
}
