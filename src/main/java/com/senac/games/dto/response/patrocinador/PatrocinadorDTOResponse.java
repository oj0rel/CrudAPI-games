package com.senac.games.dto.response.patrocinador;

import com.senac.games.entity.Patrocinador;

public class PatrocinadorDTOResponse {
    private Integer id;

    private String nome;

    private String representanteNome;

    private Integer status;

    public PatrocinadorDTOResponse() {}

    public PatrocinadorDTOResponse(Integer id, String nome, String representanteNome, Integer status) {
        this.id = id;
        this.nome = nome;
        this.representanteNome = representanteNome;
        this.status = status;
    }

    public PatrocinadorDTOResponse(Patrocinador patrocinador) {
        this.id = patrocinador.getId();
        this.nome = patrocinador.getNome();
        this.representanteNome = patrocinador.getRepresentanteNome();
        this.status = patrocinador.getStatus();
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getRepresentanteNome() { return representanteNome; }

    public void setRepresentanteNome(String representanteNome) { this.representanteNome = representanteNome; }

    public Integer getStatus() { return status; }

    public void setStatus(Integer status) { this.status = status; }
}
