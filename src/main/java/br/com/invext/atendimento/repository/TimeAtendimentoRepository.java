package br.com.invext.atendimento.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.invext.atendimento.entidade.TimeAtendimento;

@Repository
public interface TimeAtendimentoRepository extends JpaRepository<TimeAtendimento, Long> {
    TimeAtendimento findByNome(String nome); 
}
