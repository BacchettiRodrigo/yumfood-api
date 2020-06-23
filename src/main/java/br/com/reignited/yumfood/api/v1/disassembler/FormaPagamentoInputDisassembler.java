package br.com.reignited.yumfood.api.v1.disassembler;

import br.com.reignited.yumfood.api.v1.model.input.FormaPagamentoInput;
import br.com.reignited.yumfood.domain.model.FormaPagamento;
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
