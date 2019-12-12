package br.com.reignited.yumfood.domain.service;

import br.com.reignited.yumfood.domain.exception.CozinhaNaoEncontradaException;
import br.com.reignited.yumfood.domain.exception.EntidadeNaoEncontradaException;
import br.com.reignited.yumfood.domain.exception.RestauranteNaoEncontradoException;
import br.com.reignited.yumfood.domain.model.Cozinha;
import br.com.reignited.yumfood.domain.model.Restaurante;
import br.com.reignited.yumfood.domain.repository.CozinhaRepository;
import br.com.reignited.yumfood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    public Restaurante buscar(Long id) {
        return restauranteRepository.findById(id)
                .orElseThrow(() -> new RestauranteNaoEncontradoException(id));
    }

    @Transactional
    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();

        Cozinha cozinha = cozinhaRepository.findById(cozinhaId)
                .orElseThrow(()-> new CozinhaNaoEncontradaException(cozinhaId));

        restaurante.setCozinha(cozinha);

        return restauranteRepository.save(restaurante);
    }
}
