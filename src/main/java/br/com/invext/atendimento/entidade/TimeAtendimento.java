package br.com.invext.atendimento.entidade;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class TimeAtendimento implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;

	@JsonIgnore
	@OneToMany(mappedBy = "timeAtendimento")
	private List<Atendente> atendentes;


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

	public List<Atendente> getAtendentes() {
		return atendentes;
	}

	public void setAtendentes(List<Atendente> atendentes) {
		this.atendentes = atendentes;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		TimeAtendimento timeAtendimento = (TimeAtendimento) o;
		return Objects.equals(id, timeAtendimento.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	

}
