package com.senac.games.repository;

import com.senac.games.entity.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JogoRepository extends JpaRepository<Jogo, Integer> {
}
