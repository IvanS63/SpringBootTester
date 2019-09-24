package com.myapp.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Class for starting Eureka service discovery (http://localhost:8761/).
 *
 * @author Ivan_Semenov
 */
@SpringBootApplication
@EnableEurekaServer
@EnableDiscoveryClient
public class EurekaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServiceApplication.class, args);
    }
}