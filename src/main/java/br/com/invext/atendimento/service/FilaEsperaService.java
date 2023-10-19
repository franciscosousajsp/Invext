package br.com.invext.atendimento.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.springframework.stereotype.Service;

import br.com.invext.atendimento.entidade.Solicitacao;
import br.com.invext.atendimento.entidade.TimeAtendimento;

@Service
public class FilaEsperaService {
    private Map<TimeAtendimento, Queue<Solicitacao>> filaEspera = new HashMap<>();

    public void enfileirarSolicitacao(Solicitacao solicitacao, TimeAtendimento timeAtendimento) {
        if (!filaEspera.containsKey(timeAtendimento)) {
            filaEspera.put(timeAtendimento, new ConcurrentLinkedQueue<>());
        }
        Queue<Solicitacao> fila = filaEspera.get(timeAtendimento);
        fila.add(solicitacao);
    }

    public Solicitacao obterProximaSolicitacao(TimeAtendimento timeAtendimento) {
        if (filaEspera.containsKey(timeAtendimento)) {
            Queue<Solicitacao> fila = filaEspera.get(timeAtendimento);
            if (!fila.isEmpty()) {
                return fila.poll();
            }
        }
        return null;
    }
}

