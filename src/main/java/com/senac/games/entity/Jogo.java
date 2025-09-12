package com.senac.games.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
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

    //isso aki é para enviar a tabela Jogo para Categoria
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    //para trazer a tabela Inscricao para Jogo
    @OneToMany(mappedBy = "jogo")
    private List<Inscricao> inscricoes;

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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Inscricao> getInscricoes() {
        return inscricoes;
    }

    public void setInscricoes(List<Inscricao> inscricoes) {
        this.inscricoes = inscricoes;
    }
}
