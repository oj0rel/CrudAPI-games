package com.senac.games.service;

import com.senac.games.dto.CreateUserDto;
import com.senac.games.dto.LoginUserDto;
import com.senac.games.dto.RecoveryJwtTokenDto;
import com.senac.games.entity.Role;
import com.senac.games.entity.Usuario;
import com.senac.games.enums.RoleName;
import com.senac.games.repository.RoleRepository;
import com.senac.games.repository.UsuarioRepository;
import com.senac.games.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private RoleRepository roleRepository;

    public void createUser(CreateUserDto dto) {

        if (usuarioRepository.findByEmail(dto.email()).isPresent()) {
            throw new RuntimeException("Usuário com este email já existe");
        }

        Usuario user = new Usuario();
        user.setNome(dto.nome());
        user.setCpf(dto.cpf());
        user.setDataDeNascimento(dto.dataDeNascimento());
        user.setStatus(1);
        user.setEmail(dto.email());
        user.setPassword(passwordEncoder.encode(dto.password()));

        Role role = roleRepository.findByName(dto.role())
                .orElseThrow(() -> new RuntimeException("Role não encontrada"));

        user.setRoles(Collections.singletonList(role));

        usuarioRepository.save(user);
    }

    // Criação de usuário
//    public void createUser(CreateUserDto dto) {
//
//        // Verifica se já existe um usuário com o mesmo email
//        if (usuarioRepository.findByEmail(dto.email()).isPresent()) {
//            throw new RuntimeException("Usuário com este email já existe");
//        }
//
//        Usuario user = new Usuario();
//        user.setEmail(dto.email());
//        user.setCpf(dto.cpf());
//        user.setPassword(passwordEncoder.encode(dto.password()));
//
//        // Cria role diretamente a partir do enum
//        Role role = new Role();
//        role.setName(dto.role()); // dto.role() já é do tipo RoleName
//        user.setRoles(List.of(role));
//
//        usuarioRepository.save(user);
//    }

    // Autenticação de usuário e geração de token JWT
    public RecoveryJwtTokenDto authenticateUser(LoginUserDto dto) {

        // Autentica o usuário
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.email(), dto.password())
        );

        // Busca usuário no banco
        Usuario user = usuarioRepository.findByEmail(dto.email())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Gera token JWT
        String token = jwtTokenService.generateToken(new UserDetailsImpl(user));

        return new RecoveryJwtTokenDto(token);
    }
}
