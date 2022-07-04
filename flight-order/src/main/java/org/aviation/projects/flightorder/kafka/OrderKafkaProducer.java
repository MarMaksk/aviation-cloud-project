package org.aviation.projects.flightorder.kafka;

import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.aviation.projects.commons.entity.InfoForOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderKafkaProducer {

    private static final Logger LOG = LoggerFactory.getLogger(OrderKafkaProducer.class);

    private final KafkaTemplate<String, InfoForOrder> kafkaTemplate;

    @Value("catering")
    private String topic;

    @Timed("sendInfoToCateringServiceByKafka")
    public void send(InfoForOrder data) {
        LOG.info("sending data='{}' to topic='{}'", data, topic);
        Message<InfoForOrder> message = MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, topic)
                .build();
        kafkaTemplate.send(message);
    }

}
