package com.proj.demo.service;

import org.apache.kafka.common.serialization.Deserializer;
import org.aviation.entity.InfoForOrder;

public class InfoForOrderDeserializer implements Deserializer<InfoForOrder> {
    @Override
    public InfoForOrder deserialize(String s, byte[] bytes) {
        return null;
    }
}
