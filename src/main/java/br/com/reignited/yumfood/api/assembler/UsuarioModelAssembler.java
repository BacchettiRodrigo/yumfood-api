package br.com.reignited.yumfood.api.assembler;

import br.com.reignited.yumfood.api.model.UsuarioModel;
import br.com.reignited.yumfood.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioModelAssembler extends Assembler<UsuarioModel, Usuario> {

    @Override
    public UsuarioModel toModel(Usuario source) {
        return mapper.map(source, UsuarioModel.class);
    }

    @Override
    public List<UsuarioModel> toCollectionModel(Collection<Usuario> source) {
        return source.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
