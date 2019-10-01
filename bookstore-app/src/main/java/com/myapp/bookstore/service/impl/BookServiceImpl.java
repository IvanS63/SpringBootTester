package com.myapp.bookstore.service.impl;

import static java.lang.String.format;

import com.myapp.bookstore.entity.Book;
import com.myapp.bookstore.repository.BookRepository;
import com.myapp.bookstore.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * BookServiceImpl.
 *
 * @author Ivan_Semenov
 */
@Service
public class BookServiceImpl implements BookService {

    private static final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void add(Book book) {
        bookRepository.save(book);
    }

    @Override
    public Book findById(Integer bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException(format("No book with id = %s found:", bookId)));
    }

    @Override
    public void remove(Book book) {
        bookRepository.delete(book);
    }

    @Override
    public void view(Integer id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(format("No book with id = %s found:", id)));
        book.setViews(book.getViews() + 1);
        bookRepository.save(book);
        log.debug("Increased number of views for book with id = {}", id);
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public void markAsSold(Integer id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(format("No book with id = %s found:", id)));
        book.setSoldDate(new Date());
        bookRepository.save(book);
        log.debug("Book with id = {} was marked as sold", id);
    }

    @Override
    public void increasePriceByAnnualPercent(Integer percent) {
        bookRepository.increasePriceByAnnualPercent(percent);
        log.info("Prices for all books increased by {} percents", percent);
    }

    @Override
    @Scheduled(fixedRate = 20000)
    public void increaseAllPricesTwice() {
        bookRepository.increasePriceByAnnualPercent(200);
        log.info("Prices for all books increased twice");
    }
}
