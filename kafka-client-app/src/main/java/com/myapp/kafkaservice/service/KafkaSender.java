package com.myapp.kafkaservice.service;

import com.myapp.kafkaservice.dto.SendRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * KafkaService.
 *
 * @author Ivan_Semenov
 */
@Component
@Slf4j
public class KafkaSender {

    @Value("${kafka.topic}")
    private String topic;

    @Autowired
    private KafkaTemplate<String, SendRequestDto> kafkaTemplate;

    public void sendMessage(SendRequestDto msg) {
        ListenableFuture<SendResult<String, SendRequestDto>> future = kafkaTemplate.send(topic, msg);
        
        future.addCallback(new ListenableFutureCallback<SendResult<String, SendRequestDto>>() {
            @Override
            public void onSuccess(SendResult<String, SendRequestDto> result) {
                log.debug("Sent message=[{}] with offset=[{}]", result, result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                log.error("Unable to send message=[{}] due to : {}", msg, ex.getMessage());
            }
        });
    }
}
