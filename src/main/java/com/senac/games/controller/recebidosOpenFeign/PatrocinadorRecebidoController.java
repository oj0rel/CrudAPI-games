package com.senac.games.controller.recebidosOpenFeign;

import com.senac.games.dto.request.patrocinador.PatrocinadorDTORequest;
import com.senac.games.dto.response.patrocinador.PatrocinadorDTOResponse;
import com.senac.games.openFeign.PatrocinadorGamesFeignClient;
import com.senac.games.service.PatrocinadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/patrocinadorRecebido")
@Tag(name = "Patrocinador Recebido", description = "API para gerenciamento de Patrocinadores vindos de outra API.")
public class PatrocinadorRecebidoController {

    @Autowired
    PatrocinadorGamesFeignClient patrocinadorGamesFeignClient;
    @Autowired
    PatrocinadorService patrocinadorService;

    @GetMapping("listarTodosPatrocinadoresRecebidos")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<PatrocinadorDTOResponse>> listarPatrocinadoresRecebidos() {
        return ResponseEntity.ok(patrocinadorService.listarPatrocinadoresRecebidos());
    }

    @GetMapping("listarPatrocinadorRecebidoPorId/{patrocinadorId}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<PatrocinadorDTOResponse> listarPatrocinadorRecebidoPorId(
            @PathVariable("patrocinadorId") Integer patrocinadorId
    ) {
        PatrocinadorDTOResponse patrocinadorRecebido = patrocinadorGamesFeignClient.findById(patrocinadorId);
        PatrocinadorDTOResponse dto = patrocinadorRecebido;
        return ResponseEntity.ok(dto);
    }

    @PostMapping("criarPatrocinadorEnviar")
    @CrossOrigin(origins = "*")
    public ResponseEntity<PatrocinadorDTOResponse> criarPatrocinadorEnviar(
            @Valid
            @RequestBody PatrocinadorDTORequest patrocinadorDTORequest
    ) {
        return ResponseEntity.ok(patrocinadorService.criarPatrocinadorEnviar(patrocinadorDTORequest));
    }

//    @PostMapping ("/criar")
//    @Operation(summary="Criar patrocinadores", description="Endpoint para criar patrocinadores")
//    public ResponseEntity<PatrocinadorDTOResponse> criarPatrocinador(@Valid @RequestBody PatrocinadorDTORequest patrocinadorDTORequest) {
//        PatrocinadorDTOResponse novoPatrocinador = patrocinadorService.criarPatrocinador(patrocinadorDTORequest);
//        return ResponseEntity.status(HttpStatus.CREATED).body(novoPatrocinador);
//    }
}
