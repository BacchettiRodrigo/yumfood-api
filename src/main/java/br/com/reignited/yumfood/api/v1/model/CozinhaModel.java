package br.com.reignited.yumfood.api.v1.model;

import br.com.reignited.yumfood.api.v1.model.view.RestauranteView;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class CozinhaModel extends RepresentationModel<CozinhaModel> {

    @ApiModelProperty(example = "1")
    @JsonView(RestauranteView.Resumo.class)
    private Long id;

    @ApiModelProperty(example = "Japonesa")
    @JsonView(RestauranteView.Resumo.class)
    private String nome;

}
