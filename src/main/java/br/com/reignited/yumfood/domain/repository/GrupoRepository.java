package br.com.reignited.yumfood.domain.repository;

import br.com.reignited.yumfood.domain.model.Grupo;
import br.com.reignited.yumfood.domain.repository.custom.CustomJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoRepository extends CustomJpaRepository<Grupo, Long> {

}
