package br.com.reignited.yumfood.infrastructure.repository;

import br.com.reignited.yumfood.domain.model.FotoProduto;
import br.com.reignited.yumfood.domain.repository.custom.ProdutoRepositoryCustom;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ProdutoRepositoryImpl implements ProdutoRepositoryCustom {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @Override
    public FotoProduto save(FotoProduto foto) {
        return manager.merge(foto);
    }
}
