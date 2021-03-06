package br.com.reignited.yumfood.api.v1.controller;

import br.com.reignited.yumfood.api.v1.YumLinks;
import br.com.reignited.yumfood.api.v1.openapi.controller.EstatisticasControllerOpenApi;
import br.com.reignited.yumfood.core.security.CheckSecurity;
import br.com.reignited.yumfood.domain.filter.VendaDiariaFilter;
import br.com.reignited.yumfood.domain.model.dto.VendaDiaria;
import br.com.reignited.yumfood.domain.service.VendaQueryService;
import br.com.reignited.yumfood.domain.service.VendaReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/estatisticas")
public class EstatisticasController implements EstatisticasControllerOpenApi {

    @Autowired
    private YumLinks yumLinks;

    @Autowired
    private VendaQueryService vendaQueryService;

    @Autowired
    private VendaReportService vendaReportService;

    public static class EstatisticasModel extends RepresentationModel<EstatisticasModel> {
    }

    @CheckSecurity.Estatisticas.PodeConsultar
    @GetMapping
    public EstatisticasModel estatisticas() {
        var estatisticasModel = new EstatisticasModel();
        estatisticasModel.add(yumLinks.linkToEstatisticasVendasDiarias("vendas-diarias"));

        return estatisticasModel;
    }

    @CheckSecurity.Estatisticas.PodeConsultar
    @GetMapping(path = "/vendas-diarias", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VendaDiaria> consultarVendasDiarias(
            VendaDiariaFilter filtro,
            @RequestParam(required = false, defaultValue = "+00:00") String timeOffSet) {

        return vendaQueryService.consultarVendasDiarias(filtro, timeOffSet);
    }

    @CheckSecurity.Estatisticas.PodeConsultar
    @GetMapping(path = "/vendas-diarias", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> consultarVendasDiariasPdf(
            VendaDiariaFilter filtro,
            @RequestParam(required = false, defaultValue = "+00:00") String timeOffSet) {

        byte[] bytesPdf = vendaReportService.emitirVendasDiarias(filtro, timeOffSet);

        var headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=vendas-diarias.pdf");

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .headers(headers)
                .body(bytesPdf);
    }
}
