package br.com.reignited.yumfood.api.v1.openapi.model;

import br.com.reignited.yumfood.api.v1.model.GrupoModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("GruposModel")
@Data
public class GruposModelOpenApi {

    private GruposEmbeddedModelOpenApi _embedded;
    private Links _links;

    @ApiModel("GruposEmbeddedModel")
    @Data
    public class GruposEmbeddedModelOpenApi {

        private List<GrupoModel> grupos;

    }
}
