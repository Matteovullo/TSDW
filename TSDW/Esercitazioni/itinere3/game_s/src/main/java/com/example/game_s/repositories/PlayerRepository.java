package com.example.game_s.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.game_s.models.Player;

public interface PlayerRepository extends JpaRepository<Player, Long>{
    
}
