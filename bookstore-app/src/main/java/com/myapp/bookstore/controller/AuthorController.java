package com.myapp.bookstore.controller;

import com.myapp.bookstore.entity.Author;
import com.myapp.bookstore.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * AuthorController.
 *
 * @author Ivan_Semenov
 */
@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping(path = "/top", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Author> getTopAuthors(@RequestParam("from") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFrom,
                                      @RequestParam("to") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateTo) {
        return authorService.getTopSellingAuthors(dateFrom, dateTo);
    }
}
