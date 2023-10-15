package com.emazhara.tgbot.controller;

import com.emazhara.tgbot.entity.Update;
import com.emazhara.tgbot.service.CalculatorFlag;
import com.emazhara.tgbot.service.CalculatorService;
import com.emazhara.tgbot.service.SendMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;

@Component
public class BotUpdateHandler {
    @Autowired
    private SendMessageService sendMessageService;
    @Autowired
    private CalculatorService calculatorService;
    @Autowired
    private CalculatorFlag flag;

    private int counter;

    private double volume;
    private double strengthBefore;
    private double strengthAfter;

    public void handleUpdate(Update[] updates) {
        for (var update : updates) {
            if(update.getMessage().getEntities() != null) {
                for (var entity : update.getMessage().getEntities()) {
                    if (entity.getType().equals("bot_command")) {
                        var command = update.getMessage().getText().substring((int)entity.getOffset(), (int)entity.getOffset() + (int)entity.getLength());
                        switch (command) {
                            case "/calculate":
                                flag.setFlag(true);
                                counter = 0;
                                sendMessageService.sendMessage("Введите имеющийся объем в литрах, дроби через точку (не запятая)!", update.getMessage().getChat());
                                break;
                            default:
                                sendMessageService.sendMessage("Не существует такой команды!", update.getMessage().getChat());
                                break;
                        }
                    }
                }
            }
            else if (flag.isFlag())
                switch (counter) {
                    case 0:
                        try {
                            volume = Double.parseDouble(update.getMessage().getText());
                            sendMessageService.sendMessage("Введите текущий градус (дроби через точку)", update.getMessage().getChat());
                            counter++;
                        } catch (NumberFormatException e) {
                            sendMessageService.sendMessage("Нужно ввести число; дроби только через точку!!!", update.getMessage().getChat());
                        }
                        break;
                    case 1:
                        try {
                            strengthBefore = Double.parseDouble(update.getMessage().getText());
                            if (strengthBefore > 100 || strengthBefore < 0) throw new IllegalArgumentException();
                            sendMessageService.sendMessage(
                                "Введите желаемый градус (дроби через точку)",
                                update.getMessage().getChat());
                            counter++;
                        } catch (NumberFormatException e) {
                            sendMessageService.sendMessage("Нужно ввести число; дроби только через точку!!!", update.getMessage().getChat());
                        } catch (IllegalArgumentException e) {
                            sendMessageService.sendMessage("Крепость должна быть между 0 и 100", update.getMessage().getChat());
                        }
                        break;
                    case 2:
                        try {
                            strengthAfter = Double.parseDouble(update.getMessage().getText());
                            if (strengthAfter >= 100 || strengthAfter <= 0) throw new IllegalArgumentException();
                            var ans = calculatorService.calculate(volume, strengthBefore,
                                strengthAfter);
                            if (ans < 0)
                                sendMessageService.sendMessage(
                                    "Тут не разбавлять надо, а добавлять... " + -Math.round(ans * 1000.0) / 1000.0 + " литров спирта",
                                    update.getMessage().getChat());
                            else
                                sendMessageService.sendMessage(
                                    "Нужно добавить " + Math.round(ans * 1000.0) / 1000.0
                                        + " литров воды", update.getMessage().getChat());
                            flag.setFlag(false);
                        } catch (NumberFormatException e) {
                            sendMessageService.sendMessage("Нужно ввести число; дроби только через точку!!!",
                                update.getMessage().getChat());
                        } catch (IllegalArgumentException e) {
                            sendMessageService.sendMessage("Крепость должна быть между 0 и 100", update.getMessage().getChat());
                        }
                        break;
                    default:
                        break;
                }
            else sendMessageService.sendMessage("Я не умею общаться, я умею только считать :(", update.getMessage().getChat());
        }
    }
}
