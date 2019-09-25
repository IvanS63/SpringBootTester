package com.myapp.bookstore.feign;

import com.myapp.bookstore.controller.AuthorController;
import com.myapp.bookstore.feign.fallback.AuthorControllerFeignFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;


/**
 * Feign implementation for Eureka service.
 *
 * @author Ivan_Semenov
 */
@FeignClient(value = "bookstore-app",
        url = "/authors",
        fallbackFactory = AuthorControllerFeignFallbackFactory.class)
public interface AuthorControllerFeign extends AuthorController {
}
