CREATE DATABASE IF NOT EXISTS studentdb;
USE studentdb;
DROP TABLE IF EXISTS students;
CREATE TABLE students (
    id     INT PRIMARY KEY,
    name   VARCHAR(50) NOT NULL,
    age    INT NOT NULL,
    course VARCHAR(50) NOT NULL
);
