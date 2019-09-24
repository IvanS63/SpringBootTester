package com.myapp.bookstore.feign;

import com.myapp.bookstore.controller.AuthorController;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Feign implementation for Eureka service.
 *
 * @author Ivan_Semenov
 */
@FeignClient(value = "bookstore-app", fallback = AuthorControllerFeignFallback.class)
public interface AuthorControllerFeign extends AuthorController {
}
