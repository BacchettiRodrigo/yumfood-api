package br.com.reignited.yumfood.api.assembler;

import br.com.reignited.yumfood.api.model.FormaPagamentoModel;
import br.com.reignited.yumfood.domain.model.FormaPagamento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FormaPagamentoModelAssembler {

    @Autowired
    private ModelMapper mapper;

    public FormaPagamentoModel toModel(FormaPagamento formaPagamento) {
        return mapper.map(formaPagamento, FormaPagamentoModel.class);
    }

    public List<FormaPagamentoModel> toCollectionModel(List<FormaPagamento> formaPagamentos) {
        return formaPagamentos.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
