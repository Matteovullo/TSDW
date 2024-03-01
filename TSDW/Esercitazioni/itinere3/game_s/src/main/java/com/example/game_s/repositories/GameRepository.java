package com.example.game_s.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.game_s.models.Game;

public interface GameRepository extends JpaRepository<Game, Long>{
    
}
