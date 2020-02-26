package br.com.reignited.yumfood.domain.repository;

import br.com.reignited.yumfood.domain.model.FormaPagamento;
import br.com.reignited.yumfood.domain.repository.custom.CustomJpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;

@Repository
public interface FormaPagamentoRepository extends CustomJpaRepository<FormaPagamento, Long> {

    @Query("select max(dataAtualizacao) from FormaPagamento")
    OffsetDateTime getDataUltimaAtualizacao();

    @Query("select dataAtualizacao from FormaPagamento where id = :formaPagamentoId")
    OffsetDateTime getDataAtualizacaoById(Long formaPagamentoId);
}
