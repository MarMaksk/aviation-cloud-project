package org.aviation.projects.flightorder.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aviation.projects.commons.entity.enums.DeliveryStatus;
import org.aviation.projects.flightorder.service.UtilService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/util")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Api(value = "Controller for different operation")
public class UtilityController {

    static Logger LOG = LoggerFactory.getLogger(UtilityController.class);
    UtilService utilService;

    @GetMapping("/{productOrderId}")
    @ApiOperation(value = "Check delivery status from catering service", response = DeliveryStatus.class)
    public DeliveryStatus checkDelivery(@PathVariable Integer productOrderId) {
        LOG.info("Check delivery status from catering service");
        return utilService.checkDelivery(productOrderId);
    }
}
