import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MenuManager implements IMenuProvider {
    private List<MenuItem> menuItems = new ArrayList<>();

    @Override
    public void addMenuItem(MenuItem item) {
        menuItems.add(item);
        System.out.println(item.getName() + " added.");
    }

    @Override
    public boolean editMenuItem(String name, String newDescription, double newPrice, String newCategory) {
        for (MenuItem item : menuItems) {
            if (item.getName().equalsIgnoreCase(name)) {
                item.setDescription(newDescription);
                item.setPrice(newPrice);
                item.setCategory(newCategory);
                System.out.println(name + " updated.");
                return true;
            }
        }
        System.out.println("Item not found: " + name);
        return false;
    }

    @Override
    public boolean removeMenuItem(String name) {
        boolean removed = menuItems.removeIf(item -> item.getName().equalsIgnoreCase(name));
        if (removed) {
            System.out.println(name + " removed.");
        } else {
            System.out.println("Item not found: " + name);
        }
        return removed;
    }

    @Override
    public void displayMenu() {
        if (menuItems.isEmpty()) {
            System.out.println("Menu is empty.");
            return;
        }
        for (MenuItem item : menuItems) {
            System.out.println(item);
            System.out.println("---------------------------");
        }
    }

    @Override
    public void saveMenuToFile(String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(menuItems);
            System.out.println("Menu saved to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadMenuFromFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("No saved menu found. Starting with an empty menu.");
            return;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            menuItems = (List<MenuItem>) ois.readObject();
            System.out.println("Menu loaded from file.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<MenuItem> getAllMenuItems() {
        return new ArrayList<>(menuItems); // return copy to preserve encapsulation
    }
}
