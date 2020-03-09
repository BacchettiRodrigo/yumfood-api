package br.com.reignited.yumfood.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
public class ItemPedidoInput {

    @ApiModelProperty(example = "12")
    @NotNull
    private Long produtoId;

    @ApiModelProperty(example = "2")
    @NotNull
    @PositiveOrZero
    private Integer quantidade;

    @ApiModelProperty(example = "Retirar a cebola")
    private String observacao;
}
