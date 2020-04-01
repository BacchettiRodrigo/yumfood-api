package br.com.reignited.yumfood.api.v1.assembler;

import br.com.reignited.yumfood.api.v1.YumLinks;
import br.com.reignited.yumfood.api.v1.controller.FormaPagamentoController;
import br.com.reignited.yumfood.api.v1.model.FormaPagamentoModel;
import br.com.reignited.yumfood.domain.model.FormaPagamento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class FormaPagamentoModelAssembler extends RepresentationModelAssemblerSupport<FormaPagamento, FormaPagamentoModel> {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private YumLinks yumLinks;

    public FormaPagamentoModelAssembler() {
        super(FormaPagamentoController.class, FormaPagamentoModel.class);
    }

    @Override
    public FormaPagamentoModel toModel(FormaPagamento source) {
        FormaPagamentoModel model = createModelWithId(source.getId(), source);
        mapper.map(source, model);

        model.add(yumLinks.linkToFormasPagamento("formas-pagamento"));

        return model;
    }

    @Override
    public CollectionModel<FormaPagamentoModel> toCollectionModel(Iterable<? extends FormaPagamento> entities) {
        return super.toCollectionModel(entities).add(yumLinks.linkToFormasPagamento());
    }
}
