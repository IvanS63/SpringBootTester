package com.myapp.bookstore.service.impl;

import static java.lang.String.format;

import com.myapp.bookstore.entity.Author;
import com.myapp.bookstore.service.AuthorService;
import com.myapp.kafkaservice.dto.SendRequestDto;
import com.myapp.kafkaservice.dto.enums.UpdateEventType;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Objects;

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
        Author author = authorService.findByName(sendRequestDto.getAuthor().getName());
        switch (updateEventType) {
            case CREATE:
                if (Objects.nonNull(author)) {
                    log.warn("Author with name = {} already exists in the database", author.getName());
                } else {
                    authorService.add(mapperFacade.map(sendRequestDto.getAuthor(), Author.class));
                }
                break;
            case DELETE:
                if (Objects.nonNull(author)) {
                    authorService.remove(author);
                } else {
                    log.warn("No author with name = {} has been found for being deleted", sendRequestDto.getAuthor().getName());
                }
                break;
            default:
                throw new IllegalArgumentException(format("Unknown updateEventType: %s", updateEventType));
        }
    }
}
