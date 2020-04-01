package br.com.reignited.yumfood.api.v2.model.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@ApiModel("CozinhaInput")
@Getter
@Setter
public class CozinhaInputV2 {

    @ApiModelProperty(example = "Japonesa", required = true)
    @NotBlank
    private String nomeCozinha;
}
