# Lab 3 — Student CRUD with ArrayList

Standalone Spring Boot application extending Lab 2 with POST, GET, PUT and DELETE endpoints. Students are stored in an `ArrayList<Student>` and are initialized with Rahul, Priya and Amit.

## Run

```powershell
mvn clean test
mvn spring-boot:run
```

The application listens on port `8081`.

## Endpoints

| Method | Endpoint | Description |
|---|---|---|
| GET | `/hello` | Greeting |
| GET | `/student` | Predefined Rahul student |
| GET | `/students` | Read all students |
| POST | `/students` | Create student |
| GET | `/students/{id}` | Read one student |
| PUT | `/students/{id}` | Update student |
| DELETE | `/students/{id}` | Delete with an Iterator |

The implementation uses `@RequestBody`, `@PathVariable` and `ResponseEntity`. It returns HTTP 404 for missing IDs and HTTP 409 for duplicate IDs.

**Persistence note:** This lab intentionally uses an in-memory `ArrayList`. Changes are lost when the application stops or restarts. PostgreSQL persistence is introduced only in the cumulative `student-management` application for Labs 4–7.
