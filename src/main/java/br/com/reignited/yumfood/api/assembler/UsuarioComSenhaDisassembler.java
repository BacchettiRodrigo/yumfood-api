package br.com.reignited.yumfood.api.assembler;

import br.com.reignited.yumfood.api.model.input.UsuarioComSenhaInput;
import br.com.reignited.yumfood.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioComSenhaDisassembler {

    @Autowired
    private ModelMapper mapper;

    public Usuario toDomainModel(UsuarioComSenhaInput input) {
        return mapper.map(input, Usuario.class);
    }

    public void copyToDomainModel(UsuarioComSenhaInput input, Usuario usuario) {
        mapper.map(input, usuario);
    }
}
