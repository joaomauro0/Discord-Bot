package com.joaocunha.discordbot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "index"; // Deve existir um index.html ou index.jsp na pasta correta
    }
}
