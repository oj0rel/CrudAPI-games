package com.senac.games.controller;

import com.senac.games.entity.Participante;
import com.senac.games.service.ParticipanteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/participante")
public class ParticipanteController {

    private ParticipanteService participanteService;

    public ParticipanteController (ParticipanteService participanteService) {
        this.participanteService = participanteService;
    }

    @GetMapping("/listar")
    public ResponseEntity <List<Participante>> listarParticipantes() {
        return ResponseEntity.ok(participanteService.listarParticipantes());
    }
}
