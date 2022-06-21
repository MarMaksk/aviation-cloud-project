package org.aviation.projects.flightcatering.controller;

import org.aviation.projects.flightcatering.exception.NoSuchOrderException;
import org.aviation.projects.flightcatering.service.UtilService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aviation.projects.commons.entity.enums.DeliveryStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/delivery")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DeliveryController {

    UtilService utilService;

    @GetMapping("/{productOrderId}")
    public DeliveryStatus checkDelivery(@PathVariable Integer productOrderId) throws NoSuchOrderException {
        return utilService.checkDeliver(productOrderId);
    }

}