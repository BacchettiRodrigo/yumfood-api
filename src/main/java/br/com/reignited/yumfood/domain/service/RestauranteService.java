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

    private static final String MSG_RESTAURANTE_NAO_ENCONTRADO = "Não existe cadastro de restaurante com o código %d";
    private static final String MSG_COZINHA_NAO_ENCONTRADA = "Não existe cadastro de cozinha com o código %d";
    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    public Restaurante buscar(Long id) {
        return restauranteRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format(MSG_RESTAURANTE_NAO_ENCONTRADO, id)));
    }

    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();

        Cozinha cozinha = cozinhaRepository.findById(cozinhaId)
                .orElseThrow(()-> new EntidadeNaoEncontradaException(
                        String.format(MSG_COZINHA_NAO_ENCONTRADA, cozinhaId)));

        restaurante.setCozinha(cozinha);

        return restauranteRepository.save(restaurante);
    }
}
