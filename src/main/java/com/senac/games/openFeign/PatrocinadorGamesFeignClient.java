package com.senac.games.openFeign;

import com.senac.games.dto.request.patrocinador.PatrocinadorDTORequest;
import com.senac.games.dto.response.patrocinador.PatrocinadorDTOResponse;
import com.senac.games.entity.Patrocinador;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@FeignClient(
        name = "games",
        url = "http://192.168.100.10:8080", //mudar o ip
        path= "/api/patrocinador",
        configuration = FeignAuthInterceptor.class
)
public interface PatrocinadorGamesFeignClient {

    @GetMapping(value = "/listar")
    public List<PatrocinadorDTOResponse> listarPatrocinadoresRecebidos();

    @GetMapping(value= "/listarPatrocinadorPorId/{patrocinadorId}")
    public PatrocinadorDTOResponse findById(@PathVariable("patrocinadorId") Integer patrocinadorId);

    @PostMapping("/criar")
    @ResponseBody
    public PatrocinadorDTOResponse criarPatrocinadorEnviar(@RequestBody PatrocinadorDTORequest patrocinadorDTORequest);

    @PutMapping("/editarPatrocinadorPorId/{patrocinadorId}")
    public PatrocinadorDTOResponse editarPatrocinadorRecebidoPorId(
            @PathVariable("patrocinadorId") Integer patrocinadorId,
            @RequestBody PatrocinadorDTORequest patrocinadorDTORequest
    );

    @DeleteMapping(value = "/deletarPatrocinadorPorId/{patrocinadorId}")
    public void deletarPatrocinadorEnviar(@PathVariable("patrocinadorId") Integer patrocinadorId);
}
