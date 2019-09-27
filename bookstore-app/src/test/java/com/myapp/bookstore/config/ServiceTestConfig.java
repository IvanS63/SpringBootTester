package com.myapp.bookstore.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;

/**
 * Test configuration to be used in service layer testing.
 *
 * @author Ivan_Semenov
 */
@Profile("service-test")
@TestConfiguration
@ComponentScan(basePackages = {"com.myapp.bookstore"})
public class ServiceTestConfig {

    /**
     * For service layer tests Liquibase is disabled.
     *
     * @return liquibase modified bean
     */
    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setShouldRun(false);
        return liquibase;
    }
}

