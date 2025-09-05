package com.senac.games.service;

import com.senac.games.dto.request.patrocinador.PatrocinadorDTORequest;
import com.senac.games.dto.response.patrocinador.PatrocinadorDTOResponse;
import com.senac.games.entity.Patrocinador;
import com.senac.games.repository.PatrocinadorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatrocinadorService {
    private final PatrocinadorRepository patrocinadorRepository;

    @Autowired
    private ModelMapper modelMapper;

    public PatrocinadorService(PatrocinadorRepository patrocinadorRepository) { this.patrocinadorRepository = patrocinadorRepository; }

    public List<Patrocinador> listarPatrocinadores() { return this.patrocinadorRepository.listarPatrocinadores(); }

    public Patrocinador listarPatrocinadorPorId(Integer patrocinadorId) {
        return this.patrocinadorRepository.listarPatrocinadorPeloId(patrocinadorId);
    }

    public PatrocinadorDTOResponse criarPatrocinador(PatrocinadorDTORequest patrocinadorDTORequest) {
        Patrocinador patrocinador = modelMapper.map(patrocinadorDTORequest, Patrocinador.class);

        Patrocinador patrocinadorSave = this.patrocinadorRepository.save(patrocinador);

        PatrocinadorDTOResponse patrocinadorDTOResponse = modelMapper.map(patrocinadorSave, PatrocinadorDTOResponse.class);

        return patrocinadorDTOResponse;
    }

    public PatrocinadorDTOResponse atualizarPatrocinador(Integer patrocinadorId, PatrocinadorDTORequest patrocinadorDTORequest) {
        Patrocinador patrocinadorBuscado = this.listarPatrocinadorPorId(patrocinadorId);

        if (patrocinadorBuscado != null) {
            modelMapper.map(patrocinadorDTORequest, Patrocinador.class);
            Patrocinador tempPatrocinador = patrocinadorRepository.save(patrocinadorBuscado);
            return modelMapper.map(tempPatrocinador, PatrocinadorDTOResponse.class);
        } else {
            return null;
        }
    }

    public void apagarPatrocinador(Integer patrocinadorId) { patrocinadorRepository.apagadoLogicoPatrocinador(patrocinadorId); }
}
