package br.com.invext.atendimento.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.invext.atendimento.entidade.TimeAtendimento;
import br.com.invext.atendimento.repository.TimeAtendimentoRepository;

@Service
public class TimeAtendimentoService {
    @Autowired
    private TimeAtendimentoRepository timeAtendimentoRepository;

    public TimeAtendimento criarTimeAtendimento(TimeAtendimento timeAtendimento) {
        return timeAtendimentoRepository.save(timeAtendimento);
    }
}

