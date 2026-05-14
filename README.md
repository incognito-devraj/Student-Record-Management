# Student Record Management System

This is a console-based Student Record Management System built with Java 17, MySQL 8, and JDBC. It allows users to add, view, update, delete, and search student records through a simple menu-driven interface. Each database operation uses prepared statements and independent JDBC connections for safe, clean, and reliable record management.

## Tech Stack

- Java 17
- MySQL 8
- JDBC

## Prerequisites

- Java JDK 17+
- MySQL 8
- `mysql-connector-j` JAR

## Step 1: Database Setup

Run the SQL script in `database/schema.sql`:

```sql
SOURCE database/schema.sql;
```

## Step 2: Download mysql-connector-j JAR

Download the MySQL JDBC driver JAR and place it inside the `lib/` folder:

```text
lib/mysql-connector-j-<version>.jar
```

## Step 3: Compile Command

### Windows

```bat
javac -cp "lib/*" -d out src\database\DBConnection.java src\model\Student.java src\util\Validator.java src\service\StudentService.java src\Main.java
```

### Mac / Linux

```bash
javac -cp "lib/*" -d out $(find src -name "*.java")
```

## Step 4: Run Command

### Windows

```bat
java -cp "out;lib/*" Main
```

### Mac / Linux

```bash
java -cp "out:lib/*" Main
```

## Features

- Add a new student record with input validation
- View all student records in a formatted table
- Update an existing student by ID
- Delete a student by ID
- Search for a student by ID or partial name

## Folder Structure

```text
Student Record Management/
|-- database/
|   `-- schema.sql
|-- lib/
|   `-- mysql-connector-j.jar
|-- out/
|-- src/
|   |-- database/
|   |   `-- DBConnection.java
|   |-- model/
|   |   `-- Student.java
|   |-- service/
|   |   `-- StudentService.java
|   |-- util/
|   |   `-- Validator.java
|   `-- Main.java
`-- README.md
```
