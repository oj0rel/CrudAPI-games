package com.senac.games.dto.response.jogo;

import com.senac.games.entity.Inscricao;

import java.util.List;

public class JogoDTOResponse {
    private Integer id;
    private String nome;
    private Integer status;
    private List<Inscricao> inscricao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Inscricao> getInscricao() {
        return inscricao;
    }

    public void setInscricao(List<Inscricao> inscricao) {
        this.inscricao = inscricao;
    }
}
