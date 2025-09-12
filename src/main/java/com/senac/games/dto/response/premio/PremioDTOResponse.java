package com.senac.games.dto.response.premio;

public class PremioDTOResponse {
    private Integer id;
    private String descricao;
    private Integer ordem_premiacao;
    private Integer categoria;
    private Integer status;

    public PremioDTOResponse() {}

    public PremioDTOResponse(Integer id, String descricao, Integer ordem_premiacao, Integer categoria, Integer status) {
        this.id = id;
        this.descricao = descricao;
        this.ordem_premiacao = ordem_premiacao;
        this.categoria = categoria;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
