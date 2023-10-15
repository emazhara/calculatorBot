package com.emazhara.tgbot.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class CalculatorFlag {
    private boolean flag;

    public CalculatorFlag() {
        flag = false;
    }
}
