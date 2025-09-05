package com.senac.games.controller;

import com.senac.games.dto.request.inscricao.InscricaoDTORequest;
import com.senac.games.dto.response.inscricao.InscricaoDTOResponse;
import com.senac.games.entity.Inscricao;
import com.senac.games.service.InscricaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/inscricao")
@Tag(name = "Inscrição", description = "API para gerenciamento de Inscrições")
public class InscricaoController {

    private InscricaoService inscricaoService;

    public InscricaoController (InscricaoService inscricaoService) { this.inscricaoService = inscricaoService; }

    @GetMapping("/listar")
    @Operation(summary = "Listar Inscrições", description = "Endpoint para listar todas as Inscrições.")
    public ResponseEntity <List<Inscricao>> listarInscricoes() { return ResponseEntity.ok(inscricaoService.listarInscricoes()); }

    @GetMapping("/listarInscricaoId/{inscricaoId}")
    @Operation(summary = "Listar a Inscricao pelo ID dela.", description = "Endpoint para listar uma Inscricao, pelo ID.")
    public ResponseEntity <Inscricao> listarInscricaoPorId(@PathVariable("inscricaoId") Integer inscricaoId) {
        Inscricao inscricao = inscricaoService.listarInscricaoPorId(inscricaoId);

        if (inscricaoId == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(inscricao);
        }
    }

    @PostMapping("/criar")
    @Operation(summary = "Criar nova Inscrição.", description = "Endpoint para criar um novo registro de Inscrição.")
    public ResponseEntity<InscricaoDTOResponse> criarInscricao(@Valid @RequestBody InscricaoDTORequest inscricao) {
        return ResponseEntity.status(HttpStatus.CREATED).body(inscricaoService.criarInscricao(inscricao));
    }

    @PutMapping("/atualizarInscricaoPorId/{inscricaoId}")
    @Operation(summary = "Atualizar Inscrição pelo ID.", description = "Endpoint para editar a Inscrição pelo ID.")
    public ResponseEntity<InscricaoDTOResponse> editarInscricao(@Valid @PathVariable("inscricaoId") Integer inscricaoId,
                                                                @RequestBody InscricaoDTORequest inscricaoDTORequest) {
        InscricaoDTOResponse inscricaoDTOResponse = inscricaoService.atualizarInscricao(inscricaoId, inscricaoDTORequest);
        return ResponseEntity.ok(inscricaoService.atualizarInscricao(inscricaoId, inscricaoDTORequest));
    }

    @DeleteMapping("/deletarInscricaoId/{inscricaoId}")
    @Operation(summary = "Apagar uma Inscrição pelo ID.", description = "Endpoint para apagar uma Inscrição pelo ID.")
    public ResponseEntity<Void> apagarInscricao (@PathVariable Integer inscricaoId) {
        inscricaoService.apagarInscricao(inscricaoId);
        return ResponseEntity.noContent().build();
    }
}
