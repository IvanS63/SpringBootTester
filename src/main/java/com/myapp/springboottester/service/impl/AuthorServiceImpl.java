package com.myapp.springboottester.service.impl;

import com.myapp.springboottester.entity.Author;
import com.myapp.springboottester.entity.Book;
import com.myapp.springboottester.repository.AuthorDao;
import com.myapp.springboottester.service.AuthorService;
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
    private AuthorDao authorDao;

    @Override
    public void add(Author author) {
        authorDao.save(author);
    }

    @Override
    public void remove(Author author) {
        authorDao.delete(author);
    }

    @Override
    public List<Author> getAll() {
        return authorDao.findAll();
    }

    @Override
    public List<Book> getSoldBooks(Author author) {
        log.debug("getSoldBooks() - start: author = {}", author);
        List<Book> result = authorDao.getSoldBooks(author.getId());
        log.debug("getSoldBooks() - end: result size = {}", result.size());
        return result;
    }

    @Override
    public List<Author> getTopSellingAuthors(Date dateFrom, Date dateTo) {
        if (dateTo.before(dateFrom)) {
            throw new IllegalArgumentException("Incorrect date range");
        }
        log.debug("getTopSellingAuthors() - start: dateFrom = {}, dateTo = {}", dateFrom, dateTo);
        List<Author> authors = authorDao.getTopSellingAuthors(dateFrom, dateTo);
        log.debug("getTopSellingAuthors() - end: result size = {}", authors.size());
        return authors;
    }
}
