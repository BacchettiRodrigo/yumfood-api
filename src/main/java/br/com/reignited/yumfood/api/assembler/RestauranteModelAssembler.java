package br.com.reignited.yumfood.api.assembler;

import br.com.reignited.yumfood.api.YumLinks;
import br.com.reignited.yumfood.api.controller.RestauranteController;
import br.com.reignited.yumfood.api.model.RestauranteModel;
import br.com.reignited.yumfood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestauranteModelAssembler extends RepresentationModelAssemblerSupport<Restaurante, RestauranteModel> {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private YumLinks yumLinks;

    public RestauranteModelAssembler() {
        super(RestauranteController.class, RestauranteModel.class);
    }

    @Override
    public RestauranteModel toModel(Restaurante source) {
        RestauranteModel model = createModelWithId(source.getId(), source);
        mapper.map(source, model);

        model.getCozinha().add(yumLinks.linkToCozinha(model.getCozinha().getId()));
        model.add(yumLinks.linkToRestaurantes("restaurantes"));
        model.add(yumLinks.linkToRestauranteFormasPagamento(model.getId(), "formas-pagamento"));
        model.add(yumLinks.linkToRestauranteResponsavel(model.getId(), "responsaveis"));
        model.add(yumLinks.linkToProdutos(model.getId(), "produtos"));

        if (model.getEndereco() != null && model.getEndereco().getCidade() != null) {
            model.getEndereco().getCidade().add(yumLinks.linkToCidade(model.getEndereco().getCidade().getId()));
        }

        if (source.ativacaoPermitida()) {
            model.add(yumLinks.linkToAtivarRestaurante(source.getId(), "ativar"));
        }

        if (source.inativacaoPermitida()) {
            model.add(yumLinks.linkToInativarRestaurante(source.getId(), "inativar"));
        }

        if (source.aberturaPermitida()) {
            model.add(yumLinks.linkToAbrirRestaurante(source.getId(), "abrir"));
        }

        if (source.fechamentoPermitido()) {
            model.add(yumLinks.linkToFecharRestaurante(source.getId(), "fechar"));
        }

        return model;
    }

    @Override
    public CollectionModel<RestauranteModel> toCollectionModel(Iterable<? extends Restaurante> entities) {
        return super.toCollectionModel(entities).add(yumLinks.linkToRestaurantes());
    }
}
