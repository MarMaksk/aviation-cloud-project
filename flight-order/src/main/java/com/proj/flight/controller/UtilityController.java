package com.proj.flight.controller;

import com.proj.flight.entity.enums.FlightStatus;
import com.proj.flight.service.UtilService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aviation.entity.enums.DeliveryStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
