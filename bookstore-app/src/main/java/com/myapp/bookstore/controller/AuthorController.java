package com.myapp.bookstore.controller;

import com.myapp.bookstore.entity.Author;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

/**
 * AuthorController.
 *
 * @author Ivan_Semenov
 */
public interface AuthorController {

    List<Author> getTopAuthors(@RequestParam("from") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFrom,
                                      @RequestParam("to") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateTo);
}
