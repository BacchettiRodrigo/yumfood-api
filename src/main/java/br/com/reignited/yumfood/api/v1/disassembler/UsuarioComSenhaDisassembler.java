package br.com.reignited.yumfood.api.v1.disassembler;

import br.com.reignited.yumfood.api.v1.model.input.UsuarioComSenhaInput;
import br.com.reignited.yumfood.domain.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioComSenhaDisassembler extends Disassembler<Usuario, UsuarioComSenhaInput> {

    @Override
    public Usuario toDomainModel(UsuarioComSenhaInput source) {
        return mapper.map(source, Usuario.class);
    }

    @Override
    public void copyToDomainObject(UsuarioComSenhaInput source, Usuario target) {
        mapper.map(source, target);
    }
}
