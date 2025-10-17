package com.senac.games.controller;

import com.senac.games.dto.request.patrocinador.PatrocinadorDTORequest;
import com.senac.games.dto.response.patrocinador.PatrocinadorDTOResponse;
import com.senac.games.service.PatrocinadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/patrocinador")
@Tag(name = "Patrocinador", description = "API para gerenciamento de Patrocinadores.")
public class PatrocinadorController {
    private PatrocinadorService patrocinadorService;

    public PatrocinadorController(PatrocinadorService patrocinadorService) {
        this.patrocinadorService = patrocinadorService;
    }

    @GetMapping("/listar")
    @Operation(summary="Listar Patrocinadores", description="Endpoint para listar todos os Patrocinadores.")
    public ResponseEntity<List<PatrocinadorDTOResponse>> listarPatrocinadores() {
        return ResponseEntity.ok(patrocinadorService.listarPatrocinadores());
    }


    @GetMapping("/listarPatrocinadorPorId/{patrocinadorId}")
    @Operation(summary="Listar Patrocinador pelo ID", description="Endpoint para listar Patrocinador pelo ID.")
    public ResponseEntity<PatrocinadorDTOResponse> listarPatrocinadorPorId(
            @PathVariable("patrocinadorId") Integer patrocinadorId
    ) {
        try {
            PatrocinadorDTOResponse patrocinador = patrocinadorService.listarPatrocinadorPorId(patrocinadorId);
            return ResponseEntity.ok(patrocinador);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping ("/criar")
    @Operation(summary="Criar Patrocinador", description="Endpoint para criar Patrocinador.")
    public ResponseEntity<PatrocinadorDTOResponse> criarPatrocinador(
            @Valid @RequestBody PatrocinadorDTORequest patrocinadorDTORequest
    ) {
        PatrocinadorDTOResponse novoPatrocinador = patrocinadorService.criarPatrocinador(patrocinadorDTORequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPatrocinador);
    }

    @PutMapping("/editarPatrocinadorPorId/{patrocinadorId}")
    @Operation(summary="Editar Patrocinador pelo ID", description="Endpoint para editar Patrocinador pelo ID.")
    public ResponseEntity<PatrocinadorDTOResponse> editarPorPatrocinadorId(
            @PathVariable("patrocinadorId") Integer patrocinadorId,
            @Valid @RequestBody PatrocinadorDTORequest patrocinadorDTORequest
    ) {
        try {
            PatrocinadorDTOResponse patrocinadorAtualizado = patrocinadorService.editarPatrocinadorPorId(patrocinadorId, patrocinadorDTORequest);
            return ResponseEntity.ok(patrocinadorAtualizado);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deletarPatrocinadorPorId/{patrocinadorId}")
    @Operation(summary="Deletar Patrocinador pelo ID", description="Endpoint para deletar Patrocinador pelo ID.")
    public ResponseEntity<Void> deletarPorPatrocinadorId(
            @PathVariable("patrocinadorId") Integer patrocinadorId
    ) {
        try {
            patrocinadorService.deletarPatrocinadorPorId(patrocinadorId);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
