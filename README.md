# EnterpriseJavaBasics
Implementation of Basic Enterprise Java Logics.

# Lab-1 Client-Server Dependencies
Download these 2 files.
1. Install IntelliJ IDE.
2. Integrate JDK after installing by adding it to environment.

# Lab-2 Implementation of student-management Project.
Student Management System

A simple Student Management REST API built using **Java and Spring Boot** as part of Lab-2.

## Project Details

| Property | Details |
|---|---|
| Project Name | student-management |
| Language | Java |
| Build Tool | Maven |
| Framework | Spring Boot |
| Package Name | `com.example.ucs067.studentmanagement` |
| Server Port | 8081 |

## Technologies & Dependencies

- Java
- Spring Boot
- Spring Web
- Spring Boot DevTools
- Maven

## Project Structure

```text
student-management/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/example/ucs067/studentmanagement/
│       │       ├── controller/
│       │       │   └── HelloController.java
│       │       ├── Student.java
│       │       └── StudentManagementApplication.java
│       └── resources/
│           └── application.properties
├── .gitignore
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```

## Features

The application provides three REST API endpoints:

### 1. Hello Endpoint

**Method:** GET

**URL:** `/hello`

Returns a welcome message.

**Response:**
```text
Hello, Spring Boot!
```

### 2. Get Single Student

**Method:** GET

**URL:** `/student`

Returns details of a single student.

**Response:**
```json
{
  "id": 1,
  "name": "Rahul",
  "email": "rahul@example.com"
}
```

### 3. Get All Students

**Method:** GET

**URL:** `/students`

Returns a list of three students.

**Response:**
```json
[
  {
    "id": 1,
    "name": "Rahul",
    "email": "rahul@example.com"
  },
  {
    "id": 2,
    "name": "Priya",
    "email": "priya@example.com"
  },
  {
    "id": 3,
    "name": "Amit",
    "email": "amit@example.com"
  }
]
```

## Running the Project

### Prerequisites

- Java JDK 17 or compatible version
- Maven (optional, Maven Wrapper is included)

### 1. Clone the Repository

```bash
git clone https://github.com/YOUR-USERNAME/student-management.git
```

### 2. Navigate to the Project Directory

```bash
cd student-management
```

### 3. Run the Application

**Windows:**
```powershell
.\mvnw.cmd spring-boot:run
```

**Linux / macOS:**
```bash
./mvnw spring-boot:run
```

### 4. Access the Application

The application runs at:

`http://localhost:8081`

## API Endpoint Summary

| HTTP Method | Endpoint | Description |
|---|---|---|
| GET | `/hello` | Returns greeting message |
| GET | `/student` | Returns a single student |
| GET | `/students` | Returns all students |

## Testing

The endpoints can be tested using:

- Web Browser
- Postman
- Thunder Client

## Implementation Concepts

- Spring Boot application setup
- REST API development
- HTTP GET requests
- Spring MVC REST controllers
- Java classes and objects
- Java Lists
- JSON responses

---

## Lab-3: Implementation of CRUD Operations

This experiment extends the existing Student Management REST API by implementing CRUD (Create, Read, Update, Delete) operations.

### Objective

To implement REST API endpoints that allow users to create, retrieve, update, and delete student records using HTTP requests and JSON data.

### Implementation

The application uses an in-memory `ArrayList<Student>` to store student records temporarily.

The following operations are implemented:

| Operation | HTTP Method | Endpoint | Description |
|---|---|---|---|
| Create | POST | `/students` | Add a new student |
| Read | GET | `/students` | Retrieve all students |
| Read | GET | `/students/{id}` | Retrieve a student by ID |
| Update | PUT | `/students/{id}` | Update student details |
| Delete | DELETE | `/students/{id}` | Delete a student by ID |

### JSON Request Example

**Add Student (POST)**

```json
{
  "id": 4,
  "name": "Neha",
  "email": "neha@example.com"
}
```

**Update Student (PUT)**

```json
{
  "id": 2,
  "name": "Priya Sharma",
  "email": "priya.sharma@example.com"
}
```

### Concepts Implemented

- Java ArrayList and List
- In-memory data storage
- HTTP POST, GET, PUT and DELETE requests
- `@PostMapping`, `@GetMapping`, `@PutMapping`, `@DeleteMapping`
- `@RequestBody` for receiving JSON data
- `@PathVariable` for extracting URL parameters
- `ResponseEntity` for HTTP responses
- Iterator for safely removing elements
- HTTP 404 Not Found error handling

### Error Handling

The application returns HTTP `404 Not Found` when a requested student ID does not exist.

### Testing

All CRUD operations were tested using Postman and cURL.

### Storage Limitation

Student records are stored in an in-memory ArrayList. Changes remain available while the application is running but are lost when the application restarts.

Database integration is not implemented in this experiment.

### Result

Successfully implemented and tested CRUD operations for the Student Management REST API using Spring Boot.




 
