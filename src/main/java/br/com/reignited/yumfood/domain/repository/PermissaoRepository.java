package br.com.reignited.yumfood.domain.repository;

import br.com.reignited.yumfood.domain.model.Permissao;
import br.com.reignited.yumfood.domain.repository.custom.CustomJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissaoRepository extends CustomJpaRepository<Permissao, Long> {

}
