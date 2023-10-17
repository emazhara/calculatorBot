# telegramCalculatorBot
This repository contains the source code of the telegram bot ***@supercalc123bot***, that is currently on running.
## The most useful project ever
Main reason this bot exists is a huge stream of problems coming from my family.
***The structure of the problem is always the same***:
+ Let's say you have some alcohol with a certain strength that you can either find out or measure;
+ Let **V** be the volume and **S<sub>1</sub>** is the initial strength.
+ Now, you want it to be less strong, and asking yourself: >How could I possibly do that?
+ The bot will calculate how much water you need to add in order for drink to become as strong as **S<sub>2</sub>** – third parameter you need to provide.
## What's inside?
The app is written with **Java 11** and **Spring Boot 2**. 
It uses **Telegram API** to get and send messages with ***GET*** and ***POST*** requests thourgh api.telegram.com/sendMessage and api.telegram.com/getUpdates.
Since ***getUpdates*** method returns a JSON, app uses **Jackson objectMapper** to convert it to an object.
**Spring** framework is used for dependency injection and **RestTemplate** class, which allows to easily send http requests.
