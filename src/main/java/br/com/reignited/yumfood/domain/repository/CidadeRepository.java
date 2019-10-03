package br.com.reignited.yumfood.domain.repository;

import java.util.List;

import br.com.reignited.yumfood.domain.model.Cidade;

public interface CidadeRepository {

	public List<Cidade> listar();

	public Cidade buscar(Long id);

	public Cidade salvar(Cidade cidade);

	public void remover(Cidade cidade);
}
