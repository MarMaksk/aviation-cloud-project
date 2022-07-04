package org.aviation.projects.flightcatering.feign;

import io.micrometer.core.annotation.Timed;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("USER-SERVICE") // Название или адрес сервиса
public interface UserClient {
    @Timed("getCatererEmails")
    @GetMapping("/email")
    List<String> getCatererEmailsEmails();

}
