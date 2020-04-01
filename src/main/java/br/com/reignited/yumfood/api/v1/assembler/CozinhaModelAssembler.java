package br.com.reignited.yumfood.api.v1.assembler;

import br.com.reignited.yumfood.api.v1.YumLinks;
import br.com.reignited.yumfood.api.v1.controller.CozinhaController;
import br.com.reignited.yumfood.api.v1.model.CozinhaModel;
import br.com.reignited.yumfood.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class CozinhaModelAssembler extends RepresentationModelAssemblerSupport<Cozinha, CozinhaModel> {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private YumLinks yumLinks;

    public CozinhaModelAssembler() {
        super(CozinhaController.class, CozinhaModel.class);
    }

    @Override
    public CozinhaModel toModel(Cozinha source) {
        CozinhaModel cozinhaModel = createModelWithId(source.getId(), source);
        mapper.map(source, cozinhaModel);

        cozinhaModel.add(yumLinks.linkToCozinhas("cidades"));

        return cozinhaModel;
    }

}
