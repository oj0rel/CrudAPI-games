package com.senac.games.service;

import com.senac.games.entity.Patrocinador;
import com.senac.games.repository.PatrocinadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatrocinadorService {

    private PatrocinadorRepository patrocinadorRepository; //injetando o repository

    public PatrocinadorService(PatrocinadorRepository patrocinadorRepository) { //construtor
        this.patrocinadorRepository = patrocinadorRepository;
    }

    public List<Patrocinador> listarPatrocinadores() {
        return this.patrocinadorRepository.findAll();
    }
}
