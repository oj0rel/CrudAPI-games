package com.senac.games.controller;

import com.senac.games.dto.request.participante.ParticipanteDTORequest;
import com.senac.games.dto.request.participante.ParticipanteDTOUpdateRequest;
import com.senac.games.dto.response.participante.ParticipanteDTOResponse;
import com.senac.games.dto.response.participante.ParticipanteDTOUpdateResponse;
import com.senac.games.service.ParticipanteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/participante")
@Tag(name = "Participante", description = "API para gerenciamento de participantes.")
public class ParticipanteController {

    private ParticipanteService participanteService;

    public ParticipanteController(ParticipanteService participanteService) {
        this.participanteService = participanteService;
    }

    @GetMapping("/listar")
    @Operation(summary="Listar participantes", description="Endpoint para listar todos os participantes")
    public ResponseEntity<List<com.senac.games.dto.response.participante.ParticipanteDTOResponse>> listarParticipantes(){
        return ResponseEntity.ok(participanteService.listarParticipantesAtivos());
    }

    @PostMapping("/criar")
    @Operation(summary="Criar participantes", description="Endpoint para criar os participantes")
    public ResponseEntity<com.senac.games.dto.response.participante.ParticipanteDTOResponse> criarParticipante(@Valid @RequestBody com.senac.games.dto.request.participante.ParticipanteDTORequest participanteDTORequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(participanteService.criarParticipante(participanteDTORequest));
    }

    @GetMapping("/listarPorParticipanteId/{participanteId}")
    @Operation(summary="Listar participantes pelo id", description="Endpoint para listar o participante pelo id")
    public ResponseEntity<com.senac.games.dto.response.participante.ParticipanteDTOResponse> listarPorParticipanteId(@PathVariable("participanteId") Integer participanteId) {
        com.senac.games.dto.response.participante.ParticipanteDTOResponse participanteDTO = participanteService.listarPorParticipanteId(participanteId);
        return ResponseEntity.ok(participanteDTO);
    }

    @DeleteMapping("/deletarPorParticipanteId/{participanteId}")
    @Operation(summary="Deletar participantes pelo id", description="Endpoint para deletar o participante pelo id")
    public ResponseEntity<Void> deletarPorParticipanteId(@PathVariable("participanteId") Integer participanteId) {
        try {
            participanteService.apagarParticipante(participanteId);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/editarPorParticipanteId/{participanteId}")
    @Operation(summary="Atualizar participantes pelo id", description="Endpoint para editar o participante pelo id")
    public ResponseEntity<com.senac.games.dto.response.participante.ParticipanteDTOResponse> editarParticipante(@PathVariable("participanteId") Integer participanteId,
                                                                                                   @RequestBody ParticipanteDTORequest participanteDTORequest){

        ParticipanteDTOResponse participanteAtualizado = participanteService.editarPorParticipanteId(participanteId, participanteDTORequest);
        return ResponseEntity.ok(participanteService.editarPorParticipanteId(participanteId,participanteDTORequest)); // 200 OK com o participante atualizada

    }

    @PatchMapping("/editarStatusParticipante/{participanteId}")
    @Operation(summary="Atualizar campo de status", description="Endpoint para atualizar o participante status")
    public ResponseEntity<ParticipanteDTOUpdateResponse> editarParticipanteStatus(@PathVariable("participanteId") Integer participanteId,
                                                                                  @RequestBody ParticipanteDTOUpdateRequest participanteDTOUpdateRequest){
        return ResponseEntity.ok(participanteService.atualizarStatusParticipante(participanteId,participanteDTOUpdateRequest));

    }

}
