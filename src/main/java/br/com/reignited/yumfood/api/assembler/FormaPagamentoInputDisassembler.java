package br.com.reignited.yumfood.api.assembler;

import br.com.reignited.yumfood.api.model.input.FormaPagamentoInput;
import br.com.reignited.yumfood.domain.model.FormaPagamento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FormaPagamentoInputDisassembler {

    @Autowired
    private ModelMapper mapper;

    public FormaPagamento toDomainObject(FormaPagamentoInput input) {
        return mapper.map(input, FormaPagamento.class);
    }

    public void copyToDomainObject(FormaPagamentoInput input, FormaPagamento formaPagamento) {
        mapper.map(input, formaPagamento);
    }
}
