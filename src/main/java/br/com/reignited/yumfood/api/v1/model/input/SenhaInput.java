package br.com.reignited.yumfood.api.v1.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class SenhaInput {

    @ApiModelProperty(example = "12345", required = true)
    @NotBlank
    private String senhaAtual;

    @ApiModelProperty(example = "54321", required = true)
    @NotBlank
    private String novaSenha;

}
