package com.senac.games.service;

import org.modelmapper.ModelMapper;
import com.senac.games.dto.request.ParticipanteDTORequest;
import com.senac.games.dto.response.ParticipanteDTOResponse;
import com.senac.games.entity.Participante;
import com.senac.games.repository.ParticipanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipanteService {
    private final ParticipanteRepository participanteRepository; //injetando a classe.
    @Autowired
    private ModelMapper modelMapper;

    //construindo o construtor.
    public ParticipanteService(ParticipanteRepository participanteRepository) {
        this.participanteRepository = participanteRepository;
    }

    //método para listar todos os participantes.
    public List<Participante> listarParticipantes() { return this.participanteRepository.findAll(); }

    //método para listar um participante, buscando pelo ID dele.
    public Participante listarParticipantePorId(Integer participanteId) {
        return this.participanteRepository.findById(participanteId).orElse(null); //caso não venha nada na busca, o orElse faz vir null.
    }

    public ParticipanteDTOResponse criarParticipante(ParticipanteDTORequest participanteDTORequest) {

        //instanciação para inserir as informações que estão vindas do participanteDTO em um classe participante que poderá ser salva
        Participante participante = modelMapper.map(participanteDTORequest, Participante.class);

        Participante participanteSave = this.participanteRepository.save(participante);

        ParticipanteDTOResponse participanteDTOResponse = modelMapper.map(participanteSave, ParticipanteDTOResponse.class);

        return participanteDTOResponse;

        /* nao vai mais precisar desta parte
        //colocando o que vem do participanteDTO em participante
        participante.setNome(participanteDTO.getNome());
        participante.setEmail(participanteDTO.getEmail());
        participante.setIdentificacao(participanteDTO.getIdentificacao());
        participante.setEndereco(participanteDTO.getEndereco());
        participante.setStatus(participanteDTO.getStatus());
        */



        /* nao vai mais precisar desta parte
        participanteDTOResponse.setId(participanteSave.getId());
        participanteDTOResponse.setNome(participanteSave.getNome());
        participanteDTOResponse.setEmail(participanteSave.getEmail());
        participanteDTOResponse.setIdentificacao(participanteSave.getIdentificacao());
        participanteDTOResponse.setEndereco(participanteSave.getEndereco());
        participanteDTOResponse.setStatus(participanteSave.getStatus());

        return participanteDTOResponse;
         */
    }
}
