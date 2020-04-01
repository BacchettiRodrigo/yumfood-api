package br.com.reignited.yumfood.api.v1.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CidadeInput {

    @ApiModelProperty(example = "São Paulo", required = true)
    @NotBlank
    private String nome;

    @NotNull
    private EstadoIdRef estado;

    @Getter
    @Setter
    public class EstadoIdRef{

        @ApiModelProperty(example = "1", required = true)
        private Long id;
    }

}
