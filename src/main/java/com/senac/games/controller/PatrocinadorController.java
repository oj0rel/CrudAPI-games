package com.senac.games.controller;

import com.senac.games.dto.request.patrocinador.PatrocinadorDTORequest;
import com.senac.games.dto.response.patrocinador.PatrocinadorDTOResponse;
import com.senac.games.openFeign.PatrocinadorGamesFeignClient;
import com.senac.games.service.PatrocinadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/patrocinador")
@Tag(name = "Patrocinador", description = "API para gerenciamento de Patrocinadores.")
public class PatrocinadorController {
    private PatrocinadorService patrocinadorService;

    @Autowired
    PatrocinadorGamesFeignClient patrocinadorGamesFeignClient;

    public PatrocinadorController(PatrocinadorService patrocinadorService) {
        this.patrocinadorService = patrocinadorService;
    }

    @GetMapping("/listar")
    @Operation(summary="Listar patrocinador", description="Endpoint para listar todos os patrocinadores")
    public ResponseEntity<List<PatrocinadorDTOResponse>> listarPatrocinadores() {
        return ResponseEntity.ok(patrocinadorService.listarPatrocinadores());
    }


    @GetMapping("/listarPorPatrocinadorId/{patrocinadorId}")
    @Operation(summary="Listar patrocinadores pelo id do patrocinador", description="Endpoint para listar patrocinadores por id")
    public ResponseEntity<PatrocinadorDTOResponse> listarPorPatrocinadorId(@PathVariable("patrocinadorId") Integer patrocinadorId) {
        try {
            PatrocinadorDTOResponse patrocinador = patrocinadorService.listarPorPatrocinadorId(patrocinadorId);
            return ResponseEntity.ok(patrocinador);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping ("/criar")
    @Operation(summary="Criar patrocinadores", description="Endpoint para criar patrocinadores")
    public ResponseEntity<PatrocinadorDTOResponse> criarPatrocinador(@Valid @RequestBody PatrocinadorDTORequest patrocinadorDTORequest) {
        PatrocinadorDTOResponse novoPatrocinador = patrocinadorService.criarPatrocinador(patrocinadorDTORequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPatrocinador);
    }

    @DeleteMapping("/deletarPorPatrocinadorId/{patrocinadorId}")
    @Operation(summary="Deletar patrocinadores pelo id do patrocinador", description="Endpoint para deletar patrocinadores por id")
    public ResponseEntity<Void> deletarPorPatrocinadorId(@PathVariable("patrocinadorId") Integer patrocinadorId) {
        try {
            patrocinadorService.deletarPorPatrocinadorId(patrocinadorId);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/editarPorPatrocinadorId/{patrocinadorId}")
    @Operation(summary="Listar patrocinadores pelo id do patrocinador", description="Endpoint para editar patrocinadores por id")
    public ResponseEntity<PatrocinadorDTOResponse> editarPorPatrocinadorId(@PathVariable("patrocinadorId") Integer patrocinadorId,
                                                                           @Valid @RequestBody PatrocinadorDTORequest patrocinadorDTORequest) {
        try {
            PatrocinadorDTOResponse patrocinadorAtualizado = patrocinadorService.editarPorPatrocinadorId(patrocinadorId, patrocinadorDTORequest);
            return ResponseEntity.ok(patrocinadorAtualizado);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
