package br.com.reignited.yumfood.core.email;

import br.com.reignited.yumfood.domain.service.EnvioEmailService;
import br.com.reignited.yumfood.infrastructure.service.email.FakeEnvioEmailService;
import br.com.reignited.yumfood.infrastructure.service.email.SandBoxEnvioEmailService;
import br.com.reignited.yumfood.infrastructure.service.email.SmtpEnvioEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfig {

    @Autowired
    private EmailProperties emailProperties;

    @Bean
    public EnvioEmailService envioEmailService() {
        switch (emailProperties.getImpl()) {
            case FAKE:
                return new FakeEnvioEmailService();
            case SMTP:
                return new SmtpEnvioEmailService();
            case SANDBOX:
                return new SandBoxEnvioEmailService();
            default:
                return null;
        }
    }
}
