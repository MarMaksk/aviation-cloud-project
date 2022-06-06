package com.proj.demo.service;

import com.proj.demo.exception.NoSuchOrderException;
import com.proj.demo.repository.OrderRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aviation.entity.enums.DeliveryStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UtilService {

    OrderRepository orderRepository;

    public DeliveryStatus checkDeliver(Integer productOrderId) throws NoSuchOrderException {
        return orderRepository.findByProductOrderId(productOrderId).orElseThrow(NoSuchOrderException::new).getStatus();
    }

}
