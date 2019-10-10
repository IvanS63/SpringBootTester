package com.myapp.bookstore.service;

import com.myapp.bookstore.entity.Author;
import com.myapp.bookstore.entity.Book;
import org.springframework.lang.NonNull;

import java.util.Date;
import java.util.List;

/**
 * AuthorService.
 *
 * @author Ivan_Semenov
 */
public interface AuthorService {

    void add(@NonNull Author author);

    void remove(@NonNull Author author);

    List<Author> getAll();
    
    Author findByName(String name);

    List<Book> getSoldBooks(@NonNull Author author);

    List<Author> getTopSellingAuthors(Date dateFrom, Date dateTo);
}
