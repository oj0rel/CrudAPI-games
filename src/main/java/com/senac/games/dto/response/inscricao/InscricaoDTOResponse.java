package com.senac.games.dto.response.inscricao;

import com.senac.games.entity.Jogo;
import com.senac.games.entity.Participante;

import java.time.LocalDateTime;
import java.util.List;

public class InscricaoDTOResponse {

    private Integer id;
    private LocalDateTime data;
    private Integer status;
    private Integer participanteId;
    private Integer jogoId;


    public InscricaoDTOResponse() {
    }

    public InscricaoDTOResponse(Integer id, LocalDateTime data, Integer status, Integer participanteId, Integer jogoId) {
        this.id = id;
        this.data = data;
        this.status = status;
        this.participanteId = participanteId;
        this.jogoId = jogoId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
