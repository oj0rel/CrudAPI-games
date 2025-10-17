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
@Tag(name = "Participante", description = "API para gerenciamento de Participantes.")
public class ParticipanteController {

    private ParticipanteService participanteService;

    public ParticipanteController(ParticipanteService participanteService) {
        this.participanteService = participanteService;
    }

    @GetMapping("/listar")
    @Operation(summary="Listar Participantes", description="Endpoint para listar todos os Participantes.")
    public ResponseEntity<List<ParticipanteDTOResponse>> listarParticipantes() {
        return ResponseEntity.ok(participanteService.listarParticipantesAtivos());
    }

    @GetMapping("/listarParticipantePorId/{participanteId}")
    @Operation(summary="Listar Participante pelo ID", description="Endpoint para listar o Participante pelo ID.")
    public ResponseEntity<ParticipanteDTOResponse> listarParticipantePorId(
            @PathVariable("participanteId") Integer participanteId
    ) {
        ParticipanteDTOResponse participanteDTO = participanteService.listarParticipantePorId(participanteId);
        return ResponseEntity.ok(participanteDTO);
    }

    @PostMapping("/criar")
    @Operation(summary="Criar Participante", description="Endpoint para criar Participante.")
    public ResponseEntity<ParticipanteDTOResponse> criarParticipante(
            @Valid
            @RequestBody ParticipanteDTORequest participanteDTORequest) {

        return ResponseEntity.status(HttpStatus.CREATED).body(participanteService.criarParticipante(participanteDTORequest));
    }

    @PutMapping("/editarParticipantePorId/{participanteId}")
    @Operation(summary="Atualizar Participante pelo ID", description="Endpoint para editar o Participante pelo ID.")
    public ResponseEntity<ParticipanteDTOResponse> editarParticipantePorId(
            @PathVariable("participanteId") Integer participanteId,
            @RequestBody ParticipanteDTORequest participanteDTORequest) {

        ParticipanteDTOResponse participanteAtualizado = participanteService.editarParticipantePorId(participanteId, participanteDTORequest);
        return ResponseEntity.ok(participanteService.editarParticipantePorId(participanteId,participanteDTORequest)); // 200 OK com o participante atualizada

    }

    @PatchMapping("/editarStatusParticipante/{participanteId}")
    @Operation(summary="Atualizar campo de status", description="Endpoint para atualizar o status do Participante.")
    public ResponseEntity<ParticipanteDTOUpdateResponse> editarParticipanteStatus(
            @PathVariable("participanteId") Integer participanteId,
            @RequestBody ParticipanteDTOUpdateRequest participanteDTOUpdateRequest) {

        return ResponseEntity.ok(participanteService.editarStatusParticipante(participanteId,participanteDTOUpdateRequest));
    }

    @DeleteMapping("/deletarParticipantePorId/{participanteId}")
    @Operation(summary="Deletar Participante pelo ID", description="Endpoint para deletar o Participante pelo ID.")
    public ResponseEntity<Void> deletarParticipantePorId(
            @PathVariable("participanteId") Integer participanteId
    ) {
        try {
            participanteService.deletarParticipantePorId(participanteId);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
