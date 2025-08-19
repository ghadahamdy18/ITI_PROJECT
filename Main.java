import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManager studentManager = new StudentManager();
        IMenuProvider menuManager = new MenuManager();
        AdminManager adminManager = new AdminManager(scanner);
        String filePath = "menu.dat";

        // Load menu at startup
        menuManager.loadMenuFromFile(filePath);

        while (true) {
            System.out.println("\n=== Cafeteria System ===");
            System.out.println("1. Student");
            System.out.println("2. Admin");
            System.out.println("3. Exit");
            System.out.print("Choose your role: ");
            int roleChoice = scanner.nextInt();
            scanner.nextLine();

            if (roleChoice == 1) {
                handleStudent(scanner, studentManager, menuManager);
            } else if (roleChoice == 2) {
                handleAdmin(scanner, menuManager, filePath,adminManager);
            } else if (roleChoice == 3) {
                System.out.println("Exiting...");
                menuManager.saveMenuToFile(filePath);
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    private static void handleStudent(Scanner scanner, StudentManager studentManager, IMenuProvider menuManager) {
        System.out.print("Is this your first time? (yes/no): ");
        String answer = scanner.nextLine().toLowerCase();

        Student loggedIn = null;
        if (answer.equals("yes")) {
            loggedIn = studentManager.registerStudent();  // now returns Student
        } else if (answer.equals("no")) {
            loggedIn = studentManager.loginStudent();
        }

        if (loggedIn != null) {
            studentManager.displayLoyaltyPoints(loggedIn);

            // Later: show order placement options here (FR3 + FR4)
            menuManager.displayMenu();
        }
    }


    private static void handleAdmin(Scanner scanner, IMenuProvider menuManager, String filePath, AdminManager adminManager) {
        System.out.println("\n--- Admin Access ---");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.print("Choose: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        Admin loggedIn = null;

        if (choice == 1) {
            loggedIn = adminManager.loginAdmin();
        } else if (choice == 2) {
            adminManager.registerAdmin();
            loggedIn = adminManager.loginAdmin(); // login after registration
        }

        if (loggedIn != null) {
            while (true) {
                System.out.println("\n--- Admin Menu ---");
                System.out.println("1. Add Menu Item");
                System.out.println("2. Edit Menu Item");
                System.out.println("3. Remove Menu Item");
                System.out.println("4. View Menu");
                System.out.println("5. Back");
                System.out.print("Choose: ");
                int adminChoice = scanner.nextInt();
                scanner.nextLine();

                switch (adminChoice) {
                    case 1:
                        System.out.print("Enter name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter description: ");
                        String desc = scanner.nextLine();
                        System.out.print("Enter price: ");
                        double price = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.print("Enter category: ");
                        String category = scanner.nextLine();
                        menuManager.addMenuItem(new MenuItem(name, desc, price, category));
                        menuManager.saveMenuToFile(filePath);
                        break;

                    case 2:
                        System.out.print("Enter name of item to edit: ");
                        String editName = scanner.nextLine();
                        System.out.print("Enter new description: ");
                        String newDesc = scanner.nextLine();
                        System.out.print("Enter new price: ");
                        double newPrice = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.print("Enter new category: ");
                        String newCategory = scanner.nextLine();
                        menuManager.editMenuItem(editName, newDesc, newPrice, newCategory);
                        break;

                    case 3:
                        System.out.print("Enter name of item to remove: ");
                        String removeName = scanner.nextLine();
                        menuManager.removeMenuItem(removeName);
                        break;

                    case 4:
                        menuManager.displayMenu();
                        break;

                    case 5:
                        return; // back to main
                    default:
                        System.out.println("Invalid option.");
                }
            }
        }
    }
}