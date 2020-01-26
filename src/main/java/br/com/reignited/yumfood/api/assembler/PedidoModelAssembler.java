package br.com.reignited.yumfood.api.assembler;

import br.com.reignited.yumfood.api.model.PedidoModel;
import br.com.reignited.yumfood.domain.model.Pedido;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PedidoModelAssembler extends Assembler<PedidoModel, Pedido> {

    @Override
    public PedidoModel toModel(Pedido source) {
        return mapper.map(source, PedidoModel.class);
    }

    @Override
    public List<PedidoModel> toCollectionModel(Collection<Pedido> source) {
        return source.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
