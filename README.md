# ComicOn – Online Comic Store

ComicOn is a Java-based Spring Boot web application designed to manage a digital comic book store. It supports user registration, authentication via JWT, and features to create, filter, and retrieve comic records.

---

## Application Summary

ComicOn provides a RESTful interface for managing comics and user access. Key features include:

* Secure JWT-based login and registration
* Adding new comics
* Filtering comics by genre, title, or author
* Browsing all available comics
* Swagger UI integration for easy API interaction
* Profile-based DB support for H2 (dev) and PostgreSQL (prod)

---

## Key Technologies

* **Java 17+**
* **Spring Boot**
* **Spring Security (JWT)**
* **Hibernate + JPA**
* **H2 & PostgreSQL**
* **Swagger (OpenAPI 3)**
* **Lombok**
* **JUnit 5, Mockito**

---

## Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/comicon.git
cd comicon
```

### 2. Build and Run

```bash
mvn clean install
mvn spring-boot:run
```

---

## Configuration Details

### Development (Default: H2)

The H2 in-memory DB requires no setup. Access the H2 console at:
`http://localhost:8080/h2-console`
Ensure the JDBC URL matches `jdbc:h2:mem:testdb`

### Production (PostgreSQL)

Create a database called `comicsdb`.
Update `application.yml` (or `.properties`) with:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/comicsdb
    username: postgres
    password: your_password
  jpa:
    hibernate:
      ddl-auto: update
```

Switch profiles as needed using:

```bash
-Dspring.profiles.active=postgres
```

---

## API Endpoints Overview

### Authentication

* **Register**: `POST /api/auth/register`
  Payload: `{ "username": "user1", "password": "pass123" }`

* **Login**: `POST /api/auth/login`
  Payload: `{ "username": "user1", "password": "pass123" }`
  Response: `Bearer <token>`

* **Refresh Token**: `POST /api/auth/refresh`
  Payload: old JWT string (raw)

### Comic Management (Require JWT)

* **Add Comic**: `POST /api/comics`
  Headers: `Authorization: Bearer <token>`
  Payload:

  ```json
  {
    "title": "The Watchmen",
    "author": "Alan Moore",
    "genre": "Superhero",
    "plot": "A dark twist on superheroes",
    "price": 12.99
  }
  ```

* **Get All Comics**: `GET /api/comics`

* **Filter Comics**: `GET /api/comics?author=Alan&genre=Superhero`

---

## Testing

* **Unit Tests**:

  ```bash
  mvn test
  ```

* **Integration Tests**:

  ```bash
  mvn verify
  ```

---

## Directory Structure

```
src
├── main
│   ├── java
│   │   └── org.example.comicon
│   │       ├── config
│   │       ├── controller
│   │       ├── dto
│   │       ├── entities
│   │       ├── repository
│   │       ├── security
│   │       ├── service
│   │       └── ComicOnApplication.java
│   └── resources
│       ├── application.yml
│       ├── data.sql
└── test
    └── java
        └── org.example.comicon
            ├── controller
            ├── service
            └── integration
```

---

## Swagger UI

Once running, open:
**[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)**
Here you can test endpoints, authorize with JWT, and view request/response formats.
