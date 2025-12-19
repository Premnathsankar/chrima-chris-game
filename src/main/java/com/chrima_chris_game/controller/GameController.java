package com.chrima_chris_game.controller;

import org.springframework.web.bind.annotation.*;

import com.chrima_chris_game.service.GameService;



@RestController
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/reveal")
    public String reveal(@RequestParam String phone) {
        return gameService.revealName(phone);
    }
}