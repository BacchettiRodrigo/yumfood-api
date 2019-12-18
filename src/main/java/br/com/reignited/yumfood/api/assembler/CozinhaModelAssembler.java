package br.com.reignited.yumfood.api.assembler;

import br.com.reignited.yumfood.api.model.CozinhaModel;
import br.com.reignited.yumfood.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CozinhaModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public List<CozinhaModel> toCollectionModel(List<Cozinha> cozinhas) {
        return cozinhas.stream()
                .map(cozinha -> toModel(cozinha))
                .collect(Collectors.toList());
    }

    public CozinhaModel toModel(Cozinha restaurante) {
        return modelMapper.map(restaurante, CozinhaModel.class);
    }
}
