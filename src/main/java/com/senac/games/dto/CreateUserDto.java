package com.senac.games.dto;

import com.senac.games.entity.Role;
import com.senac.games.entity.Usuario;
import com.senac.games.enums.RoleName;

import java.util.Date;
import java.util.List;

public record CreateUserDto(
        String nome,
        String cpf,
        Date dataDeNascimento,
        String email,
        String password,
        RoleName role
) {
}
