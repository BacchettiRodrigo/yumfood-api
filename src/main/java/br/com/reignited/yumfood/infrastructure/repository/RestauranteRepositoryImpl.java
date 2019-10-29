package br.com.reignited.yumfood.infrastructure.repository;

import br.com.reignited.yumfood.domain.model.Restaurante;
import br.com.reignited.yumfood.domain.repository.RestauranteRepository;
import br.com.reignited.yumfood.domain.repository.custom.RestauranteRepositoryCustom;
import br.com.reignited.yumfood.infrastructure.repository.specs.RestauranteSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryCustom {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    @Lazy
    private RestauranteRepository restauranteRepository;

    @Override
    public List<Restaurante> findComFreteGratis(String nome) {
        return restauranteRepository.findAll(RestauranteSpecs.comFreteGratis()
                .and(RestauranteSpecs.comNomeSemelhante(nome)));
    }
}
