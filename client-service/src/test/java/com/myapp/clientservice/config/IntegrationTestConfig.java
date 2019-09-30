package com.myapp.clientservice.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

/**
 * IntegrationTestConfig.
 *
 * @author Ivan_Semenov
 */
@TestConfiguration
@Profile("integration-test")
public class IntegrationTestConfig {
    
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
    
}
