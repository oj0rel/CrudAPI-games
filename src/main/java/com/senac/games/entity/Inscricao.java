package com.senac.games.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "inscricao")
public class Inscricao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inscricao_id")
    private Integer id;

    @Column(name = "inscricao_data")
    private LocalDateTime data;

    @Column(name = "inscricao_status")
    private int status;

    //isso aki é para enviar a tabela Inscricao para Participante
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "participante_id", nullable = false)
    private Participante participante;

    //isso aki é para enviar a tabela Inscricao para Jogo
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "jogo_id", nullable = false)
    private Jogo jogo;

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    public Jogo getJogo() {
        return jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

}
