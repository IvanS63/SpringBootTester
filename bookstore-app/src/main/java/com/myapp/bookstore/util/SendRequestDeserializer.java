package com.myapp.bookstore.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myapp.bookstore.entity.Author;
import com.myapp.kafkaservice.dto.SendRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.util.Map;

/**
 * AuthorRequestDeserializer.
 *
 * @author Ivan_Semenov
 */
@Slf4j

public class SendRequestDeserializer implements Deserializer<SendRequestDto> {
    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public SendRequestDto deserialize(String s, byte[] bytes) {
        try {
            return new ObjectMapper().readValue(bytes, SendRequestDto.class);
        } catch (IOException e) {
            log.error("Object cannot be deserialized: {}", e);
        }
        return null;
    }

    @Override
    public void close() {

    }
}
