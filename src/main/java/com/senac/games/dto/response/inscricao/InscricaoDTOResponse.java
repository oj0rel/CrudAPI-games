package com.senac.games.dto.response.inscricao;

import com.senac.games.entity.Jogo;
import com.senac.games.entity.Participante;

import java.time.LocalDateTime;
import java.util.List;

public class InscricaoDTOResponse {

    private Integer id;
    private LocalDateTime data;
    private Integer status;

    private List<Participante> participante;
    private List<Jogo> jogo;

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

    public List<Participante> getParticipante() {
        return participante;
    }

    public void setParticipante(List<Participante> participante) {
        this.participante = participante;
    }

    public List<Jogo> getJogo() {
        return jogo;
    }

    public void setJogo(List<Jogo> jogo) {
        this.jogo = jogo;
    }
}
