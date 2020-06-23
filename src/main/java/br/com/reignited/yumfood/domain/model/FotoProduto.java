package br.com.reignited.yumfood.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name = "foto_produto")
public class FotoProduto {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "produto_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Produto produto;

    @Column(name = "nome_arquivo")
    private String nomeArquivo;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "tamanho")
    private Long tamanho;

    public Long getRestauranteId() {
        if (getProduto() != null) {
            return getProduto().getRestaurante().getId();
        }

        return null;
    }
}
