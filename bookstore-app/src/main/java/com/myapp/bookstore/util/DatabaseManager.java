package com.myapp.bookstore.util;

import org.hsqldb.util.DatabaseManagerSwing;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * DatabaseManager.
 *
 * @author Ivan_Semenov
 */
@Component
@Profile("debug")
public class DatabaseManager {

    @PostConstruct
    public void startDBManager() {
        //hsqldb
        DatabaseManagerSwing.main(new String[]{"--url", "jdbc:h2:mem:testdb", "--user", "sa", "--password", ""});
    }
}
