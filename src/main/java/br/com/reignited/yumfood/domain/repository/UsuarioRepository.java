package br.com.reignited.yumfood.domain.repository;

import br.com.reignited.yumfood.domain.model.Usuario;
import br.com.reignited.yumfood.domain.repository.custom.CustomJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends CustomJpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

}
