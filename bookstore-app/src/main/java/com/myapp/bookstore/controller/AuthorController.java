package com.myapp.bookstore.controller;

import com.myapp.bookstore.entity.Author;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

/**
 * AuthorController.
 *
 * @author Ivan_Semenov
 */

public interface AuthorController {

    @RequestMapping(method = RequestMethod.GET, path = "/top", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Author> getTopSellingAuthorsByDateRange(
            @RequestParam(value = "from", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFrom,
            @RequestParam(value = "to", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateTo);

    @RequestMapping(method = RequestMethod.GET, path = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Author> getAuthorsFilteredByParams(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "amountOfBooks", required = false) Integer amountOfBooks,
            @RequestParam(value = "earnings", required = false) Integer earnings,
            @RequestParam(value = "amountOfSoldBooks", required = false) Integer amountOfSoldBooks
    );
}
