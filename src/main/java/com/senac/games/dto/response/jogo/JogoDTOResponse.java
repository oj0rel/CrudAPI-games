package com.senac.games.dto.response.jogo;

import com.senac.games.dto.response.inscricao.InscricaoDTOResponse;

import java.util.List;

public class JogoDTOResponse {
    private Integer id;
    private String nome;
    private Integer status;
    private Integer categoriaId;
    private List<InscricaoDTOResponse> inscricoes;

    public JogoDTOResponse() {}

    public JogoDTOResponse(Integer id, String nome, Integer status,Integer categoriaId, List<InscricaoDTOResponse> inscricoes) {
        this.id = id;
        this.nome = nome;
        this.status = status;
        this.categoriaId=categoriaId;
        this.inscricoes = inscricoes;
    }

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

    public Integer getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
    }

    public List<InscricaoDTOResponse> getInscricoes() {
        return inscricoes;
    }

    public void setInscricoes(List<InscricaoDTOResponse> inscricoes) {
        this.inscricoes = inscricoes;
    }
}
