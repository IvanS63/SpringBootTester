package com.myapp.bookstore.config;

/**
 * Test configuration to be used in service layer testing.
 *
 * @author Ivan_Semenov
 */

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;

@Profile("service-test")
@TestConfiguration
@ComponentScan(basePackages = {"com.myapp.bookstore"})
public class ServiceTestConfig {
    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setShouldRun(false);
        return liquibase;
    }
}

