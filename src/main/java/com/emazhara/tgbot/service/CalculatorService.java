package com.emazhara.tgbot.service;

import org.springframework.stereotype.Component;

@Component
public class CalculatorService {
    public double calculate(double volume, double strengthBefore, double strengthAfter) {
        return volume * strengthBefore / strengthAfter - volume;
    }
}
