package com.myapp.clientservice.config;

/**
 * FeignConfig.
 *
 * @author Ivan_Semenov
 */

import com.myapp.bookstore.controller.AuthorController;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackageClasses = {AuthorController.class})
public class FeignConfig {
}
