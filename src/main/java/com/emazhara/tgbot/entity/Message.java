package com.emazhara.tgbot.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private long message_id;
    private From from;
    private Chat chat;
    private long date;
    private String text;
    private Entity[] entities;
}
