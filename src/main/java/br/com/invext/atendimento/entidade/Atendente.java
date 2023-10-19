package br.com.invext.atendimento.entidade;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Atendente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private int atendimentosSimultaneos;


	@ManyToOne
	@JoinColumn(name = "time_atendimento_id")
	private TimeAtendimento timeAtendimento;

	@OneToMany(mappedBy = "atendente")
	private List<Solicitacao> solicitacoes;


	// Getters e setters para os campos
	public String getNome() {
		return nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getAtendimentosSimultaneos() {
		return atendimentosSimultaneos;
	}

	public void setAtendimentosSimultaneos(int atendimentosSimultaneos) {
		this.atendimentosSimultaneos = atendimentosSimultaneos;
	}

	public List<Solicitacao> getSolicitacoes() {
		return solicitacoes;
	}

	public void setSolicitacoes(List<Solicitacao> solicitacoes) {
		this.solicitacoes = solicitacoes;
	}

	public TimeAtendimento getTimeAtendimento() {
		return timeAtendimento;
	}

	public void setTimeAtendimento(TimeAtendimento timeAtendimento) {
		this.timeAtendimento = timeAtendimento;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Atendente atendente = (Atendente) o;
		return Objects.equals(id, atendente.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

}
