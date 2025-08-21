package com.senac.games.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "jogo")
public class Jogo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "jogo_id")
    private Integer id;

    @Column(name = "jogo_nome")
    private String nome;

    @Column(name = "jogo_status")
    private int status;

    //isso aki é para levar a tabela Jogo para Inscricao
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "inscricao_id", nullable = false)
    private Inscricao inscricao;

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Inscricao getInscricao() {
        return inscricao;
    }

    public void setInscricao(Inscricao inscricao) {
        this.inscricao = inscricao;
    }
}
