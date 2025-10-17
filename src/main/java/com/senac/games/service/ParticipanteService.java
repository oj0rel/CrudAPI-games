package com.senac.games.service;

import com.senac.games.dto.request.participante.ParticipanteDTOUpdateRequest;
import com.senac.games.dto.response.participante.ParticipanteDTOUpdateResponse;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import com.senac.games.dto.request.participante.ParticipanteDTORequest;
import com.senac.games.dto.response.participante.ParticipanteDTOResponse;
import com.senac.games.entity.Participante;
import com.senac.games.repository.ParticipanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParticipanteService {
    private final ParticipanteRepository participanteRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ParticipanteService(ParticipanteRepository participanteRepository) {
        this.participanteRepository = participanteRepository;
    }

    @Transactional
    public ParticipanteDTOResponse criarParticipante(ParticipanteDTORequest participanteDTORequest) {
        Participante participante = modelMapper.map(participanteDTORequest, Participante.class);
        Participante participanteSave = this.participanteRepository.save(participante);
        ParticipanteDTOResponse participanteDTOResponse = modelMapper.map(participanteSave, ParticipanteDTOResponse.class);
        return participanteDTOResponse;

    }

    public List<ParticipanteDTOResponse> listarParticipantesAtivos() {
        List<Participante> participantes = participanteRepository.listarParicipantes();
        return participantes.stream()
                .map(participante -> modelMapper.map(participante, ParticipanteDTOResponse.class))
                .collect(Collectors.toList());
    }

    public ParticipanteDTOResponse listarPorParticipanteId(Integer participanteId) {
        Participante participante = this.participanteRepository.obterParticipantePeloId(participanteId);
        return modelMapper.map(participante, ParticipanteDTOResponse.class);
    }
    @Transactional
    public ParticipanteDTOResponse editarPorParticipanteId(Integer participanteId, ParticipanteDTORequest participanteDTORequest) {
        //ante de atualizar busca o registro para atualizar
        Participante participanteBuscado = this.participanteRepository.obterParticipantePeloId(participanteId);

        //Se encontra o registro
        if (participanteBuscado != null) {
            //Copia os dados a sere atualizados do DTO de entrada para um objeto do tipo participante
            //que é compativel com o repository para atualizar
            modelMapper.map(participanteDTORequest, participanteBuscado);
            Participante participanteSalvo = participanteRepository.save(participanteBuscado);
            //Com o objeto no formato correto tipo "participante" o comando "save" salva no banco de dados
            return modelMapper.map(participanteSalvo, ParticipanteDTOResponse.class);
        } else {
            return null;
        }

    }
    @Transactional
    public ParticipanteDTOUpdateResponse atualizarStatusParticipante(Integer participanteId, ParticipanteDTOUpdateRequest participanteDTOUpdateRequest){

        Participante participanteBuscado = this.participanteRepository.obterParticipantePeloId(participanteId);
        participanteBuscado.setStatus(participanteDTOUpdateRequest.getStatus());

        Participante participanteSalvo = participanteRepository.save(participanteBuscado);
        return modelMapper.map(participanteSalvo, ParticipanteDTOUpdateResponse.class);


    }
    @Transactional
    public void apagarParticipante(Integer participanteId){
        participanteRepository.apagadoLogicoParticipante(participanteId);
    }
}
