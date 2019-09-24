package com.myapp.bookstore.service;

import com.myapp.bookstore.entity.Author;
import com.myapp.bookstore.entity.Book;

import java.util.Date;
import java.util.List;

/**
 * AuthorService.
 *
 * @author Ivan_Semenov
 */
public interface AuthorService {

    void add(Author author);

    void remove(Author author);

    List<Author> getAll();

    List<Book> getSoldBooks(Author author);

    List<Author> getTopSellingAuthors(Date dateFrom, Date dateTo);
}
