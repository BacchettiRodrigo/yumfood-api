package br.com.reignited.yumfood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrupoModel {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Administração")
    private String nome;
}
