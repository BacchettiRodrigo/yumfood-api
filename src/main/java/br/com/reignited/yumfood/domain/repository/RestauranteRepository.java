package br.com.reignited.yumfood.domain.repository;

import br.com.reignited.yumfood.domain.model.Restaurante;
import br.com.reignited.yumfood.domain.repository.custom.CustomJpaRepository;
import br.com.reignited.yumfood.domain.repository.custom.RestauranteRepositoryCustom;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>, RestauranteRepositoryCustom,
        JpaSpecificationExecutor {

    @Query(" from Restaurante r join r.cozinha")
    List<Restaurante> findAll();

}
