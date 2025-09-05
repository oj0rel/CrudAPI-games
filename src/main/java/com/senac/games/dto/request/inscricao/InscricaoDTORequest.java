package com.senac.games.dto.request.inscricao;

import java.time.LocalDateTime;

public class InscricaoDTORequest {

    private LocalDateTime data;
    private Integer status;

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
