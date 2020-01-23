package br.com.reignited.yumfood.domain.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "senha", nullable = false)
    private String senha;

    @Column(name = "email", nullable = false)
    private String email;

    @CreationTimestamp
    @Column(name = "data_cadastro", nullable = false, columnDefinition = "datetime(6)")
    private OffsetDateTime dataCadastro;

    @ManyToMany
    @JoinTable(name = "usuario_grupo",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "grupo_id"))
    private Set<Grupo> grupos = new HashSet<>();

    public boolean adicionarGrupo (Grupo grupo) {
        return getGrupos().add(grupo);
    }

    public boolean removerGrupo (Grupo grupo) {
        return getGrupos().remove(grupo);
    }

    public boolean senhaCoincideCom(String senha) {
        return getSenha().equals(senha);
    }

    public boolean senhaNaoCoincideCom(String senha) {
        return !senhaCoincideCom(senha);
    }
}
