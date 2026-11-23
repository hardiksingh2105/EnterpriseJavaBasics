# EnterpriseJavaBasics

Implementation of Basic Enterprise Java Logics.

This repository records the academic progression from a first Spring Boot REST API to an in-memory CRUD application and then to a PostgreSQL-backed Student Management System. Labs 2 and 3 are intentionally retained as independent historical projects; the `student-management` application is the cumulative implementation of Labs 4–7.

## Repository overview

| Lab | Folder | Main learning objective | Persistence |
|---|---|---|---|
| 1 | `Lab-1-Client-Server-Dependencies` | Java, IntelliJ IDEA and Maven environment setup | Not applicable |
| 2 | `Lab-2-Student-Management` | Basic Spring Boot GET endpoints and JSON | In-memory list |
| 3 | `Lab-3-Student-CRUD` | REST CRUD with `ArrayList` | In-memory list; data resets on restart |
| 4 | `student-management` | PostgreSQL, JPA entities and persistent CRUD | PostgreSQL |
| 5 | `student-management` | Derived queries, pagination and sorting | PostgreSQL |
| 6 | `student-management` | One-to-many Student–Course relationship | PostgreSQL |
| 7 | `student-management` | Bean Validation and centralized exception handling | PostgreSQL |

Each application folder has its own `pom.xml` and Spring Boot entry point, so it can be opened and run independently in IntelliJ IDEA. There is deliberately no root Maven aggregator that merges the three applications.

## Repository structure

```text
EnterpriseJavaBasics/
├── README.md
├── .gitignore
├── .gitattributes
├── .env.example
├── .github/
│   └── workflows/
│       └── maven.yml
│
├── Lab-1-Client-Server-Dependencies/
│   └── README.md
│
├── Lab-2-Student-Management/
│   ├── README.md
│   ├── pom.xml
│   └── src/
│       ├── main/
│       │   ├── java/com/example/ucs067/studentmanagement/
│       │   │   ├── HelloController.java
│       │   │   ├── Student.java
│       │   │   └── StudentManagementApplication.java
│       │   └── resources/application.properties
│       └── test/java/com/example/ucs067/studentmanagement/
│           └── HelloControllerTest.java
│
├── Lab-3-Student-CRUD/
│   ├── README.md
│   ├── pom.xml
│   └── src/
│       ├── main/
│       │   ├── java/com/example/ucs067/studentmanagement/
│       │   │   ├── HelloController.java
│       │   │   ├── Student.java
│       │   │   ├── StudentController.java
│       │   │   └── StudentManagementApplication.java
│       │   └── resources/application.properties
│       └── test/java/com/example/ucs067/studentmanagement/
│           └── StudentControllerTest.java
│
└── student-management/
    ├── README.md
    ├── .gitignore
    ├── .env.example
    ├── pom.xml
    ├── requests/
    │   └── student-management.http
    └── src/
        ├── main/
        │   ├── java/com/example/ucs067/studentmanagement/
        │   │   ├── Student.java
        │   │   ├── Course.java
        │   │   ├── StudentManagementApplication.java
        │   │   ├── controller/
        │   │   │   ├── StudentController.java
        │   │   │   └── CourseController.java
        │   │   ├── service/
        │   │   │   ├── StudentService.java
        │   │   │   └── CourseService.java
        │   │   ├── repository/
        │   │   │   ├── StudentRepository.java
        │   │   │   └── CourseRepository.java
        │   │   ├── dto/
        │   │   │   ├── StudentRequest.java
        │   │   │   └── CourseRequest.java
        │   │   └── exception/
        │   │       ├── ResourceNotFoundException.java
        │   │       ├── DuplicateStudentException.java
        │   │       └── GlobalExceptionHandler.java
        │   └── resources/application.properties
        └── test/java/com/example/ucs067/studentmanagement/service/
            └── StudentServiceTest.java
```

## Technology stack

