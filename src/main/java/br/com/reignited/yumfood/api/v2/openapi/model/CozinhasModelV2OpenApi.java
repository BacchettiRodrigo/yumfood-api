package br.com.reignited.yumfood.api.v2.openapi.model;

import br.com.reignited.yumfood.api.v1.openapi.model.PageModelOpenApi;
import br.com.reignited.yumfood.api.v2.model.CozinhaModelV2;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("CozinhasModel")
@Setter
@Getter
public class CozinhasModelV2OpenApi {

    private CozinhasEmbeddedModelOpenApi _embedded;
    private Links _links;
    private PageModelOpenApi page;

    @ApiModel("CozinhasEmbeddedModel")
    @Data
    public static class CozinhasEmbeddedModelOpenApi {

        private List<CozinhaModelV2> cozinhas;

    }

}
