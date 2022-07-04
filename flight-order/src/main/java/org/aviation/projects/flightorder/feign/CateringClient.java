package org.aviation.projects.flightorder.feign;

import io.micrometer.core.annotation.Timed;
import org.aviation.projects.commons.entity.enums.DeliveryStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("flight-catering")
public interface CateringClient {
    @Timed
    @GetMapping("/delivery/{productOrderId}")
    DeliveryStatus checkDelivery(@PathVariable Integer productOrderId);

}
