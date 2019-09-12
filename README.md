# Spring Boot App

Spring Boot application with CommandLineRunner and ApplicationRunner. This application represents book online store with the following business logic:

· Keeps list of books and authors objects (CRUD operations)
· Increment counter of views for each book object
· Attach author to each object
· Track all sold books for each author
· Controller with providing report of top-5 authors collected by sum of sold books for each month in time – range defined by the user
· Emulating logic for annual price changing for each book by spring scheduler. 
· After running of application a few price changes should happened in real-time with pre-defined schedule and logged in the console
