package com.myapp.clientservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Example implementation of simple client app that communicates with bookstore-app by Eureka and Feign.
 *
 * @author Ivan_Semenov
 */
@SpringBootApplication
@ComponentScan({"com.myapp.clientservice"})
public class ClientApplication {

    /**
     * To test: http://localhost:8091/client-app/get-top-authors-v1
     * To test: http://localhost:8091/client-app/get-filtered-authors-v1
     * To test: http://localhost:8091/client-app/get-top-authors-v2
     * To test: http://localhost:8091/client-app/get-filtered-authors-v2
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }
}


