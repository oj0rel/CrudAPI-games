package com.senac.games.service;

import com.senac.games.dto.request.premio.PremioDTORequest;
import com.senac.games.dto.response.premio.PremioDTOResponse;
import com.senac.games.entity.Premio;
import com.senac.games.repository.PremioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PremioService {
    private final PremioRepository premioRepository;

    @Autowired
    private ModelMapper modelMapper;

    public PremioService(PremioRepository premioRepository) { this.premioRepository = premioRepository; }

    public List<Premio> listarPremios() { return this.premioRepository.listarPremios(); }

    public Premio listarPremioPorId(Integer premioId) {
        return this.premioRepository.listarPremioPeloId(premioId);
    }

    public PremioDTOResponse criarPremio(PremioDTORequest premioDTORequest) {
        Premio premio = modelMapper.map(premioDTORequest, Premio.class);

        Premio premioSave = this.premioRepository.save(premio);

        PremioDTOResponse premioDTOResponse = modelMapper.map(premioSave, PremioDTOResponse.class);

        return premioDTOResponse;
    }

    public PremioDTOResponse atualizarPremio(Integer premioId, PremioDTORequest premioDTORequest) {
        Premio premioBuscado = this.listarPremioPorId(premioId);

        if (premioBuscado != null) {
            modelMapper.map(premioDTORequest, Premio.class);
            Premio tempPremio = premioRepository.save(premioBuscado);
            return modelMapper.map(tempPremio, PremioDTOResponse.class);
        } else {
            return null;
        }
    }

    public void apagarPremio(Integer premioId) { premioRepository.apagadoLogicoPremio(premioId); }
}
