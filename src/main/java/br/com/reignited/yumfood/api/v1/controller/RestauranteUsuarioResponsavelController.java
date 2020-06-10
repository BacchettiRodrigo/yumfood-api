package br.com.reignited.yumfood.api.v1.controller;

import br.com.reignited.yumfood.api.v1.YumLinks;
import br.com.reignited.yumfood.api.v1.assembler.UsuarioModelAssembler;
import br.com.reignited.yumfood.api.v1.model.UsuarioModel;
import br.com.reignited.yumfood.api.v1.openapi.controller.RestauranteUsuarioResponsavelControllerOpenApi;
import br.com.reignited.yumfood.core.security.CheckSecurity;
import br.com.reignited.yumfood.domain.model.Restaurante;
import br.com.reignited.yumfood.domain.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/restaurantes/{restauranteId}/responsaveis")
public class RestauranteUsuarioResponsavelController implements RestauranteUsuarioResponsavelControllerOpenApi {

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private UsuarioModelAssembler usuarioModelAssembler;

    @Autowired
    private YumLinks yumLinks;

    @CheckSecurity.Restaurantes.PodeEditar
    @GetMapping
    public CollectionModel<UsuarioModel> listar(@PathVariable Long restauranteId) {
        Restaurante restaurante = restauranteService.buscar(restauranteId);

        CollectionModel<UsuarioModel> usuarioModels = usuarioModelAssembler.toCollectionModel(restaurante.getReponsaveis())
                .removeLinks()
                .add(yumLinks.linkToRestauranteResponsavel(restauranteId))
                .add(yumLinks.linkToRestauranteFormaPagamentoAssociacao(restauranteId, "associar"));

        usuarioModels.getContent().forEach(usuario -> {
            usuario.add(yumLinks.linkToRestauranteFormaPagamentoDesassociacao(
                    restauranteId, usuario.getId(), "desassociar"));
        });

        return usuarioModels;
    }

    @CheckSecurity.Restaurantes.PodeEditar
    @PutMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
        restauranteService.adicionarResponsavel(restauranteId, usuarioId);
        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.Restaurantes.PodeEditar
    @DeleteMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> desassociar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
        restauranteService.removerResponsavel(restauranteId, usuarioId);
        return ResponseEntity.noContent().build();
    }
}
