package com.senac.games.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "premio")
public class Premio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "premio_id")
    private Integer id;
    @Column(name = "premio_descricao")
    private String descricao;
    @Column(name = "premio_ordem_premiacao")
    private Integer ordem_premiacao;
    @Column(name = "premio_categoria")
    private Integer categoria;
    @Column(name = "premio_status")
    private Integer status;

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
