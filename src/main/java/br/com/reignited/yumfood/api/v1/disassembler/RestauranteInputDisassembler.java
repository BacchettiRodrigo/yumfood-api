package br.com.reignited.yumfood.api.v1.disassembler;

import br.com.reignited.yumfood.api.v1.model.input.RestauranteInput;
import br.com.reignited.yumfood.domain.model.Cidade;
import br.com.reignited.yumfood.domain.model.Cozinha;
import br.com.reignited.yumfood.domain.model.Restaurante;
import org.springframework.stereotype.Component;

@Component
public class RestauranteInputDisassembler extends Disassembler<Restaurante, RestauranteInput> {

    @Override
    public Restaurante toDomainModel(RestauranteInput source) {
        return mapper.map(source, Restaurante.class);
    }

    @Override
    public void copyToDomainObject(RestauranteInput source, Restaurante target) {
        // Para evitar org.hibernate.HibernateException: identifier of an instance of
        // com.algaworks.algafood.domain.model.Cozinha was altered from 1 to 2
        target.setCozinha(new Cozinha());

        if (target.getEndereco() != null) {
            target.getEndereco().setCidade(new Cidade());
        }

        mapper.map(source, target);
    }
}
