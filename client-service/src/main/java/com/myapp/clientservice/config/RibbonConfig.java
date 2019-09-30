package com.myapp.clientservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.cloud.netflix.ribbon.eureka.EurekaRibbonClientConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * RibbonConfig.
 *
 * @author Ivan_Semenov
 */
@Configuration
@RibbonClients(defaultConfiguration = {EurekaRibbonClientConfiguration.class})
public class RibbonConfig {
    
    @LoadBalanced //in case we have several instances of application for balancing.
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
