package br.com.reignited.yumfood.api.v1.disassembler;

import br.com.reignited.yumfood.api.v1.model.input.UsuarioInput;
import br.com.reignited.yumfood.domain.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioInputDisassembler extends Disassembler<Usuario, UsuarioInput> {

    @Override
    public Usuario toDomainModel(UsuarioInput source) {
        return mapper.map(source, Usuario.class);
    }

    @Override
    public void copyToDomainObject(UsuarioInput source, Usuario target) {
        mapper.map(source, target);
    }
}
