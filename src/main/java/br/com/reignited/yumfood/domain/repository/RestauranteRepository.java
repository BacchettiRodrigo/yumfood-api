package br.com.reignited.yumfood.domain.repository;

import br.com.reignited.yumfood.domain.model.Restaurante;
import br.com.reignited.yumfood.domain.repository.custom.RestauranteRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long>, RestauranteRepositoryCustom,
        JpaSpecificationExecutor {

}
