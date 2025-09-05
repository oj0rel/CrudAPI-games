package com.senac.games.controller;

import com.senac.games.dto.request.participante.ParticipanteDTORequest;
import com.senac.games.dto.response.participante.ParticipanteDTOResponse;
import com.senac.games.entity.Participante;
import com.senac.games.service.ParticipanteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    public ParticipanteController (ParticipanteService participanteService) {
        this.participanteService = participanteService;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar Participantes", description = "Endpoint para listar todos os Participantes.")
    public ResponseEntity <List<Participante>> listarParticipantes() {
        return ResponseEntity.ok(participanteService.listarParticipantes());
    }

    @GetMapping("/listarParticipanteId/{participanteId}") //tem que colocar {participanteId}, pois é o que está igual ao Integer participanteId(linha 34).
    @Operation(summary = "Listar o Participante pelo ID dele", description = "Endpoint para listar um Participante, pelo ID.")
    public ResponseEntity <Participante> listarParticipantePorId(@PathVariable("participanteId") Integer participanteId) { // <- akiii

        Participante participante = participanteService.listarParticipantePorId(participanteId);

        //tratamento caso retorne null
        if(participanteId == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(participante);
        }
    }

    @PostMapping("/criar")
    @Operation(summary = "Criar novo Participante", description = "Endpoint para criar um novo registro de Participante")
    public ResponseEntity<ParticipanteDTOResponse> criarParticipante(@Valid @RequestBody ParticipanteDTORequest participante) { //coloca DTOResponse pois é o que o usuario vai receber passando pelo DTO, e depois DTORequest para o que vai ser passado para criar
        return ResponseEntity.status(HttpStatus.CREATED).body(participanteService.criarParticipante(participante));
    }

    @PutMapping("/atualizarPorParticipanteId/{participanteId}")
    @Operation(summary="Atualizar Participantes pelo id", description="Endpoint para editar o Participante pelo id")
    public ResponseEntity<ParticipanteDTOResponse> editarParticipante(@Valid @PathVariable("participanteId") Integer participanteId,
                                                                      @RequestBody ParticipanteDTORequest participanteDTORequest){

        ParticipanteDTOResponse participanteAtualizado = participanteService.atualizarParticipante(participanteId, participanteDTORequest);
        return ResponseEntity.ok(participanteService.atualizarParticipante(participanteId,participanteDTORequest)); // 200 OK com o participante atualizada

    }

}
