package br.com.reignited.yumfood.api.assembler;

import br.com.reignited.yumfood.api.model.CozinhaModel;
import br.com.reignited.yumfood.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CozinhaModelAssembler extends Assembler<CozinhaModel, Cozinha> {

    @Override
    public CozinhaModel toModel(Cozinha source) {
        return mapper.map(source, CozinhaModel.class);
    }

    @Override
    public List<CozinhaModel> toCollectionModel(Collection<Cozinha> source) {
        return source.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
