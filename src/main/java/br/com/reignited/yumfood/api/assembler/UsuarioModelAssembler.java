package br.com.reignited.yumfood.api.assembler;

import br.com.reignited.yumfood.api.model.UsuarioModel;
import br.com.reignited.yumfood.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioModelAssembler {

    @Autowired
    private ModelMapper mapper;

    public UsuarioModel toModel(Usuario usuario) {
        return mapper.map(usuario, UsuarioModel.class);
    }

    public List<UsuarioModel> toColletionModel(List<Usuario> usuarios) {
        return usuarios.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
