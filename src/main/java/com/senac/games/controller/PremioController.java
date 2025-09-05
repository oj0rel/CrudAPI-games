package com.senac.games.controller;

import com.senac.games.dto.request.premio.PremioDTORequest;
import com.senac.games.dto.response.premio.PremioDTOResponse;
import com.senac.games.entity.Premio;
import com.senac.games.service.PremioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    public PremioController (PremioService premioService) { this.premioService = premioService; }

    @GetMapping("/listar")
    @Operation(summary = "Listar Premios.", description = "Endpoint para listar todos os Premios.")
    public ResponseEntity <List<Premio>> listarInscricoes() { return ResponseEntity.ok(premioService.listarPremios()); }

    @GetMapping("/listarPremioId/{premioId}")
    @Operation(summary = "Listar o Premio pelo ID dele.", description = "Endpoint para listar um Premio, pelo ID.")
    public ResponseEntity <Premio> listarPremioPorId(@PathVariable("premioId") Integer premioId) {
        Premio premio = premioService.listarPremioPorId(premioId);

        if (premioId == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(premio);
        }
    }

    @PostMapping("/criar")
    @Operation(summary = "Criar novo Premio.", description = "Endpoint para criar um novo registro de Premio.")
    public ResponseEntity<PremioDTOResponse> criarPremio(@Valid @RequestBody PremioDTORequest premio) {
        return ResponseEntity.status(HttpStatus.CREATED).body(premioService.criarPremio(premio));
    }

    @PutMapping("/atualizarPremioPorId/{premioId}")
    @Operation(summary = "Atualizar Premio pelo ID.", description = "Endpoint para editar o Premio pelo ID.")
    public ResponseEntity<PremioDTOResponse> editarPremio(@Valid @PathVariable("premioId") Integer premioId,
                                                      @RequestBody PremioDTORequest premioDTORequest) {
        PremioDTOResponse premioDTOResponse = premioService.atualizarPremio(premioId, premioDTORequest);
        return ResponseEntity.ok(premioService.atualizarPremio(premioId, premioDTORequest));
    }

    @DeleteMapping("/deletarPremioId/{premioId}")
    @Operation(summary = "Apagar um Premio pelo ID.", description = "Endpoint para apagar um Premio pelo ID.")
    public ResponseEntity<Void> apagarPremio (@PathVariable Integer premioId) {
        premioService.apagarPremio(premioId);
        return ResponseEntity.noContent().build();
    }
}
