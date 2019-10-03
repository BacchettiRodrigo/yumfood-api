package br.com.reignited.yumfood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.reignited.yumfood.domain.model.Permissao;
import br.com.reignited.yumfood.domain.repository.PermissaoRepository;

@Component
public class PermissaoRepositoryImpl implements PermissaoRepository{
	
	@PersistenceContext
	private EntityManager manager;
	
	public List<Permissao> listar() {
		return manager.createQuery("from Permissao", Permissao.class).getResultList();
	}

	public Permissao buscar(Long id) {
		return manager.find(Permissao.class, id);
	}
	
	@Transactional
	public Permissao salvar(Permissao permissao) {
		return manager.merge(permissao);
	}

	@Transactional
	public void remover(Permissao permissao) {
		permissao = buscar(permissao.getId());
		manager.remove(permissao);
	}
	
	
}
