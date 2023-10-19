package br.com.invext.atendimento.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.invext.atendimento.entidade.Atendente;
import br.com.invext.atendimento.entidade.Solicitacao;
import br.com.invext.atendimento.entidade.TimeAtendimento;
import br.com.invext.atendimento.repository.AtendenteRepository;
import br.com.invext.atendimento.repository.SolicitacaoRepository;
import br.com.invext.atendimento.repository.TimeAtendimentoRepository;

@Service
public class AtendimentoService {
	@Autowired
	private AtendenteRepository atendenteRepository;
	@Autowired
	private SolicitacaoRepository solicitacaoRepository;
	@Autowired
	private TimeAtendimentoRepository timeAtendimentoRepository;
	@Autowired
	private FilaEsperaService filaEsperaService;

	public List<Atendente> listarAtendentesPorTime(Long timeId) {
		return atendenteRepository.findByTimeAtendimentoId(timeId);
	}

	public Atendente criarAtendente(Atendente atendente) {
		if (atendente.getTimeAtendimento() == null || atendente.getTimeAtendimento().getId() == null) {
			throw new IllegalArgumentException("O atendente deve pertencer a um time.");
		}

		TimeAtendimento timeAtendimento = timeAtendimentoRepository.findById(atendente.getTimeAtendimento().getId())
				.orElseThrow(() -> new IllegalArgumentException("Time de atendimento não encontrado."));

		if (atendente.getAtendimentosSimultaneos() <= 0) {
			atendente.setAtendimentosSimultaneos(3);
		}

		atendente.setTimeAtendimento(timeAtendimento);

		Atendente novoAtendente = atendenteRepository.save(atendente);
		return novoAtendente;
	}

	public Solicitacao criarSolicitacao(Solicitacao solicitacao) {

		if (solicitacao.getAssunto() == null || solicitacao.getAssunto().isEmpty()) {
			throw new IllegalArgumentException("O assunto da solicitação não pode estar em branco.");
		}

		TimeAtendimento timeAtendimento = determinarTimeAtendimento(solicitacao.getAssunto());

		if (timeAtendimento == null) {
			throw new IllegalArgumentException("Assunto não correspondente a um time de atendimento.");
		}

		Atendente atendenteDisponivel = obterAtendenteDisponivel(timeAtendimento);

		if (atendenteDisponivel != null) {
			solicitacao.setAtendente(atendenteDisponivel);
		} else {
			filaEsperaService.enfileirarSolicitacao(solicitacao, timeAtendimento);
		}

		Solicitacao novaSolicitacao = solicitacaoRepository.save(solicitacao);
		return novaSolicitacao;
	}

	private TimeAtendimento determinarTimeAtendimento(String assunto) {
		if ("Problemas com cartão".equalsIgnoreCase(assunto)) {
			return timeAtendimentoRepository.findByNome("Cartões");
		} else if ("Contratação de empréstimo".equalsIgnoreCase(assunto)) {
			return timeAtendimentoRepository.findByNome("Empréstimos");
		} else {
			return timeAtendimentoRepository.findByNome("Outros Assuntos");
		}
	}

	private Atendente obterAtendenteDisponivel(TimeAtendimento timeAtendimento) {
		List<Atendente> atendentes = atendenteRepository.findByTimeAtendimentoId(timeAtendimento.getId());
		for (Atendente atendente : atendentes) {
			if (atendente.getSolicitacoes().size() < atendente.getAtendimentosSimultaneos()) {
				return atendente;
			}
		}
		return null;
	}

}
