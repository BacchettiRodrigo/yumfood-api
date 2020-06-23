package br.com.reignited.yumfood.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@ApiModel("Problema")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
@Setter
public class Problem {

    @ApiModelProperty(example = "400")
    private Integer status;

    @ApiModelProperty(example = "https://yumfood.com.br/dados-invalidos")
    private String type;

    @ApiModelProperty(example = "Dados Inválidos")
    private String title;

    @ApiModelProperty(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.")
    private String detail;

    @ApiModelProperty(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.")
    private String userMessage;

    @ApiModelProperty(example = "2020-03-02T20:11:55.70844Z")
    private OffsetDateTime timestamp;

    @ApiModelProperty(value = "Objetos ou capos que geraram um erro (opcional)", position = 10)
    private List<Object> objects;

    @ApiModel("ObjetoProblema")
    @Builder
    @Getter
    @Setter
    public static class Object {

        @ApiModelProperty(example = "preco")
        private String name;

        @ApiModelProperty(example = "O preço é obrigatório")
        private String userMessage;

    }
}
