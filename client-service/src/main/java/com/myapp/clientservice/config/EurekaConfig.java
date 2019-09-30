package com.myapp.clientservice.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;

/**
 * General App config.
 *
 * @author Ivan_Semenov
 */
@Configuration
@EnableDiscoveryClient
@ConditionalOnProperty(name = "eureka.client.enabled")
public class EurekaConfig {
}
