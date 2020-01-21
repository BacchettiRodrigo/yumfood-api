package br.com.reignited.yumfood.api.assembler;

import br.com.reignited.yumfood.api.model.EstadoModel;
import br.com.reignited.yumfood.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EstadoModelAssembler extends Assembler<EstadoModel, Estado>{

    @Override
    public EstadoModel toModel(Estado source) {
        return mapper.map(source, EstadoModel.class);
    }

    @Override
    public List<EstadoModel> toCollectionModel(Collection<Estado> source) {
        return source.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
