package br.com.reignited.yumfood.domain.service;

import br.com.reignited.yumfood.domain.exception.EntidadeEmUsoException;
import br.com.reignited.yumfood.domain.exception.EntidadeNaoEncontradaException;
import br.com.reignited.yumfood.domain.model.Estado;
import br.com.reignited.yumfood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoService {

	private static final String MSG_ESTADO_NAO_ENCONTRADO = "Não existe um cadastro de estado com o código %d.";
	private static final String MSG_ESTADO_EM_USO = "Estado de código %d não pode ser removido pois está em uso.";
	@Autowired
	private EstadoRepository estadoRepository;

	public List<Estado> listar() {
		return estadoRepository.findAll();
	}

	public Estado buscar(Long id) {
		return estadoRepository.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(
						String.format(MSG_ESTADO_NAO_ENCONTRADO, id)));
	}

	public Estado salvar(Estado estado) {
		return estadoRepository.save(estado);
	}

	public void remover(Long id) {
		try {
			estadoRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format(MSG_ESTADO_NAO_ENCONTRADO, id));
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_ESTADO_EM_USO, id));
		}
	}
}
