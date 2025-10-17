package com.senac.games.controller;

import com.senac.games.dto.request.premio.PremioDTORequest;
import com.senac.games.dto.response.premio.PremioDTOResponse;
import com.senac.games.service.PremioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/premio")
@Tag(name = "Premio", description = "API para gerenciamento de Premios.")
public class PremioController {
    private PremioService premioService;

    public PremioController(PremioService premioService) {
        this.premioService = premioService;
    }

    @GetMapping("/listar")
    @Operation(summary="Listar Premios", description="Endpoint para listar todos os Premios.")
    public ResponseEntity<List<PremioDTOResponse>> listarPremios() {
        return ResponseEntity.ok(premioService.listarPremios());
    }

    @GetMapping("/listarPremioPorId/{premioId}")
    @Operation(summary="Listar Premio pelo ID", description="Endpoint para listar Premio pelo ID.")
    public ResponseEntity<PremioDTOResponse> listarPorPremioId(
            @PathVariable("premioId") Integer premioId
    ) {
        try {
            PremioDTOResponse premio = premioService.listarPremioPorId(premioId);
            return ResponseEntity.ok(premio);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/criar")
    @Operation(summary="Criar Premio", description="Endpoint para criar Premio.")
    public ResponseEntity<PremioDTOResponse> criarPremio(
            @Valid
            @RequestBody PremioDTORequest premioDTORequest
    ) {
        PremioDTOResponse novoPremio = premioService.criarPremio(premioDTORequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPremio);
    }

    @PutMapping("/editarPremioPorId/{premioId}")
    @Operation(summary="Editar Premio pelo ID", description="Endpoint para editar Premio pelo ID.")
    public ResponseEntity<PremioDTOResponse> editarPorPremioId(
            @PathVariable("premioId") Integer premioId,
            @Valid @RequestBody PremioDTORequest premioDTORequest
    ) {
        try {
            PremioDTOResponse premioAtualizado = premioService.editarPremioPorId(premioId, premioDTORequest);
            return ResponseEntity.ok(premioAtualizado);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deletarPremioPorId/{premioId}")
    @Operation(summary="Deletar Premio pelo ID", description="Endpoint para deletar Premio pelo ID.")
    public ResponseEntity<Void> deletarPorPremioId(
            @PathVariable("premioId") Integer premioId
    ) {
        try {
            premioService.deletarPremioPorId(premioId);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
