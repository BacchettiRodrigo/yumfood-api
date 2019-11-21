package br.com.reignited.yumfood.domain.service;

import br.com.reignited.yumfood.domain.exception.EntidadeNaoEncontradaException;
import br.com.reignited.yumfood.domain.model.Cidade;
import br.com.reignited.yumfood.domain.model.Estado;
import br.com.reignited.yumfood.domain.repository.CidadeRepository;
import br.com.reignited.yumfood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CidadeService {

    private static final String MSG_ESTADO_NAO_ENCONTRADO = "Não existe cadastro de estado com o código %d";
    private static final String MSG_CIDADE_NAO_ENCONTRADA = "Não existe cadastro de cidade com o código %d";
    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

    public Cidade buscar(Long id) {
        return cidadeRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format(MSG_CIDADE_NAO_ENCONTRADA, id)));
    }

    public Cidade salvar(Cidade cidade) {
        Long estadoId = cidade.getEstado().getId();

        Estado estado = estadoRepository.findById(estadoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format(MSG_ESTADO_NAO_ENCONTRADO, estadoId)));

        cidade.setEstado(estado);
        return cidadeRepository.save(cidade);
    }

    public void remover(Long id) {
        try {
            cidadeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new EntidadeNaoEncontradaException(
                    String.format(MSG_CIDADE_NAO_ENCONTRADA, id));
        }
    }

}
