package com.myapp.bookstore.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.myapp.bookstore.entity.Author;
import com.myapp.bookstore.repository.AuthorRepository;
import com.myapp.bookstore.repository.BookRepository;
import com.myapp.bookstore.service.AuthorService;
import com.myapp.bookstore.service.BookService;
import ma.glasnost.orika.MapperFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

/**
 * Unit test for {@link AuthorController} by using WebMvcTest annotation.
 *
 * @author Ivan_Semenov
 */
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = AuthorController.class)
public class AuthorControllerTest {

    private static final Integer AUTHOR1_ID = 1;
    private static final Integer AUTHOR2_ID = 2;

    @MockBean
    private AuthorService authorService;

    @MockBean
    private AuthorRepository authorRepository;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private MapperFacade mapperFacade;
    
    @MockBean
    private BookService bookService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetTopSellingAuthorsByDateRange() throws Exception {
        when(authorService.getTopSellingAuthorsByDateRange(any(), any()))
                .thenReturn(Arrays.asList(
                        new Author().setId(AUTHOR1_ID),
                        new Author().setId(AUTHOR2_ID)));

        mockMvc.perform(get("/book-store/authors/top?from=1990-01-01&to=2020-01-01")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(AUTHOR1_ID))
                .andExpect(jsonPath("$[1].id").value(AUTHOR2_ID));

        verify(authorService).getTopSellingAuthorsByDateRange(any(), any());
    }
}