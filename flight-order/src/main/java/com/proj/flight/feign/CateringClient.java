package com.proj.flight.feign;

import org.aviation.entity.enums.DeliveryStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("flight-catering") // Название или адрес сервиса
public interface CateringClient {

    @GetMapping("/delivery/check/{productOrderId}") // Адрес как в контроллере. В этом приложении нет подходящего контроллера и с таким
                                // адресом работать не будет
    DeliveryStatus checkDelivery(@PathVariable Integer productOrderId);

}