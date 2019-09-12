Spring Boot App Application
==============
Spring Boot application with CommandLineRunner and ApplicationRunner. This application represents book online store with the following business logic:

· Keeps list of books and authors objects (CRUD operations)
· Increment counter of views for each book object
· Attach author to each object
· Track all sold books for each author
· Controller with providing report of top-5 authors collected by sum of sold books for each month in time – range defined by the user
· Emulating logic for annual price changing for each book by spring scheduler. 
· After running of application a few price changes should happened in real-time with pre-defined schedule and logged in the console


Technologies
==============
- Maven
- Java 8
- Spring Boot
- Spring Data JPA
- HSQLDB


Workflow
========
To run application SpringBootApplication.java class should be run.
To check the results the following link should be used: http://localhost:8090/book_store/authors/top?from=1990-01-01&to=2020-01-01

Application provides unit tests with different Spring profiles for each layer and also one integration test for the whole applications.

