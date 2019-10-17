package com.myapp.clientservice.controller;

import com.myapp.bookstore.entity.Author;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * ClientController.
 *
 * @author Ivan_Semenov
 */

@RequestMapping("client-app")
public interface ClientController {

    List<Author> getTopAuthorsFromBookstoreApp();
    
    List<Author> getFilteredAuthorsFromBookStoreApp();
}
