package org.aviation.projects.flightorder.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.aviation.projects.flightorder.service.UtilService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aviation.projects.commons.entity.enums.DeliveryStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/util")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Api(value = "Controller for different operation")
public class UtilityController {

    UtilService utilService;

    @GetMapping("/{productOrderId}")
    @ApiOperation(value = "Check delivery status from catering service", response = DeliveryStatus.class)
    public DeliveryStatus checkDelivery(@PathVariable Integer productOrderId) {
        return utilService.checkDelivery(productOrderId);
    }
}
