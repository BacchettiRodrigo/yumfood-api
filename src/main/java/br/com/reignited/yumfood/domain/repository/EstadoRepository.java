package br.com.reignited.yumfood.domain.repository;

import br.com.reignited.yumfood.domain.model.Estado;
import br.com.reignited.yumfood.domain.repository.custom.CustomJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends CustomJpaRepository<Estado, Long> {

}
