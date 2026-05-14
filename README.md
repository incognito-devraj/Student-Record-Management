<div align="center">
```
                            ███████╗██████╗ ███╗   ███╗███████╗
                            ██╔════╝██╔══██╗████╗ ████║██╔════╝
                            ███████╗██████╔╝██╔████╔██║███████╗
                            ╚════██║██╔══██╗██║╚██╔╝██║╚════██║
                            ███████║██║  ██║██║ ╚═╝ ██║███████║
                            ╚══════╝╚═╝  ╚═╝╚═╝     ╚═╝╚══════╝
```

# Student Record Management System

**A console-based backend application to manage student records using Java, JDBC, and MySQL.**

[![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=openjdk&logoColor=white)](https://openjdk.org/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com/)
[![JDBC](https://img.shields.io/badge/JDBC-Driver-green?style=for-the-badge&logo=java&logoColor=white)](https://dev.mysql.com/downloads/connector/j/)
[![License](https://img.shields.io/badge/License-MIT-yellow?style=for-the-badge)](LICENSE)
[![Status](https://img.shields.io/badge/Status-Active-brightgreen?style=for-the-badge)]()

</div>

---

## 📌 What Is This?

A fully functional **CRUD backend system** built from scratch using core Java and MySQL.
No frameworks. No shortcuts. Just clean layered architecture, JDBC connectivity, and solid OOP.

Built to understand how real backend systems work under the hood — how data flows from user input → service logic → database → back to the screen.

---

## ⚡ Features

| # | Feature | Description |
|---|---------|-------------|
| 1 | ➕ Add Student | Store student ID, name, age, and course |
| 2 | 📋 View All | Display all student records in a formatted table |
| 3 | ✏️ Update | Modify name, age, or course by student ID |
| 4 | 🗑️ Delete | Permanently remove a student record |
| 5 | 🔍 Search | Find students by ID or partial name match |

---

## 🛠️ Tech Stack

```
Language      →   Java 17
Database      →   MySQL 8.0
Connectivity  →   JDBC (mysql-connector-j)
IDE           →   IntelliJ IDEA / VS Code
Version Ctrl  →   Git & GitHub
```

---

## 🏗️ Project Structure

```
StudentRecordSystem/
│
├── src/
│   ├── model/
│   │     └── Student.java          # Data model (POJO)
│   ├── service/
│   │     └── StudentService.java   # CRUD business logic
│   ├── database/
│   │     └── DBConnection.java     # JDBC connection manager
│   ├── util/
│   │     └── Validator.java        # Input validation
│   └── Main.java                   # Entry point + menu loop
│
├── lib/
│   └── mysql-connector-j.jar       # JDBC driver (add manually)
│
├── database/
│   └── schema.sql                  # Database setup script
│
└── README.md
```

---

## 🗄️ Database Schema

```sql
CREATE DATABASE IF NOT EXISTS studentdb;
USE studentdb;

CREATE TABLE students (
    id     INT PRIMARY KEY,
    name   VARCHAR(50) NOT NULL,
    age    INT         NOT NULL,
    course VARCHAR(50) NOT NULL
);
```

---

## 🚀 Getting Started

### Prerequisites

Make sure you have these installed:

- [Java JDK 17+](https://openjdk.org/) → verify: `java -version`
- [MySQL 8.0+](https://www.mysql.com/) → verify: `mysql -u root -p`
- [mysql-connector-j JAR](https://dev.mysql.com/downloads/connector/j/) → place in `lib/` folder

---

### Step 1 — Clone the Repository

```bash
git clone https://github.com/incognito-devraj/student-record-system.git
cd student-record-system
```

---

### Step 2 — Set Up the Database

```bash
mysql -u root -p < database/schema.sql
```

Or open MySQL Workbench and run `database/schema.sql` manually.

---

### Step 3 — Add the JDBC Driver

Download `mysql-connector-j-x.x.x.jar` from [here](https://dev.mysql.com/downloads/connector/j/)
and place it inside the `lib/` folder.

---

### Step 4 — Compile

**Mac / Linux:**
```bash
javac -cp lib/mysql-connector-j.jar -d out $(find src -name "*.java")
```

**Windows:**
```bash
javac -cp lib\mysql-connector-j.jar -d out src\database\DBConnection.java src\model\Student.java src\util\Validator.java src\service\StudentService.java src\Main.java
```

---

### Step 5 — Run

**Mac / Linux:**
```bash
java -cp out:lib/mysql-connector-j.jar Main
```

**Windows:**
```bash
java -cp out;lib\mysql-connector-j.jar Main
```

---

## 🖥️ Console Preview

```
=============================
  STUDENT RECORD MANAGEMENT
=============================
 1. Add Student
 2. View All Students
 3. Update Student
 4. Delete Student
 5. Search Student
 0. Exit
=============================
Enter choice: 2

------------------------------------------
 ID  | Name               | Age | Course
------------------------------------------
 1   | Rahul Sharma        | 20  | CSE
 2   | Priya Mehta         | 21  | IT
 3   | Arjun Nair          | 19  | ECE
------------------------------------------
```

---

## 🧱 Architecture

```
User Input (Console)
        ↓
   Main.java          ← menu loop, Scanner, input handling
        ↓
StudentService.java   ← business logic, validation, CRUD ops
        ↓
DBConnection.java     ← JDBC connection to MySQL
        ↓
  MySQL Database      ← studentdb → students table
```

---

## 🧠 Concepts Demonstrated

- ✅ Object-Oriented Programming (Encapsulation, Abstraction)
- ✅ Layered / N-Tier Architecture
- ✅ JDBC with PreparedStatements (SQL injection safe)
- ✅ Exception Handling (SQLException, InputMismatchException)
- ✅ Input Validation (custom Validator utility)
- ✅ Modular code design (separation of concerns)

---

## 🔮 Future Scope

- [ ] JavaFX desktop GUI
- [ ] Spring Boot REST API
- [ ] React frontend
- [ ] Role-based access (Admin / Teacher)
- [ ] Export records to CSV

---

## 👤 Author

**Devraj Mukherjee**
[![GitHub](https://img.shields.io/badge/GitHub-incognito--devraj-black?style=flat-square&logo=github)](https://github.com/incognito-devraj)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-devrajom-blue?style=flat-square&logo=linkedin)](https://www.linkedin.com/in/devrajom/)

---

<div align="center">

⭐ **Star this repo if you found it useful** ⭐

</div>