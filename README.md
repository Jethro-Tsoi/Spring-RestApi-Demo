# User Management API

## Overview
This project provides a simple REST API for managing users. It allows you to create new users and retrieve existing users by their username.

## Technologies Used
- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Test
- Spring Docker Compose
- PostgreSQL (via Docker)
- JUnit 5
- Mockito
- Flyway
- Gradle 7
- Swagger
- Lombok

## How to Run
1. Clone the repository.
2. Navigate to the project directory.
3. Run the service with the following command:
    ```
    ./gradlew bootRun
    ```
4. It will automatically start the PostgreSQL (via Docker) on port 5432.
5. The application will start on http://localhost:8080.

## API Endpoints
Test the API using swagger UI at http://localhost:8080/swagger-ui.html

- GET /api/v1/user/{username}
    - Retrieves a user by their username.
    - Example: GET /api/v1/user/jsmith
- POST /api/v1/user 
  - Creates a new user.
  - Example request body:
    ```
    {
    "username": "jsmith",
    "telephone": 12345678,
    "language": "java"
    }
    ```

## Running Tests
To run the unit and integration tests, use the following command:
```
./gradlew test
```