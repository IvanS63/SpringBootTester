package com.myapp.clientservice.controller.impl;

import com.myapp.bookstore.entity.Author;
import com.myapp.clientservice.controller.ClientController;
import com.myapp.bookstore.feign.AuthorControllerFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
@Component
@Slf4j
public class ClientSpringCloudController implements ClientController {

    @Autowired
    private AuthorControllerFeign authorControllerFeign;

    @RequestMapping(method = RequestMethod.GET, value = "/get-authors-v2")
    @Override
    public List<Author> getTopAuthorsFromBookstoreApp() {
        log.debug("getTopAuthorsFromBookstoreApp() - start:");
        List<Author> authors = authorControllerFeign
                .getTopAuthors(getDateFromString("January 1, 1990"), getDateFromString("January 1, 2020"));
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
