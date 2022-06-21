package org.aviation.projects.flightorder.service;

import org.aviation.projects.flightorder.feign.CateringClient;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aviation.projects.commons.entity.enums.DeliveryStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UtilService {

    CateringClient cateringClient;

    public DeliveryStatus checkDelivery(Integer productOrderId) {
        return cateringClient.checkDelivery(productOrderId);
    }
}
