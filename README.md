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
The Application consists of sub-applications represented as Maven sub-modules:
1. Eureka Server
2. Bookstore Application
3. Client Rest Application
4. Kafka Application (enabled in kafka-branch)

### Bookstore Application
This application represents book online store with the following business logic:
- Keeps list of books and authors objects (CRUD operations and business operations) by the means of Spring Data JPA.
- Initial database is created and populated by Liquibase scripts.
- Controller with providing report of top-5 authors collected by sum of sold books for each month in time – range defined by the user
- Emulating logic for annual price changing for each book by Spring Scheduler (After running of application a few price changes should happened in real-time with pre-defined schedule and logged in the console). 
- Controller with filtration authors by pre-defined params (name, amount of books, earning, amount of sold books).

Integration and unit tests are provided.


### Eureka Server Application
Represents simple Application for Service Discovery


### Client Rest Application
Represents simple Client Application that sends requests to Bookstore Application with the help of RestTemplate and Feign Clients.


Workflow
========
1. Run **Application Runner.bat** file (that runs applications in following order: Eureka Server > Bookstore App > Client Rest App).
2. Eureka Server App can be checked from http://localhost:8761/.
3. Bookstore App can be checked from http://localhost:8090/book-store/authors/top?from=1990-01-01&to=2020-01-01 and http://localhost:8090//book-store/authors/filter?name=Straub&amountOfBooks=3&earnings=1000&amountOfSoldBooks=3.
4. Client App be checked from http://localhost:8091/client-app/get-top-authors-v1 - for checking Rest Template Functionality, http://localhost:8091/client-app/get--top-authors-v2 - to check Feign Client example). Sometimes there can be errors with loading data (in case Eureka hasn't registered applications, this can be fixed by setting smaller timeout settings).
