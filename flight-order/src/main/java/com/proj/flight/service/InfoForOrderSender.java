package com.proj.flight.service;

import org.aviation.entity.InfoForOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class InfoForOrderSender {

    private static final Logger LOG = LoggerFactory.getLogger(InfoForOrderSender.class);

    @Autowired
    private KafkaTemplate<String, InfoForOrder> kafkaTemplate;

    @Value("catering")
    private String topic;

    public void send(InfoForOrder data){
        LOG.info("sending data='{}' to topic='{}'", data, topic);
        Message<InfoForOrder> message = MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, topic)
                .build();
        kafkaTemplate.send(message);
    }

}
