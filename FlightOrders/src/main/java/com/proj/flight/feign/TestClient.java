package com.proj.flight.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("flight-order") // Название или адрес сервиса
public interface TestClient {

    @GetMapping("/kafka/{id}") // Адрес как в контроллере. В этом приложении нет подходящего контроллера и с таким
                                // адресом работать не будет
    String testKafka(@PathVariable() Long id);

}
