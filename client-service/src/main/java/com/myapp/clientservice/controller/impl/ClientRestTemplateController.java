package com.myapp.clientservice.controller.impl;

import com.myapp.bookstore.entity.Author;
import com.myapp.clientservice.controller.ClientController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Old implementation via RestTemplate to send request to bookstore-app.
 * Feign implementation in {@link ClientSpringCloudController}.
 *
 * @author Ivan_Semenov
 */
@RestController
@Slf4j
@Deprecated
public class ClientRestTemplateController implements ClientController {

    @Autowired
    private RestTemplate restTemplate;

    private static final String BOOKSTORE_APP_URL = "http://BOOKSTORE-APP/book-store/";
    private static final String TOP_AUTHORS = "authors/top?from=1990-01-01&to=2020-01-01";
    private static final String FILTERED_AUTHORS = "authors/filter?name=&amountOfBooks=&earnings=&amountOfSoldBooks=";

    @RequestMapping(method = RequestMethod.GET, value = "/get-top-authors-v1")
    @Override
    public List<Author> getTopAuthorsFromBookstoreApp() {
        log.debug("getTopAuthorsFromBookstoreApp() - start:");
        List<Author> authors = restTemplate.getForObject(BOOKSTORE_APP_URL + TOP_AUTHORS, List.class);
        log.info("Response received: {}", authors);
        log.debug("getTopAuthorsFromBookstoreApp() - end:");
        return authors;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get-filtered-authors-v1")
    @Override
    public List<Author> getFilteredAuthorsFromBookStoreApp() {
        log.debug("getTopAuthorsFromBookstoreApp() - start:");
        List<Author> authors = restTemplate.getForObject(BOOKSTORE_APP_URL + FILTERED_AUTHORS, List.class);
        log.info("Response received: {}", authors);
        log.debug("getTopAuthorsFromBookstoreApp() - end:");
        return authors;
    }
}
