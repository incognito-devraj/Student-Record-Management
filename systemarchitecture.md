# System Architecture
## Student Record Management System (SRMS)

---

## 1. Architecture Style

**Layered (N-Tier) Architecture** — Each layer has a single responsibility and communicates only with the layer directly below it.

```
┌─────────────────────────────┐
│        USER (Terminal)       │
└────────────┬────────────────┘
             │ keyboard input / console output
┌────────────▼────────────────┐
│         Main.java            │  ← Entry point, menu loop, user I/O
└────────────┬────────────────┘
             │ method calls
┌────────────▼────────────────┐
│      StudentService.java     │  ← Business logic, validation, CRUD ops
└────────────┬────────────────┘
             │ SQL queries via JDBC
┌────────────▼────────────────┐
│       DBConnection.java      │  ← Opens/closes MySQL connection
└────────────┬────────────────┘
             │ TCP / JDBC Driver
┌────────────▼────────────────┐
│        MySQL Database        │  ← studentdb → students table
└─────────────────────────────┘
```

---

## 2. Folder & File Structure

```
StudentRecordSystem/
│
├── src/
│   ├── model/
│   │     └── Student.java          # Data model (POJO)
│   ├── service/
│   │     └── StudentService.java   # CRUD logic
│   ├── database/
│   │     └── DBConnection.java     # JDBC connection manager
│   ├── util/
│   │     └── Validator.java        # Input validation helpers
│   └── Main.java                   # Entry point + menu
│
├── lib/
│   └── mysql-connector-j-x.x.x.jar
│
├── database/
│   └── schema.sql                  # DB setup script
│
└── README.md
```

---

## 3. Layer Responsibilities

### Layer 1 — Main.java (Controller / UI)
- Starts the application
- Runs the main menu loop using a `while(true)` + `switch`
- Reads user input via `Scanner`
- Calls `StudentService` methods based on user choice
- Never talks to the database directly

### Layer 2 — StudentService.java (Service / Business Logic)
- Receives data from `Main.java`
- Validates data using `Validator.java`
- Builds and executes SQL queries via JDBC
- Uses `PreparedStatement` for all queries (prevents SQL injection)
- Returns results or success/failure signals to `Main.java`

### Layer 3 — DBConnection.java (Data Access)
- Holds DB URL, username, password as constants
- `getConnection()` returns a live `Connection` object
- Connection is opened per operation (simple approach for v1)

### Layer 4 — Student.java (Model)
- Plain Java class (POJO)
- Fields: `int id`, `String name`, `int age`, `String course`
- Getters and setters
- Used to pass data cleanly between layers

### Layer 5 — Validator.java (Utility)
- `isValidId(int id)` — must be positive
- `isValidName(String name)` — not empty, only letters/spaces
- `isValidAge(int age)` — between 5 and 100
- `isValidCourse(String course)` — not empty

---

## 4. Database Design

**Database:** `studentdb`
**Table:** `students`

```sql
CREATE TABLE students (
    id     INT PRIMARY KEY,
    name   VARCHAR(50) NOT NULL,
    age    INT NOT NULL,
    course VARCHAR(50) NOT NULL
);
```

**Constraints:**
- `id` is user-provided (not auto-increment in v1)
- `id` is PRIMARY KEY → duplicate IDs automatically rejected by MySQL

---

## 5. JDBC Connection Flow

```
DBConnection.getConnection()
    │
    ├── Class.forName("com.mysql.cj.jdbc.Driver")   ← load driver
    ├── DriverManager.getConnection(URL, USER, PASS) ← open connection
    └── returns Connection object
              │
              ▼
    PreparedStatement ps = conn.prepareStatement(sql)
    ps.setInt(1, student.getId())
    ps.setString(2, student.getName())
    ps.executeUpdate()  ← for INSERT / UPDATE / DELETE
    ps.executeQuery()   ← for SELECT
```

---

## 6. Data Flow — Add Student

```
User types: 1 (Add Student)
    │
Main.java reads: id, name, age, course via Scanner
    │
Student object created with those values
    │
Validator checks all fields → passes
    │
StudentService.addStudent(student) called
    │
PreparedStatement: INSERT INTO students VALUES(?, ?, ?, ?)
    │
MySQL stores the row
    │
"Student added successfully!" printed to console
```

---

## 7. Data Flow — View All Students

```
User types: 2 (View Students)
    │
StudentService.viewAllStudents() called
    │
SELECT * FROM students executed
    │
ResultSet iterated row by row
    │
Each row printed as formatted table row in console
```

---

## 8. Error Handling Strategy

| Scenario | Handling |
|---|---|
| DB connection fails | `catch(SQLException)` → print error message, exit gracefully |
| Duplicate student ID | MySQL throws exception → caught, "ID already exists" shown |
| Invalid menu choice | `default` in switch → "Invalid option, try again" |
| Empty input | `Validator` catches → "Field cannot be empty" |
| Student not found | Check `ResultSet.next()` → "No student found with that ID" |
| Non-numeric input where number expected | `InputMismatchException` → "Please enter a valid number" |

---

## 9. Technology Versions

| Component | Version |
|---|---|
| Java | 17 (LTS) or 21 (LTS) |
| MySQL | 8.0+ |
| mysql-connector-j | 8.x (matches MySQL version) |
| IDE | IntelliJ IDEA / VS Code |
| Build | Manual (`javac`) or Maven (optional) |
