package br.com.invext.atendimento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.invext.atendimento.entidade.Solicitacao;

public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long>{

}
