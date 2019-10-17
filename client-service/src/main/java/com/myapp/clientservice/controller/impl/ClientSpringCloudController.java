package com.myapp.clientservice.controller.impl;

import com.myapp.bookstore.entity.Author;
import com.myapp.clientservice.controller.ClientController;
import com.myapp.bookstore.feign.AuthorControllerFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * ClientSpringCloudController.
 *
 * @author Ivan_Semenov
 */
@RestController
@Slf4j
public class ClientSpringCloudController implements ClientController {

    @Autowired
    private AuthorControllerFeign authorControllerFeign;

    @RequestMapping(method = RequestMethod.GET, value = "/get-top-authors-v2")
    @Override
    public List<Author> getTopAuthorsFromBookstoreApp() {
        log.debug("getTopAuthorsFromBookstoreApp() - start:");
        List<Author> authors = authorControllerFeign
                .getTopSellingAuthorsByDateRange(getDateFromString("January 1, 1990"), getDateFromString("January 1, 2020"));
        log.info("Response received: {}", authors);
        log.debug("getTopAuthorsFromBookstoreApp() - end:");
        return authors;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get-filtered-authors-v2")
    @Override
    public List<Author> getFilteredAuthorsFromBookStoreApp() {
        log.debug("getTopAuthorsFromBookstoreApp() - start:");
        List<Author> authors = authorControllerFeign
                .getBuilder()
                .name(null)
                .amountOfBooks(0)
                .amountOfSoldBooks(0)
                .earnings(0)
                .execute();
        log.info("Response received: {}", authors);
        log.debug("getTopAuthorsFromBookstoreApp() - end:");
        return authors;
    }

    private Date getDateFromString(String value) {
        Date date = null;
        DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
        try {
            date = format.parse(value);
        } catch (ParseException e) {
            log.error("There was an error parsing date: {}", value);
        }
        return date;
    }
}
