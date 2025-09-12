package com.senac.games.controller;

import com.senac.games.dto.request.categoria.CategoriaDTORequest;
import com.senac.games.dto.response.categoria.CategoriaDTOResponse;
import com.senac.games.entity.Categoria;
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
    @Operation(summary="Listar categorias", description="Endpoint para listar todas as categorias")
    public ResponseEntity<List<CategoriaDTOResponse>> listarCategorias(){
        return ResponseEntity.ok(categoriaService.listarCategoriasAtivas());
    }

    @PostMapping ("/criar")
    @Operation(summary="Criar categorias", description="Endpoint para listar todas as categorias")
    public ResponseEntity<CategoriaDTOResponse> criarCategoria(@Valid @RequestBody CategoriaDTORequest categoria){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.criarCategoria(categoria));
    }

    @GetMapping("/listarPorCategoriaId/{categoriaId}")
    @Operation(summary="Listar categoria pelo id de categoria", description="Endpoint para listar o categoria pelo id")
    public ResponseEntity <CategoriaDTOResponse> listarPorCategoriaId(@PathVariable Integer categoriaId) {
        try{
            CategoriaDTOResponse categoria = categoriaService.listarPorCategoriaId(categoriaId);
            return ResponseEntity.ok(categoria);
        }catch(EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deletarPorCategoriaId/{categoriaId}")
    @Operation(summary="Deletar categoria pelo id da categoria", description="Endpoint para deletar categoria pelo id")
    public ResponseEntity<Void> deletarPorCategoriaId(@PathVariable Integer categoriaId) {
        try {
            categoriaService.apagarCategoria(categoriaId);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/editarPorCategoriaId/{categoriaId}")
    @Operation(summary="Editar categoria pelo id da categoria", description="Endpoint para deletar categoria pelo id")
    public ResponseEntity<CategoriaDTOResponse> editarPorCategoriaId(@PathVariable Integer categoriaId,
                                                                     @RequestBody CategoriaDTORequest categoriaDTORequest) {
        try {
            CategoriaDTOResponse categoriaAtualizada = categoriaService.editarPorCategoriaId(categoriaId, categoriaDTORequest);
            return ResponseEntity.ok(categoriaAtualizada); // 200 OK com a categoria atualizada
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build(); // 404 se não achar a categoria
        }
    }
}
