package br.com.reignited.yumfood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CidadeResumoModel {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Belo Horizonte")
    private String nome;

    @ApiModelProperty(example = "Minas Gerais")
    private String estado;
}
