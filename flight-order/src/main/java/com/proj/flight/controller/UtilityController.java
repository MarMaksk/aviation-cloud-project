package com.proj.flight.controller;

import com.proj.flight.service.UtilService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aviation.entity.enums.DeliveryStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/util")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UtilityController {

    UtilService utilService;

    @GetMapping("/{productOrderId}")
    public DeliveryStatus checkDelivery(@PathVariable Integer productOrderId) {
        return utilService.checkDelivery(productOrderId);
    }
}
