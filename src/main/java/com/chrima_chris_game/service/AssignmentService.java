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
        	        players.get(i).getPhone(),          // giver
        	        shuffled.get(i).getPhone(),          // receiver phone ✅
        	        shuffled.get(i).getName()            // receiver name
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
    
 // Assign or update task for the person I got
    public String assignTask(String giverPhone, String task) {

        Assignment assignment = assignmentRepository
                .findByGiverPhone(giverPhone)
                .orElseThrow(() -> new IllegalStateException("Game not started yet"));


        // ALLOW EMPTY -> overwrite allowed
        assignment.setTaskForReceiver(task);
        assignmentRepository.save(assignment);

        return "Task assigned successfully";
    }

    public String viewTaskForMe(String myPhone) {

        return assignmentRepository
                .findByReceiverPhone(myPhone)
                .map(a -> a.getTaskForReceiver() == null || a.getTaskForReceiver().isBlank()
                        ? "No task assigned yet"
                        : a.getTaskForReceiver())
                .orElse("No task assigned yet");
    }
    
    public String getTaskAssignedByMe(String giverPhone) {
        System.out.println("Fetching task for giver: " + giverPhone);

        return assignmentRepository
                .findByGiverPhone(giverPhone)
                .map(a -> {
                    System.out.println("Task found: " + a.getTaskForReceiver());
                    return a.getTaskForReceiver() == null ? "" : a.getTaskForReceiver();
                })
                .orElse("");
    }





}
