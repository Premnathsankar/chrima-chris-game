package com.chrima_chris_game.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chrima_chris_game.entity.Player;
import com.chrima_chris_game.repository.PlayerRepository;


@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public String addPlayer(Player player) {

        if (player.getName() == null || player.getName().trim().isEmpty()
            || player.getPhone() == null || player.getPhone().trim().isEmpty()) {
            return "Name and phone number must not be empty";
        }

        if (playerRepository.existsByPhone(player.getPhone())) {
            return "Phone number already exists";
        }

        playerRepository.save(player);
        return "Player added successfully";
    }
    
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }
    
    public String deletePlayer(int id) {
        if (!playerRepository.existsById(id)) {
            return "Player not found";
        }
        playerRepository.deleteById(id);
        return "Player deleted successfully";
    }


}
