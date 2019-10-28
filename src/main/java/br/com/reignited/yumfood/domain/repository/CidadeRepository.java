package br.com.reignited.yumfood.domain.repository;

import br.com.reignited.yumfood.domain.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

}
