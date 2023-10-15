package com.emazhara.tgbot.service;

import com.emazhara.tgbot.entity.Chat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SendMessageService {
    @Value("${app.secret.bot.token}")
    private String botToken;
    private RestTemplate restTemplate;

    public SendMessageService() {
        restTemplate = new RestTemplate();
    }

    public void sendMessage(String message, Chat chat) {
        String url = "https://api.telegram.org/bot" + botToken + "/sendMessage";
        var msgRequest = MessageRequest.builder().text(message).chat_id(chat.getId()).build();
        restTemplate.postForEntity(url, msgRequest, String.class);
    }
}
