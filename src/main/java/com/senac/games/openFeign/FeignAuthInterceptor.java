package com.senac.games.openFeign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

@Component
public class FeignAuthInterceptor implements RequestInterceptor {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    @Override
    public void apply(RequestTemplate template) {
        // Pega os atributos da requisição ATUAL (que chegou no seu controller)
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (Objects.nonNull(attributes)) {
            // Pega o cabeçalho "Authorization" da requisição atual
            String authorizationHeader = attributes.getRequest().getHeader(AUTHORIZATION_HEADER);

            if (Objects.nonNull(authorizationHeader)) {
                // Adiciona o cabeçalho na requisição do Feign
                template.header(AUTHORIZATION_HEADER, authorizationHeader);
            }
        }
    }

}
