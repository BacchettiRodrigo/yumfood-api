package br.com.reignited.yumfood.api.v1.openapi.model;

import br.com.reignited.yumfood.api.v1.model.RestauranteBasicoModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("RestaurantesBasicosModel")
@Data
public class RestaurantesBasicosModelOpenApi {

    private RestaurantesEmbeddedModelOpenApi _embedded;
    private Links _links;

    @ApiModel("RestaurantesEmbeddedModel")
    @Data
    public class RestaurantesEmbeddedModelOpenApi {

        private List<RestauranteBasicoModel> restaurantes;

    }
}
