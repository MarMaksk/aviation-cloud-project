package com.proj.demo.service;

import com.proj.demo.exception.NoSuchOrderException;
import com.proj.demo.repository.OrderRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aviation.entity.enums.DeliveryStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UtilService {

    OrderRepository orderRepository;

    public DeliveryStatus checkDeliver(Integer productOrderId) throws NoSuchOrderException {
        return orderRepository.findByProductOrderIdAndDeletedFalse(productOrderId).orElseThrow(NoSuchOrderException::new).getStatus();
    }

}
