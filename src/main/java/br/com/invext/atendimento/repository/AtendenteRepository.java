package br.com.invext.atendimento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.invext.atendimento.entidade.Atendente;

public interface AtendenteRepository extends JpaRepository<Atendente, Long> {

    @Query("SELECT a FROM Atendente a WHERE a.timeAtendimento.id = :timeId")
    List<Atendente> findByTimeAtendimentoId(@Param("timeId") Long timeId);
}
