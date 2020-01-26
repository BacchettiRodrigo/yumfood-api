package br.com.reignited.yumfood.api.assembler;

import br.com.reignited.yumfood.api.model.RestauranteModel;
import br.com.reignited.yumfood.domain.model.Restaurante;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestauranteModelAssembler extends Assembler<RestauranteModel, Restaurante> {

    @Override
    public RestauranteModel toModel(Restaurante source) {
        return mapper.map(source, RestauranteModel.class);
    }

    @Override
    public List<RestauranteModel> toCollectionModel(Collection<Restaurante> source) {
        return source.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
