package com.proj.flight.service;

import com.proj.flight.feign.CateringClient;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aviation.entity.enums.DeliveryStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UtilService {

    CateringClient cateringClient;

    public DeliveryStatus checkDelivery(Integer productOrderId) {
        return cateringClient.checkDelivery(productOrderId);
    }
}
