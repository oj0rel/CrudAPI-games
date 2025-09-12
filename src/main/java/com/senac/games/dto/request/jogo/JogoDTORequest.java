package com.senac.games.dto.request.jogo;

public class JogoDTORequest {
    private String nome;
    private Integer status;
    private Integer categoriaId;

    public JogoDTORequest() {}

    public JogoDTORequest(String nome, Integer status, Integer categoriaId) {
        this.nome = nome;
        this.status = status;
        this.categoriaId = categoriaId;
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
}
