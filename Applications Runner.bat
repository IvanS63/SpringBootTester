@echo off
call "Applications Builder.bat"
start "Eureka Server" java -jar eureka-server/target/eureka-server-0.0.1-SNAPSHOT.jar 
timeout /t 10
start "Bookstore App" java -jar bookstore-app/target/bookstore-app-1.0-SNAPSHOT.jar
timeout /t 10
start "Client Rest App" java -jar client-service/target/client-service-1.0-SNAPSHOT.jar