package br.com.reignited.yumfood.domain.model;

import br.com.reignited.yumfood.domain.event.PedidoCanceladoEvent;
import br.com.reignited.yumfood.domain.event.PedidoConfirmadoEvent;
import br.com.reignited.yumfood.domain.exception.NegocioException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(name = "pedido")
public class Pedido extends AbstractAggregateRoot<Pedido> {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo")
    private String codigo;

    @ManyToOne
    @JoinColumn(name = "restaurante_id", nullable = false)
    private Restaurante restaurante;

    @ManyToOne
    @JoinColumn(name = "usuario_cliente_id", nullable = false)
    private Usuario cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "forma_pagamento_id", nullable = false)
    private FormaPagamento formaPagamento;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itens;

    @Column(name = "subtotal")
    private BigDecimal subtotal;

    @Column(name = "taxa_frete")
    private BigDecimal taxaFrete;

    @Column(name = "valor_total")
    private BigDecimal valorTotal;

    @CreationTimestamp
    private OffsetDateTime dataCriacao;

    @Column(name = "data_confirmacao")
    private OffsetDateTime dataConfirmacao;

    @Column(name = "data_cancelamento")
    private OffsetDateTime dataCancelamento;

    @Column(name = "data_entrega")
    private OffsetDateTime dataEntrega;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusPedido status = StatusPedido.CRIADO;

    @Embedded
    private Endereco enderecoEntrega;

    public void confirmar() {
        setStatus(StatusPedido.CONFIRMADO);
        setDataConfirmacao(OffsetDateTime.now());

        registerEvent(new PedidoConfirmadoEvent(this));
    }

    public void entregar() {
        setStatus(StatusPedido.ENTREGUE);
        setDataEntrega(OffsetDateTime.now());
    }

    public void cancelar() {
        setStatus(StatusPedido.CANCELADO);
        setDataCancelamento(OffsetDateTime.now());

        registerEvent(new PedidoCanceladoEvent(this));
    }

    private void setStatus(StatusPedido status) {
        if (getStatus().naoPodeAlterarPara(status)) {
            throw new NegocioException(String.format("Status do pedido %d não pode ser alterado de %s para %s.",
                    getId(), getStatus().getDescricao(), status.getDescricao()));
        }

        this.status = status;
    }

    public void calcularValorTotal() {
        getItens().forEach(ItemPedido::calcularPrecoTotal);

        this.subtotal = getItens().stream()
                .map(ItemPedido::getPrecoTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        setValorTotal(getSubtotal().add(getTaxaFrete()));
    }

    @PrePersist
    private void gerarCodigo(){
        setCodigo(UUID.randomUUID().toString());
    }

}
