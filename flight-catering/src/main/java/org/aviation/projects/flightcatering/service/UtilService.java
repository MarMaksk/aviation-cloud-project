package org.aviation.projects.flightcatering.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aviation.projects.commons.entity.enums.DeliveryStatus;
import org.aviation.projects.flightcatering.exception.NoSuchOrderException;
import org.aviation.projects.flightcatering.repository.OrderRepository;
import org.jfree.util.Log;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UtilService {

    OrderRepository orderRepository;

    public DeliveryStatus checkDeliver(Integer productOrderId) throws NoSuchOrderException {
        Log.info("checkDeliver method called in UtilService. For productOrderId: " + productOrderId);
        return orderRepository.findByProductOrderIdAndDeletedFalse(productOrderId).orElseThrow(NoSuchOrderException::new).getStatus();
    }

}
