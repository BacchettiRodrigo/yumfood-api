package br.com.reignited.yumfood.api.v1.assembler;

import br.com.reignited.yumfood.api.v1.YumLinks;
import br.com.reignited.yumfood.api.v1.controller.RestauranteController;
import br.com.reignited.yumfood.api.v1.model.RestauranteModel;
import br.com.reignited.yumfood.core.security.YumSecurity;
import br.com.reignited.yumfood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class RestauranteModelAssembler extends RepresentationModelAssemblerSupport<Restaurante, RestauranteModel> {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private YumLinks yumLinks;

    @Autowired
    private YumSecurity yumSecurity;

    public RestauranteModelAssembler() {
        super(RestauranteController.class, RestauranteModel.class);
    }

    @Override
    public RestauranteModel toModel(Restaurante source) {
        RestauranteModel model = createModelWithId(source.getId(), source);
        mapper.map(source, model);

        if (yumSecurity.podeConsultarCozinhas()) {
            model.getCozinha().add(yumLinks.linkToCozinha(model.getCozinha().getId()));
            model.add(yumLinks.linkToProdutos(model.getId(), "produtos"));
            model.add(yumLinks.linkToRestauranteFormasPagamento(model.getId(), "formas-pagamento"));
        }

        if (yumSecurity.podeConsultarRestaurantes()) {
            model.add(yumLinks.linkToRestaurantes("restaurantes"));
        }

        if (yumSecurity.podeGerenciarCadastroRestaurantes()) {
            if (source.ativacaoPermitida()) {
                model.add(yumLinks.linkToAtivarRestaurante(source.getId(), "ativar"));
            }

            if (source.inativacaoPermitida()) {
                model.add(yumLinks.linkToInativarRestaurante(source.getId(), "inativar"));
            }

            model.add(yumLinks.linkToRestauranteResponsavel(model.getId(), "responsaveis"));
        }

        if (yumSecurity.podeGerenciarFuncionamentoRestaurantes(model.getId())) {
            if (source.aberturaPermitida()) {
                model.add(yumLinks.linkToAbrirRestaurante(source.getId(), "abrir"));
            }

            if (source.fechamentoPermitido()) {
                model.add(yumLinks.linkToFecharRestaurante(source.getId(), "fechar"));
            }
        }

        if (yumSecurity.podeConsultarCidades()) {
            if (model.getEndereco() != null && model.getEndereco().getCidade() != null) {
                model.getEndereco().getCidade().add(yumLinks.linkToCidade(model.getEndereco().getCidade().getId()));
            }
        }

        return model;
    }

    @Override
    public CollectionModel<RestauranteModel> toCollectionModel(Iterable<? extends Restaurante> entities) {
        CollectionModel<RestauranteModel> collection = super.toCollectionModel(entities);

        if (yumSecurity.podeConsultarRestaurantes()) {
            collection.add(yumLinks.linkToRestaurantes());
        }

        return collection;
    }
}
