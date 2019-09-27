package com.myapp.clientservice.config;

import com.myapp.bookstore.feign.AuthorControllerFeign;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration of Feign clients.
 *
 * @author Ivan_Semenov
 */
@Configuration
@EnableFeignClients(basePackageClasses = {AuthorControllerFeign.class})
public class FeignConfig {
}