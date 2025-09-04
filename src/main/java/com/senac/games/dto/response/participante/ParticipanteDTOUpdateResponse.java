package com.senac.games.dto.response.participante;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public class ParticipanteDTOUpdateResponse {
    @NotEmpty
    private Integer id;

    @NotEmpty
    @Min(0)
    @Max(2)
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}