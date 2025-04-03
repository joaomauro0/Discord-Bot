package com.joaocunha.discordbot.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.HashMap;
import java.util.Map;

@Service
public class InviteProcessor {
    private static final String DISCORD_API_URL = "https://discord.com/api/v10/channels/{channelId}/invites";
    private static final String BOT_TOKEN = "MTM0NDM4MDcyODc3OTIxMDc1Mw.GXI4_A.PBicMLWeacERoDJGOZVLmG3SxC6rmAXEySToeU";
    private static final String CHANNEL_ID = "1288511546233716770";

    public String generateInvite() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bot " + BOT_TOKEN);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> body = new HashMap<>();
        body.put("max_age", 0); // Convite sem expiração
        body.put("max_uses", 1); // Apenas um uso
        body.put("temporary", false); // Acesso permanente
        body.put("unique", true); // Cada convite é único

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(DISCORD_API_URL, request, Map.class, CHANNEL_ID);

        return response.getBody().get("code").toString();
    }
}
