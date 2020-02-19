package br.com.reignited.yumfood.domain.listener;

import br.com.reignited.yumfood.domain.event.PedidoConfirmadoEvent;
import br.com.reignited.yumfood.domain.model.Pedido;
import br.com.reignited.yumfood.domain.service.EnvioEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class NotificaoClientePedidoConfirmadoListener {

    @Autowired
    private EnvioEmailService envioEmailService;

    @TransactionalEventListener//(phase = TransactionPhase.BEFORE_COMMIT)
    public void aoConfirmarPedido(PedidoConfirmadoEvent event) {
        Pedido pedido = event.getPedido();

        var mensagem = EnvioEmailService.Mensagem.builder()
                .assunto(pedido.getRestaurante().getNome() + " - Pedido confirmado")
                .corpo("pedido-confirmado.html")
                .variavel("pedido", pedido)
                .destinatario(pedido.getCliente().getEmail())
                .build();

        envioEmailService.enviar(mensagem);
    }
}
