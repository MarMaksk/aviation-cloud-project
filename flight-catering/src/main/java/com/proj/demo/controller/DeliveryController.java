package com.proj.demo.controller;

import com.proj.demo.exception.NoSuchOrderException;
import com.proj.demo.service.UtilService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aviation.entity.enums.DeliveryStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/delivery")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DeliveryController {

    UtilService utilService;

    @GetMapping("/check/{productOrderId}")
    public DeliveryStatus checkDelivery(@PathVariable Integer productOrderId) throws NoSuchOrderException {
        return utilService.checkDeliver(productOrderId);
    }

}
