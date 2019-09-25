package com.myapp.bookstore;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.myapp.bookstore.entity.Author;
import com.myapp.bookstore.entity.Book;
import com.myapp.bookstore.service.AuthorService;
import com.myapp.bookstore.service.BookService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Date;

/**
 * Author book store application integration test.
 *
 * @author Ivan_Semenov
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("integration-test") //will automatically use properties from application-integration-test.yml
public class BookStoreIntegrationTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private MockMvc mockMvc;

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
                        new Book("Some Book Tilte 1", 1000).setAuthor(author1),
                        new Book("Some Book Tilte 2", 2000).setAuthor(author1).setSoldDate(new Date()),
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
    public void shouldReturnDefaultMessage() throws Exception {
        mockMvc.perform(get("/book-store/authors/top?from=1990-01-01&to=2020-01-01")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Chuck Palanik"))
                .andExpect(jsonPath("$[1].name").value("Stephen King"))
                .andExpect(jsonPath("$[2].name").value("Peter Straub"))
                .andExpect(jsonPath("$[3].name").value("F. Scott Fitzgerald"))
                .andExpect(jsonPath("$[4].name").value("George Orwell"));
    }
}
