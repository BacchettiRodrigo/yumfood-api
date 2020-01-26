package br.com.reignited.yumfood.api.assembler;

import br.com.reignited.yumfood.api.model.FormaPagamentoModel;
import br.com.reignited.yumfood.domain.model.FormaPagamento;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FormaPagamentoModelAssembler extends Assembler<FormaPagamentoModel, FormaPagamento> {

    @Override
    public FormaPagamentoModel toModel(FormaPagamento source) {
        return mapper.map(source, FormaPagamentoModel.class);
    }

    @Override
    public List<FormaPagamentoModel> toCollectionModel(Collection<FormaPagamento> source) {
        return source.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
