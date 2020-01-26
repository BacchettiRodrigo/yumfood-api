package br.com.reignited.yumfood.domain.service;

import br.com.reignited.yumfood.domain.exception.EntidadeEmUsoException;
import br.com.reignited.yumfood.domain.exception.FormaPagamentoNaoEncontradaException;
import br.com.reignited.yumfood.domain.model.FormaPagamento;
import br.com.reignited.yumfood.domain.repository.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class FormaPagamentoService {

    private static final String MSG_FORMA_PAGAMENT_EM_USO =
            "A forma de pagamento de código %d nãopode ser excluída pois está em uso.";

    @Autowired
    private FormaPagamentoRepository repository;

    public List<FormaPagamento> listar() {
        return repository.findAll();
    }

    public FormaPagamento buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new FormaPagamentoNaoEncontradaException(id));
    }

    @Transactional
    public FormaPagamento salvar(FormaPagamento formaPagamento) {
        return repository.save(formaPagamento);
    }

    @Transactional
    public void excluir(Long id) {
        try {
            repository.deleteById(id);
            repository.flush();
        } catch (EmptyResultDataAccessException ex) {
            throw new FormaPagamentoNaoEncontradaException(id);
        } catch (DataIntegrityViolationException ex) {
            throw new EntidadeEmUsoException(String.format(MSG_FORMA_PAGAMENT_EM_USO, id));
        }
    }
}
