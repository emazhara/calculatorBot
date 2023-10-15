package com.emazhara.tgbot.controller;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatusCode;

@Data
@Builder
public class SendMessageResponse {
    HttpStatusCode responseCode;
}
