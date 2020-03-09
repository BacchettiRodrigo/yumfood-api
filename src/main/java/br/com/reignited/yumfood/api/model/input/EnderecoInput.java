package br.com.reignited.yumfood.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class EnderecoInput {

    @ApiModelProperty(example = "12345-123", required = true)
    @NotBlank
    private String cep;

    @ApiModelProperty(example = "Rua das Palmeiras", required = true)
    @NotBlank
    private String logradouro;

    @ApiModelProperty(example = "123", required = true)
    @NotBlank
    private String numero;

    @ApiModelProperty(example = "Edifício das Goiabeiras", required = true)
    private String complemento;

    @ApiModelProperty(example = "Centro", required = true)
    @NotBlank
    private String bairro;

    @Valid
    @NotNull
    private CidadeIdInput cidade;
}
