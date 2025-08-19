import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminManager {
    private List<Admin> admins = new ArrayList<>();
    private Scanner scanner;
    private final String filePath = "admins.dat"; // File for persistence

    public AdminManager(Scanner scanner) {
        this.scanner = scanner;
        loadAdmins(); // Load existing admins from file

        // If no admins exist, create a default one
        if (admins.isEmpty()) {
            admins.add(new Admin("admin", "1234"));
            saveAdmins();
        }
    }

    // Register a new admin (adds to list + saves)
    public void registerAdmin() {
        System.out.print("Enter new admin username: ");
        String username = scanner.nextLine();

        System.out.print("Enter new admin password: ");
        String password = scanner.nextLine();

        admins.add(new Admin(username, password));
        saveAdmins();
        System.out.println("Admin registered successfully!");
    }

    // Login existing admin
    public Admin loginAdmin() {
        System.out.print("Enter admin username: ");
        String username = scanner.nextLine();

        System.out.print("Enter admin password: ");
        String password = scanner.nextLine();

        for (Admin a : admins) {
            if (a.getUsername().equals(username) && a.getPassword().equals(password)) {
                System.out.println("Login successful. Welcome, " + a.getUsername() + "!");
                return a;
            }
        }

        System.out.println("Invalid admin credentials.");
        return null;
    }

    // Save admins to file
    private void saveAdmins() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(admins);
        } catch (IOException e) {
            System.out.println("Error saving admins: " + e.getMessage());
        }
    }

    // Load admins from file
    @SuppressWarnings("unchecked")
    private void loadAdmins() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            admins = (List<Admin>) ois.readObject();
        } catch (FileNotFoundException e) {
            admins = new ArrayList<>(); // first time run
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading admins: " + e.getMessage());
            admins = new ArrayList<>();
        }
    }
}
