package com.myapp.clientservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Application configuration.
 *
 * @author Ivan_Semenov
 */
@Configuration
public class AppConfig {

    @LoadBalanced //in case we have several instances of application for balancing.
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
