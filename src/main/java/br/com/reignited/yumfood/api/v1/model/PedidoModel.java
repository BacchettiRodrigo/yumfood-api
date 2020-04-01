package br.com.reignited.yumfood.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
public class PedidoModel extends RepresentationModel<PedidoModel> {

    @ApiModelProperty(example = "6063650e-5e03-4c74-9ad5-04ec2c6d5f04")
    private String codigo;

    @ApiModelProperty(example = "57.55")
    private BigDecimal subtotal;

    @ApiModelProperty(example = "11.0")
    private BigDecimal taxaFrete;

    @ApiModelProperty(example = "68.55")
    private BigDecimal valorTotal;

    @ApiModelProperty(example = "CRIADO")
    private String status;

    @ApiModelProperty(example = "2020-03-02T20:11:55.70844Z")
    private OffsetDateTime dataCriacao;

    @ApiModelProperty(example = "2020-03-02T20:11:55.70844Z")
    private OffsetDateTime dataConfirmacao;

    @ApiModelProperty(example = "2020-03-02T20:11:55.70844Z")
    private OffsetDateTime dataEntraga;

    @ApiModelProperty(example = "2020-03-02T20:11:55.70844Z")
    private OffsetDateTime dataCancelamento;

    private RestauranteResumoModel restaurante;
    private UsuarioModel cliente;
    private FormaPagamentoModel formaPagamento;
    private EnderecoModel enderecoEntrega;
    private List<ItemPedidoModel> itens;

}
