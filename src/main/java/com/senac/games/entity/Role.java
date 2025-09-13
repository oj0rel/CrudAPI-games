package com.senac.games.entity;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.senac.games.enums.RoleName;
import jakarta.persistence.*;

@Entity
@Table(name="role")
@JsonPOJOBuilder
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name")
    private RoleName name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }
}
