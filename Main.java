import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManager manager = new StudentManager();
        MenuManager menuManager = new MenuManager();
        String filePath = "menu.dat"; // File to store menu items

        // Student login or registration
        System.out.print("Is this your first time on the website? (yes/no): ");
        String answer = scanner.nextLine().toLowerCase();

        if (answer.equals("yes")) {
            manager.registerStudent();
        } else if (answer.equals("no")) {
            Student loggedIn = manager.loginStudent();
            if (loggedIn != null) {
                manager.displayLoyaltyPoints(loggedIn);
            }
        } else {
            System.out.println("Invalid choice. Please restart and type 'yes' or 'no'.");
        }

        // Auto-load menu at startup
        menuManager.loadMenuFromFile(filePath);

        while (true) {
            System.out.println("\n=== Cafeteria System ===");
            System.out.println("1. Admin - Add Menu Item");
            System.out.println("2. Admin - Edit Menu Item");
            System.out.println("3. Admin - Remove Menu Item");
            System.out.println("4. Student - View Menu");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // clear buffer

            switch (choice) {
                case 1: // Add
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

                case 2: // Edit
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

                case 3: // Remove
                    System.out.print("Enter name of item to remove: ");
                    String removeName = scanner.nextLine();
                    menuManager.removeMenuItem(removeName);
                    break;

                case 4: // Student View Menu
                    menuManager.displayMenu();
                    break;

                case 5: // Exit with auto-save
                    menuManager.saveMenuToFile(filePath);
                    System.out.println("Goodbye!");
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
