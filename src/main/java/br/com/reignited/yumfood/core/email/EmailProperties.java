package br.com.reignited.yumfood.core.email;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
@Getter
@Setter
@Component
@ConfigurationProperties("yumfood.email")
public class EmailProperties {

    @NotNull
    private String remetente;
    private TipoEnvioEmail impl = TipoEnvioEmail.FAKE;
    private SandBox sandBox = new SandBox();

    public enum TipoEnvioEmail {
        SMTP, SANDBOX, FAKE;
    }

    @Getter
    @Setter
    public class SandBox {
        private String destinatario;
    }
}
