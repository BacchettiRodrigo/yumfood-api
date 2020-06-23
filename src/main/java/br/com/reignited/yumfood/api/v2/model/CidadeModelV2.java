package br.com.reignited.yumfood.api.v2.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@ApiModel("CidadeModel")
@Relation(collectionRelation = "cidades")
@Getter
@Setter
public class CidadeModelV2 extends RepresentationModel<CidadeModelV2> {

    @ApiModelProperty(example = "1")
    private Long idCidade;

    @ApiModelProperty(example = "Campinas")
    private String nomeCidade;

    @ApiModelProperty(example = "1")
    private Long idEstado;

    @ApiModelProperty(example = "São Paulo")
    private String nomeEstado;

    //private EstadoModel estado;
}
