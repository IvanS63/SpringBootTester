package com.myapp.clientservice.hoverfly.bookstore;

import static io.specto.hoverfly.junit.dsl.HoverflyDsl.service;
import static io.specto.hoverfly.junit.dsl.ResponseCreators.success;
import static io.specto.hoverfly.junit.dsl.matchers.HoverflyMatchers.startsWith;

import com.myapp.bookstore.entity.Author;
import io.specto.hoverfly.junit.dsl.StubServiceBuilder;

import java.util.Collections;
import java.util.List;

/**
 * BookstoreStubs.
 *
 * @author Ivan_Semenov
 */
public class BookstoreStubs {
    private static final List<Author> AUTHOR_LIST = Collections.singletonList(new Author().setId(1).setName("Author"));

    public static StubServiceBuilder getTopAuthors() {
        return service("bookstore-app:8090")
                .get(startsWith("/books-store/authors/*"))
                .anyQueryParams()
                .willReturn(success(AUTHOR_LIST.toString(), "application/json"));

    }
}
