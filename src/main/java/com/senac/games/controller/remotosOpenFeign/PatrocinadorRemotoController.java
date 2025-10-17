package com.senac.games.controller.remotosOpenFeign;

import com.senac.games.dto.request.patrocinador.PatrocinadorDTORequest;
import com.senac.games.dto.response.patrocinador.PatrocinadorDTOResponse;
import com.senac.games.service.PatrocinadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/patrocinadorRemoto")
@Tag(name = "Patrocinador Remoto", description = "API para gerenciamento de Patrocinadores vindos de outra API.")
public class PatrocinadorRemotoController {

    @Autowired
    PatrocinadorService patrocinadorService;


    @GetMapping("/listarTodosPatrocinadoresRemotamente")
    @CrossOrigin(origins = "*")
    @Operation(summary="Listar Patrocinadores remotamente", description="Endpoint para listar todos os Patrocinadores remotamente.")
    public ResponseEntity<List<PatrocinadorDTOResponse>> listarPatrocinadoresRecebidos() {
        return ResponseEntity.ok(patrocinadorService.listarPatrocinadoresRemotamente());
    }

    @GetMapping("/listarPatrocinadorPorIdRemotamente/{patrocinadorId}")
    @CrossOrigin(origins = "*")
    @Operation(summary="Listar Patrocinador remotamente pelo ID", description="Endpoint para listar Patrocinador remotamente por ID.")
    public ResponseEntity<PatrocinadorDTOResponse> listarPatrocinadorRecebidoPorId(
            @PathVariable("patrocinadorId") Integer patrocinadorId
    ) {
        return ResponseEntity.ok(patrocinadorService.listarPatrocinadorPorIdRemotamente(patrocinadorId));
    }

    @PostMapping("/criarPatrocinadorRemotamente")
    @CrossOrigin(origins = "*")
    @Operation(summary="Criar Patrocinador remotamente", description="Endpoint para criar Patrocinador remotamente.")
    public ResponseEntity<PatrocinadorDTOResponse> criarPatrocinadorEnviar(
            @Valid
            @RequestBody PatrocinadorDTORequest patrocinadorDTORequest
    ) {
        PatrocinadorDTOResponse patrocinadorCriadoRemotamente = patrocinadorService.criarPatrocinadorRemotamente(patrocinadorDTORequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(patrocinadorCriadoRemotamente);
    }

    @PutMapping("/editarPorPatrocinadorIdRemotamente/{patrocinadorId}")
    @CrossOrigin(origins = "*")
    @Operation(summary="Editar Patrocinadores remotamente pelo ID", description="Endpoint para editar Patrocinadores remotamente pelo ID.")
    public ResponseEntity<PatrocinadorDTOResponse> editarPorPatrocinadorIdEnviar(
            @PathVariable("patrocinadorId") Integer patrocinadorId,
            @Valid @RequestBody PatrocinadorDTORequest patrocinadorDTORequest
    ) {
        try {
            PatrocinadorDTOResponse patrocinadorAtualizado = patrocinadorService.editarPatrocinadorPorIdRemotamente(patrocinadorId, patrocinadorDTORequest);
            return ResponseEntity.ok(patrocinadorAtualizado);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deletarPorPatrocinadorIdRemotamente/{patrocinadorId}")
    @CrossOrigin(origins = "*")
    @Operation(summary="Deletar Patrocinador remotamente pelo ID", description="Endpoint para deletar Patrocinador remotamente por ID.")
    public ResponseEntity<Void> deletarPorPatrocinadorIdRemotamente(
            @PathVariable("patrocinadorId") Integer patrocinadorId
    ) {
        patrocinadorService.deletarPatrocinadorPorIdRemotamente(patrocinadorId);

        return ResponseEntity.noContent().build();
    }
}
