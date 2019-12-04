package br.com.reignited.yumfood.domain.model;

import br.com.reignited.yumfood.core.validation.Groups;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "estado")
public class Estado {

	@NotNull(groups = Groups.EstadoId.class)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(name = "nome", nullable = false)
	private String nome;

	public Estado() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Estado)) return false;
		Estado estado = (Estado) o;
		return id.equals(estado.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "Estado{" +
				"id=" + id +
				", nome='" + nome + '\'' +
				'}';
	}
}
