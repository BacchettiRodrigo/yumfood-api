package br.com.reignited.yumfood.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Getter
@Setter
public class RestauranteInput {

    @NotBlank
    private String nome;

    @NotNull
    @PositiveOrZero
    private BigDecimal taxaFrete;

    @NotNull
    private CozinhaIdInput cozinha;

    @Getter
    @Setter
    public class CozinhaIdInput {
        private Long id;
    }
}
