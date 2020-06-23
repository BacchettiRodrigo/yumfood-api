package br.com.reignited.yumfood.api.v1.controller;

import br.com.reignited.yumfood.api.v1.YumLinks;
import br.com.reignited.yumfood.core.security.YumSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class RootEntryPointController {

    @Autowired
    private YumLinks yumLinks;

    @Autowired
    private YumSecurity yumSecurity;

    private static class RootEntryPointModel extends RepresentationModel<RootEntryPointModel> {

    }

    @GetMapping
    public RootEntryPointModel root() {
        var rootEntryPointModel = new RootEntryPointModel();

        if (yumSecurity.podeConsultarCozinhas()) {
            rootEntryPointModel.add(yumLinks.linkToCozinhas("cozinhas"));
        }
        if (yumSecurity.podePesquisarPedidos()) {
            rootEntryPointModel.add(yumLinks.linkToPedidos("pedidos"));
        }
        if (yumSecurity.podeConsultarRestaurantes()) {
            rootEntryPointModel.add(yumLinks.linkToRestaurantes("restaurantes"));
        }
        if (yumSecurity.podeConsultarUsuariosGruposPermissoes()) {
            rootEntryPointModel.add(yumLinks.linkToGrupos("grupos"));
            rootEntryPointModel.add(yumLinks.linkToUsuarios("usuarios"));
            rootEntryPointModel.add(yumLinks.linkToPermissoes("permissoes"));
        }
        if (yumSecurity.podeConsultarFormasPagamento()) {
            rootEntryPointModel.add(yumLinks.linkToFormasPagamento("formas-pagamento"));
        }
        if (yumSecurity.podeConsultarEstados()) {
            rootEntryPointModel.add(yumLinks.linkToEstados("estados"));
        }
        if (yumSecurity.podeConsultarCidades()) {
            rootEntryPointModel.add(yumLinks.linkToCidades("cidades"));
        }
        if (yumSecurity.podeConsultarEstatisticas()) {
            rootEntryPointModel.add(yumLinks.linkToEstatisticas("estatisticas"));
        }

        return rootEntryPointModel;
    }
}
