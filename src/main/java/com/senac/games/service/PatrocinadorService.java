package com.senac.games.service;

import com.senac.games.dto.request.patrocinador.PatrocinadorDTORequest;
import com.senac.games.dto.response.patrocinador.PatrocinadorDTOResponse;
import com.senac.games.entity.Patrocinador;
import com.senac.games.openFeign.PatrocinadorGamesFeignClient;
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

    private final PatrocinadorGamesFeignClient patrocinadorGamesFeignClient;

    @Autowired
    private ModelMapper modelMapper;

    public PatrocinadorService(
            PatrocinadorRepository patrocinadorRepository,
            PatrocinadorGamesFeignClient patrocinadorGamesFeignClient
    ) {
        this.patrocinadorRepository = patrocinadorRepository;
        this.patrocinadorGamesFeignClient = patrocinadorGamesFeignClient;
    }

    public List<PatrocinadorDTOResponse> listarPatrocinadores() {
        List<Patrocinador> patrocinadores = patrocinadorRepository.listarPatrocinadores();
        return patrocinadores.stream()
                .map(patrocinador -> modelMapper.map(patrocinador, PatrocinadorDTOResponse.class))
                .collect(Collectors.toList());
    }

    public PatrocinadorDTOResponse listarPatrocinadorPorId(Integer patrocinadorId) {
        Patrocinador patrocinador = patrocinadorRepository.obterPatrocinadorPeloId(patrocinadorId);
        return modelMapper.map(patrocinador, PatrocinadorDTOResponse.class);
    }

    @Transactional
    public PatrocinadorDTOResponse criarPatrocinador(PatrocinadorDTORequest patrocinadorDTORequest) {
        Patrocinador patrocinador = modelMapper.map(patrocinadorDTORequest, Patrocinador.class);
        Patrocinador patrocinadorSalvo = patrocinadorRepository.save(patrocinador);
        return modelMapper.map(patrocinadorSalvo, PatrocinadorDTOResponse.class);
    }

    @Transactional
    public PatrocinadorDTOResponse editarPatrocinadorPorId(Integer patrocinadorId, PatrocinadorDTORequest patrocinadorDTORequest) {
        return patrocinadorRepository.findById(patrocinadorId)
                .map(patrocinadorExistente -> {
                    // Atualiza apenas os campos que foram fornecidos no DTO
                    modelMapper.map(patrocinadorDTORequest, patrocinadorExistente);

                    Patrocinador patrocinadorAtualizado = patrocinadorRepository.save(patrocinadorExistente);
                    return modelMapper.map(patrocinadorAtualizado, PatrocinadorDTOResponse.class);
                })
                .orElseThrow(() -> new EntityNotFoundException("Patrocinador não encontrado com id " + patrocinadorId));
    }

    @Transactional
    public void deletarPatrocinadorPorId(Integer patrocinadorId) {
        if (!patrocinadorRepository.existsById(patrocinadorId)) {
            throw new EntityNotFoundException("Patrocinador com ID " + patrocinadorId + " não encontrado");
        }
        patrocinadorRepository.apagadoLogicoPatrocinador(patrocinadorId);
    }

    /*
    ======================== MÉTODOS PARA PUXAR DE OUTRA API ========================
     */

    public List<PatrocinadorDTOResponse> listarPatrocinadoresRemotamente() {
        return patrocinadorGamesFeignClient.listarPatrocinadoresRecebidos();
    }

    public PatrocinadorDTOResponse listarPatrocinadorPorIdRemotamente(Integer patrocinadorId) {
        PatrocinadorDTOResponse patrocinadorRecebido = patrocinadorGamesFeignClient.findById(patrocinadorId);

        return patrocinadorRecebido;
    }

    public PatrocinadorDTOResponse criarPatrocinadorRemotamente(PatrocinadorDTORequest patrocinadorDTORequest) {

        PatrocinadorDTOResponse patrocinadorCriadoRemotamente = patrocinadorGamesFeignClient.criarPatrocinadorEnviar(patrocinadorDTORequest);

        return patrocinadorCriadoRemotamente;
    }

    public PatrocinadorDTOResponse editarPatrocinadorPorIdRemotamente(
            Integer patrocinadorId,
            PatrocinadorDTORequest patrocinadorAtualizarDTORequest
    ) {
        PatrocinadorDTOResponse patrocinadorAtualizadoRemotamente = patrocinadorGamesFeignClient.editarPatrocinadorRecebidoPorId(
                patrocinadorId, patrocinadorAtualizarDTORequest
        );


        return patrocinadorAtualizadoRemotamente;
    }

    public void deletarPatrocinadorPorIdRemotamente(Integer patrocinadorId) {
        patrocinadorGamesFeignClient.deletarPatrocinadorEnviar(patrocinadorId);

    }

}
