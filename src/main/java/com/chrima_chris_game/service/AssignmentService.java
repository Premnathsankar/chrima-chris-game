package com.chrima_chris_game.service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.chrima_chris_game.entity.Assignment;
import com.chrima_chris_game.entity.Player;
import com.chrima_chris_game.repository.AssignmentRepository;
import com.chrima_chris_game.repository.PlayerRepository;


@Service
public class AssignmentService {

	
    private final PlayerRepository playerRepository;
    private final AssignmentRepository assignmentRepository;

    public AssignmentService(PlayerRepository playerRepository,
                             AssignmentRepository assignmentRepository) {
        this.playerRepository = playerRepository;
        this.assignmentRepository = assignmentRepository;
    }

    public String startGame() {

        List<Player> players = playerRepository.findAll();

        if (players.size() < 2) {
            return "At least 2 players are required";
        }

        // clear old assignments if any
        assignmentRepository.deleteAll();

        List<Player> shuffled = new ArrayList<>(players);

        boolean valid = false;

        while (!valid) {
            Collections.shuffle(shuffled);
            valid = true;

            for (int i = 0; i < players.size(); i++) {
                if (players.get(i).getPhone()
                        .equals(shuffled.get(i).getPhone())) {
                    valid = false;
                    break;
                }
            }
        }

        // save assignments
        for (int i = 0; i < players.size(); i++) {
            Assignment assignment = new Assignment(
                    players.get(i).getPhone(),
                    shuffled.get(i).getName()
            );
            assignmentRepository.save(assignment);
        }

        return "Game started and assignments generated";
    }
    
    public String resetGame() {
        assignmentRepository.deleteAll();
        return "Game reset successfully. Players are retained.";
    }
    
    public String fullResetGame() {
        assignmentRepository.deleteAll();
        playerRepository.deleteAll();
        return "Full game reset completed. All players and assignments removed.";
    }


}
