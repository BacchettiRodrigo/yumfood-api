package br.com.reignited.yumfood.api.v1.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class FormaPagamentoIdInput {

    @ApiModelProperty(example = "3")
    @NotNull
    private Long id;

}
