import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Student {
    String firstName;
    String lastName;
    int registration;
    int grade;
    int year;

    // Constructors
    public Student(String firstName, String lastName, int registration, int grade, int year) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.registration = registration;
        this.grade = grade;
        this.year = year;
    }

    public Student(String firstName, String lastName, int registration) {
        this(firstName, lastName, registration, 0, 1);
    }

    public Student(String firstName, String lastName) {
        this(firstName, lastName, 0, 0, 1);
    }

    // Methods
    public void printFullName() {
        System.out.println(firstName + " " + lastName);
    }

    public boolean isApproved() {
        return grade >= 60;
    }

    public int changeYearIfApproved() {
        if (isApproved()) {
            year++;
            System.out.println("Congratulations!");
        }
        return year;
    }

    public int getGrade() {
        return grade;
    }

    public static void main(String[] args) {
        // Create students
        Student student1 = new Student("John", "Doe", 1, 70, 1);
        Student student2 = new Student("Jane", "Smith", 2, 85, 1);
        Student student3 = new Student("Bob", "Johnson", 3, 55, 1);
    
        // Create course
        Course course = new Course("Java Programming", "Prof. Johnson", 2023);
    
        // Enroll individual students
        course.enroll(student1);
        course.enroll(student2);
        course.enroll(student3);
    
        // Print name and grade of each student
        for (Student student : course.enrolledStudents) {
            student.printFullName();
            System.out.println("Grade: " + student.grade);
            System.out.println("--------");
        }
    
        // Print best grade and the student with the best grade
        int bestGrade = course.bestGrade();
        if (bestGrade != -1) {
            System.out.println("Best grade in the course: " + bestGrade);
            System.out.println("Student(s) with the best grade:");
            for (Student student : course.enrolledStudents) {
                if (student.grade == bestGrade) {
                    student.printFullName();
                }
            }
        }
    }
    
}

class Course {
    String courseName;
    String professorName;
    int year;
    List<Student> enrolledStudents;

    // Constructor
    public Course(String courseName, String professorName, int year) {
        this.courseName = courseName;
        this.professorName = professorName;
        this.year = year;
        this.enrolledStudents = new ArrayList<>();
    }

    // Methods
    public void enroll(Student student) {
        enrolledStudents.add(student);
    }

    public void enroll(List<Student> students) {
        enrolledStudents.addAll(students);
    }

    public void unEnroll(Student student) {
        enrolledStudents.removeIf(s -> s.registration == student.registration);
    }

    public int countStudents() {
        return enrolledStudents.size();
    }

    public int bestGrade() {
        if (enrolledStudents.isEmpty()) {
            System.out.println("No students enrolled in the course.");
            return -1; // or any other appropriate value to indicate no grade
        }

        int maxGrade = enrolledStudents.get(0).grade;
        for (Student student : enrolledStudents) {
            maxGrade = Math.max(maxGrade, student.grade);
        }
        return maxGrade;
    }

    public void printStudentGrades() {
        for (Student student : enrolledStudents) {
            System.out.println(student.firstName + " " + student.lastName + ": " + student.getGrade());
        }
    }
}
