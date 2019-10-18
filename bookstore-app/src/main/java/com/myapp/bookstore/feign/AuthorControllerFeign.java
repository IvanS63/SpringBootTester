package com.myapp.bookstore.feign;

import com.myapp.bookstore.controller.AuthorController;
import com.myapp.bookstore.entity.Author;
import com.myapp.bookstore.feign.fallback.AuthorControllerFeignFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;


/**
 * Feign implementation for Eureka service.
 *
 * @author Ivan_Semenov
 */
@FeignClient(
        name = "bookstore-app",
        path = "/book-store/authors",
        fallbackFactory = AuthorControllerFeignFallbackFactory.class)
public interface AuthorControllerFeign extends AuthorController {

    default Builder getBuilder() {
        return new Builder(this);
    }

    class Builder {
        
        private String name;
        private Integer amountOfBooks;
        private Integer earnings;
        private Integer amountOfSoldBooks;

        private AuthorControllerFeign authorControllerFeign;

        public Builder(AuthorControllerFeign authorControllerFeign) {
            this.authorControllerFeign = authorControllerFeign;
        }

        public Builder name(final String name) {
            this.name = name;
            return this;
        }

        public Builder amountOfBooks(final Integer amountOfBooks) {
            this.amountOfBooks = amountOfBooks;
            return this;
        }
        
        public Builder earnings(final Integer earnings) {
            this.earnings = earnings;
            return this;
        }

        public Builder amountOfSoldBooks(final Integer amountOfSoldBooks) {
            this.amountOfSoldBooks = amountOfSoldBooks;
            return this;
        }

        public List<Author> execute() {
            return authorControllerFeign.getAuthorsFilteredByParams(name, amountOfBooks, earnings, amountOfSoldBooks);
        }

    }
}
