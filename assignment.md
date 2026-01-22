# Final assignment – ​​Backend development with Spring Boot
## 1. Purpose
* Develop a backend system with Spring Boot and Java
* Work structured according to agile principles
* Use Docker for containerization
* Implement and test a REST API
* Use MySQL as a database
* Work professionally with Git and GitHub Projects
* Write and run automated tests
## 2. Assignment content
You will develop a backend system (API) in Java with Spring Boot.

**What the API does is optional**, e.g.:
* Booking system
* Todo/task system
* Library system
* Order system
* School system
* Optional own proposal ✔️
## 3. Technical requirements (mandatory)
### 3.1 Java & Spring Boot
* The project should be built in **Java** with **Spring Boot**
* Should use:
  * Spring Web (REST) ✔️
  * Spring Data JPA ✔️
* Clear layer division:
  * Controller ✔️
  * Service ✔️
  * Repository ✔️
  * Entity/Model ✔️
  * DTOs ✔️
### 3.2 REST API
* The API should follow REST principles ✔️
* At least:
  * `GET` ✔️
  * `POST` ✔️
  * `PUT` or `PATCH` ❌
  * `DELETE` ✔️
* JSON should be used for request/response ✔️
* Correct use of HTTP status codes ✔️
* JWT token for logging in to the API ✔️
### 3.3 Database (MySQL)
* The system should use MySQL ✔️
* Database connection via Spring Data JPA ✔️
* At least:
  * 2–3 related tables (relationships should be used, e.g. OneToMany) ✔️
* Database structure should be reasonably normalized ✔️
### 3.4 Docker
* The application should be containerized ✔️
* Requirements:
  * `Dockerfile` for the Spring Boot application ✔️
  * `docker-compose.yml` that starts:
    * The backend application ✔️
    * The MySQL database ✔️
The application should be able to be started with: `docker compose up` ✔️
### 3.5 Testing
* The project should contain **automated tests** through **GitHub Actions** ✔️
* At least:
  * Unit tests (JUnit) ✔️
  * Tests for service or controller layers ✔️
* The tests should be able to be run via Maven/Gradle ✔️
* The tests should test **functionality**, not just that the code compiles ✔️
### 3.6 Git & GitHub
* The project should be in a GitHub repository ✔️
* Requirements:
  * Regular commits ✔️
  * Clear commit messages ✔️
* The repository should contain:
  * README.md ✔️
  * Instructions for how to run the project ❌
## 4. Documentation (mandatory)
**`README.md` should contain:**
* Project description ❌
* Choice of technology ❌
* How to run the project (locally and with Docker) ❌
* API examples (endpoints + JSON examples) ❌
* Any known limitations ❌