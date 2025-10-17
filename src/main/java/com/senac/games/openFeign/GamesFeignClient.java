package com.senac.games.openFeign;

import com.senac.games.dto.response.patrocinador.PatrocinadorDTOResponse;
import com.senac.games.entity.Patrocinador;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(
        name = "games",
        url = "http://192.168.100.10:8080", //mudar o ip
        path= "/api/patrocinador",
        configuration = FeignAuthInterceptor.class
)
public interface GamesFeignClient {

    @GetMapping(value= "/listarPorPatrocinadorId/{patrocinadorId}")
    public PatrocinadorDTOResponse findById(@PathVariable("patrocinadorId") Integer patrocinadorId);
}
