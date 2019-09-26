package com.myapp.bookstore.config;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for enabling Eureka client.
 *
 * @author Ivan_Semenov
 */
@EnableEurekaClient
@Configuration
public class AppConfig {
}
