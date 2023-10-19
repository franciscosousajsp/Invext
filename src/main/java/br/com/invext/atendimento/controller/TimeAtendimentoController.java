package br.com.invext.atendimento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.invext.atendimento.entidade.TimeAtendimento;
import br.com.invext.atendimento.service.TimeAtendimentoService;

@RestController
@RequestMapping("/timeatendimentos")
public class TimeAtendimentoController {
    @Autowired
    private TimeAtendimentoService timeAtendimentoService;

    @PostMapping
    public TimeAtendimento criarTimeAtendimento(@RequestBody TimeAtendimento timeAtendimento) {
        return timeAtendimentoService.criarTimeAtendimento(timeAtendimento);
    }
}
