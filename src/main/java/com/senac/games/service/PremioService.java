package com.senac.games.service;

import com.senac.games.dto.request.premio.PremioDTORequest;
import com.senac.games.dto.response.premio.PremioDTOResponse;
import com.senac.games.entity.Premio;
import com.senac.games.repository.PremioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PremioService {
    private final PremioRepository premioRepository;

    @Autowired
    private ModelMapper modelMapper;

    public PremioService(PremioRepository premioRepository) {
        this.premioRepository = premioRepository;
    }

    public List<PremioDTOResponse> listarPremios() {
        List<Premio> premios = premioRepository.listarPremios();
        return premios.stream()
                .map(premio -> modelMapper.map(premio, PremioDTOResponse.class))
                .collect(Collectors.toList());
    }
    @Transactional
    public PremioDTOResponse criarPremio(PremioDTORequest premioDTORequest) {
        Premio premio = modelMapper.map(premioDTORequest, Premio.class);
        Premio premioSalvo = premioRepository.save(premio);
        return modelMapper.map(premioSalvo, PremioDTOResponse.class);
    }

    public PremioDTOResponse listarPorPremioId(Integer premioId) {
        Premio premio = premioRepository.obterPremioPeloId(premioId);
        return modelMapper.map(premio, PremioDTOResponse.class);
    }
    @Transactional
    public void deletarPorPremioId(Integer premioId) {
        if (!premioRepository.existsById(premioId)) {
            throw new EntityNotFoundException("Prêmio com ID " + premioId + " não encontrado");
        }
        premioRepository.apagadoLogicoPremio(premioId);
    }
    @Transactional
    public PremioDTOResponse editarPorPremioId(Integer premioId, PremioDTORequest premioDTORequest) {
        return premioRepository.findById(premioId)
                .map(premioExistente -> {
                    // Atualiza apenas os campos que foram fornecidos no DTO
                    modelMapper.map(premioDTORequest, premioExistente);

                    Premio premioAtualizado = premioRepository.save(premioExistente);
                    return modelMapper.map(premioAtualizado, PremioDTOResponse.class);
                })
                .orElseThrow(() -> new EntityNotFoundException("Prêmio não encontrado com id " + premioId));
    }
}
