package service;

import database.DBConnection;
import model.Student;
import util.Validator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentService {
    public void addStudent(Student s) {
        if (!Validator.isValidId(s.getId())) {
            System.out.println("Error: Invalid student ID. ID must be greater than 0.");
            return;
        }
        if (!Validator.isValidName(s.getName())) {
            System.out.println("Error: Invalid name. Name must contain only letters and spaces.");
            return;
        }
        if (!Validator.isValidAge(s.getAge())) {
            System.out.println("Error: Invalid age. Age must be between 5 and 100.");
            return;
        }
        if (!Validator.isValidCourse(s.getCourse())) {
            System.out.println("Error: Invalid course. Course cannot be blank.");
            return;
        }

        String sql = "INSERT INTO students(id, name, age, course) VALUES(?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, s.getId());
            preparedStatement.setString(2, s.getName());
            preparedStatement.setInt(3, s.getAge());
            preparedStatement.setString(4, s.getCourse());

            preparedStatement.executeUpdate();
            System.out.println("Student added successfully!");
        } catch (SQLException e) {
            if (e.getMessage() != null && e.getMessage().contains("Duplicate entry")) {
                System.out.println("Error: Student ID " + s.getId() + " already exists.");
            } else {
                System.out.println("Database error: " + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void viewAllStudents() {
        String sql = "SELECT * FROM students ORDER BY id ASC";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            if (!resultSet.isBeforeFirst()) {
                System.out.println("No student records found.");
                return;
            }

            System.out.println("------------------------------------------");
            System.out.println(" ID  | Name               | Age | Course");
            System.out.println("------------------------------------------");

            while (resultSet.next()) {
                System.out.printf(" %-3d | %-18s | %-3d | %-20s%n",
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("age"),
                        resultSet.getString("course"));
            }

            System.out.println("------------------------------------------");
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void updateStudent(int id, String name, int age, String course) {
        String checkSql = "SELECT id FROM students WHERE id = ?";
        String updateSql = "UPDATE students SET name=?, age=?, course=? WHERE id=?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement checkStatement = connection.prepareStatement(checkSql)) {

            checkStatement.setInt(1, id);

            try (ResultSet resultSet = checkStatement.executeQuery()) {
                if (!resultSet.next()) {
                    System.out.println("Student with ID " + id + " not found.");
                    return;
                }
            }

            if (!Validator.isValidName(name)) {
                System.out.println("Error: Invalid name. Name must contain only letters and spaces.");
                return;
            }
            if (!Validator.isValidAge(age)) {
                System.out.println("Error: Invalid age. Age must be between 5 and 100.");
                return;
            }
            if (!Validator.isValidCourse(course)) {
                System.out.println("Error: Invalid course. Course cannot be blank.");
                return;
            }

            try (PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {
                updateStatement.setString(1, name);
                updateStatement.setInt(2, age);
                updateStatement.setString(3, course);
                updateStatement.setInt(4, id);
                updateStatement.executeUpdate();
                System.out.println("Student ID " + id + " updated successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void deleteStudent(int id) {
        String checkSql = "SELECT id FROM students WHERE id = ?";
        String deleteSql = "DELETE FROM students WHERE id=?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement checkStatement = connection.prepareStatement(checkSql)) {

            checkStatement.setInt(1, id);

            try (ResultSet resultSet = checkStatement.executeQuery()) {
                if (!resultSet.next()) {
                    System.out.println("Student with ID " + id + " not found.");
                    return;
                }
            }

            try (PreparedStatement deleteStatement = connection.prepareStatement(deleteSql)) {
                deleteStatement.setInt(1, id);
                deleteStatement.executeUpdate();
                System.out.println("Student ID " + id + " deleted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void searchStudent(String keyword) {
        String searchByIdSql = "SELECT * FROM students WHERE id = ?";
        String searchByNameSql = "SELECT * FROM students WHERE name LIKE ?";

        try (Connection connection = DBConnection.getConnection()) {
            try {
                int id = Integer.parseInt(keyword);
                try (PreparedStatement preparedStatement = connection.prepareStatement(searchByIdSql)) {
                    preparedStatement.setInt(1, id);
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if (!resultSet.isBeforeFirst()) {
                            System.out.println("No student found matching \"" + keyword + "\".");
                            return;
                        }

                        System.out.println("------------------------------------------");
                        System.out.println(" ID  | Name               | Age | Course");
                        System.out.println("------------------------------------------");

                        while (resultSet.next()) {
                            System.out.printf(" %-3d | %-18s | %-3d | %-20s%n",
                                    resultSet.getInt("id"),
                                    resultSet.getString("name"),
                                    resultSet.getInt("age"),
                                    resultSet.getString("course"));
                        }

                        System.out.println("------------------------------------------");
                    }
                }
            } catch (NumberFormatException e) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(searchByNameSql)) {
                    preparedStatement.setString(1, "%" + keyword + "%");
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if (!resultSet.isBeforeFirst()) {
                            System.out.println("No student found matching \"" + keyword + "\".");
                            return;
                        }

                        System.out.println("------------------------------------------");
                        System.out.println(" ID  | Name               | Age | Course");
                        System.out.println("------------------------------------------");

                        while (resultSet.next()) {
                            System.out.printf(" %-3d | %-18s | %-3d | %-20s%n",
                                    resultSet.getInt("id"),
                                    resultSet.getString("name"),
                                    resultSet.getInt("age"),
                                    resultSet.getString("course"));
                        }

                        System.out.println("------------------------------------------");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
