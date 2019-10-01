package com.myapp.bookstore;

import static org.junit.Assert.assertEquals;

import com.google.gson.Gson;
import com.myapp.bookstore.config.KafkaTestConfig;
import com.myapp.bookstore.repository.AuthorRepository;
import com.myapp.bookstore.repository.BookRepository;
import com.myapp.bookstore.util.IntegrationTest;
import com.myapp.bookstore.util.KafkaAbstractIntegrationTest;
import com.myapp.kafkaservice.dto.SendRequestDto;
import com.myapp.kafkaservice.dto.enums.UpdateEventType;
import org.apache.commons.io.IOUtils;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ContextConfiguration;

import java.io.IOException;

/**
 * KafkaIntegrationTest.
 *
 * @author Ivan_Semenov
 */
@IntegrationTest
@ContextConfiguration(classes = KafkaTestConfig.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class KafkaIntegrationTest extends KafkaAbstractIntegrationTest {

    @Value("${kafka-service-app.topic}")
    private String topic;

    private static final String ADD_REQUEST_JSON_FILE = "/data/json/add-author-request.json";
    private static final String REMOVE_REQUEST_JSON_FILE = "/data/json/remove-author-request.json";

    @Autowired
    private KafkaTemplate<String, SendRequestDto> kafkaTemplate;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void step1_testKafkaAddRequest() throws IOException, InterruptedException {
        SendRequestDto sendRequestDto = generateRequestFromFile(ADD_REQUEST_JSON_FILE);

        kafkaTemplate.send(topic, sendRequestDto);
        Thread.sleep(200);

        assertEquals(1, authorRepository.findAll().size());
        assertEquals(1, bookRepository.findAll().size());
    }

    @Test
    public void step2_testKafkaRemoveRequest() throws IOException, InterruptedException {
        SendRequestDto sendRequestDto = generateRequestFromFile(REMOVE_REQUEST_JSON_FILE);

        kafkaTemplate.send(topic, sendRequestDto);
        Thread.sleep(200);

        assertEquals(0, authorRepository.findAll().size());
        assertEquals(0, bookRepository.findAll().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void step3_testKafkaUnknownRequest() {
        kafkaTemplate.send(topic, new SendRequestDto().setUpdateEventType(UpdateEventType.UPDATE));
    }

    private SendRequestDto generateRequestFromFile(String fileName) throws IOException {
        return new Gson().fromJson(IOUtils.toString(this.getClass().getResourceAsStream(fileName), "UTF-8"), SendRequestDto.class);
    }
}