| Technology | Version / configuration |
|---|---|
| Java | 17 language target |
| Spring Boot | 4.2.0 |
| Spring MVC | `spring-boot-starter-webmvc` |
| Spring Data JPA | `spring-boot-starter-data-jpa` |
| ORM | Hibernate managed by Spring Boot |
| Validation | `spring-boot-starter-validation` |
| Database | PostgreSQL 18.7 (or a compatible PostgreSQL 18 installation) |
| Build | Maven 3.6.3 or later |
| Development | Spring Boot DevTools |
| IDE | IntelliJ IDEA |
| Default application port | 8081 |

Spring Boot 4.2.0 is published and supports Java 17. The project uses the Spring Boot 4 MVC starter name, `spring-boot-starter-webmvc`.

## Prerequisites

Install the following on Windows 11:

1. JDK 17 and configure `JAVA_HOME` and `Path`.
2. IntelliJ IDEA with Maven project support.
3. Apache Maven 3.6.3 or newer, unless using IntelliJ's bundled Maven.
4. PostgreSQL 18.7 and `psql` (Labs 4–7 only).
5. Git for version control.

Verify installations in PowerShell:

```powershell
java -version
javac -version
mvn -version
git --version
psql --version
```

Spring Boot 4.2.0 supports JDK 17 and newer. For the academic target, keep the compiler release set to 17 even when a newer JDK is installed.

## Lab 1 — Client-Server Dependencies and Environment Setup

**Objective:** Prepare the Java Enterprise development environment.

The detailed installation and configuration instructions are in [`Lab-1-Client-Server-Dependencies/README.md`](Lab-1-Client-Server-Dependencies/README.md). They cover IntelliJ IDEA, JDK 17, Windows environment variables, IntelliJ project SDK configuration and Maven.

The original lab instruction refers to two additional client-server dependency files. Their filenames and download locations were not supplied. They must be identified and obtained from the instructor; this repository intentionally does not invent names or URLs for them.

## Lab 2 — Implementation of Student Management Project

**Project:** `Lab-2-Student-Management`

| Property | Details |
|---|---|
| Project name | `student-management` |
| Language | Java |
| Build tool | Maven |
| Framework | Spring Boot 4.2.0 |
| Package | `com.example.ucs067.studentmanagement` |
| Server port | 8081 |

Technologies: Java, Spring Boot, Spring Web MVC, Spring Boot DevTools and Maven.

Features:

- `GET /hello` — returns `Hello, Spring Boot!`.
- `GET /student` — returns the predefined Rahul student object.
- `GET /students` — returns Rahul, Priya and Amit from an in-memory Java list.

Concepts: REST API development, HTTP GET, controllers, Java classes and objects, constructors, getters/setters, Java Lists and JSON serialization.

Run this project from its folder:

```powershell
cd Lab-2-Student-Management
mvn clean test
mvn spring-boot:run
```

Open `http://localhost:8081/hello` or run the requests manually in an HTTP client. Lab 2 uses an in-memory list and does not require PostgreSQL.

## Lab 3 — CRUD Operations Using ArrayList

**Project:** `Lab-3-Student-CRUD`

Lab 3 extends the basic endpoints with an `ArrayList<Student>` and complete CRUD operations.

| Operation | Method | Endpoint |
|---|---|---|
| Create | POST | `/students` |
| Read all | GET | `/students` |
| Read one | GET | `/students/{id}` |
| Update | PUT | `/students/{id}` |
| Delete | DELETE | `/students/{id}` |

Concepts: `@PostMapping`, `@GetMapping`, `@PutMapping`, `@DeleteMapping`, `@RequestBody`, `@PathVariable`, `ResponseEntity`, Iterator-based deletion, duplicate-ID response and HTTP 404 handling. The `/hello` and `/student` endpoints are also retained.

Example POST body:

```json
{
  "id": 4,
  "name": "Neha",
  "email": "neha@example.com"
}
```

Example PUT body:

```json
{
  "id": 2,
  "name": "Priya Sharma",
  "email": "priya.sharma@example.com"
}
```

Run this project independently:

```powershell
cd Lab-3-Student-CRUD
mvn clean test
mvn spring-boot:run
```

**Important:** Lab 3 is intentionally in-memory. All students created or changed through its API are lost when the application stops or restarts. It does not use PostgreSQL.

