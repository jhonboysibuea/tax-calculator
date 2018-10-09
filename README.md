# Tax Calculator API
Tax Calculator API is a Spring RESTful API to store and display tax amounts from several Product items. Tax amount calculated by some rules. For more details you can find on my API Documentation below.

## Stack
- Docker
- Java
- Spring Boot
- MySQL
- JPA
- NGINX
- Maven

## Project Structure
```
tax-calculator
|   docker-compose.yaml
|   README.md
|
+---nginx
|   \---conf.d
|           app.conf
|
\---tax-calculator
    |   .gitignore
    |   Dockerfile
    |   mvnw
    |   mvnw.cmd
    |   pom.xml
    |
    +---src
        +---main
        |   +---java
        |   |   \---com
        |   |       \---shopee
        |   |           \---taxcalculator
        |   |               |   TaxCalculatorApplication.java
        |   |               |
        |   |               +---controller
        |   |               |       DetailController.java
        |   |               |       ProductController.java
        |   |               |       TaxCalculatorController.java
        |   |               |
        |   |               +---exception
        |   |               |       ResourceNotFoundException.java
        |   |               |
        |   |               +---model
        |   |               |       Detail.java
        |   |               |       Product.java
        |   |               |       TaxCalculator.java
        |   |               |
        |   |               +---repository
        |   |               |       ProductRepository.java
        |   |               |
        |   |               \---service
        |   |                       DetailService.java
        |   |                       ProductService.java
        |   |                       TaxCalculatorService.java
        |   |
        |   \---resources
        |       |   application.properties
        |       |
        |       +---static
        |       \---templates
        \---test
            +---java
            |   \---com
            |       \---shopee
            |           \---taxcalculator
            |                   DetailControllerTests.java
            |                   PoductControllerTests.java
            |                   ProductServiceTests.java
            |                   TaxCalculatorControllerTests.java
            |
            \---resources
                    application.properties
```

## Run
- Run command `docker-compose up`
- Base URL to `http://localhost/` (Make sure NGINX container running properly)

## API Contract

https://www.getpostman.com/collections/9231f051c8881f914021
