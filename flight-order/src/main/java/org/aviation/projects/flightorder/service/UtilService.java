package org.aviation.projects.flightorder.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aviation.projects.commons.entity.enums.DeliveryStatus;
import org.aviation.projects.flightorder.feign.CateringClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UtilService {

    static Logger LOG = LoggerFactory.getLogger(UtilService.class);
    CateringClient cateringClient;

    public DeliveryStatus checkDelivery(Integer productOrderId) {
        LOG.info("Checking delivery status for product order {}", productOrderId);
        return cateringClient.checkDelivery(productOrderId);
    }
}
