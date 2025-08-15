package com.senac.games.controller;

import com.senac.games.entity.Premio;
import com.senac.games.service.PremioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/premio")
public class PremioController {

    private PremioService premioService;

    public PremioController (PremioService premioService) {
        this.premioService = premioService;
    }

    @GetMapping("/listar")
    public ResponseEntity <List<Premio>> listarPremios() {
        return ResponseEntity.ok(premioService.listarPremios());
    }
}
