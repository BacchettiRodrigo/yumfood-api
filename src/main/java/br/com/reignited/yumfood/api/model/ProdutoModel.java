package br.com.reignited.yumfood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoModel {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Costela Barbecue Prime")
    private String nome;

    @ApiModelProperty(example = "Costela de porco ao molho barbecue especial", required = true)
    private String descricao;

    @ApiModelProperty(example = "55.10")
    private BigDecimal preco;

    @ApiModelProperty(example = "true")
    private Boolean ativo;
    
}
