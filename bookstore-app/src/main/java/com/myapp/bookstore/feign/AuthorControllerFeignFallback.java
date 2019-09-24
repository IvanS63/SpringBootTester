package com.myapp.bookstore.feign;

import com.myapp.bookstore.entity.Author;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Class that will return default error response in case bookstore-app service is unavailable.
 *
 * @author Ivan_Semenov
 */
@Component
@Slf4j
public class AuthorControllerFeignFallback implements AuthorControllerFeign {
    
    @Override
    public List<Author> getTopAuthors(Date dateFrom, Date dateTo) {
        log.error("There was an error invoking getTopAuthors() method");
        return Collections.emptyList();
    }
}
