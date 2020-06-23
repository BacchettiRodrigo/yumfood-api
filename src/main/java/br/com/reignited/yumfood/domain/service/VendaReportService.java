package br.com.reignited.yumfood.domain.service;

import br.com.reignited.yumfood.domain.filter.VendaDiariaFilter;

public interface VendaReportService {

    byte[] emitirVendasDiarias(VendaDiariaFilter filtro, String timeOffSet);

}
