package com.myapp.bookstore.controller;

import com.myapp.bookstore.entity.Author;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

/**
 * AuthorController.
 *
 * @author Ivan_Semenov
 */
@RequestMapping("/authors")
public interface AuthorController {

    @GetMapping(path = "/top", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Author> getTopAuthors(@RequestParam("from") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFrom,
                                      @RequestParam("to") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateTo);
}
