package com.myapp.springboottester.service;

import com.myapp.springboottester.entity.Author;
import com.myapp.springboottester.entity.Book;

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
