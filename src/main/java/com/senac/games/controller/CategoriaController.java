package com.senac.games.controller;

import com.senac.games.entity.Categoria;
import com.senac.games.service.CategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("api/categoria")
public class CategoriaController {

    private CategoriaService categoriaService;

    public CategoriaController (CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("/listar")
    public ResponseEntity <List<Categoria>> listarCategorias() { return ResponseEntity.ok(categoriaService.listarCategorias()); }
}
