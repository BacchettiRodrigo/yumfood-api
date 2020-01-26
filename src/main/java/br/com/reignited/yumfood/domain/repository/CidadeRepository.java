package br.com.reignited.yumfood.domain.repository;

import br.com.reignited.yumfood.domain.model.Cidade;
import br.com.reignited.yumfood.domain.repository.custom.CustomJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends CustomJpaRepository<Cidade, Long> {

}
