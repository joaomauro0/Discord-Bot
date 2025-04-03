package com.joaocunha.discordbot.controller;




import java.util.List; // Adicionando a importação correta de List

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joaocunha.discordbot.service.GoogleSheetsService;

@RestController
@RequestMapping("/api")
public class TestController {

    private final GoogleSheetsService googleSheetsService;

    public TestController(GoogleSheetsService googleSheetsService) {
        this.googleSheetsService = googleSheetsService;
    }

 
    @GetMapping("/emails")
    public ResponseEntity<List<String>> getEmails() throws Exception {
        List<String> emails = googleSheetsService.getEmails();
        return ResponseEntity.ok(emails);
    }

    
    @GetMapping("/send-invites")
    public ResponseEntity<String> sendInvites() throws Exception {
        googleSheetsService.processInvites();
        return ResponseEntity.ok("Convites enviados com sucesso!");
    }


}
