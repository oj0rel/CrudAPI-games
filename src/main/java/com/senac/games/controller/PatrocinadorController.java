package com.senac.games.controller;

import com.senac.games.dto.request.patrocinador.PatrocinadorDTORequest;
import com.senac.games.dto.response.patrocinador.PatrocinadorDTOResponse;
import com.senac.games.entity.Patrocinador;
import com.senac.games.service.PatrocinadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    public PatrocinadorController (PatrocinadorService patrocinadorService) { this.patrocinadorService = patrocinadorService; }

    @GetMapping("/listar")
    @Operation(summary = "Listar Patrocinadores.", description = "Endpoint para listar todos os Patrocinadores.")
    public ResponseEntity <List<Patrocinador>> listarInscricoes() { return ResponseEntity.ok(patrocinadorService.listarPatrocinadores()); }

    @GetMapping("/listarPatrocinadorId/{patrocinadorId}")
    @Operation(summary = "Listar o Patrocinador pelo ID dele.", description = "Endpoint para listar um Patrocinador, pelo ID.")
    public ResponseEntity <Patrocinador> listarPatrocinadorPorId(@PathVariable("patrocinadorId") Integer patrocinadorId) {
        Patrocinador patrocinador = patrocinadorService.listarPatrocinadorPorId(patrocinadorId);

        if (patrocinadorId == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(patrocinador);
        }
    }

    @PostMapping("/criar")
    @Operation(summary = "Criar novo Patrocinador.", description = "Endpoint para criar um novo registro de Patrocinador.")
    public ResponseEntity<PatrocinadorDTOResponse> criarPatrocinador(@Valid @RequestBody PatrocinadorDTORequest patrocinador) {
        return ResponseEntity.status(HttpStatus.CREATED).body(patrocinadorService.criarPatrocinador(patrocinador));
    }

    @PutMapping("/atualizarPatrocinadorPorId/{patrocinadorId}")
    @Operation(summary = "Atualizar Patrocinador pelo ID.", description = "Endpoint para editar o Patrocinador pelo ID.")
    public ResponseEntity<PatrocinadorDTOResponse> editarPatrocinador(@Valid @PathVariable("patrocinadorId") Integer patrocinadorId,
                                                      @RequestBody PatrocinadorDTORequest patrocinadorDTORequest) {
        PatrocinadorDTOResponse patrocinadorDTOResponse = patrocinadorService.atualizarPatrocinador(patrocinadorId, patrocinadorDTORequest);
        return ResponseEntity.ok(patrocinadorService.atualizarPatrocinador(patrocinadorId, patrocinadorDTORequest));
    }

    @DeleteMapping("/deletarPatrocinadorId/{patrocinadorId}")
    @Operation(summary = "Apagar um Patrocinador pelo ID.", description = "Endpoint para apagar um Patrocinador pelo ID.")
    public ResponseEntity<Void> apagarPatrocinador (@PathVariable Integer patrocinadorId) {
        patrocinadorService.apagarPatrocinador(patrocinadorId);
        return ResponseEntity.noContent().build();
    }
}
