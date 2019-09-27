# Spring Cloud + Spring Boot Application Example
Simple example for Spring Cloud+Spring Boot applications.

## Technologies
- Maven
- Java 8
- Spring Cloud (Eureka, Ribbon) 
- Spring Boot
- Spring Data JPA
- HSQLDB
- Liquibase



## Functionality
The Application contains the modules:
- Eureka Server
- Book store Application
- Client Application


### Bookstore Application
This application represents book online store with the following business logic:
· Keeps list of books and authors objects (CRUD operations) by the means of Spring Data JPA.
· Initial database is created and populated by Liquibase scripts.
· Increment counter of views for each book object.
· Attach author to each object
· Track all sold books for each author
· Controller with providing report of top-5 authors collected by sum of sold books for each month in time – range defined by the user
· Emulating logic for annual price changing for each book by spring scheduler. 
· After running of application a few price changes should happened in real-time with pre-defined schedule and logged in the console

Integration and unit tests are provided.


### Eureka Server Application
Represents simple Application for Service Discovery


### Client Applications
Represents simple Client Application that sends requests to Bookstore Application with the help of RestTemplate and Feign Clients.


Workflow
========
1. Run mvn clean install from the root directory.
2. Run Eureka Server App (http://localhost:8761/).
3. Run Bookstore App (http://localhost:8090/book-store/authors/top?from=1990-01-01&to=2020-01-01).
4. Run Client App (http://localhost:8091/client-app/get-authors-v1 - for checking Rest Template Functionality, http://localhost:8091/client-app/get-authors-v2 - to check Feign Client example).