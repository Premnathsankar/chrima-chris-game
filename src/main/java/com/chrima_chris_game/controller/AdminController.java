package com.chrima_chris_game.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.chrima_chris_game.entity.Player;
import com.chrima_chris_game.service.AssignmentService;
import com.chrima_chris_game.service.PlayerService;


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
    public String addPlayer(@RequestBody Player player) {
        return playerService.addPlayer(player);
    }
    
    @PostMapping("/startGame")
    public String startGame() {
        return assignmentService.startGame();
    }
    
    @PostMapping("/resetGame")
    public String resetGame() {
        return assignmentService.resetGame();
    }
    
    @PostMapping("/fullResetGame")
    public String fullResetGame() {
        return assignmentService.fullResetGame();
    }
    
    @GetMapping("/players")
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }
    
    @DeleteMapping("/deletePlayer/{id}")
    public String deletePlayer(@PathVariable int id) {
        return playerService.deletePlayer(id);
    }




}