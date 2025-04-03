package com.joaocunha.discordbot.service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class GoogleSheetsService {
    private static final String APPLICATION_NAME = "Discord Invite Bot";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY);
    private static final String CREDENTIALS_FILE_PATH = "src/main/resources/credentials.json";

    private Sheets sheetsService;

    public GoogleSheetsService() throws Exception {
        sheetsService = getSheetsService();
    }

    private Sheets getSheetsService() throws Exception {
        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        FileInputStream fileInputStream = new FileInputStream(new File(CREDENTIALS_FILE_PATH));
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(fileInputStream));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                httpTransport, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();

        Credential credential = new AuthorizationCodeInstalledApp(
                flow, 
                new LocalServerReceiver.Builder().setPort(8080).setCallbackPath("/oauth2callback").build()
        ).authorize("user");


        return new Sheets.Builder(httpTransport, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public List<String> getEmails() throws Exception {
        String spreadsheetId = "1udlX57T4x0whGuU5I6xQ1Co5jRFPAAF3zXEj8sDOLGk";
        String range = "A:A"; // Supondo que os e-mails estão na coluna A

        ValueRange response = sheetsService.spreadsheets().values()
                .get(spreadsheetId, range)
                .execute();

        List<List<Object>> values = response.getValues();
        List<String> emails = new ArrayList<>();

        if (values != null && !values.isEmpty()) {
            for (List<Object> row : values) {
                if (!row.isEmpty()) {
                    emails.add(row.get(0).toString()); // Adiciona o e-mail à lista
                }
            }
        }
        return emails;
    }

    public void processInvites() throws Exception {
        List<String> emails = getEmails(); // Obtém os e-mails da planilha

        for (String email : emails) {
            String inviteLink = generateDiscordInvite(); // Gera um link de convite único
            sendEmailInvite(email, inviteLink); // Envia o convite por e-mail (simulação)
        }
    }

    private String generateDiscordInvite() {
        return "https://discord.gg/exemploConvite"; // Substitua pela integração real com o Discord
    }

    @Autowired
    private EmailService emailService;

    private void sendEmailInvite(String email, String inviteLink) {
        String subject = "Seu Convite para o Discord";
        String body = "Olá! Aqui está o seu convite exclusivo para o Discord: " + inviteLink;
        
        emailService.sendEmail(email, subject, body);
    }

   }

