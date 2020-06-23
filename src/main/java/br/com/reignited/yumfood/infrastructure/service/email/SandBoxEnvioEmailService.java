package br.com.reignited.yumfood.infrastructure.service.email;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class SandBoxEnvioEmailService extends SmtpEnvioEmailService {

    @Override
    protected MimeMessage criarMimeMessage(Mensagem mensagem) throws MessagingException {
        MimeMessage mimeMessage = super.criarMimeMessage(mensagem);

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
        helper.setTo(emailProperties.getSandBox().getDestinatario());

        return mimeMessage;
    }
}
