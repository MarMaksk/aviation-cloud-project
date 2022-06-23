package org.aviation.projects.flightcatering.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("user-service") // Название или адрес сервиса
public interface UserClient {

    @GetMapping("/email") // Адрес как в контроллере
    List<String> getCatererEmailsEmails();

}
