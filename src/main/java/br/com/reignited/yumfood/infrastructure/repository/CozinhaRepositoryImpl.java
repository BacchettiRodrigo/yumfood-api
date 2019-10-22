package br.com.reignited.yumfood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.reignited.yumfood.domain.model.Cozinha;
import br.com.reignited.yumfood.domain.repository.CozinhaRepository;

@Component
public class CozinhaRepositoryImpl implements CozinhaRepository {

	@PersistenceContext
	EntityManager entityManager;

	public List<Cozinha> listar() {
		return entityManager.createQuery("from Cozinha", Cozinha.class).getResultList();
	}

	public Cozinha buscar(Long id) {
		return entityManager.find(Cozinha.class, id);
	}

	@Transactional
	public Cozinha salvar(Cozinha cozinha) {
		return entityManager.merge(cozinha);
	}

	@Transactional
	public void remover(Long id) {
		Cozinha cozinha = buscar(id);
		
		if(cozinha == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		entityManager.remove(cozinha);
	}

}
