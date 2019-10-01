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
 * KafkaController.
 *
 * @author Ivan_Semenov
 */
@RestController
@RequestMapping("kafka-app")
public class KafkaController {

    @Autowired
    private KafkaSender kafkaSender;

    @GetMapping(value = "/send-message", produces = MediaType.APPLICATION_JSON_VALUE)
    public void sendMessage() {
        SendRequestDto sendRequestDto = new SendRequestDto()
                .setUpdateEventType(UpdateEventType.CREATE)
                .setAuthor(new AuthorDto().setName("John Tolkien")
                        .setBooks(Collections.singletonList(
                                new BookDto()
                                        .setTitle("Lord of The Rings")
                                        .setSold(new Date())))
                );

        kafkaSender.sendMessage(sendRequestDto);
    }
}
