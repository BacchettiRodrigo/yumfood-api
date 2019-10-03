package br.com.reignited.yumfood.domain.repository;

import java.util.List;

import br.com.reignited.yumfood.domain.model.Permissao;

public interface PermissaoRepository {

	public List<Permissao> listar();

	public Permissao buscar(Long id);

	public Permissao salvar(Permissao permissao);

	public void remover(Permissao permissao);
}