## Lab 4 — PostgreSQL and Spring Data JPA

The cumulative application begins in `student-management`. Lab 4 replaces Lab 3's `ArrayList` with PostgreSQL persistence.

Create the database using `psql` or pgAdmin:

```sql
CREATE DATABASE studentdb;
```

Connect to it:

```powershell
psql -U postgres -h localhost -p 5432 -d studentdb
```

The final application's configuration is in `student-management/src/main/resources/application.properties`:

```ini
spring.application.name=student-management
server.port=8081
spring.datasource.url=jdbc:postgresql://localhost:5432/studentdb
spring.datasource.username=postgres
spring.datasource.password=${DB_PASSWORD}
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

The `Student` entity maps to the `students` table. Student IDs are supplied by the client and are **not** generated by the database. `StudentRepository` extends `JpaRepository<Student, Integer>` and supplies persistent CRUD operations.

## Lab 5 — Derived Queries, Pagination and Sorting

`StudentRepository` provides these derived queries:

- `findByName(String name)` — exact name search.
- `findByNameContaining(String keyword)` — partial name search.
- `findByEmail(String email)` — email search.

The final app also exposes `GET /students/page?page=0&size=2` using Spring Data `Pageable` and `Page<Student>`, and `GET /students/sort/name` using `Sort.by("name")`. Page sizes are limited to 1–100 records.

## Lab 6 — One-to-Many Student-Course Relationship

The final app implements one Student to many Courses:

```text
Student
  ├── Enterprise Java
  ├── Python
  └── DBMS
```

- `Student` has `@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)`.
- `Course` owns the relationship using `@ManyToOne` and `@JoinColumn(name = "student_id")`.
- Course records use generated IDs; Student IDs remain manually supplied.
- The foreign key is `course.student_id` (the entity table is explicitly named `courses`).
- `@JsonManagedReference` and `@JsonBackReference` prevent recursive Student–Course JSON serialization.
- Deleting a student cascades to the associated courses.

Endpoints:

- `GET /courses`
- `POST /students/{studentId}/courses`

Course request example:

```json
{
  "courseName": "Enterprise Java",
  "credits": 4
}
```

## Lab 7 — Validation and Centralized Exception Handling

The final app validates incoming student and course requests. Student ID must be positive, name and email cannot be blank, and email must have a valid format. Incoming POST and PUT request bodies use `@Valid`.

A `@RestControllerAdvice` handles:

- `MethodArgumentNotValidException` — HTTP 400 with field-specific JSON messages.
- Missing students — HTTP 404.
- Duplicate student IDs — HTTP 409.
- Invalid path/body IDs and pagination values — HTTP 400.
- Database integrity conflicts — HTTP 409.

Example validation response:

```json
{
  "name": "Student name is required",
  "email": "Please provide a valid email address"
}
```

Invalid POST and PUT requests are rejected before the service writes to the repository.

## Environment variable setup

The real database password is never stored in source control. The root and final-app `.env.example` files are safe templates only. Spring Boot does not automatically load a plain `.env` file; set the environment variable in the shell or in IntelliJ's run configuration.

For the current PowerShell session:

```powershell
$env:DB_PASSWORD = "your_local_postgresql_password"
```

To persist it for the current Windows user (open a new terminal afterward):

```powershell
setx DB_PASSWORD "your_local_postgresql_password"
```

In IntelliJ IDEA, open **Run → Edit Configurations → Environment variables** and set `DB_PASSWORD=your_local_postgresql_password` for the final application's run configuration. Never commit the real value.

## Maven commands

Run commands from the relevant project directory:

```powershell
mvn clean test
mvn clean package
mvn spring-boot:run
```

Build all three independent projects from the repository root:

```powershell
mvn -f Lab-2-Student-Management/pom.xml clean verify
mvn -f Lab-3-Student-CRUD/pom.xml clean verify
mvn -f student-management/pom.xml clean verify
```

## How to run the final Lab 4–7 application

1. Start PostgreSQL and create `studentdb`.
2. Set `DB_PASSWORD` in the shell or IntelliJ run configuration.
3. Open `student-management/pom.xml` as a Maven project in IntelliJ IDEA, or change directory to `student-management`.
4. Run:

   ```powershell
   mvn clean test
   mvn spring-boot:run
   ```

5. The service listens on `http://localhost:8081`.
6. Use `student-management/requests/student-management.http` in IntelliJ's HTTP Client to exercise the API.

