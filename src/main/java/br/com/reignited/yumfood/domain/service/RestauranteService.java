package br.com.reignited.yumfood.domain.service;

import br.com.reignited.yumfood.domain.exception.EntidadeNaoEncontradaException;
import br.com.reignited.yumfood.domain.model.Cozinha;
import br.com.reignited.yumfood.domain.model.Restaurante;
import br.com.reignited.yumfood.domain.repository.CozinhaRepository;
import br.com.reignited.yumfood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public List<Restaurante> listar(){
        return restauranteRepository.listar();
    }

    public Restaurante buscar(Long id){
        return restauranteRepository.buscar(id);
    }

    public Restaurante salvar(Restaurante restaurante){
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);

        if(cozinha == null){
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe cadastro de cozinha com o código %d", cozinhaId));
        }

        restaurante.setCozinha(cozinha);

        return restauranteRepository.salvar(restaurante);
    }
}
