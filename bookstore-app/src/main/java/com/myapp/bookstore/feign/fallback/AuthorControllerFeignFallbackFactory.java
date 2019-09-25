package com.myapp.bookstore.feign.fallback;

import com.myapp.bookstore.entity.Author;
import com.myapp.bookstore.feign.AuthorControllerFeign;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Fallback factory for {@link AuthorControllerFeign}.
 *
 * @author Ivan_Semenov
 */
@Component
@Slf4j
public class AuthorControllerFeignFallbackFactory implements FallbackFactory<AuthorControllerFeign> {

    @Override
    public AuthorControllerFeign create(Throwable throwable) {
        return new AuthorControllerFeign() {
            @Override
            public List<Author> getTopAuthors(Date dateFrom, Date dateTo) {
                log.error("Using fallback method for getTopAuthors() because of an exception: {}", throwable);
                return Collections.emptyList();
            }
        };
    }
}