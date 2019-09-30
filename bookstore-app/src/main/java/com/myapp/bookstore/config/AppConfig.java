package com.myapp.bookstore.config;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Configuration for enabling Eureka client.
 *
 * @author Ivan_Semenov
 */
@EnableEurekaClient
@Configuration
@EnableScheduling
public class AppConfig {
}
