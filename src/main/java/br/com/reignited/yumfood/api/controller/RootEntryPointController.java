package br.com.reignited.yumfood.api.controller;

import br.com.reignited.yumfood.api.YumLinks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class RootEntryPointController {

    @Autowired
    private YumLinks yumLinks;

    private static class RootEntryPointModel extends RepresentationModel<RootEntryPointModel> {

    }

    @GetMapping
    public RootEntryPointModel root() {
        var rootEntryPointModel = new RootEntryPointModel();

        rootEntryPointModel.add(yumLinks.linkToCozinhas("cozinhas"));
        rootEntryPointModel.add(yumLinks.linkToPedidos("pedidos"));
        rootEntryPointModel.add(yumLinks.linkToRestaurantes("restaurantes"));
        rootEntryPointModel.add(yumLinks.linkToGrupos("grupos"));
        rootEntryPointModel.add(yumLinks.linkToUsuarios("usuarios"));
        rootEntryPointModel.add(yumLinks.linkToPermissoes("permissoes"));
        rootEntryPointModel.add(yumLinks.linkToFormasPagamento("formas-pagamento"));
        rootEntryPointModel.add(yumLinks.linkToEstados("estados"));
        rootEntryPointModel.add(yumLinks.linkToCidades("cidades"));
        rootEntryPointModel.add(yumLinks.linkToEstatisticas("estatisticas"));

        return rootEntryPointModel;
    }
}
