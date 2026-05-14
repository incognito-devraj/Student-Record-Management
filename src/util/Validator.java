package util;

public class Validator {
    public static boolean isValidId(int id) {
        return id > 0;
    }

    public static boolean isValidName(String name) {
        return name != null && !name.isBlank() && name.matches("[a-zA-Z ]+");
    }

    public static boolean isValidAge(int age) {
        return age >= 5 && age <= 100;
    }

    public static boolean isValidCourse(String course) {
        return course != null && !course.isBlank();
    }
}
