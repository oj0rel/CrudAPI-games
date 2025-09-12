package com.senac.games.controller;

import com.senac.games.dto.request.inscricao.InscricaoDTORequest;
import com.senac.games.dto.response.inscricao.InscricaoDTOResponse;
import com.senac.games.entity.Inscricao;
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
    @Operation(summary="Listar inscricoes", description="Endpoint para listar todas as inscricoes")
    public ResponseEntity<List<InscricaoDTOResponse>> listarInscricao(){
        return ResponseEntity.ok(inscricaoService.listarInscricoesAtivos());
    }

    @PostMapping("/criar")
    @Operation(summary="Criar inscricoes", description="Endpoint para criar inscricoes")
    public ResponseEntity<InscricaoDTOResponse> criarInscricao(@Valid @RequestBody InscricaoDTORequest inscricaoDTORequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(inscricaoService.criarInscricao(inscricaoDTORequest));
    }

    @GetMapping("/listarPorInscricaoId/{inscricaoId}")
    @Operation(summary="Listar inscricoes pelo id inscricao", description="Endpoint para listar inscricoes por id")
    public ResponseEntity<InscricaoDTOResponse> listarPorInscricaoId(@PathVariable("inscricaoId") Integer inscricaoId) {
        try {
            InscricaoDTOResponse inscricao = inscricaoService.listarPorInscricaoId(inscricaoId);
            return ResponseEntity.ok(inscricao);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deletarPorInscricaoId/{inscricaoId}")
    @Operation(summary="Deletar inscricoes pelo id da inscricao", description="Endpoint para deletar inscricoes por id")
    public ResponseEntity<Void> deletarPorInscricaoId(@PathVariable Integer inscricaoId) {
        try {
            inscricaoService.deletarPorInscricaoId(inscricaoId);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/editarPorInscricaoId/{inscricaoId}")
    @Operation(summary="Editar inscricoes pelo id da inscricao", description="Endpoint para editar inscricoes por id")
    public ResponseEntity<InscricaoDTOResponse> editarPorInscricaoId(@PathVariable Integer inscricaoId,
                                                                     @RequestBody InscricaoDTORequest inscricaoDTORequest) {
        try {
            InscricaoDTOResponse inscricaoAtualizada = inscricaoService.editarPorInscricaoId(inscricaoId, inscricaoDTORequest);
            return ResponseEntity.ok(inscricaoAtualizada); // 200 OK com o inscricao atualizado
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build(); // 404 se não achar o inscricao
        }
    }
}
