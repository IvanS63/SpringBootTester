package com.myapp.bookstore.config;

/**
 * Test configuration to be used in service layer testing.
 *
 * @author Ivan_Semenov
 */

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;

@Profile("service-test")
@TestConfiguration
@ComponentScan(basePackages = {"com.myapp.bookstore"})
public class ServiceTestConfig {
}

