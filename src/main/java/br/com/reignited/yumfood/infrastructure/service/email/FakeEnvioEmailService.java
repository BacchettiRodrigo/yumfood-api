package br.com.reignited.yumfood.infrastructure.service.email;

import br.com.reignited.yumfood.domain.service.EnvioEmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Slf4j
@Service
public class FakeEnvioEmailService extends SmtpEnvioEmailService {

    @Override
    public void enviar(Mensagem mensagem) {
        String corpo = processarTemplate(mensagem);
        log.info("[FAKE E-MAIL] Para: {}\n{}", mensagem.getDestinatarios(), corpo);
    }
}
