# Project Plan
## Student Record Management System (SRMS)

---

## Timeline Overview

Total estimated time: **6–8 hours** (spread over 2–3 days for learners)

| Phase | What You Build | Est. Time |
|---|---|---|
| Phase 1 | Environment Setup | 30 min |
| Phase 2 | Database Setup | 20 min |
| Phase 3 | Project Structure + Model | 30 min |
| Phase 4 | DB Connection Layer | 30 min |
| Phase 5 | Service Layer (CRUD) | 2 hrs |
| Phase 6 | Main Menu + Controller | 1 hr |
| Phase 7 | Validator Utility | 30 min |
| Phase 8 | Testing & Bug Fixes | 1 hr |
| Phase 9 | README + Cleanup | 30 min |

---

## Phase 1 — Environment Setup

**Goal:** Have Java + MySQL + IDE working on your machine.

Steps:
1. Install Java JDK 17 or 21 → verify with `java -version`
2. Install MySQL 8.x → verify with `mysql -u root -p`
3. Download `mysql-connector-j` JAR from [mysql.com](https://dev.mysql.com/downloads/connector/j/)
4. Set up your project folder structure as defined in architecture
5. Add the JAR to your project's classpath (in IntelliJ: File → Project Structure → Libraries → Add JAR)

**Done when:** You can run a blank `Main.java` with `System.out.println("Hello")` successfully.

---

## Phase 2 — Database Setup

**Goal:** MySQL database and table created and ready.

Steps:
1. Open MySQL Workbench or terminal
2. Run `schema.sql`:
   ```sql
   CREATE DATABASE IF NOT EXISTS studentdb;
   USE studentdb;
   CREATE TABLE IF NOT EXISTS students (
       id     INT PRIMARY KEY,
       name   VARCHAR(50) NOT NULL,
       age    INT NOT NULL,
       course VARCHAR(50) NOT NULL
   );
   ```
3. Verify with `SHOW TABLES;` and `DESCRIBE students;`

**Done when:** Table exists and you can manually INSERT + SELECT a test row.

---

## Phase 3 — Project Structure + Student Model

**Goal:** Set up all files and build `Student.java`.

Steps:
1. Create all folders: `model/`, `service/`, `database/`, `util/`
2. Create empty files: `Student.java`, `StudentService.java`, `DBConnection.java`, `Validator.java`, `Main.java`
3. Build `Student.java` — fields, constructor, getters, setters, `toString()`

**Done when:** `Student s = new Student(1, "Rahul", 20, "CSE");` compiles and prints correctly.

---

## Phase 4 — DB Connection Layer

**Goal:** Java can successfully connect to MySQL.

Steps:
1. Write `DBConnection.java` with your DB URL, user, password
2. Write `getConnection()` method using `DriverManager`
3. Test the connection from `Main.java` — print "Connected!" and close

**Done when:** Running the app prints "Connected to MySQL successfully!" without errors.

---

## Phase 5 — Service Layer (CRUD Methods)

**Goal:** All 5 CRUD operations work.

Build in this order:

**5a. addStudent(Student s)**
- PreparedStatement INSERT
- Test: add one student, check in MySQL Workbench

**5b. viewAllStudents()**
- SELECT * query, iterate ResultSet, print rows
- Test: should show the student you just added

**5c. updateStudent(int id)**
- Ask what to update, run UPDATE query
- Test: change a student's course, verify in DB

**5d. deleteStudent(int id)**
- Check if ID exists first, then DELETE
- Test: delete a student, run view to confirm

**5e. searchStudent(String keyword)**
- Search by ID or name using LIKE
- Test: search by partial name, verify matches shown

**Done when:** All 5 methods work individually when called from `Main.java` with hardcoded test data.

---

## Phase 6 — Main Menu + Controller

**Goal:** Interactive console menu that ties everything together.

Steps:
1. Write the menu print function
2. Wrap in a `while(true)` loop
3. Use `Scanner` to read user choices
4. Use `switch` to call the right `StudentService` method
5. Handle exit option (break the loop)
6. Handle invalid inputs gracefully

**Done when:** You can run the app and use all 5 features through the menu without crashing.

---

## Phase 7 — Validator Utility

**Goal:** Prevent bad data from reaching the database.

Steps:
1. `isValidAge(int age)` — 5 to 100
2. `isValidName(String name)` — not empty, no numbers
3. `isValidId(int id)` — positive integer
4. `isValidCourse(String course)` — not empty
5. Integrate validator calls inside `StudentService` methods before SQL execution

**Done when:** Entering age = -1 or empty name shows a clear error without crashing.

---

## Phase 8 — Testing & Bug Fixes

Test every scenario:

| Test Case | Expected Result |
|---|---|
| Add student with valid data | Success message, record in DB |
| Add student with duplicate ID | "ID already exists" message |
| Add student with empty name | Validation error |
| View when no records exist | "No records found" message |
| Update non-existent ID | "Student not found" message |
| Delete non-existent ID | "Student not found" message |
| Search by partial name | All matching records shown |
| Enter letters when number expected | "Invalid input" message |
| Enter menu option 9 (invalid) | "Invalid choice" message |

---

## Phase 9 — README + Final Cleanup

Write `README.md` with:
- Project description
- Tech stack
- How to set up (DB + classpath)
- How to run
- Features list
- Screenshots (optional)

Final cleanup:
- Remove test print statements
- Consistent formatting and indentation
- Add comments to non-obvious code sections
- Push to GitHub

---

## Risk & Mitigation

| Risk | Mitigation |
|---|---|
| JDBC driver not found at runtime | Verify JAR is on classpath before running |
| MySQL connection refused | Check MySQL service is running, port 3306 open |
| SQL exceptions on duplicate ID | Catch and show user-friendly message |
| `InputMismatchException` on Scanner | Wrap in try-catch, call `scanner.nextLine()` to clear buffer |
