package br.com.reignited.yumfood.api.disassembler;

import br.com.reignited.yumfood.api.model.input.PedidoInput;
import br.com.reignited.yumfood.domain.model.Pedido;
import org.springframework.stereotype.Component;

@Component
public class PedidoInputDisassembler extends Disassembler<Pedido, PedidoInput> {

    @Override
    public Pedido toDomainModel(PedidoInput source) {
        return mapper.map(source, Pedido.class);
    }

    @Override
    public void copyToDomainObject(PedidoInput source, Pedido target) {
        mapper.map(source, target);
    }
}
