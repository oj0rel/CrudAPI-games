package com.senac.games.dto.request.premio;

public class PremioDTORequest {
    private String descricao;
    private Integer ordem_premiacao;
    private Integer categoria;
    private Integer status;

    public PremioDTORequest() {
    }

    public PremioDTORequest(String descricao, Integer ordem_premiacao, Integer categoria, Integer status) {
        this.descricao = descricao;
        this.ordem_premiacao = ordem_premiacao;
        this.categoria = categoria;
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getOrdem_premiacao() {
        return ordem_premiacao;
    }

    public void setOrdem_premiacao(Integer ordem_premiacao) {
        this.ordem_premiacao = ordem_premiacao;
    }

    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
