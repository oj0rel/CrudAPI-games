package com.senac.games.config;

import com.senac.games.dto.response.jogo.JogoDTOResponse;
import com.senac.games.entity.Jogo;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

        // Mapeamento de Jogo -> JogoDTOResponse
        mapper.typeMap(Jogo.class, JogoDTOResponse.class)
                .addMappings(m -> m.map(Jogo::getInscricoes, JogoDTOResponse::setInscricoes));

        return mapper;
    }
}
