package com.myapp.clientservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * ClientController.
 *
 * @author Ivan_Semenov
 */
@Component
@Slf4j
@RequestMapping("/client-app")
public class ClientController {
    @Autowired
    private RestTemplate restTemplate;
    
    private static final String BOOKSTORE_APP_URL=""; 
    
    @GetMapping("/get-authors")
    public void getTopAuthorsFromBookstoreApp(){
        /*List<Author> authors = restTemplate.getForObject(BOOKSTORE_APP_URL, List<Author>.class);
        log.info("Response received: {}", authors);  
    */}
    
}
