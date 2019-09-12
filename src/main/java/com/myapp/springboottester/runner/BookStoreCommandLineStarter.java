package com.myapp.springboottester.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * CommandLineAppStarter.
 *
 * @author Ivan_Semenov
 */
@Component
@Order(1)
public class BookStoreCommandLineStarter implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(BookStoreCommandLineStarter.class);

    public void run(String... args) throws Exception {
        logger.info("Book store application started with command-line arguments: {} . \n To kill this application, press Ctrl + C.", Arrays.toString(args));
    }
}