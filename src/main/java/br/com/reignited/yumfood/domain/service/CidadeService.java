package br.com.reignited.yumfood.domain.service;

import br.com.reignited.yumfood.domain.exception.CidadeNaoEncontradaException;
import br.com.reignited.yumfood.domain.exception.EntidadeEmUsoException;
import br.com.reignited.yumfood.domain.exception.EntidadeNaoEncontradaException;
import br.com.reignited.yumfood.domain.exception.EstadoNaoEncontradoException;
import br.com.reignited.yumfood.domain.model.Cidade;
import br.com.reignited.yumfood.domain.model.Estado;
import br.com.reignited.yumfood.domain.repository.CidadeRepository;
import br.com.reignited.yumfood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

    public Cidade buscar(Long id) {
        return cidadeRepository.findById(id)
                .orElseThrow(() -> new CidadeNaoEncontradaException(id));
    }

    @Transactional
    public Cidade salvar(Cidade cidade) {
        Long estadoId = cidade.getEstado().getId();

        Estado estado = estadoRepository.findById(estadoId)
                .orElseThrow(() -> new EstadoNaoEncontradoException(estadoId));

        cidade.setEstado(estado);
        return cidadeRepository.save(cidade);
    }

    @Transactional
    public void remover(Long id) {
        try {
            cidadeRepository.deleteById(id);
            cidadeRepository.flush();
        } catch (DataIntegrityViolationException ex) {
            throw new EntidadeEmUsoException(id);
        } catch (EmptyResultDataAccessException e) {
            throw new CidadeNaoEncontradaException(id);
        }
    }

}
