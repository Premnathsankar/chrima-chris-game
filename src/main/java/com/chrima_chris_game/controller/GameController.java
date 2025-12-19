package com.chrima_chris_game.controller;

import org.springframework.web.bind.annotation.*;

import com.chrima_chris_game.service.AssignmentService;
import com.chrima_chris_game.service.GameService;



@RestController
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;
    private final AssignmentService assignmentService;

    public GameController(GameService gameService,AssignmentService assignmentService) {
        this.gameService = gameService;
        this.assignmentService=assignmentService;
    }

    @PostMapping("/reveal")
    public String reveal(@RequestParam String phone) {
        return gameService.revealName(phone);
    }
    
    @PostMapping("/assignTask")
    public String assignTask(
            @RequestParam String giverPhone,
            @RequestParam(required = false) String task
    ) {
        return assignmentService.assignTask(giverPhone, task);
    }

    @GetMapping("/viewTask")
    public String viewTask(@RequestParam String phone) {
        return assignmentService.viewTaskForMe(phone);
    }
    
    
    @GetMapping("/myAssignedTask")
    public String myAssignedTask(@RequestParam String phone) {
        return assignmentService.getTaskAssignedByMe(phone);
    }




}