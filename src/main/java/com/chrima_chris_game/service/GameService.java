package com.chrima_chris_game.service;

import org.springframework.stereotype.Service;

import com.chrima_chris_game.entity.Assignment;
import com.chrima_chris_game.repository.AssignmentRepository;
import com.chrima_chris_game.repository.PlayerRepository;



@Service
public class GameService {

    private final AssignmentRepository assignmentRepository;
    private final PlayerRepository playerRepository;

    public GameService(AssignmentRepository assignmentRepository,
                       PlayerRepository playerRepository) {
        this.assignmentRepository = assignmentRepository;
        this.playerRepository = playerRepository;
    }

    public String revealName(String phone) {

        // check phone exists
        if (!playerRepository.existsByPhone(phone)) {
            return "Phone number not found. Contact admin.";
        }

        Assignment assignment = assignmentRepository.findByGiverPhone(phone);

        if (assignment == null) {
            return "Game not started yet.";
        }

        if (assignment.isRevealed()) {
            return "🎁 You got: " + assignment.getReceiverName();
        }


        assignment.setRevealed(true);
        assignmentRepository.save(assignment);

        return "🎁 You got: " + assignment.getReceiverName();
    }
}
