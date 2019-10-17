package com.myapp.clientservice.hoverfly.bookstore;

import static com.myapp.clientservice.hoverfly.HoverflyStubs.toJsonString;
import static io.specto.hoverfly.junit.dsl.HoverflyDsl.service;
import static io.specto.hoverfly.junit.dsl.ResponseCreators.success;
import static io.specto.hoverfly.junit.dsl.matchers.HoverflyMatchers.matches;
import static io.specto.hoverfly.junit.dsl.matchers.HoverflyMatchers.startsWith;

import com.myapp.bookstore.entity.Author;
import io.specto.hoverfly.junit.dsl.StubServiceBuilder;
import lombok.experimental.UtilityClass;

import java.util.Collections;
import java.util.List;

/**
 * BookstoreStubs.
 *
 * @author Ivan_Semenov
 */
@UtilityClass
public class BookstoreStubs {
    private static final List<Author> AUTHOR_LIST = Collections.singletonList(new Author().setId(1).setName("Author"));

    public static StubServiceBuilder getTopAuthors() {
        return service("bookstore-app")
                .get(matches("/book-store/authors/top"))
                .anyQueryParams()
                .willReturn(success(toJsonString(AUTHOR_LIST), "application/json"));

    }

    public static StubServiceBuilder getFilteredAuthors() {
        return service("bookstore-app")
                .get(matches("/book-store/authors/filter"))
                .anyQueryParams()
                .willReturn(success(toJsonString(AUTHOR_LIST), "application/json"));

    }
}
