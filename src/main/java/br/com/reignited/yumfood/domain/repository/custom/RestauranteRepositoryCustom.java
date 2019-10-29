package br.com.reignited.yumfood.domain.repository.custom;

import br.com.reignited.yumfood.domain.model.Restaurante;

import java.util.List;

public interface RestauranteRepositoryCustom {

    List<Restaurante> findComFreteGratis(String nome);
}
