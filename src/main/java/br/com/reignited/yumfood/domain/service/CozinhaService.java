package br.com.reignited.yumfood.domain.service;

import java.util.List;
import java.util.Optional;

import br.com.reignited.yumfood.domain.exception.CozinhaNaoEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.reignited.yumfood.domain.exception.EntidadeEmUsoException;
import br.com.reignited.yumfood.domain.exception.EntidadeNaoEncontradaException;
import br.com.reignited.yumfood.domain.model.Cozinha;
import br.com.reignited.yumfood.domain.repository.CozinhaRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CozinhaService {

	private static final String MSG_COZINHA_EM_USO = "Cozinha de código %d não pode ser removida pois está em uso";

	@Autowired
	private CozinhaRepository cozinhaRepository;

	public Page<Cozinha> listar(Pageable pageable) {
		return cozinhaRepository.findAll(pageable);
	}

	public Cozinha buscar(Long id) {
		return cozinhaRepository.findById(id)
				.orElseThrow(() -> new CozinhaNaoEncontradaException(id));
	}

	@Transactional
	public Cozinha salvar(Cozinha cozinha) {
		return cozinhaRepository.save(cozinha);
	}

	@Transactional
	public void excluir(Long cozinhaId) {
		try {
			cozinhaRepository.deleteById(cozinhaId);
			cozinhaRepository.flush();
		} catch (EmptyResultDataAccessException ex) {
			throw new CozinhaNaoEncontradaException(cozinhaId);
		} catch (DataIntegrityViolationException ex) {
			throw new EntidadeEmUsoException(
					String.format(MSG_COZINHA_EM_USO, cozinhaId));
		}
	}
}
