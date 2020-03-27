package br.com.reignited.yumfood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;

@Getter
@Setter
public class ItemPedidoModel extends RepresentationModel<ItemPedidoModel> {

    @ApiModelProperty(example = "12")
    private Long produtoId;

    @ApiModelProperty(example = "Costela Barbecue Prime")
    private String produtoNome;

    @ApiModelProperty(example = "3")
    private Integer quantidade;

    @ApiModelProperty(example = "24.5")
    private BigDecimal precoUnitario;

    @ApiModelProperty(example = "73.5")
    private BigDecimal precoTotal;

    @ApiModelProperty(example = "Retirar a cebola")
    private String observacao;

}
