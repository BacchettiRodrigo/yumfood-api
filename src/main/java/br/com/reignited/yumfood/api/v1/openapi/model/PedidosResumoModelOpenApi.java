package br.com.reignited.yumfood.api.v1.openapi.model;

import br.com.reignited.yumfood.api.v1.model.PedidoResumoModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("PedidosResumoModel")
@Data
public class PedidosResumoModelOpenApi {

    private PedidosEmbeddedModelOpenApi _embedded;
    private Links _links;
    private PageModelOpenApi page;

    @ApiModel("CozinhasEmbeddedModel")
    @Data
    public class PedidosEmbeddedModelOpenApi {

        private List<PedidoResumoModel> pedidos;

    }
}
