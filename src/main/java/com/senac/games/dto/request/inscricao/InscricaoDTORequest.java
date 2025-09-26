package com.senac.games.dto.request.inscricao;

import java.time.LocalDateTime;

public class InscricaoDTORequest {

    private LocalDateTime data;
    private Integer status;
    private Integer participanteId;
    private Integer jogoId;

    public InscricaoDTORequest() {}

    public InscricaoDTORequest(LocalDateTime data, Integer status, Integer participanteId, Integer jogoId) {
        this.data = data;
        this.status = status;
        this.participanteId = participanteId;
        this.jogoId = jogoId;
    }

    // Getters e setters...


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

    public Integer getParticipanteId() {
        return participanteId;
    }

    public void setParticipanteId(Integer participanteId) {
        this.participanteId = participanteId;
    }

    public Integer getJogoId() {
        return jogoId;
    }

    public void setJogoId(Integer jogoId) {
        this.jogoId = jogoId;
    }
}
