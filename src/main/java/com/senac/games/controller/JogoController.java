package com.senac.games.controller;

import com.senac.games.dto.request.jogo.JogoDTORequest;
import com.senac.games.dto.response.jogo.JogoDTOResponse;
import com.senac.games.service.JogoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/jogo")
@Tag(name = "Jogo", description = "API para gerenciamento de Jogos.")
public class JogoController {
    private JogoService jogoService;

    public JogoController(JogoService jogoService) {
        this.jogoService = jogoService;
    }

    @GetMapping("/listar")
    @Operation(summary="Listar Jogos", description="Endpoint para listar todos os Jogos.")
    public ResponseEntity<List<JogoDTOResponse>> listarJogos() {
        return ResponseEntity.ok(jogoService.listarJogos());
    }

    @GetMapping("/listarJogoPorId/{jogoId}")
    @Operation(summary="Listar Jogo pelo ID", description="Endpoint para listar Jogo pelo ID.")
    public ResponseEntity<JogoDTOResponse> listarJogoPorId(
            @PathVariable("jogoId") Integer jogoId
    ) {
        JogoDTOResponse jogo = jogoService.listarJogoPorId(jogoId);
        return ResponseEntity.ok(jogo);
    }

    @PostMapping("/criar")
    @Operation(summary="Criar Jogo", description="Endpoint para criar Jogo.")
    public ResponseEntity<JogoDTOResponse> criarJogo(
            @Valid
            @RequestBody JogoDTORequest jogoDTORequest
    ) {
        try {
            JogoDTOResponse novoJogo = jogoService.criarJogo(jogoDTORequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoJogo);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/editarJogoPorId/{jogoId}")
    @Operation(summary="Editar Jogo pelo ID", description="Endpoint para editar Jogo pelo ID.")
    public ResponseEntity<JogoDTOResponse> editarJogoPorId(
            @PathVariable("jogoId") Integer jogoId,
            @Valid @RequestBody JogoDTORequest jogoDTORequest
    ) {
        try {
            JogoDTOResponse jogoAtualizado = jogoService.editarJogoPorId(jogoId, jogoDTORequest);
            return ResponseEntity.ok(jogoAtualizado);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deletarJogoPorId/{jogoId}")
    @Operation(summary="Deletar Jogo pelo ID", description="Endpoint para deletar Jogo pelo ID.")
    public ResponseEntity<Void> deletarJogoPorId(
            @PathVariable("jogoId") Integer jogoId
    ) {
        try {
            jogoService.deletarJogoPorId(jogoId);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
