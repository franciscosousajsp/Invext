package br.com.invext.atendimento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.invext.atendimento.entidade.Atendente;
import br.com.invext.atendimento.service.AtendimentoService;

@RestController
@RequestMapping("/atendentes")
public class AtendenteController {
    @Autowired
    private AtendimentoService atendimentoService;

    @GetMapping("/time/{timeId}")
    public List<Atendente> listarAtendentesPorTime(@PathVariable Long timeId) {
        return atendimentoService.listarAtendentesPorTime(timeId);
    }

    @PostMapping
    public Atendente criarAtendente(@RequestBody Atendente atendente) {
        return atendimentoService.criarAtendente(atendente);
    }
}

