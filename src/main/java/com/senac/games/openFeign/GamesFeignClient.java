package com.senac.games.openFeign;

import com.senac.games.entity.Patrocinador;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(
        name = "games",
        url = "http://10.136.36.3:8080",
        path= "/api/patrocinador",
        configuration = FeignAuthInterceptor.class
)
public interface GamesFeignClient {

    @GetMapping(value= "listarPorPatrocinadorId/{patrocinadorId}")
    public Patrocinador findById(@PathVariable("patrocinadorId") Integer patrocinadorId);
}
