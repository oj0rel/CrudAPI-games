package com.senac.games.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "patrocinador") //definir o nome da tabela
public class Patrocinador {
    @Id //anotação explicitando que é um ID
    @GeneratedValue(strategy = GenerationType.IDENTITY) //gera o valor automaticamente
    @Column(name = "patrocinador_id") //vai puxar do banco a informacao da coluna que tiver com este mesmo nome entre parênteses
    private Integer id;
    @Column(name = "patrocinador_nome")
    private String nome;
    @Column(name = "patrocinador_representante_nome")
    private String representanteNome;
    @Column(name = "patrocinador_status")
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRepresentanteNome() {
        return representanteNome;
    }

    public void setRepresentanteNome(String representanteNome) {
        this.representanteNome = representanteNome;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
