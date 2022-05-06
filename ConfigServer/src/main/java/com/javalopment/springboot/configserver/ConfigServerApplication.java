package com.javalopment.springboot.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/*
Для каждого сервисы создаются контроллеры?
Что с Feign?
Catering Заявка создаётся автоматически при создании рейса? Наполнием занимаются дргуие люди в другое время?
Пример приложения?
Как определить куда какое сообщение отправлять? Создавать кучу топиков?
 */
@EnableConfigServer
@SpringBootApplication
public class ConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }

}
