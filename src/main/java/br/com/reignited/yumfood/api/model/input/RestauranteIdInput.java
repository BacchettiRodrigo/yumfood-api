package br.com.reignited.yumfood.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RestauranteIdInput {

    @ApiModelProperty(example = "2")
    @NotNull
    private Long id;

}
