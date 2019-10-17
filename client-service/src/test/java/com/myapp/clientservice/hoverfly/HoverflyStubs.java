package com.myapp.clientservice.hoverfly;

import static com.myapp.clientservice.hoverfly.bookstore.BookstoreStubs.getFilteredAuthors;
import static com.myapp.clientservice.hoverfly.bookstore.BookstoreStubs.getTopAuthors;
import static io.specto.hoverfly.junit.core.SimulationSource.dsl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.specto.hoverfly.junit.core.SimulationSource;
import lombok.experimental.UtilityClass;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;

import java.io.IOException;

/**
 * Stubs for imitating other services responses.
 *
 * @author Ivan_Semenov
 */
@UtilityClass
public class HoverflyStubs {

    public static final SimulationSource SIMULATION_SOURCE = dsl(
            getTopAuthors(),
            getFilteredAuthors()
    );

    /**
     * Returns JSON representation of an object for HoverflyTesting
     *
     * @param object object
     * @return JSON object
     */
    public static String toJsonString(Object object) {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        try {
            staticMappingJackson2HttpMessageConverter().write(object, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        } catch (IOException e) {
            System.err.println(e);
        }
        return mockHttpOutputMessage.getBodyAsString();
    }

    private static MappingJackson2HttpMessageConverter staticMappingJackson2HttpMessageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        jsonConverter.setObjectMapper(objectMapper);
        return jsonConverter;
    }
}
