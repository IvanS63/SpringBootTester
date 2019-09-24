package com.myapp.clientservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * Example implementation of simple client app that communicates with bookstore-app by Eureka and Feign.
 *
 * @author Ivan_Semenov
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan({"com.myapp.clientservice"})
public class ClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }
}


