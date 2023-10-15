package com.emazhara.tgbot.service;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageRequest {
    private long chat_id;
    private String text;
}
