package com.senac.games.controller;

import com.senac.games.dto.request.categoria.CategoriaDTORequest;
import com.senac.games.dto.response.categoria.CategoriaDTOResponse;
import com.senac.games.entity.Categoria;
import com.senac.games.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categoria")
@Tag(name = "Categoria", description = "API para gerenciamento de Categorias.")
public class CategoriaController {

    private CategoriaService categoriaService;

    public CategoriaController (CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar Categorias.", description = "Endpoint para listar todas as Categorias.")
    public ResponseEntity <List<Categoria>> listarCategorias() { return ResponseEntity.ok(categoriaService.listarCategorias()); }

    @GetMapping("/listarCategoriaId/{categoriaId}")
    @Operation(summary = "Listar a Categoria pelo ID dela.", description = "Endpoint para listar uma Categoria, pelo ID.")
    public ResponseEntity <Categoria> listarCategoriaPorId(@PathVariable("categoriaId") Integer categoriaId) {
        Categoria categoria = categoriaService.listarCategoriaPorId(categoriaId);

        if (categoriaId == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(categoria);
        }
    }

    @PostMapping("/criar")
    @Operation(summary = "Criar nova Categoria.", description = "Endpoint para criar um novo registro de Categoria.")
    public ResponseEntity<CategoriaDTOResponse> criarCategoria(@Valid @RequestBody CategoriaDTORequest categoria) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.criarCategoria(categoria));
    }

    @PutMapping("/atualizarCategoriaPorId/{categoriaId}")
    @Operation(summary = "Atualizar Categoria pelo ID.", description = "Endpoint para editar a Categoria pelo ID.")
    public ResponseEntity<CategoriaDTOResponse> editarCategoria(@Valid @PathVariable("participanteId") Integer categoriaId,
                                                                @RequestBody CategoriaDTORequest categoriaDTORequest) {
        CategoriaDTOResponse categoriaDTOResponse = categoriaService.atualizarCategoria(categoriaId, categoriaDTORequest);
        return ResponseEntity.ok(categoriaService.atualizarCategoria(categoriaId, categoriaDTORequest));
    }

    @DeleteMapping("/deletarCategoriaId/{categoriaId}")
    @Operation(summary = "Apagar uma Categoria pelo ID.", description = "Endpoint para apagar uma Categoria pelo ID.")
    public ResponseEntity<Void> apagarCategoria (@PathVariable Integer categoriaId) {
        categoriaService.apagarCategoria(categoriaId);
        return ResponseEntity.noContent().build();
    }
}
