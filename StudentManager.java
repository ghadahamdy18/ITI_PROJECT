import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentManager implements IStudentRepository {
    private List<Student> students;
    private final String FILE_NAME = "students.dat";
    private Scanner scanner;

    public StudentManager() {
        this.students = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        loadStudentsFromFile(); // Load data when system starts
    }

    //  Register student
    public Student registerStudent() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();

        // Ensure ID is unique
        if (getStudentById(studentId) != null) {
            System.out.println("Error: Student ID already exists.");
            return null;
        }

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        Student newStudent = new Student(name, studentId, password);
        students.add(newStudent);
        saveStudentsToFile();
        System.out.println("Registration successful. Welcome " + newStudent.getName() + "!");
        return newStudent;
    }


    public Student loginStudent() {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        Student student = getStudentById(studentId);

        if (student != null && student.getPassword().equals(password)) {
            System.out.println("Login successful. Welcome " + student.getName() + "!");
            return student;
        }
        System.out.println("Invalid ID or password.");
        return null;
    }

    // Search by ID
    public Student getStudentById(String studentId) {
        for (Student s : students) {
            if (s.getStudentId().equals(studentId)) {
                return s;
            }
        }
        return null;
    }

    // FR1.3: Display loyalty points
    public void displayLoyaltyPoints(Student student) {
        System.out.println("Current Loyalty Points: " + student.getLoyaltyPoints());
    }

    // Save to file
    public void saveStudentsToFile() {
        saveStudents(students);
    }

    // Load from file
    public void loadStudentsFromFile() {
        List<Student> loaded = loadStudents();
        if (loaded != null) {
            students = loaded;
        }
    }

    @Override
    public void saveStudents(List<Student> students) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(students);
        } catch (IOException e) {
            System.out.println("Error saving students: " + e.getMessage());
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Student> loadStudents() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<Student>) ois.readObject();
        } catch (FileNotFoundException e) {
            return new ArrayList<>(); // No file yet, return empty list
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading students: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
