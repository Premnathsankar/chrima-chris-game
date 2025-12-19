package com.chrima_chris_game.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.chrima_chris_game.entity.Player;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

    boolean existsByPhone(String phone);

    Player findByPhone(String phone);
}
