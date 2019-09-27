package com.myapp.clientservice.config;

import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.cloud.netflix.ribbon.eureka.EurekaRibbonClientConfiguration;
import org.springframework.context.annotation.Configuration;

/**
 * RibbonConfig.
 *
 * @author Ivan_Semenov
 */
@Configuration
@RibbonClients(defaultConfiguration = {EurekaRibbonClientConfiguration.class})
public class RibbonConfig {
}
