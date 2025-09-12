package com.senac.games.controller;

import com.senac.games.dto.request.premio.PremioDTORequest;
import com.senac.games.dto.response.premio.PremioDTOResponse;
import com.senac.games.entity.Premio;
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
@Tag(name = "Premio", description = "API para gerenciamento de prêmios.")
public class PremioController {
    private PremioService premioService;

    public PremioController(PremioService premioService) {
        this.premioService = premioService;
    }

    @GetMapping("/listar")
    @Operation(summary="Listar premio", description="Endpoint para listar todos os premios")
    public ResponseEntity<List<PremioDTOResponse>> listarPremios() {
        return ResponseEntity.ok(premioService.listarPremios());
    }
    @PostMapping("/criar")
    @Operation(summary="Criar premio", description="Endpoint para criar premios")
    public ResponseEntity<PremioDTOResponse> criarPremio(@Valid @RequestBody PremioDTORequest premioDTORequest) {
        PremioDTOResponse novoPremio = premioService.criarPremio(premioDTORequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPremio);
    }

    @GetMapping("/listarPorPremioId/{premioId}")
    @Operation(summary="Listar premio pelo id do premio", description="Endpoint para listar premio por id")
    public ResponseEntity<PremioDTOResponse> listarPorPremioId(@PathVariable("premioId") Integer premioId) {
        try {
            PremioDTOResponse premio = premioService.listarPorPremioId(premioId);
            return ResponseEntity.ok(premio);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deletarPorPremioId/{premioId}")
    @Operation(summary="Deletar premio pelo id do premio", description="Endpoint para deletar premio por id")
    public ResponseEntity<Void> deletarPorPremioId(@PathVariable("premioId") Integer premioId) {
        try {
            premioService.deletarPorPremioId(premioId);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/editarPorPremioId/{premioId}")
    @Operation(summary="Editar premio pelo id do premio", description="Endpoint para editar premio por id")
    public ResponseEntity<PremioDTOResponse> editarPorPremioId(@PathVariable("premioId") Integer premioId,
                                                               @Valid @RequestBody PremioDTORequest premioDTORequest) {
        try {
            PremioDTOResponse premioAtualizado = premioService.editarPorPremioId(premioId, premioDTORequest);
            return ResponseEntity.ok(premioAtualizado);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
