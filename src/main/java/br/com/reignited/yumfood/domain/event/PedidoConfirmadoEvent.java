package br.com.reignited.yumfood.domain.event;

import br.com.reignited.yumfood.domain.model.Pedido;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PedidoConfirmadoEvent {

    private Pedido pedido;

}
