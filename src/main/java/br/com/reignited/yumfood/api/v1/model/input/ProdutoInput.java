package br.com.reignited.yumfood.api.v1.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoInput {

    @ApiModelProperty(example = "Costela barbecue prime", required = true)
    @NotBlank
    private String nome;

    @ApiModelProperty(example = "Costela de porco ao molho barbecue especial", required = true)
    @NotBlank
    private String descricao;

    @ApiModelProperty(example = "55.10")
    @NotNull
    @PositiveOrZero
    private BigDecimal preco;

    @ApiModelProperty(example = "true")
    @NotNull
    private Boolean ativo;

}
