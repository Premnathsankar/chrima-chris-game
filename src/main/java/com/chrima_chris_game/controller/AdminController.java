package com.chrima_chris_game.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.chrima_chris_game.entity.Player;
import com.chrima_chris_game.service.AssignmentService;
import com.chrima_chris_game.service.PlayerService;

import jakarta.servlet.http.HttpSession;


@RestController
@RequestMapping("/admin")
public class AdminController {

	private final AssignmentService assignmentService;
    private final PlayerService playerService;

	public AdminController(PlayerService playerService,
	                       AssignmentService assignmentService) {
	    this.playerService = playerService;
	    this.assignmentService = assignmentService;
	}


    @PostMapping("/addPlayer")
    public String addPlayer(@RequestBody Player player,HttpSession session) {
    	checkAdmin(session);
        return playerService.addPlayer(player);
    }
    
    @PostMapping("/startGame")
    public String startGame(HttpSession session) {
    	checkAdmin(session);
        return assignmentService.startGame();
    }
    
    @PostMapping("/resetGame")
    public String resetGame(HttpSession session) {
    	checkAdmin(session);
        return assignmentService.resetGame();
    }
    
    @PostMapping("/fullResetGame")
    public String fullResetGame(HttpSession session) {
    	checkAdmin(session);
        return assignmentService.fullResetGame();
    }
    
    @GetMapping("/players")
    public List<Player> getAllPlayers(HttpSession session) {
    	checkAdmin(session);
        return playerService.getAllPlayers();
    }
    
    @DeleteMapping("/deletePlayer/{id}")
    public String deletePlayer(@PathVariable int id,HttpSession session) {
    	checkAdmin(session);
        return playerService.deletePlayer(id);
    }

    private void checkAdmin(HttpSession session) {
        if (session.getAttribute("ADMIN") == null) {
            throw new RuntimeException("Unauthorized");
        }
    }




}