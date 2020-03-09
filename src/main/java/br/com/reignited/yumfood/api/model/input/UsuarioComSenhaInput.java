package br.com.reignited.yumfood.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UsuarioComSenhaInput {

    @ApiModelProperty(example = "João da Silva", required = true)
    @NotBlank
    private String nome;

    @ApiModelProperty(example = "joao_silva@yumfood.com", required = true)
    @Email
    @NotBlank
    private String email;

    @ApiModelProperty(example = "12345", required = true)
    @NotBlank
    private String senha;

}
