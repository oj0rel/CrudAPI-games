package com.senac.games.controller;

import com.senac.games.dto.request.categoria.CategoriaDTORequest;
import com.senac.games.dto.response.categoria.CategoriaDTOResponse;
import com.senac.games.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categoria")
@Tag(name = "Categoria", description = "API para gerenciamento de Categorias.")
public class CategoriaController {private CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("/listar")
    @Operation(summary="Listar Categorias", description="Endpoint para listar todas as Categorias.")
    public ResponseEntity<List<CategoriaDTOResponse>> listarCategorias(){
        return ResponseEntity.ok(categoriaService.listarCategoriasAtivas());
    }

    @GetMapping("/listarCategoriaPorId/{categoriaId}")
    @Operation(summary="Listar Categoria pelo ID", description="Endpoint para listar a Categoria pelo ID.")
    public ResponseEntity <CategoriaDTOResponse> listarCategoriaPorId(
            @PathVariable Integer categoriaId
    ) {
        try{
            CategoriaDTOResponse categoria = categoriaService.listarCategoriaPorId(categoriaId);
            return ResponseEntity.ok(categoria);
        }catch(EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping ("/criar")
    @Operation(summary="Criar Categoria", description="Endpoint para criar uma Categoria.")
    public ResponseEntity<CategoriaDTOResponse> criarCategoria(
            @Valid
            @RequestBody CategoriaDTORequest categoria
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.criarCategoria(categoria));
    }

    @PutMapping("/editarCategoriaPorId/{categoriaId}")
    @Operation(summary="Editar Categoria por ID", description="Endpoint para editar Categoria por id")
    public ResponseEntity<CategoriaDTOResponse> editarCategoriaPorId(
            @PathVariable Integer categoriaId,
            @RequestBody CategoriaDTORequest categoriaDTORequest
    ) {
        try {
            CategoriaDTOResponse categoriaAtualizada = categoriaService.editarCategoriaPorId(categoriaId, categoriaDTORequest);
            return ResponseEntity.ok(categoriaAtualizada); // 200 OK com a categoria atualizada
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build(); // 404 se não achar a categoria
        }
    }

    @DeleteMapping("/deletarCategoriaPorId/{categoriaId}")
    @Operation(summary="Deletar Categoria por ID", description="Endpoint para deletar Categoria pelo ID.")
    public ResponseEntity<Void> deletarCategoriaPorId(
            @PathVariable Integer categoriaId
    ) {
        try {
            categoriaService.apagarCategoria(categoriaId);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
