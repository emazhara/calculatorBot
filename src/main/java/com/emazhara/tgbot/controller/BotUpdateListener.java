package com.emazhara.tgbot.controller;

import com.emazhara.tgbot.entity.UpdateResponse;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BotUpdateListener {
    @Value("${app.secret.bot.token}")
    private String botToken;
    private long lastUpdateId;
    private RestTemplate restTemplate;
    private String updateUrl;
    private ObjectMapper objectMapper;
    @Autowired
    private BotUpdateHandler botUpdateHandler;

    public BotUpdateListener() {
        lastUpdateId = -1;
        restTemplate = new RestTemplate();
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Scheduled(fixedRate = 1000)
    public void listen() throws IOException {
        ResponseEntity<String> updateJSON;
        updateUrl = "https://api.telegram.org/bot" + botToken +"/getUpdates";
        if (lastUpdateId == -1)
            updateJSON = restTemplate.getForEntity(updateUrl, String.class);
        else
            updateJSON = restTemplate.getForEntity(updateUrl + "?offset=" + (lastUpdateId + 1), String.class);
        UpdateResponse updateResponse = objectMapper.readValue(updateJSON.getBody(), UpdateResponse.class);
        if (updateResponse.getResult().length > 0 && updateResponse.getResult()[0].getUpdate_id() > lastUpdateId) {
            lastUpdateId = updateResponse.getResult()[updateResponse.getResult().length - 1].getUpdate_id();
            botUpdateHandler.handleUpdate(updateResponse.getResult());
        }
    }
}
