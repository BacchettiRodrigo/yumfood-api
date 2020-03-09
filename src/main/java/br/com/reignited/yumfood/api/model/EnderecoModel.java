package br.com.reignited.yumfood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoModel {

    @ApiModelProperty(example = "12345-123")
    private String cep;

    @ApiModelProperty(example = "Rua das Palmeiras")
    private String logradouro;

    @ApiModelProperty(example = "123")
    private String numero;

    @ApiModelProperty(example = "Edifício das Goiabeiras")
    private String complemento;

    @ApiModelProperty(example = "Centro")
    private String bairro;

    private CidadeResumoModel cidade;
}
