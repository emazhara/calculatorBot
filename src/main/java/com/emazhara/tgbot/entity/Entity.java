package com.emazhara.tgbot.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Entity {
    private long offset;
    private long length;
    private String type;
}
