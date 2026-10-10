import java.util.ArrayList;
import java.util.List;

/*
Write a Java program to create a class called Student with private instance variables student_id,
student_name, and grades. Provide public getter and setter methods to access and modify the
student_id and student_name variables. However, provide a method called addGrade() that allows
adding a grade to the grades variable while performing additional validation.
*/
class Student {
    // Private instance variables (encapsulation)
    private int student_id;
    private String student_name;
    private List<Double> grades;

    public Student(int student_id, String student_name) {
        setStudentId(student_id);
        setStudentName(student_name);
        this.grades = new ArrayList<>();
    }

    // ---------- Getter and setter for student_id ----------
    public int getStudentId() {
        return student_id;
    }

    public void setStudentId(int student_id) {
        if (student_id <= 0) {
            throw new IllegalArgumentException("Student id must be a positive number.");
        }
        this.student_id = student_id;
    }

    // ---------- Getter and setter for student_name ----------
    public String getStudentName() {
        return student_name;
    }

    public void setStudentName(String student_name) {
        if (student_name == null || student_name.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be empty.");
        }
        this.student_name = student_name.trim();
    }

    // ---------- grades: no setter, grades can only be added through addGrade() ----------
    public void addGrade(double grade) {
        // Additional validation: the grade must be a real number between 0 and 100
        if (Double.isNaN(grade) || grade < 0 || grade > 100) {
            throw new IllegalArgumentException(
                    "Invalid grade " + grade + ". A grade must be between 0 and 100.");
        }
        grades.add(grade);
    }

    // Returns a copy so outside code cannot change the grades directly
    public List<Double> getGrades() {
        return new ArrayList<>(grades);
    }

    public double getAverageGrade() {
        if (grades.isEmpty()) {
            return 0;
        }
        double sum = 0;
        for (double g : grades) {
            sum += g;
        }
        return sum / grades.size();
    }

    public void displayInfo() {
        System.out.println("Student ID  : " + student_id);
        System.out.println("Student Name: " + student_name);
        System.out.println("Grades      : " + grades);
        System.out.printf("Average     : %.2f%n", getAverageGrade());
    }
}

public class StudentGrades {
    public static void main(String[] args) {
        Student student = new Student(101, "Rahim Uddin");

        // Valid grades
        student.addGrade(85.5);
        student.addGrade(92);
        student.addGrade(78);

        // Invalid grades are rejected by the validation in addGrade()
        double[] badGrades = {-5, 105};
        for (double bad : badGrades) {
            try {
                student.addGrade(bad);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        System.out.println();
        student.displayInfo();

        // Using the setters to modify id and name
        student.setStudentId(202);
        student.setStudentName("Karim Ahmed");

        System.out.println();
        System.out.println("After updating id and name:");
        student.displayInfo();

        // An invalid name is rejected by its setter
        try {
            student.setStudentName("   ");
        } catch (IllegalArgumentException e) {
            System.out.println();
            System.out.println("Error: " + e.getMessage());
        }
    }
}
