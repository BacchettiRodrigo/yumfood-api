package br.com.reignited.yumfood.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name = "item_pedido")
public class ItemPedido {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @Column(name = "quantidade")
    private Integer quantidade;

    @Column(name = "preco_unitario")
    private BigDecimal precoUnitario;

    @Column(name = "preco_total")
    private BigDecimal precoTotal;

    @Column(name = "observacao")
    private String observacao;

    public void calcularPrecoTotal() {
        BigDecimal precoUnitario = getPrecoUnitario();
        Integer quantidade = getQuantidade();

        if (precoUnitario == null) {
            precoUnitario = BigDecimal.ZERO;
        }

        if (quantidade == null) {
            quantidade = 0;
        }

        setPrecoTotal(precoUnitario.multiply(new BigDecimal(quantidade)));
    }
}
