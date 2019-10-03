package br.com.reignited.yumfood.domain.repository;

import java.util.List;

import br.com.reignited.yumfood.domain.model.FormaPagamento;

public interface FormaPagamentoRepository {

	public List<FormaPagamento> listar();

	public FormaPagamento buscar(Long id);

	public FormaPagamento salvar(FormaPagamento formaPagamento);

	public void remover(FormaPagamento formaPagamento);
}
