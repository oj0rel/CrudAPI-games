package com.senac.games.service;

import com.senac.games.entity.Participante;
import com.senac.games.repository.ParticipanteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipanteService {
    private ParticipanteRepository participanteRepository;

    public ParticipanteService(ParticipanteRepository participanteRepository) {
        this.participanteRepository = participanteRepository;
    }

    public List<Participante> listarParticipantes() { return this.participanteRepository.findAll(); }
}
