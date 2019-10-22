package br.com.reignited.yumfood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.reignited.yumfood.domain.model.Estado;
import br.com.reignited.yumfood.domain.repository.EstadoRepository;

@Component
public class EstadoRepositoryImpl implements EstadoRepository {

	@PersistenceContext
	EntityManager entityManager;

	public List<Estado> listar() {
		return entityManager.createQuery("from Estado", Estado.class).getResultList();
	}

	public Estado buscar(Long id) {
		return entityManager.find(Estado.class, id);
	}

	@Transactional
	public Estado salvar(Estado estado) {
		return entityManager.merge(estado);
	}

	@Transactional
	public void remover(Long id) {
		Estado estado = buscar(id);

		if (estado == null) {
			throw new EmptyResultDataAccessException(1);
		}

		entityManager.remove(estado);
	}

}
