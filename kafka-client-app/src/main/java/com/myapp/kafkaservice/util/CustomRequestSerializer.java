package com.myapp.kafkaservice.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * CustomRequestSerializer.
 *
 * @author Ivan_Semenov
 */
@Slf4j
public class CustomRequestSerializer implements Serializer {
    
    @Override
    public void configure(Map map, boolean b) {

    }
    @Override
    public byte[] serialize(String s, Object result) {
        byte[] serializationObject = new byte[]{};
        try {
            serializationObject = new ObjectMapper().writeValueAsString(result).getBytes(StandardCharsets.UTF_8);
        } catch (JsonProcessingException e) {
            log.error("Cannot serialize object = {}", result, e);
        }
        return serializationObject;
    }

    @Override
    public void close() {

    }
}
