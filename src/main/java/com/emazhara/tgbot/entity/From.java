package com.emazhara.tgbot.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class From {
    private long id;
    private Object is_bot;
    private String first_name;
    private String last_name;
    private String username;
    private String language_code;
}
