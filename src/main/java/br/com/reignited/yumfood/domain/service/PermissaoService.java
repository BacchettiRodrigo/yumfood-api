package br.com.reignited.yumfood.domain.service;

import br.com.reignited.yumfood.domain.exception.PermissaoNaoEncontradaException;
import br.com.reignited.yumfood.domain.model.Permissao;
import br.com.reignited.yumfood.domain.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissaoService {

    @Autowired
    private PermissaoRepository permissaoRepository;


    public Permissao buscar(Long permissaoId) {
        return permissaoRepository.findById(permissaoId)
                .orElseThrow(() -> new PermissaoNaoEncontradaException(permissaoId));
    }
}
