# Lab 2 — Basic Student Management REST API

Standalone Spring Boot application for the initial GET-only student API. It uses an in-memory Java `List` and does not require PostgreSQL.

## Run

```powershell
mvn clean test
mvn spring-boot:run
```

The application listens on port `8081`.

## Endpoints

| Method | Endpoint | Result |
|---|---|---|
| GET | `/hello` | `Hello, Spring Boot!` |
| GET | `/student` | Rahul as JSON |
| GET | `/students` | Rahul, Priya and Amit as JSON |

This folder is the historical Lab 2 version. It is independent of the final PostgreSQL-backed application in `student-management`.