The final application requires a reachable PostgreSQL instance at `localhost:5432`. Hibernate's `ddl-auto=update` creates/updates the mapped tables; it does not create the database itself.

## Complete REST API reference — final application

Base URL: `http://localhost:8081`

| Method | Endpoint | Purpose |
|---|---|---|
| GET | `/hello` | Greeting |
| GET | `/student` | Predefined Rahul student |
| GET | `/students` | Get all persisted students |
| POST | `/students` | Create a student |
| GET | `/students/{id}` | Get student by ID |
| PUT | `/students/{id}` | Update a student |
| DELETE | `/students/{id}` | Delete student and cascade-delete courses |
| GET | `/students/search/name/{name}` | Search exact name |
| GET | `/students/search/name/contains/{keyword}` | Search partial name |
| GET | `/students/search/email/{email}` | Search email |
| GET | `/students/page?page=0&size=2` | Paginated students |
| GET | `/students/sort/name` | Sort students by name ascending |
| GET | `/courses` | Get all courses |
| POST | `/students/{studentId}/courses` | Add a course to a student |

The final `GET /students` reads PostgreSQL and is not pre-populated with the Lab 2/3 sample list. `GET /student` is retained as the original predefined example endpoint and does not insert that object into the database.

## Testing instructions

The IntelliJ HTTP Client request collection is at `student-management/requests/student-management.http`. Execute requests from top to bottom. It includes setup cleanup requests for its demonstration IDs, successful CRUD/search/page/sort/course scenarios, validation failures, and a missing-student request.

Unit tests are included in all three Maven projects. They do not use an embedded alternative database; PostgreSQL remains the only database used by the final app. Database-backed end-to-end tests should be run with a local PostgreSQL service configured as above.

## PostgreSQL verification

Connect to `studentdb`:

```powershell
psql -U postgres -h localhost -p 5432 -d studentdb
```

Inspect the rows:

```sql
SELECT * FROM students;
SELECT * FROM courses;
```

The mapped table names are `students` and `courses`. The relationship column is `courses.student_id`. To inspect the relationship directly:

```sql
SELECT c.id, c.course_name, c.credits, c.student_id, s.name, s.email
FROM courses AS c
JOIN students AS s ON s.id = c.student_id
ORDER BY s.id, c.id;
```

## Learning outcomes

After completing the repository, a student should be able to prepare a Java/Maven development environment, create Spring MVC REST endpoints, model and manipulate Java objects, implement in-memory CRUD, connect Spring Data JPA to PostgreSQL, define derived repository queries, paginate and sort records, map one-to-many relationships, validate incoming JSON and return consistent HTTP errors.

## Conclusion

`EnterpriseJavaBasics` preserves the learning progression across Labs 1–7. Labs 2 and 3 demonstrate the initial in-memory implementations. The final `student-management` project consolidates Labs 4–7 into a maintainable controller–service–repository application backed exclusively by PostgreSQL.
<<<<<<< HEAD


## Push to GitHub

Create an empty GitHub repository named `EnterpriseJavaBasics` (do not initialize it with a second README), then run these commands from this repository root. Replace the remote URL with your own repository URL.

```powershell
git init -b main
git add .
git status
git commit -m "Implement Enterprise Java Labs 1-7"
git remote add origin https://github.com/<YOUR_GITHUB_USERNAME>/EnterpriseJavaBasics.git
git push -u origin main
```

Before committing, inspect `git status` and confirm that no real `.env` file, database password, IDE secrets or build output is staged. The included GitHub Actions workflow verifies each of the three Maven projects independently on Java 17.
=======
>>>>>>> 8ab0b5221f7a8a6442fa51c78c6bd1fb8aa2b9eb
