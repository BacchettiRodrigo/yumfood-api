package br.com.reignited.yumfood.api.assembler;

import br.com.reignited.yumfood.api.model.input.UsuarioInput;
import br.com.reignited.yumfood.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioInputDisassemble {

    @Autowired
    private ModelMapper mapper;

    public Usuario toDomainObject(UsuarioInput input) {
        return mapper.map(input, Usuario.class);
    }

    public void copyToDomainObject(UsuarioInput input, Usuario usuario) {
        mapper.map(input, usuario);
    }
 }
