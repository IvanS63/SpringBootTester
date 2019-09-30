package com.myapp.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.myapp.bookstore"})
@EntityScan("com.myapp.bookstore.entity")
@EnableJpaRepositories("com.myapp.bookstore.repository")
public class BookstoreApplication {

	//To use debug profile use command following VM arguments:
	//-Dspring.profiles.active=debug -Djava.awt.headless=false
	
	//To test go to: http://localhost:8090/book-store/authors/top?from=1990-01-01&to=2020-01-01
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

}

