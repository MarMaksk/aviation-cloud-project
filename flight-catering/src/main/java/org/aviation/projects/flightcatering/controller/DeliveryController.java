package org.aviation.projects.flightcatering.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aviation.projects.commons.entity.enums.DeliveryStatus;
import org.aviation.projects.flightcatering.exception.NoSuchOrderException;
import org.aviation.projects.flightcatering.service.UtilService;
import org.jfree.util.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/delivery")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Api(value = "Controller for send info from catering")
public class DeliveryController {

    UtilService utilService;

    @GetMapping("/{productOrderId}")
    @ApiOperation(value = "check delivery order for flight", response = DeliveryStatus.class)
    public DeliveryStatus checkDelivery(@PathVariable Integer productOrderId) throws NoSuchOrderException {
        Log.info("Check delivery for order: " + productOrderId);
        return utilService.checkDeliver(productOrderId);
    }

}
