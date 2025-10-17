package com.senac.games.openFeign;

import com.senac.games.dto.response.patrocinador.PatrocinadorDTOResponse;
import com.senac.games.repository.PatrocinadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Component
@FeignClient(
        name = "games",
        url = "http://10.136.36.31:8082", //mudar o ip
        path= "/api/patrocinador",
        configuration = FeignAuthInterceptor.class
)
public interface PatrocinadorGamesFeignClient {

    @GetMapping(value = "/listar")
    public List<PatrocinadorDTOResponse> listarPatrocinadoresRecebidos();

    @GetMapping(value= "/listarPorPatrocinadorId/{patrocinadorId}")
    public PatrocinadorDTOResponse findById(@PathVariable("patrocinadorId") Integer patrocinadorId);


}
