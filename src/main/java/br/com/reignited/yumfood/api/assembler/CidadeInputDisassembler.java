package br.com.reignited.yumfood.api.assembler;

import br.com.reignited.yumfood.api.model.input.CidadeInput;
import br.com.reignited.yumfood.domain.model.Cidade;
import br.com.reignited.yumfood.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CidadeInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Cidade toDomainObject(CidadeInput cidadeInput) {
        return modelMapper.map(cidadeInput, Cidade.class);
    }

    public void copyToDomainObject(CidadeInput cidadeInput, Cidade cidade) {
        cidade.setEstado(new Estado());

        modelMapper.map(cidadeInput, cidade);
    }
}
