package br.com.reignited.yumfood.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class CidadeIdInput {

    @ApiModelProperty(example = "12")
    @NotNull
    private Long id;
}
