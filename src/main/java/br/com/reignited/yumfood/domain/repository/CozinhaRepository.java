package br.com.reignited.yumfood.domain.repository;

import java.util.List;

import br.com.reignited.yumfood.domain.model.Cozinha;

public interface CozinhaRepository {

	public List<Cozinha> listar();

	public Cozinha buscar(Long id);

	public Cozinha salvar(Cozinha cozinha);

	public void remover(Long id);
}
