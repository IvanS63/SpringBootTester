package com.myapp.bookstore.service;

import static org.junit.Assert.*;

import com.myapp.bookstore.config.ServiceTestConfig;
import com.myapp.bookstore.entity.Author;
import com.myapp.bookstore.entity.Book;
import com.myapp.bookstore.service.impl.AuthorServiceImpl;
import com.myapp.bookstore.service.impl.BookServiceImpl;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Unit tests for {@link BookServiceImpl} and {@link AuthorServiceImpl} with H2 embedded DB.
 *
 * @author Ivan_Semenov
 */
@ActiveProfiles("service-test")//Just to test working with profiles
@ContextConfiguration(classes = ServiceTestConfig.class)
public class BookServicesTest extends AbstractJUnit4SpringContextTests {

    private static final Integer BOOK_ID = 2;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookService bookService;

    @Before
    public void initDbData() {
        Author author1 = new Author().setName("Peter Straub");
        Author author2 = new Author().setName("F. Scott Fitzgerald");
        Author author3 = new Author().setName("George Orwell");
        Author author4 = new Author().setName("Tierry Henry");
        Author author5 = new Author().setName("Stephen King");
        Author author6 = new Author().setName("Chuck Palanik");

        authorService.add(author1
                .setBooks(Arrays.asList(
                        new Book("Some Book Tilte 1", 1000).setAuthor(author1).setSoldDate(new Date()),
                        new Book("Some Book Tilte 2", 2000).setAuthor(author1),
                        new Book("Some Book Tilte 3", 3000).setAuthor(author1).setSoldDate(new Date()),
                        new Book("Some Book Tilte 4", 4000).setAuthor(author1).setSoldDate(new Date()))));
        authorService.add(author2
                .setBooks(Arrays.asList(
                        new Book("Some Book Tilte 11", 100).setAuthor(author2).setSoldDate(new Date()),
                        new Book("Some Book Tilte 12", 200).setAuthor(author2).setSoldDate(new Date()),
                        new Book("Some Book Tilte 13", 300).setAuthor(author2).setSoldDate(new Date()),
                        new Book("Some Book Tilte 14", 400).setAuthor(author2).setSoldDate(new Date()))));
        authorService.add(author3
                .setBooks(Arrays.asList(
                        new Book("Some Book Tilte 21", 10).setAuthor(author3).setSoldDate(new Date()),
                        new Book("Some Book Tilte 22", 20).setAuthor(author3).setSoldDate(new Date()),
                        new Book("Some Book Tilte 23", 30).setAuthor(author3).setSoldDate(new Date()),
                        new Book("Some Book Tilte 24", 40).setAuthor(author3).setSoldDate(new Date()))));
        authorService.add(author4
                .setBooks(Arrays.asList(
                        new Book("Some Book Tilte 31", 1).setAuthor(author4).setSoldDate(new Date()),
                        new Book("Some Book Tilte 32", 2).setAuthor(author4).setSoldDate(new Date()),
                        new Book("Some Book Tilte 33", 3).setAuthor(author4).setSoldDate(new Date()),
                        new Book("Some Book Tilte 34", 4).setAuthor(author4).setSoldDate(new Date()))));
        authorService.add(author5
                .setBooks(Arrays.asList(
                        new Book("Some Book Tilte 41", 10000).setAuthor(author5).setSoldDate(new Date()),
                        new Book("Some Book Tilte 42", 20000).setAuthor(author5).setSoldDate(new Date()),
                        new Book("Some Book Tilte 43", 30000).setAuthor(author5).setSoldDate(new Date()),
                        new Book("Some Book Tilte 44", 40000).setAuthor(author5).setSoldDate(new Date()))));
        authorService.add(author6
                .setBooks(Arrays.asList(
                        new Book("Some Book Tilte 51", 50000).setAuthor(author6).setSoldDate(new Date()),
                        new Book("Some Book Tilte 52", 60000).setAuthor(author6).setSoldDate(new Date()),
                        new Book("Some Book Tilte 53", 70000).setAuthor(author6).setSoldDate(new Date()),
                        new Book("Some Book Tilte 54", 80000).setAuthor(author6).setSoldDate(new Date()))));
    }

    @Test
    public void testMarkAsSold() {
        Book book = bookService.findById(BOOK_ID);
        assertNull(book.getSoldDate());
        bookService.markAsSold(BOOK_ID);
        book = bookService.findById(BOOK_ID);
        assertNotNull(book.getViews());
    }

    @Test
    public void testView() {
        Book book = bookService.findById(BOOK_ID);
        assertEquals((Integer) 0, book.getViews());
        bookService.view(BOOK_ID);
        book = bookService.findById(BOOK_ID);
        assertEquals((Integer) 1, book.getViews());
    }

    @Test
    public void testIncreasePriceByAnnualPercent() {
        bookService.increasePriceByAnnualPercent(10);
        Book book = bookService.findById(BOOK_ID);
        assertEquals((Integer) 2200, book.getPrice());
    }

    @Test
    public void testGetTopSellingAuthors() throws ParseException {
        List<Author> authors = authorService.getTopSellingAuthorsByDateRange(dateFromString("January 2, 2010"), dateFromString("January 2, 2020"));
        assertEquals(5, authors.size());
    }

    @Test
    public void testGetAuthorSoldBooks() {
        Author author1 = new Author().setName("Chuck Palanik");

        authorService.add(author1
                .setBooks(Arrays.asList(
                        new Book("Some Book Tilte 1", 10000).setAuthor(author1).setSoldDate(new Date()),
                        new Book("Some Book Tilte 2", 20000).setAuthor(author1).setSoldDate(new Date()),
                        new Book("Some Book Tilte 3", 30000).setAuthor(author1),
                        new Book("Some Book Tilte 4", 40000).setAuthor(author1))));

        assertEquals(2, authorService.getSoldBooks(author1).size());
    }

    private Date dateFromString(String date) throws ParseException {
        DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
        return format.parse(date);
    }

}