package br.com.reignited.yumfood.domain.repository;

import br.com.reignited.yumfood.domain.model.FormaPagamento;
import br.com.reignited.yumfood.domain.repository.custom.CustomJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormaPagamentoRepository extends CustomJpaRepository<FormaPagamento, Long> {

}
