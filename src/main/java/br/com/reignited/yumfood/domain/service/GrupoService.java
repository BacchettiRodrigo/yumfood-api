package br.com.reignited.yumfood.domain.service;

import br.com.reignited.yumfood.domain.exception.EntidadeEmUsoException;
import br.com.reignited.yumfood.domain.exception.GrupoNaoEncontradoException;
import br.com.reignited.yumfood.domain.model.Grupo;
import br.com.reignited.yumfood.domain.model.Permissao;
import br.com.reignited.yumfood.domain.repository.GrupoRepository;
import br.com.reignited.yumfood.domain.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Service
public class GrupoService {

    public static final String MSG_ENTIDADE_EM_USO = "O grupo de código %d não pode ser removido pois está em uso.";

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private PermissaoService permissaoService;

    public List<Grupo> listar() {
        return grupoRepository.findAll();
    }

    public Grupo buscar(Long grupoId) {
        return grupoRepository.findById(grupoId)
                .orElseThrow(() -> new GrupoNaoEncontradoException(grupoId));
    }

    @Transactional
    public Grupo salvar(Grupo grupo) {
        return grupoRepository.save(grupo);
    }

    @Transactional
    public void exluir(Long id) {
        try {
            grupoRepository.deleteById(id);
            grupoRepository.flush();
        } catch (EmptyResultDataAccessException ex) {
            throw new GrupoNaoEncontradoException(id);
        } catch (DataIntegrityViolationException ex) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_ENTIDADE_EM_USO, id));
        }
    }

    @Transactional
    public void associar(Long grupoId, Long permissaoId) {
        Grupo grupo = buscar(grupoId);
        Permissao permissao = permissaoService.buscar(permissaoId);
        grupo.adicionarPermissao(permissao);
    }

    @Transactional
    public void desassociar(Long grupoId, Long permissaoId) {
        Grupo grupo = buscar(grupoId);
        Permissao permissao = permissaoService.buscar(permissaoId);
        grupo.removerPermissao(permissao);
    }
}
