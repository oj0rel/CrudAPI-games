package com.senac.games.controller;

import com.senac.games.dto.request.jogo.JogoDTORequest;
import com.senac.games.dto.response.jogo.JogoDTOResponse;
import com.senac.games.entity.Jogo;
import com.senac.games.service.JogoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    public JogoController (JogoService jogoService) { this.jogoService = jogoService; }

    @GetMapping("/listar")
    @Operation(summary = "Listar Jogos.", description = "Endpoint para listar todos os Jogos.")
    public ResponseEntity <List<JogoDTOResponse>> listarInscricoes() { return ResponseEntity.ok(jogoService.listarJogos()); }

    @GetMapping("/listarJogoId/{jogoId}")
    @Operation(summary = "Listar o Jogo pelo ID dele.", description = "Endpoint para listar um Jogo, pelo ID.")
    public ResponseEntity <Jogo> listarJogoPorId(@PathVariable("jogoId") Integer jogoId) {
        Jogo jogo = jogoService.listarJogoPorId(jogoId);

        if (jogoId == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(jogo);
        }
    }

    @PostMapping("/criar")
    @Operation(summary = "Criar novo Jogo.", description = "Endpoint para criar um novo registro de Jogo.")
    public ResponseEntity<JogoDTOResponse> criarJogo(@Valid @RequestBody JogoDTORequest jogo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(jogoService.criarJogo(jogo));
    }

    @PutMapping("/atualizarJogoPorId/{jogoId}")
    @Operation(summary = "Atualizar Jogo pelo ID.", description = "Endpoint para editar o Jogo pelo ID.")
    public ResponseEntity<JogoDTOResponse> editarJogo(@Valid @PathVariable("jogoId") Integer jogoId,
                                                                @RequestBody JogoDTORequest jogoDTORequest) {
        JogoDTOResponse jogoDTOResponse = jogoService.atualizarJogo(jogoId, jogoDTORequest);
        return ResponseEntity.ok(jogoService.atualizarJogo(jogoId, jogoDTORequest));
    }

    @DeleteMapping("/deletarJogoId/{jogoId}")
    @Operation(summary = "Apagar um Jogo pelo ID.", description = "Endpoint para apagar um Jogo pelo ID.")
    public ResponseEntity<Void> apagarJogo (@PathVariable Integer jogoId) {
        jogoService.apagarJogo(jogoId);
        return ResponseEntity.noContent().build();
    }
}
