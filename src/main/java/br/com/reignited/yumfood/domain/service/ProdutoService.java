package br.com.reignited.yumfood.domain.service;

import br.com.reignited.yumfood.domain.exception.ProdutoNaoEncontradoException;
import br.com.reignited.yumfood.domain.model.Produto;
import br.com.reignited.yumfood.domain.model.Restaurante;
import br.com.reignited.yumfood.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto buscar(Long restauranteId, Long produtoId) {
        return produtoRepository.findById(restauranteId, produtoId)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(restauranteId, produtoId));
    }

    public List<Produto> buscarProdutosPorRestaurante(Restaurante restaurante) {
        return produtoRepository.findByRestaurante(restaurante);
    }

    public List<Produto> buscarAtivosPorRestaurante(Restaurante restaurante) {
        return produtoRepository.findAtivosByRestaurante(restaurante);
    }

    @Transactional
    public Produto salvar (Produto produto) {
        return produtoRepository.save(produto);
    }
}
