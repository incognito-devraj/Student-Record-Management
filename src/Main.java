import model.Student;
import service.StudentService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentService service = new StudentService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=============================");
            System.out.println("  STUDENT RECORD MANAGEMENT  ");
            System.out.println("=============================");
            System.out.println(" 1. Add Student");
            System.out.println(" 2. View All Students");
            System.out.println(" 3. Update Student");
            System.out.println(" 4. Delete Student");
            System.out.println(" 5. Search Student");
            System.out.println(" 0. Exit");
            System.out.println("=============================");
            System.out.print("Enter choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1: {
                        System.out.print("Enter Student ID: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter Age: ");
                        int age = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter Course: ");
                        String course = scanner.nextLine();
                        service.addStudent(new Student(id, name, age, course));
                        break;
                    }
                    case 2:
                        service.viewAllStudents();
                        break;
                    case 3: {
                        System.out.print("Enter Student ID to update: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("New Name: ");
                        String name = scanner.nextLine();
                        System.out.print("New Age: ");
                        int age = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("New Course: ");
                        String course = scanner.nextLine();
                        service.updateStudent(id, name, age, course);
                        break;
                    }
                    case 4: {
                        System.out.print("Enter Student ID to delete: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        service.deleteStudent(id);
                        break;
                    }
                    case 5: {
                        System.out.print("Enter Student ID or Name to search: ");
                        String keyword = scanner.nextLine();
                        service.searchStudent(keyword);
                        break;
                    }
                    case 0:
                        System.out.println("Goodbye!");
                        scanner.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Enter 0 to 5.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number between 0 and 5.");
                scanner.nextLine();
            }
        }
    }
}
