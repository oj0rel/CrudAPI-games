package com.senac.games.controller;

import com.senac.games.entity.Patrocinador;
import com.senac.games.service.PatrocinadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/patrocinador")
@Tag(name = "Patrocinador", description = "API para gerenciamento de patrocinadores.")
public class PatrocinadorController {

    private PatrocinadorService patrocinadorService;

    public PatrocinadorController(PatrocinadorService patrocinadorService) { //neste construtor ocorre a injeçao de dependencias
        this.patrocinadorService = patrocinadorService;
    }

    //este metodo esta chamando a funcao que eu criei la no service
    @GetMapping("/listar") //definindo a rota
    @Operation(summary = "Listar patrocinadores", description = "Endpoint para listar todos os patrocinadores.")
    public ResponseEntity <List<Patrocinador>> listarPatrocinadores() { //ResponseEntity devolve o cabeçario necessario para o request
        return ResponseEntity.ok(patrocinadorService.listarPatrocinadores());
    }
}
