package br.com.reignited.yumfood.core.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModule extends SimpleModule {

    private static final long serialVersionUID = 1L;

    public JacksonMixinModule() {
        //setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
        //setMixInAnnotation(Cidade.class, CidadeMixin.class);
    }
}
