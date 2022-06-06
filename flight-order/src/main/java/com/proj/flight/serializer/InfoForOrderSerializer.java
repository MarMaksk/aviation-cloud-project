package com.proj.flight.serializer;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;
import org.aviation.entity.InfoForOrder;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class InfoForOrderSerializer implements Serializer<InfoForOrder> {

    @Override
    public byte[] serialize(String topic, InfoForOrder infoForOrder) {
        try {
            byte[] serializedIata;
            int stringSize;
            if (infoForOrder == null) return null;
            else {
                if (infoForOrder.getIataCode() != null) {
                    serializedIata = infoForOrder.getIataCode().getBytes(StandardCharsets.UTF_8);
                    stringSize = serializedIata.length;
                } else {
                    serializedIata = new byte[0];
                    stringSize = 0;
                }
            }
            ByteBuffer buffer = ByteBuffer.allocate(4 + stringSize);
            buffer.putInt(stringSize);
            buffer.put(serializedIata);
            return buffer.array();
        } catch (Exception ex) {
            throw new SerializationException("Error when serializing InfoForOrder to byte[] "  +ex);
        }
    }
}
