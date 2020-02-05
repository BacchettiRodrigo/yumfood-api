package br.com.reignited.yumfood.domain.service;

import br.com.reignited.yumfood.domain.filter.VendaDiariaFilter;
import br.com.reignited.yumfood.domain.model.dto.VendaDiaria;

import java.util.List;

public interface VendaQueryService {

    List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, String timeOffSet);

}
