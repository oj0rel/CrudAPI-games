package com.senac.games.controller;

import com.senac.games.dto.request.jogo.JogoDTORequest;
import com.senac.games.dto.response.jogo.JogoDTOResponse;
import com.senac.games.entity.Jogo;
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
    @Operation(summary="Listar jogos", description="Endpoint para listar todos os jogos")
    public ResponseEntity<List<JogoDTOResponse>> listarJogos() {
        return ResponseEntity.ok(jogoService.listarJogos());
    }
    @GetMapping("/listarPorJogoId/{jogoId}")
    @Operation(summary="Listar jogo pelo id do jogo", description="Endpoint para listar jogo por id")
    public ResponseEntity<JogoDTOResponse> listarPorJogoId(@PathVariable("jogoId") Integer jogoId) {
        JogoDTOResponse jogo = jogoService.listarPorJogoId(jogoId);
        return ResponseEntity.ok(jogo);
    }

    @PostMapping("/criar")
    @Operation(summary="Criar jogo", description="Endpoint para criar jogo")
    public ResponseEntity<JogoDTOResponse> criarJogo(@Valid @RequestBody JogoDTORequest jogoDTORequest) {
        try {
            JogoDTOResponse novoJogo = jogoService.criarJogo(jogoDTORequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoJogo);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/editarPorJogoId/{jogoId}")
    @Operation(summary="Editar jogo pelo id do jogo", description="Endpoint para editar jogo por id")
    public ResponseEntity<JogoDTOResponse> editarPorJogoId(@PathVariable("jogoId") Integer jogoId,
                                                           @Valid @RequestBody JogoDTORequest jogoDTORequest) {
        try {
            JogoDTOResponse jogoAtualizado = jogoService.editarPorJogoId(jogoId, jogoDTORequest);
            return ResponseEntity.ok(jogoAtualizado);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deletarPorJogoId/{jogoId}")
    @Operation(summary="Deletar jogo pelo id do jogo", description="Endpoint para deletar jogo por id")
    public ResponseEntity<Void> deletarPorJogoId(@PathVariable("jogoId") Integer jogoId) {
        try {
            jogoService.deletarPorJogoId(jogoId);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
