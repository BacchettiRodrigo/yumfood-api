package br.com.reignited.yumfood.api.v2.assembler;

import br.com.reignited.yumfood.api.v1.YumLinks;
import br.com.reignited.yumfood.api.v1.controller.CozinhaController;
import br.com.reignited.yumfood.api.v1.model.CozinhaModel;
import br.com.reignited.yumfood.api.v2.YumLinksV2;
import br.com.reignited.yumfood.api.v2.controller.CozinhaControllerV2;
import br.com.reignited.yumfood.api.v2.model.CozinhaModelV2;
import br.com.reignited.yumfood.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class CozinhaModelAssemblerV2 extends RepresentationModelAssemblerSupport<Cozinha, CozinhaModelV2> {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private YumLinksV2 yumLinks;

    public CozinhaModelAssemblerV2() {
        super(CozinhaControllerV2.class, CozinhaModelV2.class);
    }

    @Override
    public CozinhaModelV2 toModel(Cozinha source) {
        CozinhaModelV2 cozinhaModel = createModelWithId(source.getId(), source);
        mapper.map(source, cozinhaModel);

        cozinhaModel.add(yumLinks.linkToCozinhas("cozinhas"));

        return cozinhaModel;
    }

}
