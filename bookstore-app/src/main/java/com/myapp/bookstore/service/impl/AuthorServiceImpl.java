package com.myapp.bookstore.service.impl;

import com.myapp.bookstore.entity.Author;
import com.myapp.bookstore.entity.Book;
import com.myapp.bookstore.repository.AuthorRepository;
import com.myapp.bookstore.service.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * AuthorServiceImpl.
 *
 * @author Ivan_Semenov
 */
@Service
public class AuthorServiceImpl implements AuthorService {

    private static final Logger log = LoggerFactory.getLogger(AuthorServiceImpl.class);

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public void add(Author author) {
        authorRepository.save(author);
    }

    @Override
    public void remove(Author author) {
        authorRepository.delete(author);
    }

    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @Override
    public List<Book> getSoldBooks(Author author) {
        log.debug("getSoldBooks() - start: author = {}", author);
        List<Book> result = authorRepository.getSoldBooks(author.getId());
        log.debug("getSoldBooks() - end: result size = {}", result.size());
        return result;
    }

    @Override
    public List<Author> getTopSellingAuthors(Date dateFrom, Date dateTo) {
        if (dateTo.before(dateFrom)) {
            throw new IllegalArgumentException("Incorrect date range");
        }
        log.debug("getTopSellingAuthors() - start: dateFrom = {}, dateTo = {}", dateFrom, dateTo);
        List<Author> authors = authorRepository.getTopSellingAuthors(dateFrom, dateTo);
        log.debug("getTopSellingAuthors() - end: result size = {}", authors.size());
        return authors;
    }
}
