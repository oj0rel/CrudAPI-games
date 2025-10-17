package com.senac.games.controller;

import com.senac.games.dto.request.inscricao.InscricaoDTORequest;
import com.senac.games.dto.response.inscricao.InscricaoDTOResponse;
import com.senac.games.service.InscricaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
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

    public InscricaoController(InscricaoService inscricaoService) {
        this.inscricaoService = inscricaoService;
    }

    @GetMapping("/listar")
    @Operation(summary="Listar Inscrições", description="Endpoint para listar todas as Inscrições.")
    public ResponseEntity<List<InscricaoDTOResponse>> listarInscricao(){
        return ResponseEntity.ok(inscricaoService.listarInscricoesAtivos());
    }

    @GetMapping("/listarInscricaoPorId/{inscricaoId}")
    @Operation(summary="Listar Inscrição pelo ID", description="Endpoint para listar Inscrição pelo ID.")
    public ResponseEntity<InscricaoDTOResponse> listarInscricaoPorId(
            @PathVariable("inscricaoId") Integer inscricaoId
    ) {
        try {
            InscricaoDTOResponse inscricao = inscricaoService.listarPorInscricaoId(inscricaoId);
            return ResponseEntity.ok(inscricao);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/criar")
    @Operation(summary="Criar Inscrições", description="Endpoint para criar Inscrições.")
    public ResponseEntity<InscricaoDTOResponse> criarInscricao(
            @Valid
            @RequestBody InscricaoDTORequest inscricaoDTORequest
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(inscricaoService.criarInscricao(inscricaoDTORequest));
    }

    @PutMapping("/editarInscricaoPorId/{inscricaoId}")
    @Operation(summary="Editar Inscrições pelo ID", description="Endpoint para editar Inscrições pelo ID.")
    public ResponseEntity<InscricaoDTOResponse> editarInscricaoPorId(
            @PathVariable Integer inscricaoId,
            @RequestBody InscricaoDTORequest inscricaoDTORequest
    ) {
        try {
            InscricaoDTOResponse inscricaoAtualizada = inscricaoService.editarInscricaoPorId(inscricaoId, inscricaoDTORequest);
            return ResponseEntity.ok(inscricaoAtualizada); // 200 OK com o inscricao atualizado
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build(); // 404 se não achar o inscricao
        }
    }

    @DeleteMapping("/deletarInscricaoPorId/{inscricaoId}")
    @Operation(summary="Deletar Inscrições pelo ID", description="Endpoint para deletar Inscrições pelo ID.")
    public ResponseEntity<Void> deletarInscricaoPorId(
            @PathVariable Integer inscricaoId
    ) {
        try {
            inscricaoService.deletarInscricaoPorId(inscricaoId);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
