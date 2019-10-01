package com.myapp.bookstore.service.impl;

import static java.lang.String.format;

import com.myapp.bookstore.entity.Author;
import com.myapp.bookstore.service.AuthorService;
import com.myapp.kafkaservice.dto.SendRequestDto;
import com.myapp.kafkaservice.dto.enums.UpdateEventType;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * KafkaListener.
 *
 * @author Ivan_Semenov
 */
@Component
@Slf4j
@ConditionalOnProperty("kafka.enabled")
public class BookstoreKafkaListener {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private MapperFacade mapperFacade;

    @KafkaListener(topics = "author-request-dto")
    public void receiveMessage(SendRequestDto sendRequestDto) {
        log.debug("Received request {} from topic: ", sendRequestDto, "author-request-dto");
        UpdateEventType updateEventType = sendRequestDto.getUpdateEventType();
        Author author = mapperFacade.map(sendRequestDto.getAuthor(), Author.class);
        switch (updateEventType) {
            case CREATE:
                authorService.add(author);
                break;
            case DELETE:
                authorService.remove(author);
                break;
            default:
                throw new IllegalArgumentException(format("Unknown updateEventType: %s", updateEventType));
        }
    }
}
