package br.com.reignited.yumfood.api.disassembler;

import br.com.reignited.yumfood.api.model.input.FormaPagamentoInput;
import br.com.reignited.yumfood.domain.model.FormaPagamento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FormaPagamentoInputDisassembler extends Disassembler<FormaPagamento, FormaPagamentoInput>{

    @Override
    public FormaPagamento toDomainModel(FormaPagamentoInput source) {
        return mapper.map(source, FormaPagamento.class);
    }

    @Override
    public void copyToDomainObject(FormaPagamentoInput source, FormaPagamento target) {
        mapper.map(source, target);
    }
}
