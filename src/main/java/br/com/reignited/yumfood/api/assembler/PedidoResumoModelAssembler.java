package br.com.reignited.yumfood.api.assembler;

import br.com.reignited.yumfood.api.model.PedidoResumoModel;
import br.com.reignited.yumfood.domain.model.Pedido;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PedidoResumoModelAssembler extends Assembler<PedidoResumoModel, Pedido>{

    @Override
    public PedidoResumoModel toModel(Pedido source) {
        return mapper.map(source, PedidoResumoModel.class);
    }

    @Override
    public List<PedidoResumoModel> toCollectionModel(Collection<Pedido> source) {
        return source.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
