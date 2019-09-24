package com.myapp.bookstore.config;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Configuration for enabling Eureka client.
 *
 * @author Ivan_Semenov
 */
@EnableEurekaClient
@EnableWebMvc
@Configuration
public class AppConfig {
}
