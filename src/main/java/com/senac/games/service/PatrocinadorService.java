package com.senac.games.service;

import com.senac.games.dto.request.patrocinador.PatrocinadorDTORequest;
import com.senac.games.dto.response.patrocinador.PatrocinadorDTOResponse;
import com.senac.games.entity.Patrocinador;
import com.senac.games.openFeign.GamesFeignClient;
import com.senac.games.repository.PatrocinadorRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatrocinadorService {
    private final PatrocinadorRepository patrocinadorRepository;

    private final GamesFeignClient gamesFeignClient;

    @Autowired
    private ModelMapper modelMapper;

    public PatrocinadorService(
            PatrocinadorRepository patrocinadorRepository,
            GamesFeignClient gamesFeignClient
    ) {
        this.patrocinadorRepository = patrocinadorRepository;
        this.gamesFeignClient = gamesFeignClient;
    }

    public List<PatrocinadorDTOResponse> listarPatrocinadores() {
        List<Patrocinador> patrocinadores = patrocinadorRepository.listarPatrocinadores();
        return patrocinadores.stream()
                .map(patrocinador -> modelMapper.map(patrocinador, PatrocinadorDTOResponse.class))
                .collect(Collectors.toList());
    }
    @Transactional
    public PatrocinadorDTOResponse criarPatrocinador(PatrocinadorDTORequest patrocinadorDTORequest) {
        Patrocinador patrocinador = modelMapper.map(patrocinadorDTORequest, Patrocinador.class);
        Patrocinador patrocinadorSalvo = patrocinadorRepository.save(patrocinador);
        return modelMapper.map(patrocinadorSalvo, PatrocinadorDTOResponse.class);
    }
    @Transactional
    public void deletarPorPatrocinadorId(Integer patrocinadorId) {
        if (!patrocinadorRepository.existsById(patrocinadorId)) {
            throw new EntityNotFoundException("Patrocinador com ID " + patrocinadorId + " não encontrado");
        }
        patrocinadorRepository.apagadoLogicoPatrocinador(patrocinadorId);
    }
    @Transactional
    public PatrocinadorDTOResponse editarPorPatrocinadorId(Integer patrocinadorId, PatrocinadorDTORequest patrocinadorDTORequest) {
        return patrocinadorRepository.findById(patrocinadorId)
                .map(patrocinadorExistente -> {
                    // Atualiza apenas os campos que foram fornecidos no DTO
                    modelMapper.map(patrocinadorDTORequest, patrocinadorExistente);

                    Patrocinador patrocinadorAtualizado = patrocinadorRepository.save(patrocinadorExistente);
                    return modelMapper.map(patrocinadorAtualizado, PatrocinadorDTOResponse.class);
                })
                .orElseThrow(() -> new EntityNotFoundException("Patrocinador não encontrado com id " + patrocinadorId));
    }

    public PatrocinadorDTOResponse listarPorPatrocinadorId(Integer patrocinadorId) {
        Patrocinador patrocinador = patrocinadorRepository.obterPatrocinadorPeloId(patrocinadorId);
        return modelMapper.map(patrocinador, PatrocinadorDTOResponse.class);
    }

    public PatrocinadorDTOResponse receberPatrocinadorPorId(Integer patrocinadorId) {
        Patrocinador patrocinador = gamesFeignClient.findById(patrocinadorId);
        return modelMapper.map(patrocinador, PatrocinadorDTOResponse.class);
    }
}
