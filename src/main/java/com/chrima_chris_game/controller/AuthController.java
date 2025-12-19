package com.chrima_chris_game.controller;

import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session
    ) {
        if ("admin".equals(username) && "root".equals(password)) {
            session.setAttribute("ADMIN", true);
            return "success";
        }
        return "Invalid credentials";
    }

    @PostMapping("/logout")
    public void logout(HttpSession session) {
        session.invalidate();
    }
}

