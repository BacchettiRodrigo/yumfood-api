package br.com.reignited.yumfood.domain.repository;

import java.util.List;

import br.com.reignited.yumfood.domain.model.Estado;

public interface EstadoRepository {

	public List<Estado> listar();

	public Estado buscar(Long id);

	public Estado salvar(Estado estado);

	public void remover(Estado estado);
}
