package com.proj.demo.kafka;

import com.proj.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.aviation.entity.InfoForOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderKafkaConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(InfoForOrder.class);
    private final OrderService orderService;

    @KafkaListener(topics = "catering")
    public void receive(@Payload InfoForOrder data,
                        @Headers MessageHeaders headers) {
        LOG.info("received data='{}'", data);
        headers.keySet().forEach(key -> {
            LOG.info("{}: {}", key, headers.get(key));
        });
        orderService.createByInfo(data);
    }

}