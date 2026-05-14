# Product Requirements Document (PRD)
## Student Record Management System (SRMS)

---

## 1. Overview

**Product Name:** Student Record Management System (SRMS)
**Type:** Console-based Backend Application
**Stack:** Java + MySQL + JDBC
**Goal:** Build a working CRUD system to manage student records from the terminal, deployable and runnable on any machine with Java + MySQL installed.

---

## 2. Problem Statement

Managing student records manually (spreadsheets, paper) becomes error-prone and slow as student count grows. This system replaces that with a simple, fast, software-based solution that stores data persistently in a relational database.

---

## 3. Target Users

- Developers learning Java + JDBC + MySQL
- Students building portfolio projects
- Small institutions needing a lightweight record system

---

## 4. Functional Requirements

### FR-01 — Add Student
- User enters: Student ID, Name, Age, Course
- System validates inputs (no empty fields, valid age range, no duplicate ID)
- Record is inserted into the database
- Confirmation message displayed on success

### FR-02 — View All Students
- System fetches and displays all records from the database
- Displayed in a formatted table in the console
- Shows message if no records exist

### FR-03 — Update Student
- User provides Student ID to locate the record
- User can modify: Name, Age, Course
- System validates new values
- Record is updated in the database

### FR-04 — Delete Student
- User provides Student ID
- System confirms existence before deletion
- Record is permanently removed
- Confirmation message displayed

### FR-05 — Search Student
- User can search by Student ID or Name
- Partial name search supported (LIKE query)
- Matching records displayed in formatted output
- "Not found" message if no match

### FR-06 — Console Menu Navigation
- Main menu loops until user chooses to exit
- Invalid menu choices are handled gracefully
- Clean exit option (option 6 or 0)

---

## 5. Non-Functional Requirements

| Requirement | Detail |
|---|---|
| Language | Java 17+ |
| Database | MySQL 8.x |
| Connectivity | JDBC (mysql-connector-j) |
| Input Method | Scanner (console) |
| Error Handling | Try-catch for DB errors + input validation |
| Portability | Runs on any OS with Java + MySQL |
| Code Style | Modular, OOP, clean naming |

---

## 6. Out of Scope (v1)

- GUI (Swing / JavaFX)
- REST API or web interface
- Authentication / login system
- Multi-user concurrent access
- Report generation / export to PDF or Excel

---

## 7. Future Enhancements (v2+)

- JavaFX desktop UI
- Spring Boot REST API
- React frontend
- Role-based access (Admin / Teacher / Student)
- Export records to CSV

---

## 8. Acceptance Criteria

- [ ] All 5 CRUD operations work end-to-end
- [ ] Data persists after application restart
- [ ] Invalid inputs do not crash the application
- [ ] Duplicate Student IDs are rejected
- [ ] Console output is clean and readable
