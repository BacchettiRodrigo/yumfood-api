package br.com.reignited.yumfood.domain.repository;

import br.com.reignited.yumfood.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

}
