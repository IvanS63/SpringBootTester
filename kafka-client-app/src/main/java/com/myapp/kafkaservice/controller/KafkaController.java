package com.myapp.kafkaservice.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.myapp.kafkaservice.dto.AuthorDto;
import com.myapp.kafkaservice.dto.BookDto;
import com.myapp.kafkaservice.dto.SendRequestDto;
import com.myapp.kafkaservice.dto.enums.UpdateEventType;
import com.myapp.kafkaservice.service.KafkaSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Date;

/**
 * Class with Rest API endpoints for generating Kafka requests.
 *
 * @author Ivan_Semenov
 */
@RestController
@RequestMapping("kafka-app")
public class KafkaController {

    @Autowired
    private KafkaSender kafkaSender;

    /**
     * Generate and send CREATE author request.
     */
    @GetMapping(value = "/send-create-message", produces = MediaType.APPLICATION_JSON_VALUE)
    public void sendCreateMessage() {
        kafkaSender.sendMessage(generateHardcodedRequest(UpdateEventType.CREATE));
    }

    /**
     * Generate and send DELETE author request.
     */
    @GetMapping(value = "/send-delete-message", produces = MediaType.APPLICATION_JSON_VALUE)
    public void sendDeleteMessage() {
        kafkaSender.sendMessage(generateHardcodedRequest(UpdateEventType.DELETE));
    }

    /**
     * Just generate simple hardcoded request DTO.
     *
     * @param updateEventType
     * @return
     */
    private SendRequestDto generateHardcodedRequest(UpdateEventType updateEventType) {
        return new SendRequestDto()
                .setUpdateEventType(updateEventType)
                .setAuthor(new AuthorDto().setName("John Tolkien")
                        .setBooks(Collections.singletonList(
                                new BookDto()
                                        .setTitle("Lord of The Rings")
                                        .setSold(new Date())))
                );
    }
}
