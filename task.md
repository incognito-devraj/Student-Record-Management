# Task List
## Student Record Management System (SRMS)

Check off tasks as you complete them. Follow the order — each phase builds on the previous.

---

## Phase 1 — Environment Setup

- [ ] Install Java JDK 17 or 21
- [ ] Run `java -version` in terminal to confirm
- [ ] Install MySQL 8.x
- [ ] Run `mysql -u root -p` to confirm MySQL works
- [ ] Download `mysql-connector-j` JAR file
- [ ] Create project folder `StudentRecordSystem/`
- [ ] Create subfolder structure: `src/model/`, `src/service/`, `src/database/`, `src/util/`, `lib/`, `database/`
- [ ] Place JAR in `lib/` folder
- [ ] Add JAR to project classpath in IDE
- [ ] Create and run a blank `Main.java` with `System.out.println("Setup done!")`

---

## Phase 2 — Database Setup

- [ ] Open MySQL terminal or Workbench
- [ ] Create `database/schema.sql` with CREATE DATABASE + CREATE TABLE statements
- [ ] Execute `schema.sql`
- [ ] Verify with `SHOW TABLES;`
- [ ] Verify with `DESCRIBE students;`
- [ ] Manually insert one test row and SELECT it to confirm table works

---

## Phase 3 — Student Model

- [ ] Create `src/model/Student.java`
- [ ] Add fields: `int id`, `String name`, `int age`, `String course`
- [ ] Add constructor: `Student(int id, String name, int age, String course)`
- [ ] Add no-arg constructor
- [ ] Add getters for all fields
- [ ] Add setters for all fields
- [ ] Add `toString()` method for easy printing
- [ ] Test: instantiate a `Student` in `Main.java` and print it

---

## Phase 4 — DB Connection

- [ ] Create `src/database/DBConnection.java`
- [ ] Define constants: `URL`, `USER`, `PASSWORD`
  - URL format: `jdbc:mysql://localhost:3306/studentdb`
- [ ] Write `public static Connection getConnection()` method
- [ ] Load driver: `Class.forName("com.mysql.cj.jdbc.Driver")`
- [ ] Use `DriverManager.getConnection(URL, USER, PASSWORD)`
- [ ] Wrap in try-catch, print error if connection fails
- [ ] Test from `Main.java`: call `getConnection()`, print "Connected!" on success

---

## Phase 5 — Service Layer

### 5a — Add Student
- [ ] Create `src/service/StudentService.java`
- [ ] Write `public void addStudent(Student s)` method
- [ ] Write INSERT PreparedStatement
- [ ] Bind parameters: id, name, age, course
- [ ] Execute with `executeUpdate()`
- [ ] Print success/failure message
- [ ] Close connection after operation
- [ ] Test: hardcode a student in `Main.java` and call `addStudent()`

### 5b — View All Students
- [ ] Write `public void viewAllStudents()` method
- [ ] Write SELECT * PreparedStatement
- [ ] Iterate ResultSet with `while(rs.next())`
- [ ] Print each row in a formatted layout
- [ ] Handle empty result: print "No students found"
- [ ] Test: call from `Main.java`, confirm your test student appears

### 5c — Update Student
- [ ] Write `public void updateStudent(int id, String name, int age, String course)` method
- [ ] Check if student exists first with SELECT
- [ ] Write UPDATE PreparedStatement
- [ ] Bind new values + ID
- [ ] Execute with `executeUpdate()`
- [ ] Print success/failure message
- [ ] Test: update the test student's course, verify in DB

### 5d — Delete Student
- [ ] Write `public void deleteStudent(int id)` method
- [ ] Check if student exists first
- [ ] Write DELETE PreparedStatement
- [ ] Execute with `executeUpdate()`
- [ ] Print confirmation or "not found" message
- [ ] Test: delete the test student, confirm gone from DB

### 5e — Search Student
- [ ] Write `public void searchStudent(String keyword)` method
- [ ] Write SELECT query with WHERE id = ? OR name LIKE ?
- [ ] Handle both numeric (search by ID) and string (search by name) input
- [ ] Iterate and print results
- [ ] Handle no results found
- [ ] Test: search by partial name, confirm matches shown

---

## Phase 6 — Main Menu

- [ ] Write `printMenu()` method in `Main.java`
- [ ] Add while loop with `Scanner` reading user choice
- [ ] Add `switch` statement with cases 1–5 + exit
- [ ] Case 1 (Add): read id, name, age, course → call `addStudent()`
- [ ] Case 2 (View): call `viewAllStudents()`
- [ ] Case 3 (Update): read id, new values → call `updateStudent()`
- [ ] Case 4 (Delete): read id → call `deleteStudent()`
- [ ] Case 5 (Search): read keyword → call `searchStudent()`
- [ ] Case 0 or 6 (Exit): break loop + print "Goodbye!"
- [ ] Default case: print "Invalid choice, try again"
- [ ] Test: run app, navigate all menu options

---

## Phase 7 — Validator

- [ ] Create `src/util/Validator.java`
- [ ] Write `public static boolean isValidId(int id)` — id > 0
- [ ] Write `public static boolean isValidName(String name)` — not null, not empty, only letters + spaces
- [ ] Write `public static boolean isValidAge(int age)` — between 5 and 100
- [ ] Write `public static boolean isValidCourse(String course)` — not null, not empty
- [ ] Integrate: call validators in `StudentService` before running SQL
- [ ] Integrate: handle `InputMismatchException` in `Main.java` when reading integers
- [ ] Test: try entering age = -5, empty name, confirm errors shown

---

## Phase 8 — Testing

- [ ] Test: add student with all valid inputs → success
- [ ] Test: add student with same ID → duplicate error
- [ ] Test: add student with empty name → validation error
- [ ] Test: add student with age = 0 → validation error
- [ ] Test: view students when table is empty → "No records" message
- [ ] Test: view students after adding 3 → all 3 shown
- [ ] Test: update student that exists → updated in DB
- [ ] Test: update student that doesn't exist → "Not found" message
- [ ] Test: delete student that exists → removed from DB
- [ ] Test: delete student that doesn't exist → "Not found" message
- [ ] Test: search by exact name → found
- [ ] Test: search by partial name → all matches shown
- [ ] Test: search by ID → correct student shown
- [ ] Test: search with no match → "Not found"
- [ ] Test: type letters when menu expects a number → no crash
- [ ] Test: choose invalid menu option → "Invalid choice"

---

## Phase 9 — Cleanup & README

- [ ] Remove all debug `System.out.println` test statements
- [ ] Add comments above each class and method
- [ ] Make sure all files have consistent indentation
- [ ] Write `README.md`:
  - [ ] Project title and description
  - [ ] Tech stack section
  - [ ] Setup instructions (MySQL + classpath)
  - [ ] How to run
  - [ ] Features list
- [ ] Initialize git: `git init`
- [ ] Add `.gitignore` (exclude `.class` files, IDE config)
- [ ] Commit all files: `git commit -m "Initial commit: Student Record Management System"`
- [ ] Push to GitHub

---

## Quick Reference — Key Files

| File | Your Job |
|---|---|
| `Main.java` | Menu loop, Scanner input, call service methods |
| `Student.java` | Fields, constructor, getters/setters, toString |
| `StudentService.java` | All 5 CRUD methods with SQL |
| `DBConnection.java` | `getConnection()` using JDBC |
| `Validator.java` | Input checks before SQL runs |
| `schema.sql` | CREATE DATABASE + CREATE TABLE |
| `README.md` | Setup and usage guide |
