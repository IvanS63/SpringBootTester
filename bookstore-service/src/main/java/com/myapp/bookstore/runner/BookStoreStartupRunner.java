package com.myapp.bookstore.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * BookStoreStartupRunner.
 *
 * @author Ivan_Semenov
 */
@Component
@Order(2)
public class BookStoreStartupRunner implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(BookStoreStartupRunner.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("Book store application started with option names : {}", args.getOptionNames());
    }
}
