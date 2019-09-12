package com.myapp.springboottester.config;

/**
 * Test configuration to be used in service layer testing.
 *
 * @author Ivan_Semenov
 */

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;

@Profile("service_test")
@TestConfiguration
@ComponentScan(basePackages = {"com.myapp.springboottester"})
public class ServiceTestConfig {
}

